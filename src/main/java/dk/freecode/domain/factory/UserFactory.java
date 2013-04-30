package dk.freecode.domain.factory;

import dk.freecode.domain.model.user.User;
import dk.freecode.domain.model.user.UserType;

public class UserFactory {

    /**
     * Create an empty user of type {@link UserType#USER}.
     * <p>
     * <strong>Note:</strong> the user is created with a dummy encoded password.
     * 
     * @return the empty user
     */
    public static User createRegularUser() {
        final User user = new User();
        user.setEncodedPassword(User.DUMMY_PASSWORD);
        user.setType(UserType.USER);
        return user;
    }
}
