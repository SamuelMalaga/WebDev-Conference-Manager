package CMApplication.ConferenceManager.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long activityId;

    private String nameActivity;

    private BigDecimal priceActivity;

    private BigDecimal priceActCompanion;

    private Integer nbMaxParticipants;

    @ManyToMany
    @JoinTable(
            name="offer",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "conference_id")
    )
    private Set<Conference> activityConferences = new HashSet<>();

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getNameActivity() {
        return nameActivity;
    }

    public void setNameActivity(String nameActivity) {
        this.nameActivity = nameActivity;
    }

    public BigDecimal getPriceActivity() {
        return priceActivity;
    }

    public void setPriceActivity(BigDecimal priceActivity) {
        this.priceActivity = priceActivity;
    }

    public BigDecimal getPriceActCompanion() {
        return priceActCompanion;
    }

    public void setPriceActCompanion(BigDecimal priceActCompanion) {
        this.priceActCompanion = priceActCompanion;
    }

    public Integer getNbMaxParticipants() {
        return nbMaxParticipants;
    }

    public void setNbMaxParticipants(Integer nbMaxParticipants) {
        this.nbMaxParticipants = nbMaxParticipants;
    }

    public Set<Conference> getActivityConferences() {
        return activityConferences;
    }

    public void setActivityConferences(Set<Conference> activityConferences) {
        this.activityConferences = activityConferences;
    }


}
