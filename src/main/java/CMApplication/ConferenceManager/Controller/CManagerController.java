package CMApplication.ConferenceManager.Controller;

import CMApplication.ConferenceManager.model.Activity;
import CMApplication.ConferenceManager.model.Conference;
import CMApplication.ConferenceManager.model.Participant;
import CMApplication.ConferenceManager.model.Theme;
import CMApplication.ConferenceManager.model.jpa.*;
import CMApplication.ConferenceManager.utils.CryptographicEncoder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller("/")
public class CManagerController {

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ThemeService themeService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ThemeChoiceService themeChoiceService;

    @Autowired
    private ActivityChoiceService activityChoiceService;

    private CryptographicEncoder cryptographicEncoder = new CryptographicEncoder();

    @RequestMapping("")
    public String index(Model model, HttpSession session){


//        session.setAttribute("loggedIn",false);
//        session.setAttribute("authenticationError",false);
        return "index";
    }

    @RequestMapping("conferences")
    public String conferences(Model model, HttpSession httpSession){
        List<Conference> avaliableConferences = conferenceService.getAllConferences();
        model.addAttribute("avaliableConferences", avaliableConferences);
        List<Conference> conferences = conferenceService.getAllConferences();
        model.addAttribute(conferences);
        return "conferences";
    }

    @RequestMapping("menu")
    public String menu(){
        return "menu";
    }

    @RequestMapping("participant/login")
    public String login(
            @RequestParam(name="participantEmail") String participantEmail,
            @RequestParam(name="password") String participantPassword,
            HttpSession session,
            Model model
    ){

        //CryptographicEncoder encoder = new CryptographicEncoder();
        String testCrypto = cryptographicEncoder.encodeSHA256(participantPassword);


        try{
            Participant foundParticipant = participantService.findByHashCode(testCrypto);
            if(!Objects.equals(foundParticipant.getEmailPart(), participantEmail)){
                throw new Exception();
            } else {
                session.setAttribute("loggedParticipant", foundParticipant);
                session.setAttribute("loggedIn",true);
                System.out.println("LoggedIn");
            }
        } catch (Exception e) {
            //System.out.println("Not found");
            session.setAttribute("loggedIn",false);
            session.setAttribute("authenticationError",true);
            return "redirect:/login";
        }

        return "redirect:/";
    }

    @RequestMapping("participant/logout")
    public String logout(
            Model model,
            HttpSession httpSession
    ){
        System.out.println("Logout");
        httpSession.setAttribute("loggedIn", false);
        httpSession.setAttribute("authenticationError",false);
        httpSession.setAttribute("loggedParticipant", null);
        return "redirect:/";
    }

    @RequestMapping("login")
    public String participant(){return "loginPage";}

    @RequestMapping("participant/register")
    public String registration(){return "registration";}

    //TODO:INPUT VALIDATION ON THE html FIELDS
    @RequestMapping("createParticipant")
    public String createParticipant(
            @RequestParam(name = "namePart", required = true) String namePart,
            @RequestParam(name = "givenNamePart", required = true) String givenNamePart,
            @RequestParam(name = "organismPart", required = true) String organismPart,
            @RequestParam(name = "zipCodePart", required = true) String zipCodePart,
            @RequestParam(name = "addressPart", required = true) String addressPart,
            @RequestParam(name = "cityPart", required = true) String cityPart,
            @RequestParam(name = "countryPart", required = true) String countryPart,
            @RequestParam(name = "emailPart", required = true) String emailPart,
            @RequestParam(name = "passwordPart", required = true) String passwordPart,
            HttpSession httpSession
    ){

        Date currentDate = new Date();
        // ---> Cryptography to store passwords
        String encryptedPassword = cryptographicEncoder.encodeSHA256(passwordPart);

        Participant createdParticipant = participantService.createParticipant(
                namePart,
                givenNamePart,
                organismPart,
                zipCodePart,
                addressPart,
                cityPart,
                countryPart,
                emailPart,
                currentDate,
                encryptedPassword
        );

        httpSession.setAttribute("loggedParticipant",createdParticipant);


        return "redirect:/participant/details";
    }


    @RequestMapping("conferenceCreate")
    public String createConferencePage(
            Model model
    ){
        List<Theme> themes = themeService.getAllThemes();
        List<Activity> activities = activityService.getAllActivities();
        model.addAttribute("conferenceThemes",themes);
        model.addAttribute("conferenceActivities", activities );
        return "conferenceCreate";
    }

    @RequestMapping("conferences/create")
    public String createConference(
            @RequestParam(name = "titleConf") String titleConf,
            @RequestParam(name="nbEditionConf") Integer nbEditionConf,
            @RequestParam(name="dtStartConf") String dtStartConf,
            @RequestParam(name="dtEndConf") String dtEndConf,
            @RequestParam(name = "urlWebsiteConf") String urlWebsiteConf,
            @RequestParam(name ="themes") ArrayList<Long> conferenceThemesIds,
            @RequestParam(name = "activities") ArrayList<Long> conferenceActivitiesIds
    ) throws ParseException {
        System.out.println(conferenceThemesIds);
        //Format 2025-03-07
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD", Locale.FRENCH);
        Conference createdConference = null;
        try {
            Date validatedDtStartConf = dateFormat.parse(dtStartConf);
            Date validatedDtEndConf = dateFormat.parse(dtEndConf);

            createdConference = conferenceService.createConference(
                    titleConf,
                    nbEditionConf,
                    validatedDtStartConf,
                    validatedDtEndConf,
                    urlWebsiteConf,
                    conferenceThemesIds,
                    conferenceActivitiesIds
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/conferences/"+createdConference.getIdConf();
    }

    @RequestMapping("conferences/registerParticipant")
    public String registerParticipantToConference(
            @RequestParam(name = "confId") Long idConf,
            @RequestParam(name = "participantId") Long idParticipant,
            @RequestParam(name = "themeIds") List<Long> themeIds,
            @RequestParam(name = "activityIds") List<Long> activityIds
    ){
        Optional<Conference> conference = conferenceService.findById(idConf);
        Optional<Participant> participant = participantService.findById(idParticipant);

        System.out.println(idConf);
        System.out.println(idParticipant);

        //Register the theme choices
        for(Long themeId: themeIds){
            themeChoiceService.createThemeChoice(themeId, idConf, idParticipant);
        }

        //Register the activity choices
        for(Long activityId: activityIds){
            activityChoiceService.createActivityChoice(activityId, idConf, idParticipant);
        }

        //Enroll Participant in a conference
        participantService.enrollParticipantInConference(idParticipant,idConf);

        System.out.println("registerParticipant");

        return "redirect:/conferences/" + idConf ;
    }

    @RequestMapping("/participant/details")
    public String participantDetails(
    ){
        return "participantDetails";
    }

    @RequestMapping("/conferences/{confId}")
    public String conferenceDetails(
            Model model,
            @PathVariable Long confId
    ){
        Optional<Conference> selectedConference = conferenceService.findById(confId);
        model.addAttribute("selectedConference",selectedConference);
        return "conferenceDetails";
    }


    @RequestMapping("/conferences/{confId}/register")
    public String conferenceRegister(
            Model model,
            @PathVariable Long confId
    ){
        Optional<Conference> selectedConference = conferenceService.findById(confId);
        model.addAttribute("selectedConference",selectedConference);

        return "conferenceRegistration";
    }

    @RequestMapping("participant/createThemeChoice")
    public String participantCreateThemeChoices(
            @RequestParam(name="participantId") Long participantId,
            @RequestParam(name="conferenceId") Long conferenceId,
            @RequestParam(name = "themeIds") List<Long> themeIds
    ){
        for(Long themeId: themeIds){
            themeChoiceService.createThemeChoice(themeId, conferenceId, participantId);
        }

        return "redirect:/conferences/" + conferenceId ;
    }

}
