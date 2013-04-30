package dk.freecode.domain.model.user;

import java.util.EnumSet;

public enum UserType {
    ADMIN,
    USER;

    public EnumSet<UserRole> getRoles() {
        switch (this) {
        case ADMIN:
            return EnumSet.of(UserRole.ADMIN, UserRole.USER);
        case USER:
            return EnumSet.of(UserRole.USER);
        default:
            return EnumSet.noneOf(UserRole.class);
        }
    }
}
