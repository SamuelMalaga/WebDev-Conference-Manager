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
}
