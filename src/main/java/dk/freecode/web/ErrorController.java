package dk.freecode.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/error")
    public String error(final Model model, final HttpServletRequest request) {
        model.addAttribute("statusCode", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        return "error/error";
    }

    @RequestMapping("/error/404")
    public String notFound(final Model model, final HttpServletRequest request) {
        final String originalUri = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
        model.addAttribute("originalUri", originalUri);
        return "error/404";
    }

    @RequestMapping("/error/restricted")
    public String restricted() {
        return "error/restricted";
    }
}
