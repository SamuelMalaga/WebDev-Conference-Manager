package CMApplication.ConferenceManager.model.jpa;

import CMApplication.ConferenceManager.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
