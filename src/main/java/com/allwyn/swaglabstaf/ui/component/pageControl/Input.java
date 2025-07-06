package com.allwyn.swaglabstaf.ui.component.pageControl;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class Input extends BaseComponent {

    private static final String ERROR_CLASS_NAME = "input_error";

    public Input(String name, By locator) {
        this.name = name;
        this.element = $(locator);
    }

    @Step("Input text '{0}' into Input element")
    public void inputText(String text) {
        log.debug("Inputting text '{}' into {}", text, getAlias());
        element.should(exist).shouldBe(visible).shouldBe(interactable);

        // workaround for clearing the input value as in case of input type=password Keys.CONTROL + "a" don't work
        // setting an empty value wit JS code doesn't work as well
        element.clear();
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);

        element.sendKeys(text);
    }

    @Step("Check if input has the error mark")
    public boolean hasErrorMark() {
        return element.sibling(0).$(INPUT_ERROR_MARK_XPATH).isDisplayed();
    }

    @Step("Check if the input has the red line underneath")
    public boolean hasRedErrorLine() {
        return Objects.requireNonNull(element.getAttribute("class")).contains(ERROR_CLASS_NAME);
    }
}
