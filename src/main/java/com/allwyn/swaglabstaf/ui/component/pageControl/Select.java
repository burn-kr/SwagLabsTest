package com.allwyn.swaglabstaf.ui.component.pageControl;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class Select extends BaseComponent {

    public Select(String name, By locator) {
        this.name = name;
        this.element = $(locator);
    }

    public void selectOption(String optionName) {
        log.debug("Selecting by value '{}' from the '{}' select", optionName, name);
        element.click();
        element.selectOption(optionName);
    }
}
