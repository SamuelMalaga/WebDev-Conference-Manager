package CMApplication.ConferenceManager.Controller;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

import CMApplication.ConferenceManager.model.Conference;
import CMApplication.ConferenceManager.model.Participant;
import CMApplication.ConferenceManager.model.jpa.ConferenceService;
import CMApplication.ConferenceManager.model.jpa.ParticipantService;
import CMApplication.ConferenceManager.utils.CryptographicEncoder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Controller("/")
public class CManagerController {

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ParticipantService participantService;

    private CryptographicEncoder cryptographicEncoder;

    @RequestMapping("")
    public String index(Model model, HttpSession session){
        session.setAttribute("loggedIn",false);
        session.setAttribute("authenticationError",false);
        List<Conference> conferences = conferenceService.getAllConferences();
        model.addAttribute(conferences);
        return "index";
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


//        System.out.println("Participant name ---->" + participantEmail);
//        System.out.println("Participant password ---->" + participantPassword);
//        System.out.println("Participant password hashed ---->" + participantPassword.hashCode());
//        System.out.println("Participant password SHA256 hashed ---->" + testCrypto);

        try{
            Participant foundParticipant = participantService.findByHashCode(testCrypto);
            //System.out.println(foundParticipant.getEmailPart());
            if(!Objects.equals(foundParticipant.getEmailPart(), participantEmail)){
                throw new Exception();
            } else {
                session.setAttribute("loggedIn",true);
            }
        } catch (Exception e) {
            //System.out.println("Not found");
            session.setAttribute("loggedIn",false);
            session.setAttribute("authenticationError",true);
            return "redirect:/participant";
        }

        return "redirect:/";
    }

    @RequestMapping("participant")
    public String participant(){return "participant";}

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
            @RequestParam(name = "passwordPart", required = true) String passwordPart
    ){

        Date currentDate = new Date();
        // ---> Cryptography to store passwords
        String encryptedPassword = cryptographicEncoder.encodeSHA256(passwordPart);

        participantService.createParticipant(
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
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//
//            // Change this to UTF-16 if needed
//            md.update(passwordPart.getBytes(StandardCharsets.UTF_8));
//            byte[] digest = md.digest();
//
//            String encryptedPassword = String.format("%064x", new BigInteger(1, digest));
//
//            System.out.println("Encrypted password ---->" + encryptedPassword);
//
//            participantService.createParticipant(
//                    namePart,
//                    givenNamePart,
//                    organismPart,
//                    zipCodePart,
//                    addressPart,
//                    cityPart,
//                    countryPart,
//                    emailPart,
//                    currentDate,
//                    encryptedPassword
//            );
//
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }


        return "redirect:/menu";
    }

    @RequestMapping("conferences/create")
    public String createConference(
            @RequestParam(name = "titleConf") String titleConf,
            @RequestParam(name="nbEditionConf") Integer nbEditionConf,
            @RequestParam(name="dtStartConf") String dtStartConf,
            @RequestParam(name="dtEndConf") String dtEndConf,
            @RequestParam(name = "urlWebsiteConf") String urlWebsiteConf
    ) throws ParseException {
        //Format 2025-03-07
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD", Locale.FRENCH);
        try {
            Date validatedDtStartConf = dateFormat.parse(dtStartConf);
            Date validatedDtEndConf = dateFormat.parse(dtEndConf);

            conferenceService.createConference(
                    titleConf,
                    nbEditionConf,
                    validatedDtStartConf,
                    validatedDtEndConf,
                    urlWebsiteConf
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/menu";
    }

}
