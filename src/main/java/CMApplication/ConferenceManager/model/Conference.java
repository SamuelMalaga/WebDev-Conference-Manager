package CMApplication.ConferenceManager.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="conference")
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConf;

    private String titleConf;

    private Integer nbEditionConf;

    private Date dtStartConf;

    private Date dtEndConf;

    private String urlWebSiteConf;

    @ManyToMany(mappedBy = "participantConferences")
    private Set<Participant> conferenceParticipants = new HashSet<>();

    @ManyToMany(mappedBy ="themeConferences" )
    private Set<Theme> conferenceThemes = new HashSet<>();

    @ManyToMany(mappedBy = "activityConferences")
    private Set<Activity> conferenceActivities = new HashSet<>();

    public Set<Participant> getConferenceParticipants() {
        return conferenceParticipants;
    }

    public Long getIdConf() {
        return idConf;
    }

    public String getTitleConf() {
        return titleConf;
    }

    public void setTitleConf(String titleConf) {
        this.titleConf = titleConf;
    }

    public Integer getNbEditionConf() {
        return nbEditionConf;
    }

    public void setNbEditionConf(Integer nbEditionConf) {
        this.nbEditionConf = nbEditionConf;
    }

    public Date getDtStartConf() {
        return dtStartConf;
    }

    public void setDtStartConf(Date dtStartConf) {
        this.dtStartConf = dtStartConf;
    }

    public Date getDtEndConf() {
        return dtEndConf;
    }

    public void setDtEndConf(Date dtEndConf) {
        this.dtEndConf = dtEndConf;
    }

    public String getUrlWebSiteConf() {
        return urlWebSiteConf;
    }

    public void setUrlWebSiteConf(String urlWebSiteConf) {
        this.urlWebSiteConf = urlWebSiteConf;
    }

    public Set<Theme> getConferenceThemes() {
        return conferenceThemes;
    }

    public void setConferenceThemes(Set<Theme> conferenceThemes) {
        this.conferenceThemes = conferenceThemes;
    }

    public void setConferenceParticipants(Set<Participant> conferenceParticipants) {
        this.conferenceParticipants = conferenceParticipants;
    }

    public Set<Activity> getConferenceActivities() {
        return conferenceActivities;
    }
}
