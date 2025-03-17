package CMApplication.ConferenceManager.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "themes")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long themeId;

    @Column(nullable = false, unique = true)
    private String nameTheme;

    public Long getThemeId() {
        return themeId;
    }

    public String getNameTheme() {
        return nameTheme;
    }

    public Set<Conference> getThemeConferences() {
        return themeConferences;
    }

    @ManyToMany
    @JoinTable(
            name="on_themes",
            joinColumns = @JoinColumn(name = "theme_id"),
            inverseJoinColumns = @JoinColumn(name = "conference_id")
    )
    private Set<Conference> themeConferences = new HashSet<>();

}
