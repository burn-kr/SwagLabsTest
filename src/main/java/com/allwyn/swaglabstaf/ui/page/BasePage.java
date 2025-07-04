package com.allwyn.swaglabstaf.ui.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public abstract class BasePage {

    @Value("${app.url}")
    private String baseUrl;

    protected String pageUrl = "";

    public String getPageUrl() {
        log.debug("Getting the page URL ({})", pageUrl);
        return "%s%s".formatted(baseUrl, pageUrl);
    }
}
