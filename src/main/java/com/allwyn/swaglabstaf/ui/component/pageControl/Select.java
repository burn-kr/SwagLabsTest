package com.allwyn.swaglabstaf.ui.component.pageControl;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class Select extends BaseComponent {

    public Select(String name, By locator) {
        this.name = name;
        this.element = $(locator);
    }

    @Step("Select the '{0}' option from Select")
    public void selectOption(String optionName) {
        log.debug("Selecting by value '{}' from the '{}' select", optionName, name);
        element.click();
        element.selectOption(optionName);
    }
}
