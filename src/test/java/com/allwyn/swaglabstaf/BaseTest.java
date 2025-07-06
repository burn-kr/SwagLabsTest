package com.allwyn.swaglabstaf;

import com.allwyn.swaglabstaf.config.env.Credentials;
import com.allwyn.swaglabstaf.config.env.CustomProvider;
import com.allwyn.swaglabstaf.config.env.Timeout;
import com.allwyn.swaglabstaf.config.listener.TestNGExecutionListener;
import com.allwyn.swaglabstaf.ui.component.module.Header;
import com.allwyn.swaglabstaf.ui.component.module.MainMenu;
import com.allwyn.swaglabstaf.ui.page.LoginPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.allwyn.swaglabstaf.constant.AssertionMessage.PAGE_URL_INCORRECT;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Listeners(TestNGExecutionListener.class)
public class BaseTest extends AbstractTestNGSpringContextTests {

    protected static final String BACKPACK_PRODUCT_NAME = "Sauce Labs Backpack";
    protected static final String ONESIE_PRODUCT_NAME = "Sauce Labs Onesie";
    protected static final String PRODUCTS_PAGE_TITLE = "Products";

    @Value("${app.url}")
    private String pageUrl;

    @Autowired
    private Timeout timeout;

    @Autowired
    protected Credentials credentials;

    @Autowired
    private Header header;

    @Autowired
    protected MainMenu mainMenu;

    @Autowired
    protected LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        Configuration.browser = CustomProvider.class.getName();
        Configuration.baseUrl = pageUrl;
        Configuration.screenshots = false;
        Configuration.fastSetValue = true;
        Configuration.timeout = timeout.implicitWait() * 1000;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @BeforeMethod
    public void openHomePage() {
        Selenide.open("/");
    }

    @AfterMethod
    public void logout() {
        if (mainMenu.isDisplayed()) {
            mainMenu.clickLogoutLink();
        } else if (header.isDisplayed()) {
            header.clickMainMenuButton()
                    .clickLogoutLink();
        }
    }

    protected void validatePageUrl(String expectedPageUrl) {
        var pageUrl = url();

        assertThat(pageUrl)
                .as(PAGE_URL_INCORRECT)
                .isEqualTo(expectedPageUrl);
    }
}
