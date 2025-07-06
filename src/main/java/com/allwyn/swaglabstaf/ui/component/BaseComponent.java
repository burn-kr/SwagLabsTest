package com.allwyn.swaglabstaf.ui.component;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.*;

@Slf4j
public abstract class BaseComponent {

    protected static final String INPUT_ERROR_MARK_XPATH = "//*[@data-icon = 'times-circle']";

    protected SelenideElement element;
    @Getter
    protected String name;

    public String getAlias() {
        return "'%s' %s".formatted(name, this.getClass().getSimpleName().toLowerCase());
    }

    public String getText() {
        log.info("Getting text from {}", getAlias());
        return element.getText();
    }

    public void click() {
        log.debug("Clicking the {}", getAlias());
        element.should(exist).shouldBe(visible).shouldBe(interactable);
        element.click();
    }

    public boolean isDisplayed() {
        log.info("Checking if {} is displayed", getAlias());
        return element.isDisplayed();
    }

    public boolean isEnabled() {
        log.info("Checking if {} is enabled", getAlias());
        return !element.has(cssClass("disabled")) && element.isEnabled();
    }
}
