package com.allwyn.swaglabstaf.ui.component.pageControl;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Text extends BaseComponent {

    public Text(String name, SelenideElement element) {
        this.name = name;
        this.element = element;
    }
}
