package com.allwyn.swaglabstaf.ui.component.module;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selectors.byTagName;

/**
 * Represents a single item displayed within a shopping cart on a web page.
 * This class extends {@link BaseComponent} and encapsulates the functionality
 * and elements specific to a cart item, such as its name and a "Remove" button.
 */
@Slf4j
public class CartItem extends BaseComponent {

    private static final String BUTTON_TAG = "button";

    private final Button removeButton;

    /**
     * Constructs a new {@link CartItem} instance.
     * Initializes the item with its given name and associates it with a specific
     * {@link SelenideElement} on the page that represents the entire cart item.
     * It also locates and initializes the "Remove" button within this item's element.
     *
     * @param name The name of the product item in the cart.
     * @param element The {@link SelenideElement} that represents the container of this cart item on the UI.
     */
    public CartItem(String name, SelenideElement element) {
        this.name = name;
        this.element = element;

        removeButton = new Button("Remove", element.$(byTagName(BUTTON_TAG)));
    }

    /**
     * Clicks the "Remove" button associated with this cart item.
     * This action is typically used to remove the item from the shopping cart.
     */
    @Step("Click Remove button")
    public void clickRemoveButton() {
        removeButton.click();
    }
}