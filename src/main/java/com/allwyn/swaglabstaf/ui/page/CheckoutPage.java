package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.ui.component.module.ErrorMessageContainer;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.allwyn.swaglabstaf.ui.component.pageControl.Input;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selectors.byId;

/**
 * Represents the "Checkout: Your Information" page, which is the first step in the checkout process.
 * This page requires the user to enter their shipping information (first name, last name, and postal code).
 */
@Slf4j
@Component
public class CheckoutPage extends BasePage {

    private static final String FIRST_NAME_INPUT_ID = "first-name";
    private static final String LAST_NAME_INPUT_ID = "last-name";
    private static final String POSTAL_CODE_INPUT_ID = "postal-code";
    private static final String CONTINUE_BUTTON_ID = "continue";
    private static final String CANCEL_BUTTON_ID = "cancel";

    private final Input firstNameInput;
    private final Input lastNameInput;
    private final Input postalCodeInput;
    private final Button continueButton;
    private final Button cancelButton;

    @Getter
    private final ErrorMessageContainer errorMessageContainer;

    /**
     * Constructs a new {@link CheckoutPage} instance.
     * Sets the specific URL path for this checkout step and initializes
     * input fields and action buttons, as well as the error message container.
     *
     * @param errorMessageContainer The {@link ErrorMessageContainer} component to display validation errors.
     */
    public CheckoutPage(ErrorMessageContainer errorMessageContainer) {
        pageUrl = "/checkout-step-one.html";
        this.errorMessageContainer = errorMessageContainer;

        firstNameInput = new Input("First name", byId(FIRST_NAME_INPUT_ID));
        lastNameInput = new Input("Last name", byId(LAST_NAME_INPUT_ID));
        postalCodeInput = new Input("Postal code", byId(POSTAL_CODE_INPUT_ID));
        continueButton = new Button("Continue", byId(CONTINUE_BUTTON_ID));
        cancelButton = new Button("Cancel", byId(CANCEL_BUTTON_ID));
    }

    /**
     * Enters the provided first name into the first name input field.
     *
     * @param firstName The first name to enter.
     * @return The current {@link CheckoutPage} instance, allowing for method chaining.
     */
    @Step("Enter user's first name: {0}")
    public CheckoutPage inputFirstName(String firstName) {
        firstNameInput.inputText(firstName);
        return this;
    }

    /**
     * Enters the provided last name into the last name input field.
     *
     * @param lastName The last name to enter.
     * @return The current {@link CheckoutPage} instance, allowing for method chaining.
     */
    @Step("Enter user's last name: {0}")
    public CheckoutPage inputFLastName(String lastName) {
        lastNameInput.inputText(lastName);
        return this;
    }

    /**
     * Enters the provided postal code into the postal code input field.
     *
     * @param postalCode The postal code to enter.
     * @return The current {@link CheckoutPage} instance, allowing for method chaining.
     */
    @Step("Enter user's postal code: {0}")
    public CheckoutPage inputPostalCode(String postalCode) {
        postalCodeInput.inputText(postalCode);
        return this;
    }

    /**
     * Clicks the "Cancel" button on the checkout information page.
     * This action typically aborts the checkout process and might return the user to the cart page.
     */
    @Step("Click the 'Cancel' button")
    public void clickCancelButton() {
        cancelButton.click();
    }

    /**
     * Clicks the "Continue" button on the checkout information page.
     * This action typically proceeds to the next step of the checkout process (e.g., checkout overview).
     */
    @Step("Click the 'Continue' button")
    public void clickContinueButton() {
        continueButton.click();
    }
}