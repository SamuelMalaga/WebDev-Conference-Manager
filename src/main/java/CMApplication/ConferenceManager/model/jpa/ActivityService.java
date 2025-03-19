package CMApplication.ConferenceManager.model.jpa;

import CMApplication.ConferenceManager.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAllActivities(){
        return activityRepository.findAll(Sort.by("nameActivity"));
    }
}
