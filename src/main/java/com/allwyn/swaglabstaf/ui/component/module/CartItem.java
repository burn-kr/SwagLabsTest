package com.allwyn.swaglabstaf.ui.component.module;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selectors.byTagName;

@Slf4j
public class CartItem extends BaseComponent {

    private static final String BUTTON_TAG = "button";

    private final Button removeButton;

    public CartItem(String name, SelenideElement element) {
        this.name = name;
        this.element = element;

        removeButton = new Button("Remove", element.$(byTagName(BUTTON_TAG)));
    }

    @Step("Click Remove button")
    public void clickRemoveButton() {
        removeButton.click();
    }
}
