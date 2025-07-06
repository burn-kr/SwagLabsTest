package com.allwyn.swaglabstaf.ui.component.module;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Represents an error message container displayed on a web page.
 * This component encapsulates the error message element and provides methods
 * to interact with or retrieve information from it. It's typically used to
 * verify error feedback to the user.
 */
@Slf4j
@Component
public class ErrorMessageContainer extends BaseComponent {

    private static final String SELF_XPATH = "//div[@class = 'error-message-container error']";
    private static final String ERROR_TEXT_XPATH = ".//*[@data-test='error']";

    /**
     * Constructs a new {@link ErrorMessageContainer} instance.
     * Initializes the component by locating the main error message container element
     * on the page using its predefined XPath.
     */
    public ErrorMessageContainer() {
        element = $(byXpath(SELF_XPATH));
    }

    /**
     * Retrieves the text content of the error message displayed within the container.
     *
     * @return A {@link String} representing the visible error message text.
     */
    @Step("Get error message text")
    public String getErrorMessage() {
        return element.$(byXpath(ERROR_TEXT_XPATH)).getText();
    }
}