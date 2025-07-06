package com.allwyn.swaglabstaf.ui.component;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.*;

/**
 * An abstract base class for all UI components in the application.
 * This class provides common functionalities and properties for interacting with
 * web elements using Selenide, such as retrieving text, clicking, and checking
 * visibility or enabled states. All UI component classes should extend this class.
 */
@Slf4j
public abstract class BaseComponent {

    protected static final String INPUT_ERROR_MARK_XPATH = "//*[@data-icon = 'times-circle']";

    protected SelenideElement element;

    @Getter
    protected String name;

    /**
     * Generates a user-friendly alias for the component, combining its logical name
     * and a simplified version of its class name (e.g., "'Login' input", "'Add to Cart' button").
     * This alias is primarily used in logging messages.
     *
     * @return A {@link String} representing the alias of the component.
     */
    public String getAlias() {
        return "'%s' %s".formatted(name, this.getClass().getSimpleName().toLowerCase());
    }

    /**
     * Retrieves the visible text content of the component's underlying {@link SelenideElement}.
     *
     * @return A {@link String} containing the text of the element.
     */
    @Step("Get text of the element")
    public String getText() {
        log.info("Getting text from {}", getAlias());
        return element.getText();
    }

    /**
     * Clicks the component's underlying {@link SelenideElement}.
     * Before clicking, it asserts that the element exists, is visible, and is interactable.
     */
    @Step("Click the element")
    public void click() {
        log.debug("Clicking the {}", getAlias());
        element.should(exist).shouldBe(visible).shouldBe(interactable);
        element.click();
    }

    /**
     * Checks if the component's underlying {@link SelenideElement} is currently displayed on the page.
     *
     * @return {@code true} if the element is displayed, {@code false} otherwise.
     */
    @Step("Check if the element is displayed")
    public boolean isDisplayed() {
        log.info("Checking if {} is displayed", getAlias());
        return element.isDisplayed();
    }

    /**
     * Checks if the component's underlying {@link SelenideElement} is enabled and interactable.
     * It also checks if the element explicitly has a "disabled" CSS class.
     *
     * @return {@code true} if the element is enabled and not visually disabled, {@code false} otherwise.
     */
    @Step("Check if the element is enabled")
    public boolean isEnabled() {
        log.info("Checking if {} is enabled", getAlias());
        return !element.has(cssClass("disabled")) && element.isEnabled();
    }
}