package com.allwyn.swaglabstaf.ui.component;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class Button extends BaseComponent {

    public Button(String name, By locator) {
        this.name = name;
        this.element = $(locator);
    }

    public void click() {
        log.debug("Clicking the {}", getAlias());
        element.should(exist).shouldBe(visible).shouldBe(interactable);
        element.click();
    }
}
