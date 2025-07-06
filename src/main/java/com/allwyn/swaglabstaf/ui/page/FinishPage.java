package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.allwyn.swaglabstaf.ui.component.pageControl.Text;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

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

    public FinishPage() {
        pageUrl = "/checkout-complete.html";

        messageTitleText = new Text("Message title", byCssSelector(MESSAGE_TITLE_CSS));
        messageContentText = new Text("Message content", byCssSelector(MESSAGE_CONTENT_CSS));
        backHomeButton = new Button("Back home", byId(BACK_BUTTON_ID));
    }

    public boolean isGreenCheckmarkDisplayed() {
        log.info("Checking if the green checkmark is displayed");
        return $(byCssSelector(IMG_SUCCESS_CSS)).isDisplayed();
    }

    public String getMessageTitleText() {
        return messageTitleText.getText();
    }

    public String getMessageContentText() {
        return messageContentText.getText();
    }

    public void clickBackHomeButton() {
        backHomeButton.click();
    }
}
