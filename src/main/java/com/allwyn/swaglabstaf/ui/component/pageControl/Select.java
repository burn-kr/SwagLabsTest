package com.allwyn.swaglabstaf.ui.component.pageControl;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Represents a dropdown ({@code <select>}) web control.
 * This class extends {@link BaseComponent} and provides methods to interact with
 * select elements, allowing options to be chosen.
 */
@Slf4j
public class Select extends BaseComponent {

    /**
     * Constructs a new {@link Select} instance by specifying its name and a {@link By} locator.
     * The select element will be located on the page using the provided locator.
     *
     * @param name The logical name of the select dropdown (e.g., "Sort Order", "Country").
     * @param locator The {@link By} locator used to find the select element on the page.
     */
    public Select(String name, By locator) {
        this.name = name;
        this.element = $(locator);
    }

    /**
     * Selects an option from the dropdown by its visible text.
     * The method first clicks on the select element to open the dropdown, then selects the specified option.
     *
     * @param optionName The visible text of the option to be selected.
     */
    @Step("Select the '{0}' option from Select")
    public void selectOption(String optionName) {
        log.debug("Selecting by value '{}' from the '{}' select", optionName, name);
        element.click();
        element.selectOption(optionName);
    }
}