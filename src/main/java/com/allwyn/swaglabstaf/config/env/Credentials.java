package com.allwyn.swaglabstaf.config.env;

import com.allwyn.swaglabstaf.constant.UserName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "credentials")
public class Credentials {

    private static final String NO_SUCH_USER_MESSAGE = "User '%s' was not defined in the configuration file";

    private List<User> users;

    public User getUser(UserName userName) {
        return users.stream()
                .filter(user -> user.getLogin().equals(userName.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(NO_SUCH_USER_MESSAGE.formatted(userName.getName())));
    }
}
