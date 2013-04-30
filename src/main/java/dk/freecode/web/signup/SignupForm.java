package dk.freecode.web.signup;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.validator.constraints.NotBlank;

import dk.freecode.domain.model.user.User;
import dk.freecode.validation.constraints.FieldMatch;

@FieldMatch(first = "password", second = "confirmPassword")
@Data
@EqualsAndHashCode(of = "user")
public class SignupForm {

    @Valid
    private final User user;

    @NotBlank
    @Size(min = 5, max = 250)
    private String password;

    private String confirmPassword;
}
