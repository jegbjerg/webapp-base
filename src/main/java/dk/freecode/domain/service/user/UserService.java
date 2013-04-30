package dk.freecode.domain.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import dk.freecode.domain.model.user.User;

public interface UserService extends UserDetailsService {

    /**
     * Save a new user with an encoded password.
     * <p>
     * Password encoding is done by first saving the user with a dummy password, and then encoding the password with the
     * user id as the salt. Finally, we again save the user with the encoded password.
     * </p>
     * <p>
     * <strong>Note:</strong> this first sets a dummy password as the users encoded password, so the database will never
     * see the real password.
     * </p>
     * 
     * @param user
     *            the user
     * @param password
     *            the (unencoded) password to save
     */
    void saveUser(final User user, final String password);
}
