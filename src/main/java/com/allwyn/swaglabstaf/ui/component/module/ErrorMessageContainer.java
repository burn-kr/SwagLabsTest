package com.allwyn.swaglabstaf.ui.component.module;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
@Component
public class ErrorMessageContainer extends BaseComponent {

    private static final String SELF_XPATH = "//div[contains(@class, 'error-message-container')]";
    private static final String ERROR_TEXT_XPATH = ".//*[@data-test='error']";

    public ErrorMessageContainer() {
        element = $(byXpath(SELF_XPATH));
    }

    public String getErrorMessage() {
        return element.$(byXpath(ERROR_TEXT_XPATH)).getText();
    }
}
