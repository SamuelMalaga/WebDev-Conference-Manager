package CMApplication.ConferenceManager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "choice_activities")
@IdClass(ActivityChoiceId.class)
public class ActivityChoice {

    @Id
    private Long conferenceId;

    @Id
    private Long partId;

    @Id
    private Long activityId;

    public Long getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }

    public Long getPartID() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
