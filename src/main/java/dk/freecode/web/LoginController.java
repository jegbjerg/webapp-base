package dk.freecode.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(final HttpServletRequest request, final HttpServletResponse response, final Model model) {
        addRequestUrlToModel(request, response, model);
        return "login";
    }

    private void addRequestUrlToModel(final HttpServletRequest request,
                                      final HttpServletResponse response,
                                      final Model model) {
        final SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        String requestUrl = null;
        if (savedRequest != null) {
            requestUrl = savedRequest.getRedirectUrl();
        }
        model.addAttribute("requestUrl", requestUrl);
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginFailed(final HttpServletRequest request, final HttpServletResponse response, final Model model) {
        addRequestUrlToModel(request, response, model);
        model.addAttribute("failed", true);
        return "login";
    }
}
