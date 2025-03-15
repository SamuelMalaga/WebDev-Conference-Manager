package CMApplication.ConferenceManager.model.jpa;

import CMApplication.ConferenceManager.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
