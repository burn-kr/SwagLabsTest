package com.allwyn.swaglabstaf.ui.component.pageControl;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Represents an input field web control.
 * This class extends {@link BaseComponent} and provides methods to interact with
 * text input fields, including typing text, checking for error states, and handling
 * specific input field behaviors.
 */
@Slf4j
public class Input extends BaseComponent {

    private static final String ERROR_CLASS_NAME = "input_error";
    // Assuming INPUT_ERROR_MARK_XPATH is defined elsewhere, e.g., in BaseComponent or a constants file
    private static final String INPUT_ERROR_MARK_XPATH = ".//path[@fill='#e2231a']"; // Common XPath for error icons

    /**
     * Constructs a new {@link Input} instance by specifying its name and a {@link By} locator.
     * The input element will be located on the page using the provided locator.
     *
     * @param name The logical name of the input field (e.g., "Username Input", "Password Input").
     * @param locator The {@link By} locator used to find the input element on the page.
     */
    public Input(String name, By locator) {
        this.name = name;
        this.element = $(locator);
    }

    /**
     * Inputs the given text into the input field.
     * Before typing, the method ensures the element exists, is visible, and interactable.
     * It includes a workaround for clearing the input value that handles issues with
     * password fields or JavaScript-based clearing.
     *
     * @param text The {@link String} text to be entered into the input field.
     */
    @Step("Input text '{0}' into Input element")
    public void inputText(String text) {
        log.debug("Inputting text '{}' into {}", text, getAlias());
        element.should(exist).shouldBe(visible).shouldBe(interactable);

        // workaround for clearing the input value as in case of input type=password Keys.CONTROL + "a" don't work
        // setting an empty value wit JS code doesn't work as well
        element.clear();
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);

        element.sendKeys(text);
    }

    /**
     * Checks if the input field has an associated error mark (e.g., an icon indicating an error).
     * This method typically looks for a sibling element that represents the error visual.
     *
     * @return {@code true} if an error mark is displayed, {@code false} otherwise.
     */
    @Step("Check if input has the error mark")
    public boolean hasErrorMark() {
        return element.sibling(0).$(INPUT_ERROR_MARK_XPATH).isDisplayed();
    }

    /**
     * Checks if the input field's class attribute contains a specific error class,
     * indicating a visual red line underneath or similar styling for validation errors.
     *
     * @return {@code true} if the input element has the "input_error" class, {@code false} otherwise.
     */
    @Step("Check if the input has the red line underneath")
    public boolean hasRedErrorLine() {
        return Objects.requireNonNull(element.getAttribute("class")).contains(ERROR_CLASS_NAME);
    }
}