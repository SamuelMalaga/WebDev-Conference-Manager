package CMApplication.ConferenceManager.model.jpa;


import CMApplication.ConferenceManager.model.ThemeChoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeChoiceService {

    @Autowired
    private ThemeChoiceRepository themeChoiceRepository;

    public List<ThemeChoice> getThemeChoicesByPartAndConf(
            Long participantId,
            Long conferenceId
    ){
        return themeChoiceRepository.findThemeChoicesByPartIdAndConfId(conferenceId,participantId);
    }

    public List<ThemeChoice> getThemeChoicesByThemeAndConf(
            Long themeId,
            Long conferenceId
    ){
        return themeChoiceRepository.findThemeChoicesByThemeIdAndConferenceId(themeId,conferenceId);
    }

    public List<ThemeChoice> getThemeChoicesByThemeAndPart(
            Long themeId,
            Long partID
    ){
        return themeChoiceRepository.findThemeChoicesByPartIdAndThemeId(themeId, partID);
    }

    public ThemeChoice createThemeChoice(
            Long themeId,
            Long participantId,
            Long conferenceId
    ){
        ThemeChoice createdThemeChoice = new ThemeChoice();

        createdThemeChoice.setThemeId(themeId);
        createdThemeChoice.setConferenceId(conferenceId);
        createdThemeChoice.setPartId(participantId);

        ThemeChoice newThemeChoice = themeChoiceRepository.save(createdThemeChoice);

        return newThemeChoice;
    }

}
