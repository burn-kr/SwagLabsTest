package com.allwyn.swaglabstaf.ui.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public abstract class BasePage {

    protected static final String CART_ITEM_CSS = ".cart_item";
    protected static final String PRODUCT_NAME_CSS = ".inventory_item_name";
    protected static final String PAGE_TITLE_CSS = ".title";

    @Value("${app.url}")
    private String baseUrl;

    protected String pageUrl = "";

    public String getPageUrl() {
        log.debug("Getting the page URL ({})", pageUrl);
        return "%s%s".formatted(baseUrl, pageUrl);
    }

    public String getPageTitle() {
        log.info("Getting the Products page title");
        return $(PAGE_TITLE_CSS).getText();
    }
}
