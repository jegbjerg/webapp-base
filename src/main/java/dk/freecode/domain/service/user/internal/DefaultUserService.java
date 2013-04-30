package dk.freecode.domain.service.user.internal;

import javax.inject.Inject;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dk.freecode.domain.model.user.User;
import dk.freecode.domain.repository.user.UserRepository;
import dk.freecode.domain.service.user.UserService;

@Service
public class DefaultUserService implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private SaltSource saltSource;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userRepository.getUser(username);

        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }

        return new AuthUser(user);
    }

    @Override
    @Transactional
    public void saveUser(final User user, final String password) {
        user.setEncodedPassword(User.DUMMY_PASSWORD);
        userRepository.saveUser(user);

        final UserDetails userDetails = new AuthUser(user);
        user.setEncodedPassword(passwordEncoder.encodePassword(password, saltSource.getSalt(userDetails)));

        userRepository.saveUser(user);
    }
}
