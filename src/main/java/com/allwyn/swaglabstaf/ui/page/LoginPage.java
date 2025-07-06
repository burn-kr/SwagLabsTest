package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.config.env.User;
import com.allwyn.swaglabstaf.ui.component.module.ErrorMessageContainer;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.allwyn.swaglabstaf.ui.component.pageControl.Input;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Represents the Login page of the application.
 * This page allows users to enter their username and password to authenticate and access the application.
 * It also handles the display of login-related error messages.
 */
@Slf4j
@Component
public class LoginPage extends BasePage {

    private static final String USERNAME_INPUT_ID = "user-name";
    private static final String PASSWORD_INPUT_ID = "password";
    private static final String LOGIN_BUTTON_ID = "login-button";

    @Getter
    private final ErrorMessageContainer errorMessageContainer;

    private final Input usernameInput;
    private final Input passordInput;
    private final Button loginButton;

    /**
     * Constructs a new {@link LoginPage} instance.
     * Sets the specific URL path for the login page and initializes
     * the username and password input fields, the login button, and the error message container.
     *
     * @param errorMessageContainer The {@link ErrorMessageContainer} component to display login validation errors.
     */
    @Autowired
    public LoginPage(ErrorMessageContainer errorMessageContainer) {
        pageUrl = "/";
        this.errorMessageContainer = errorMessageContainer;

        usernameInput = new Input("Username", By.id(USERNAME_INPUT_ID));
        passordInput = new Input("Password", By.id(PASSWORD_INPUT_ID));
        loginButton = new Button("Login", By.id(LOGIN_BUTTON_ID));
    }

    /**
     * Logs into the application using a {@link User} object for credentials.
     * This method extracts the username and password from the provided user object
     * and then calls the overloaded login method.
     *
     * @param user The {@link User} object containing the login credentials.
     */
    @Step("Log in to the app")
    public void login(User user) {
        login(user.getLogin(), user.getPassword());
    }

    /**
     * Logs into the application using a specified username and password.
     * This method inputs the credentials into the respective fields and clicks the login button.
     *
     * @param username The username to enter into the login field.
     * @param password The password to enter into the password field.
     */
    @Step("Log in to the app as {0}")
    public void login(String username, String password) {
        log.info("Logging in as the {}", username);
        usernameInput.inputText(username);
        passordInput.inputText(password);
        loginButton.click();
    }
}