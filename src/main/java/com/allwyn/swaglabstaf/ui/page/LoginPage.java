package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.config.env.User;
import com.allwyn.swaglabstaf.ui.component.module.ErrorMessageContainer;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.allwyn.swaglabstaf.ui.component.pageControl.Input;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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

    @Autowired
    public LoginPage(ErrorMessageContainer errorMessageContainer) {
        pageUrl = "/";
        this.errorMessageContainer = errorMessageContainer;

        usernameInput = new Input("Username", By.id(USERNAME_INPUT_ID));
        passordInput = new Input("Password", By.id(PASSWORD_INPUT_ID));
        loginButton = new Button("Login", By.id(LOGIN_BUTTON_ID));
    }

    public void login(User user) {
        login(user.getLogin(), user.getPassword());
    }

    public void login(String username, String password) {
        log.info("Logging in as the {}", username);
        usernameInput.inputText(username);
        passordInput.inputText(password);
        loginButton.click();
    }
}
