package CMApplication.ConferenceManager.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ActivityChoiceId implements Serializable {


    private Long partId;

    private Long conferenceId;

    private Long activityId;

    public ActivityChoiceId(
            Long _partId,
            Long _activityId,
            Long _conferenceId
    ){
        this.partId = _partId;
        this.activityId = _activityId;
        this.conferenceId = _conferenceId;
    }

    public ActivityChoiceId(){

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ActivityChoiceId that = (ActivityChoiceId) o;
        return Objects.equals(partId, that.partId) && Objects.equals(conferenceId, that.conferenceId) && Objects.equals(activityId, that.activityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partId, conferenceId, activityId);
    }
}
