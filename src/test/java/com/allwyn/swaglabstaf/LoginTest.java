package com.allwyn.swaglabstaf;

import com.allwyn.swaglabstaf.ui.page.InventoryPage;
import com.allwyn.swaglabstaf.ui.page.LoginPage;
import io.qameta.allure.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.allwyn.swaglabstaf.constant.AssertionMessage.ERROR_MESSAGE_INCORRECT;
import static com.allwyn.swaglabstaf.constant.AssertionMessage.PAGE_URL_INCORRECT;
import static com.allwyn.swaglabstaf.constant.TestGroup.ALL;
import static com.allwyn.swaglabstaf.constant.TestGroup.LOGIN;
import static com.allwyn.swaglabstaf.constant.UserName.LOCKED_OUT_USER;
import static com.allwyn.swaglabstaf.constant.UserName.STANDARD_USER;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

@Test(groups = {ALL, LOGIN}, testName = "Login Tests")
public class LoginTest extends BaseTest {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private InventoryPage inventoryPage;

    @Test(description = "Successful login Test")
    @Description("Verifies that a user can successfully log in")
    public void successfulLoginTest() {
        loginPage.login(credentials.getUser(STANDARD_USER));

        var pageUrl = url();

        assertThat(pageUrl)
                .as(PAGE_URL_INCORRECT)
                .isEqualTo(inventoryPage.getPageUrl());
    }

    @Test(description = "Locked user Test")
    @Description("Verifies that a locked out user cannot log in")
    public void lockedUserTest() {
        var expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
        loginPage.login(credentials.getUser(LOCKED_OUT_USER));

        var pageUrl = url();

        assertThat(pageUrl)
                .as(PAGE_URL_INCORRECT)
                .isEqualTo(loginPage.getPageUrl());

        validateError(expectedErrorMessage);
    }

    @DataProvider
    public Object[][] invalidCredentialsProvider() {
        return new Object[][] {
                {"", credentials.getUser(STANDARD_USER).getPassword(), "Epic sadface: Username is required"},
                {credentials.getUser(STANDARD_USER).getLogin(), "", "Epic sadface: Password is required"},
                {"fake_login", credentials.getUser(STANDARD_USER).getPassword(),
                        "Epic sadface: Username and password do not match any user in this service"},
                {credentials.getUser(STANDARD_USER).getLogin(), "fakePassword",
                        "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(description = "Invalid credentials Test", dataProvider = "invalidCredentialsProvider")
    @Description("Verifies that it's not possible to log in with incorrect/invalid credentials")
    public void invalidCredentialsTest(String userName, String password, String expectedErrorMessage) {
        loginPage.login(userName, password);

        var pageUrl = url();

        assertThat(pageUrl)
                .as(PAGE_URL_INCORRECT)
                .isEqualTo(loginPage.getPageUrl());

        validateError(expectedErrorMessage);
    }

    private void validateError(String expectedErrorMessage) {
        assertThat(loginPage.getErrorMessageContainer().getErrorMessage())
                .as(ERROR_MESSAGE_INCORRECT)
                .isEqualTo(expectedErrorMessage);
    }
}
