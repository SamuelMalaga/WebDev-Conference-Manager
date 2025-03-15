package CMApplication.ConferenceManager.model;


import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="participants")
public class Participant {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long idPart;

    public Long getIdPart() {
        return idPart;
    }

    private String namePart;

    private String givenNamePart;

    private String organismPart;

    private String zipCodePart;

    private String addressPart;

    private String cityPart;

    private String countryPart;

    @Column(unique = true)
    private String emailPart;

    private Date dtRegistration;

    private String passwordPart;

    @ManyToMany
    @JoinTable(
            name="registration",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "conference_id")
    )
    private Set<Conference> participantConferences = new HashSet<>();

    public Set<Conference> getParticipantConferences() {
        return participantConferences;
    }

    public void setParticipantConferences(Set<Conference> participantConferences) {
        this.participantConferences = participantConferences;
    }

    public String getPasswordPart() {
        return passwordPart;
    }

    public void setPasswordPart(String passwordPart) {
        this.passwordPart = passwordPart;
    }

    public Date getDtRegistration() {
        return dtRegistration;
    }

    public void setDtRegistration(Date dtRegistration) {
        this.dtRegistration = dtRegistration;
    }

    public String getEmailPart() {
        return emailPart;
    }

    public void setEmailPart(String emailPart) {
        this.emailPart = emailPart;
    }

    public String getCountryPart() {
        return countryPart;
    }

    public void setCountryPart(String countryPart) {
        this.countryPart = countryPart;
    }

    public String getCityPart() {
        return cityPart;
    }

    public void setCityPart(String cityPart) {
        this.cityPart = cityPart;
    }

    public String getAddressPart() {
        return addressPart;
    }

    public void setAddressPart(String addressPart) {
        this.addressPart = addressPart;
    }

    public String getZipCodePart() {
        return zipCodePart;
    }

    public void setZipCodePart(String zipCodePart) {
        this.zipCodePart = zipCodePart;
    }

    public String getOrganismPart() {
        return organismPart;
    }

    public void setOrganismPart(String organismPart) {
        this.organismPart = organismPart;
    }

    public String getGivenNamePart() {
        return givenNamePart;
    }

    public void setGivenNamePart(String givenNamePart) {
        this.givenNamePart = givenNamePart;
    }

    public String getNamePart() {
        return namePart;
    }

    public void setNamePart(String namePart) {
        this.namePart = namePart;
    }
}
