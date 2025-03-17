package CMApplication.ConferenceManager.model.jpa;

import CMApplication.ConferenceManager.model.ActivityChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityChoiceRepository extends JpaRepository<ActivityChoice, Long> {


    @Query("SELECT ac FROM ActivityChoice ac WHERE ac.conferenceId = :confId AND ac.partId = :partId")
    List<ActivityChoice> findActivityChoicesByPartIdAndConfId(
            @Param("confId") Long confId,
            @Param("partId") Long partId
    );

    @Query("SELECT ac FROM ActivityChoice ac WHERE ac.conferenceId = :confId AND ac.activityId = :activityId")
    List<ActivityChoice> findActivityChoicesByActivitydAndConferenceId(
            @Param("activityId") Long activityId,
            @Param("confId") Long confId
    );

    @Query("SELECT ac FROM ActivityChoice ac WHERE ac.partId = :confId AND ac.activityId = :activityId")
    List<ActivityChoice> findActivityChoicesByPartIdAndActivityId(
            @Param("activityId") Long activityId,
            @Param("partId") Long partId
    );
    
    
    
}
