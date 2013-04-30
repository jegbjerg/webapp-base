package dk.freecode.web.signup;

import java.util.Locale;

import javax.inject.Inject;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dk.freecode.domain.factory.UserFactory;
import dk.freecode.domain.service.user.UserService;

@Controller
@RequestMapping("/signup")
@SessionAttributes(SignupController.FORM)
@Slf4j
public class SignupController {

    public static final String FORM = "signupForm";

    @Inject
    private UserService userService;

    @Inject
    private MessageSource messageSource;

    @InitBinder
    public void setAllowedFields(final WebDataBinder dataBinder) {
        dataBinder.setAllowedFields("user.username", "user.email", "password", "confirmPassword");
    }

    @ModelAttribute(FORM)
    public SignupForm createForm() {
        final SignupForm signupForm = new SignupForm(UserFactory.createRegularUser());
        return signupForm;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void form() {
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSignup(@Valid final SignupForm signupForm,
                                final BindingResult result,
                                final RedirectAttributes redirectAttrs,
                                final Locale locale) {
        if (result.hasErrors()) {
            return null;
        } else {
            try {
                userService.saveUser(signupForm.getUser(), signupForm.getPassword());
            } catch (final DataIntegrityViolationException e) {
                log.info("User exists: {}", signupForm.getUser());
                result.addError(new FieldError(FORM, "user.username", messageSource.getMessage("signup.user_exists",
                                                                                               null,
                                                                                               locale)));
                return null;
            }

            redirectAttrs.addFlashAttribute("info", messageSource.getMessage("signup.success",
                                                                             new String[] { signupForm.getUser()
                                                                                     .getUsername() },
                                                                             locale));
            return "redirect:/";
        }
    }
}
