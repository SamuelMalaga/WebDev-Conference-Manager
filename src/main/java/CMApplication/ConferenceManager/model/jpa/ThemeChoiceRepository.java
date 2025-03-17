package CMApplication.ConferenceManager.model.jpa;

import CMApplication.ConferenceManager.model.ThemeChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThemeChoiceRepository extends JpaRepository<ThemeChoice,Long> {


    @Query("SELECT tc FROM ThemeChoice tc WHERE tc.conferenceId = :confId AND tc.partId = :partId")
    List<ThemeChoice> findThemeChoicesByPartIdAndConfId(
            @Param("confId") Long confId,
            @Param("partId") Long partId
    );

    @Query("SELECT tc FROM ThemeChoice tc WHERE tc.conferenceId = :confId AND tc.themeId = :themeId")
    List<ThemeChoice> findThemeChoicesByThemeIdAndConferenceId(
            @Param("themeId") Long themeId,
            @Param("confId") Long confId
    );

    @Query("SELECT tc FROM ThemeChoice tc WHERE tc.partId = :confId AND tc.themeId = :themeId")
    List<ThemeChoice> findThemeChoicesByPartIdAndThemeId(
            @Param("themeId") Long themeId,
            @Param("partId") Long partId
    );

}
