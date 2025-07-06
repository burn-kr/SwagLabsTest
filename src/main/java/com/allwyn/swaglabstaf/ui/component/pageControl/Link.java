package com.allwyn.swaglabstaf.ui.component.pageControl;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Represents a hyperlink (anchor tag) web control.
 * This class extends {@link BaseComponent} and provides a way to interact with
 * link elements on a web page, typically for navigation.
 */
@Slf4j
public class Link extends BaseComponent {

    /**
     * Constructs a new {@link Link} instance by specifying its name and a {@link By} locator.
     * The link element will be located on the page using the provided locator.
     *
     * @param name The logical name of the link (e.g., "Privacy Policy Link", "Back to Products").
     * @param locator The {@link By} locator used to find the link element on the page.
     */
    public Link(String name, By locator) {
        this.name = name;
        this.element = $(locator);
    }
}