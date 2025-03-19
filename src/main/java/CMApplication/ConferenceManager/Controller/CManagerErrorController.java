package CMApplication.ConferenceManager.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class CManagerErrorController implements ErrorController {

    @RequestMapping("/error")
    public String errorHandler(Model model, HttpSession session){
        if(null == session.getAttribute("loggedParticipant")){
            session.setAttribute("ErrorMessage", "Teste error message");
        } else {
            session.setAttribute("ErrorMessage", "Outro test");
        }
        System.out.println("Custom error");
        return "error";
    }


}
