package com.allwyn.swaglabstaf;

import com.allwyn.swaglabstaf.ui.page.InventoryPage;
import com.allwyn.swaglabstaf.ui.page.LoginPage;
import io.qameta.allure.Description;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static com.allwyn.swaglabstaf.constant.AssertionMessage.PAGE_URL_INCORRECT;
import static com.allwyn.swaglabstaf.constant.TestGroup.ALL;
import static com.allwyn.swaglabstaf.constant.TestGroup.LOGIN;
import static com.allwyn.swaglabstaf.constant.UserName.LOCKED_OUT_USER;
import static com.allwyn.swaglabstaf.constant.UserName.STANDARD_USER;
import static com.codeborne.selenide.WebDriverRunner.url;

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

        Assertions.assertThat(pageUrl)
                .as(PAGE_URL_INCORRECT)
                .isEqualTo(inventoryPage.getPageUrl());
    }
}
