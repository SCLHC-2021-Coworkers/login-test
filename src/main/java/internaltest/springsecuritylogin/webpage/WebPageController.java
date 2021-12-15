package internaltest.springsecuritylogin.webpage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WebPageController {

    @GetMapping("/")
    public String home(Model model) {
        return "pages/home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "pages/login";
    }
}
