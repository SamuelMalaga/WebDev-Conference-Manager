package CMApplication.ConferenceManager.model.jpa;

import CMApplication.ConferenceManager.model.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

}
