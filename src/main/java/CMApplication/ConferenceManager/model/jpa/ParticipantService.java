package CMApplication.ConferenceManager.model.jpa;


import CMApplication.ConferenceManager.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

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
}
