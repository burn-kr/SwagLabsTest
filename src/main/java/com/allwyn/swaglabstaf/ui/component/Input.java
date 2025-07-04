package com.allwyn.swaglabstaf.ui.component;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class Input extends BaseComponent {

    private static final String ERROR_CLASS_NAME = "input_error";

    public Input(String name, By locator) {
        this.name = name;
        this.element = $(locator);
    }

    public void inputText(String text) {
        log.debug("Inputting text '{}' into {}", text, getAlias());
        element.should(exist).shouldBe(visible).shouldBe(interactable);
        element.clear();
        element.sendKeys(text);
    }

    public boolean hasErrorMark() {
        return element.sibling(0).$(INPUT_ERROR_MARK_XPATH).isDisplayed();
    }

    public boolean hasRedErrorLine() {
        return Objects.requireNonNull(element.getAttribute("class")).contains(ERROR_CLASS_NAME);
    }
}
