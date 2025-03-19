package CMApplication.ConferenceManager.model.jpa;

import CMApplication.ConferenceManager.model.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    public List<Theme> getAllThemes(){
        return themeRepository.findAll(Sort.by("nameTheme"));
    }

}
