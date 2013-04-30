package dk.freecode.domain.service.user.internal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import dk.freecode.domain.model.user.UserRole;

public class AuthUser extends User {
    private static final long serialVersionUID = 1L;

    private static final String ROLE_PREFIX = "ROLE_";

    private final Long userId;

    public AuthUser(final dk.freecode.domain.model.user.User user) {
        super(user.getUsername(), user.getEncodedPassword(), true, true, true, true, getAuthorities(user));
        userId = user.getId();
    }

    public Long getUserId() {
        return userId;
    }

    private static Set<SimpleGrantedAuthority> getAuthorities(final dk.freecode.domain.model.user.User user) {
        final Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        for (final UserRole role : user.getType().getRoles()) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.name()));
        }

        return authorities;
    }

}
