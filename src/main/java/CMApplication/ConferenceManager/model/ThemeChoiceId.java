package CMApplication.ConferenceManager.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ThemeChoiceId implements Serializable {


    private Long partId;

    private Long themeId;

    private Long conferenceId;

    public ThemeChoiceId(
            Long _partId,
            Long _themeId,
            Long _conferenceId
    ){
        this.partId = _partId;
        this.themeId = _themeId;
        this.conferenceId = _conferenceId;
    }

    public ThemeChoiceId() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ThemeChoiceId that = (ThemeChoiceId) o;
        return Objects.equals(partId, that.partId) && Objects.equals(themeId, that.themeId) && Objects.equals(conferenceId, that.conferenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partId, themeId, conferenceId);
    }
}
