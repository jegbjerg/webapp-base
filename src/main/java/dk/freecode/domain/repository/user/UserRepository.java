package dk.freecode.domain.repository.user;

import dk.freecode.domain.model.user.User;

public interface UserRepository {

    User getUser(final String username);

    void saveUser(final User user);
}
