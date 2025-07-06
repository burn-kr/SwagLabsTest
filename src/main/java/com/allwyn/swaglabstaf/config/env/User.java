package com.allwyn.swaglabstaf.config.env;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a user with authentication credentials, including a login and a password.
 * This class is typically used for storing or transmitting user account information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String login;
    private String password;
}
