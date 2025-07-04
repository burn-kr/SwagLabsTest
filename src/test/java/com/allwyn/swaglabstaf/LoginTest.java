package com.allwyn.swaglabstaf;

import com.allwyn.swaglabstaf.ui.page.InventoryPage;
import com.allwyn.swaglabstaf.ui.page.LoginPage;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static com.allwyn.swaglabstaf.constant.AssertionMessage.PAGE_URL_INCORRECT;
import static com.allwyn.swaglabstaf.constant.TestGroup.ALL;
import static com.allwyn.swaglabstaf.constant.TestGroup.LOGIN;
import static com.allwyn.swaglabstaf.constant.UserName.STANDARD_USER;
import static com.codeborne.selenide.WebDriverRunner.url;

@Test(groups = {ALL, LOGIN}, testName = "Login Tests")
public class LoginTest extends BaseTest {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private InventoryPage inventoryPage;

    @Test
    public void loginTest() {
        loginPage.login(STANDARD_USER.getName(), "secret_sauce");

        var pageUrl = url();

        Assertions.assertThat(pageUrl)
                .as(PAGE_URL_INCORRECT)
                .isEqualTo(inventoryPage.getPageUrl());
    }
}
