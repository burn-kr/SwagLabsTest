package com.allwyn.swaglabstaf.config.env;

import com.allwyn.swaglabstaf.constant.UserName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Represents a collection of user credentials and provides methods to access them.
 * This class is typically used to manage user authentication information loaded from a configuration.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "credentials")
public class Credentials {

    private static final String NO_SUCH_USER_MESSAGE = "User '%s' was not defined in the configuration file";

    private List<User> users;

    /**
     * Retrieves a {@link User} object based on the provided {@link UserName}.
     *
     * @param userName The {@link UserName} object representing the login name of the user to retrieve.
     * @return The {@link User} object corresponding to the given user name.
     * @throws RuntimeException If no user with the specified {@code userName} is found in the
     * configured list of users. The exception message will indicate which user was not found.
     */
    public User getUser(UserName userName) {
        return users.stream()
                .filter(user -> user.getLogin().equals(userName.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(NO_SUCH_USER_MESSAGE.formatted(userName.getName())));
    }
}
