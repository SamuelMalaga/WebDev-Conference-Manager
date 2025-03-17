package CMApplication.ConferenceManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "theme_choices")
@IdClass(ThemeChoiceId.class)
public class ThemeChoice {

    @Id
    private Long conferenceId;

    @Id
    private Long partId;

    @Id
    private Long themeId;

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    public Long getConferenceId() {
        return conferenceId;
    }

    public Long getThemeId() {
        return themeId;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }
}
