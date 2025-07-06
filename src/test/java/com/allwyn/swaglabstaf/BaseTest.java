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
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.allwyn.swaglabstaf.constant.AssertionMessage.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Listeners(TestNGExecutionListener.class)
public class BaseTest extends AbstractTestNGSpringContextTests {

    protected static final String BACKPACK_PRODUCT_NAME = "Sauce Labs Backpack";
    protected static final String ONESIE_PRODUCT_NAME = "Sauce Labs Onesie";
    protected static final String PRODUCTS_PAGE_TITLE = "Products";
    protected static final String CART_PAGE_TITLE = "Your Cart";
    protected static final String CHECKOUT_PAGE_TITLE = "Checkout: Your Information";

    @Value("${app.url}")
    private String pageUrl;

    @Autowired
    private Timeout timeout;

    @Autowired
    protected Credentials credentials;

    @Autowired
    protected Faker faker;

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
        if (header.isDisplayed()) {
            header
                    .clickMainMenuButton()
                    .clickResetLink()
                    .clickLogoutLink();
        }
    }

    /**
     * Validates that the current page URL matches the expected URL.
     * If the URLs do not match, an assertion error is thrown with a descriptive message.
     *
     * @param expectedPageUrl The expected full URL of the page.
     */
    protected void validatePageUrl(String expectedPageUrl) {
        var pageUrl = url(); // Gets the current URL from Selenide WebDriver

        assertThat(pageUrl)
                .as(PAGE_URL_INCORRECT) // Uses a predefined assertion message constant
                .isEqualTo(expectedPageUrl);
    }

    /**
     * Validates that the actual page title matches the expected title.
     * If the titles do not match, an assertion error is thrown with a descriptive message.
     *
     * @param actualTitle The actual title retrieved from the page.
     * @param expectedTitle The expected title that the page should have.
     */
    protected void validatePageTitle(String actualTitle, String expectedTitle) {
        assertThat(actualTitle)
                .as(PAGE_TITLE_INCORRECT) // Uses a predefined assertion message constant
                .isEqualTo(expectedTitle);
    }

    /**
     * Validates that an actual error message matches the expected error message.
     * This is typically used to confirm correct error handling and message display.
     *
     * @param actualErrorMessage The actual error message text displayed on the UI.
     * @param expectedErrorMessage The expected error message text.
     */
    protected void validateError(String actualErrorMessage, String expectedErrorMessage) {
        assertThat(actualErrorMessage)
                .as(ERROR_MESSAGE_INCORRECT) // Uses a predefined assertion message constant
                .isEqualTo(expectedErrorMessage);
    }
}
