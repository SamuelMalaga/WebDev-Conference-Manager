package CMApplication.ConferenceManager.model;

import jakarta.persistence.*;

import java.util.Date;


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
}
