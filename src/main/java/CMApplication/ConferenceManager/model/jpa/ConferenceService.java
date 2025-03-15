package CMApplication.ConferenceManager.model.jpa;

import CMApplication.ConferenceManager.model.Conference;
import CMApplication.ConferenceManager.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    public List<Conference> getAllConferences(){
        return conferenceRepository.findAll();
    }

    public Conference createConference(
            String titleConf,
            Integer nbEditionConf,
            Date dtStartConf,
            Date dtEndConf,
            String urlWebSiteConf
    ){
        Conference newConference = new Conference();

        newConference.setTitleConf(titleConf);
        newConference.setNbEditionConf(nbEditionConf);
        newConference.setDtStartConf(dtStartConf);
        newConference.setDtEndConf(dtEndConf);
        newConference.setUrlWebSiteConf(urlWebSiteConf);

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


}
