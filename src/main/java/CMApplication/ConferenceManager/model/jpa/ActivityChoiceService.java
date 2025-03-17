package CMApplication.ConferenceManager.model.jpa;


import CMApplication.ConferenceManager.model.ActivityChoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityChoiceService {

    @Autowired
    private ActivityChoiceRepository activityChoiceRepository;

    public List<ActivityChoice> getActivityChoicesByPartAndConf(
            Long participantId,
            Long conferenceId
    ){
        return activityChoiceRepository.findActivityChoicesByPartIdAndConfId(conferenceId,participantId);
    }

    public List<ActivityChoice> getActivityChoicesByThemeAndConf(
            Long activityId,
            Long conferenceId
    ){
        return activityChoiceRepository.findActivityChoicesByActivitydAndConferenceId(activityId,conferenceId);
    }

    public List<ActivityChoice> getActivityChoicesByThemeAndPart(
            Long activityId,
            Long partID
    ){
        return activityChoiceRepository.findActivityChoicesByPartIdAndActivityId(activityId, partID);
    }

    public ActivityChoice createActivityChoice(
            Long activityId,
            Long participantId,
            Long conferenceId
    ){
        ActivityChoice createdActivityChoice = new ActivityChoice();

        createdActivityChoice.setActivityId(activityId);
        createdActivityChoice.setConferenceId(conferenceId);
        createdActivityChoice.setPartId(participantId);

        ActivityChoice newActivityChoice = activityChoiceRepository.save(createdActivityChoice);

        return newActivityChoice;
    }


}
