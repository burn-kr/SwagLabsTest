package com.allwyn.swaglabstaf.ui.page;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.$;

/**
 * An abstract base class for all page objects in the application.
 * This class provides common functionalities and properties applicable to most web pages,
 * such as retrieving the page URL and page title. All concrete page classes should extend this class.
 */
@Slf4j
public abstract class BasePage {

    protected static final String CART_ITEM_CSS = ".cart_item";
    protected static final String PRODUCT_NAME_CSS = ".inventory_item_name";
    protected static final String PAGE_TITLE_CSS = ".title";

    @Value("${app.url}")
    private String baseUrl;

    protected String pageUrl = "";

    /**
     * Constructs the full URL of the current page by combining the base URL
     * (injected from properties) and the page-specific URL path.
     *
     * @return A {@link String} representing the complete URL of the page.
     */
    @Step("Get page URL")
    public String getPageUrl() {
        log.debug("Getting the page URL ({})", pageUrl);
        return "%s%s".formatted(baseUrl, pageUrl);
    }

    /**
     * Retrieves the text content of the main page title element.
     * This is typically used to verify that the correct page has been loaded.
     *
     * @return A {@link String} containing the title of the page.
     */
    @Step("Get page title")
    public String getPageTitle() {
        log.info("Getting the Products page title"); // Note: Consider making this more generic if not always "Products"
        return $(PAGE_TITLE_CSS).getText();
    }
}