package CMApplication.ConferenceManager.model.jpa;

import CMApplication.ConferenceManager.model.Activity;
import CMApplication.ConferenceManager.model.Conference;
import CMApplication.ConferenceManager.model.Participant;
import CMApplication.ConferenceManager.model.Theme;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    public List<Conference> getAllConferences(){
        return conferenceRepository.findAll(Sort.by("dtStartConf"));
    }

    public Conference createConference(
            String titleConf,
            Integer nbEditionConf,
            Date dtStartConf,
            Date dtEndConf,
            String urlWebSiteConf,
            ArrayList<Long> relatedConferenceIds,
            ArrayList<Long> relatedActivitiesIds
    ){
        Conference newConference = new Conference();

        newConference.setTitleConf(titleConf);
        newConference.setNbEditionConf(nbEditionConf);
        newConference.setDtStartConf(dtStartConf);
        newConference.setDtEndConf(dtEndConf);
        newConference.setUrlWebSiteConf(urlWebSiteConf);

        //Fetch theme by ids
        List<Theme> themesList = themeRepository.findAllById(relatedConferenceIds);
        Set<Theme> themesSet = new HashSet<>(themesList);
        newConference.setConferenceThemes(themesSet);

        //Reference the Conference in the themes
        for(Theme t: themesSet){
            t.getThemeConferences().add(newConference);
        }

        //Fetch activities by ids
        List<Activity> activitiesList = activityRepository.findAllById(relatedActivitiesIds);
        Set<Activity> activitiesSet = new HashSet<>(activitiesList);

        //Reference the Conference in the activities
        for(Activity a: activitiesSet){
            a.getActivityConferences().add(newConference);
        }

        Conference createdConference = conferenceRepository.save(newConference);

        return createdConference;
    }

    public Optional<Conference> findById(Long conferenceId){
        return conferenceRepository.findById(conferenceId);
    }

    public Set<Participant> getAllParticipants(
            Long conferenceId
    ){
        Optional<Conference> selectedConference = conferenceRepository.findById(conferenceId);
        return selectedConference.get().getConferenceParticipants();
    }

    public Boolean checkIfParticipantIsRegistered(
            Long conferenceId,
            Long participantId
    ){
        Conference foundConference = conferenceRepository.getReferenceById(conferenceId);
        Optional<Participant> foundParticipant = participantRepository.findById(participantId);
        ArrayList<Participant> participants = new ArrayList<>(foundConference.getConferenceParticipants());

        return participants.contains(foundParticipant.get());
    }


}
