package com.allwyn.swaglabstaf.ui.component;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseComponent {

    protected static final String INPUT_ERROR_MARK_XPATH = "//*[@data-icon = 'times-circle']";

    public SelenideElement element;
    public String name;

    public String getAlias() {
        return "'%s' %s".formatted(name, this.getClass().getSimpleName().toLowerCase());
    }

    public String getText() {
        log.info("Getting text from {}", getAlias());
        return element.getText();
    }
}
