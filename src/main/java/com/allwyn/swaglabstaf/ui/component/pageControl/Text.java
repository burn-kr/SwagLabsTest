package com.allwyn.swaglabstaf.ui.component.pageControl;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Represents a static text web element, such as a label, paragraph, or display message.
 * This class extends {@link BaseComponent} and provides a way to interact with
 * non-interactive text elements on a web page, typically for retrieving their content.
 */
@Slf4j
public class Text extends BaseComponent {

    /**
     * Constructs a new {@link Text} instance by specifying its name and an existing {@link SelenideElement}.
     * This constructor is useful when the text element has already been located or is part of a larger component.
     *
     * @param name The logical name of the text element (e.g., "Product Description", "Page Title").
     * @param element The {@link SelenideElement} that represents the text element.
     */
    public Text(String name, SelenideElement element) {
        this.name = name;
        this.element = element;
    }

    /**
     * Constructs a new {@link Text} instance by specifying its name and a {@link By} locator.
     * The text element will be located on the page using the provided locator.
     *
     * @param name The logical name of the text element.
     * @param locator The {@link By} locator used to find the text element on the page.
     */
    public Text(String name, By locator) {
        this.name = name;
        this.element = $(locator);
    }
}