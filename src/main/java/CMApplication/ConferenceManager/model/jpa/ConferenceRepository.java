package CMApplication.ConferenceManager.model.jpa;

import CMApplication.ConferenceManager.model.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {

}
