package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.allwyn.swaglabstaf.ui.component.pageControl.Text;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

/**
 * Represents the "Checkout: Complete!" (Finish) page, which is the final step of the checkout process.
 * This page confirms that the order has been placed successfully and provides an option to return to the products.
 */
@Slf4j
@Component
public class FinishPage extends BasePage {

    private static final String IMG_SUCCESS_CSS = ".pony_express";
    private static final String MESSAGE_TITLE_CSS = ".complete-header";
    private static final String MESSAGE_CONTENT_CSS = ".complete-text";
    private static final String BACK_BUTTON_ID = "back-to-products";

    private final Text messageTitleText;
    private final Text messageContentText;
    private final Button backHomeButton;

    /**
     * Constructs a new {@link FinishPage} instance.
     * Sets the specific URL path for the checkout complete page and initializes
     * elements displaying success messages and the "Back Home" button.
     */
    public FinishPage() {
        pageUrl = "/checkout-complete.html";

        messageTitleText = new Text("Message title", byCssSelector(MESSAGE_TITLE_CSS));
        messageContentText = new Text("Message content", byCssSelector(MESSAGE_CONTENT_CSS));
        backHomeButton = new Button("Back home", byId(BACK_BUTTON_ID));
    }

    /**
     * Checks if the success green checkmark image (often a "pony express" image)
     * is displayed on the page, indicating a successful order completion.
     *
     * @return {@code true} if the success checkmark is displayed, {@code false} otherwise.
     */
    @Step("Check if the success green mark is displayed")
    public boolean isGreenCheckmarkDisplayed() {
        log.info("Checking if the green checkmark is displayed");
        return $(byCssSelector(IMG_SUCCESS_CSS)).isDisplayed();
    }

    /**
     * Retrieves the text of the main title from the success message.
     *
     * @return A {@link String} containing the success message title (e.g., "THANK YOU FOR YOUR ORDER").
     */
    @Step("Get the success message title")
    public String getMessageTitleText() {
        return messageTitleText.getText();
    }

    /**
     * Retrieves the text content of the detailed success message.
     *
     * @return A {@link String} containing the detailed success message (e.g., "Your order has been dispatched...").
     */
    @Step("Get the success message content")
    public String getMessageContentText() {
        return messageContentText.getText();
    }

    /**
     * Clicks the "Back Home" button on the finish page.
     * This action typically navigates the user back to the product inventory page.
     */
    @Step("Click the 'Back Home' button")
    public void clickBackHomeButton() {
        backHomeButton.click();
    }
}