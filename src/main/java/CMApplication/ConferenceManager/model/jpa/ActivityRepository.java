package CMApplication.ConferenceManager.model.jpa;

import CMApplication.ConferenceManager.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
