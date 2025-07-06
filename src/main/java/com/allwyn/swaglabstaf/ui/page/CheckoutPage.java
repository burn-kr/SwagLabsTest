package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.ui.component.module.ErrorMessageContainer;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.allwyn.swaglabstaf.ui.component.pageControl.Input;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selectors.byId;

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

    public CheckoutPage(ErrorMessageContainer errorMessageContainer) {
        pageUrl = "/checkout-step-one.html";
        this.errorMessageContainer = errorMessageContainer;

        firstNameInput = new Input("First name", byId(FIRST_NAME_INPUT_ID));
        lastNameInput = new Input("Last name", byId(LAST_NAME_INPUT_ID));
        postalCodeInput = new Input("Postal code", byId(POSTAL_CODE_INPUT_ID));
        continueButton = new Button("Continue", byId(CONTINUE_BUTTON_ID));
        cancelButton = new Button("Cancel", byId(CANCEL_BUTTON_ID));
    }

    public CheckoutPage inputFirstName(String firstName) {
        firstNameInput.inputText(firstName);
        return this;
    }

    public CheckoutPage inputFLastName(String lastName) {
        lastNameInput.inputText(lastName);
        return this;
    }

    public CheckoutPage inputPostalCode(String postalCode) {
        postalCodeInput.inputText(postalCode);
        return this;
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

    public void clickContinueButton() {
        continueButton.click();
    }
}
