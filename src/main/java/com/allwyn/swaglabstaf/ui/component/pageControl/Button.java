package com.allwyn.swaglabstaf.ui.component.pageControl;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Represents a clickable button web control.
 * This class extends {@link BaseComponent} and provides a way to interact with
 * button elements on a web page. It can be initialized either by a locator or
 * directly by an existing {@link SelenideElement}.
 */
@Slf4j
public class Button extends BaseComponent {

    /**
     * Constructs a new {@link Button} instance by specifying its name and a {@link By} locator.
     * The button element will be located on the page using the provided locator.
     *
     * @param name The logical name of the button (e.g., "Login Button", "Add to Cart").
     * @param locator The {@link By} locator used to find the button element on the page.
     */
    public Button(String name, By locator) {
        this.name = name;
        this.element = $(locator);
    }

    /**
     * Constructs a new {@link Button} instance by specifying its name and an existing {@link SelenideElement}.
     * This constructor is useful when the button element has already been located or is part of a larger component.
     *
     * @param name The logical name of the button.
     * @param element The {@link SelenideElement} that represents the button.
     */
    public Button(String name, SelenideElement element) {
        this.name = name;
        this.element = element;
    }
}