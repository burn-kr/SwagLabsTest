package com.allwyn.swaglabstaf.ui.component.pageControl;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class Button extends BaseComponent {

    public Button(String name, By locator) {
        this.name = name;
        this.element = $(locator);
    }

    public Button(String name, SelenideElement element) {
        this.name = name;
        this.element = element;
    }
}
