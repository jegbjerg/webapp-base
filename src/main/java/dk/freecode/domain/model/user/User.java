package dk.freecode.domain.model.user;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import dk.freecode.domain.model.DomainEntity;

@Entity
@Data
@EqualsAndHashCode(callSuper = false, of = "username")
public class User extends DomainEntity {

    public static final String DUMMY_PASSWORD = "dummy-password";

    @NaturalId
    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @Email
    @NotBlank
    @Size(max = 250)
    private String email;

    @NotNull
    private String encodedPassword;

    @Enumerated(EnumType.STRING)
    @NotNull
    private UserType type;
}
