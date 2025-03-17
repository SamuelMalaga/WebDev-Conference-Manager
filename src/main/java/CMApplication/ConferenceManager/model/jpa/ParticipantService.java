package CMApplication.ConferenceManager.model.jpa;


import CMApplication.ConferenceManager.model.Conference;
import CMApplication.ConferenceManager.model.Participant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    public Optional<Participant> findById(Long participantId){

        Optional<Participant> participant = participantRepository.findById(participantId);

        return participant;
    }

    public Participant createParticipant(
            String namePart,
            String givenNamePart,
            String organismPart,
            String zipCodePart,
            String addressPart,
            String cityPart,
            String countryPart,
            String emailPart,
            Date dtRegistration,
            String passwordPart
    ){
        Participant newParticipant = new Participant();

        newParticipant.setNamePart(namePart);
        newParticipant.setGivenNamePart(givenNamePart);
        newParticipant.setOrganismPart(organismPart);
        newParticipant.setZipCodePart(zipCodePart);
        newParticipant.setAddressPart(addressPart);
        newParticipant.setCityPart(cityPart);
        newParticipant.setCountryPart(countryPart);
        newParticipant.setEmailPart(emailPart);
        newParticipant.setDtRegistration(dtRegistration);
        newParticipant.setPasswordPart(passwordPart);

        Participant createdParticipant = participantRepository.save(newParticipant);

        return createdParticipant;
    }

    public Participant findByHashCode(String hashCode){
        Participant foundParticipant = participantRepository.findParticipantByPasswordPartEquals(hashCode);

        return foundParticipant;
    }

    @Transactional
    public void enrollParticipantInConference(Long participantId, Long conferenceId){
        System.out.println("Transactional");
        System.out.println("Participant id" + participantId);
        System.out.println("conference id" + conferenceId);

        Participant participant = participantRepository.findById(participantId).orElseThrow();
        Conference conference = conferenceRepository.findById(conferenceId).orElseThrow();

        //System.out.println("Found Participant id" + participant.getIdPart());
        //System.out.println("Found conference id" + conference.getIdConf());

        participant.getParticipantConferences().add(conference);
        conference.getConferenceParticipants().add(participant);

        participantRepository.save(participant);
    }
}
