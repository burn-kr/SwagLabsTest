package com.allwyn.swaglabstaf.ui.component.module;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.allwyn.swaglabstaf.ui.component.pageControl.Text;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selectors.*;

/**
 * Represents a single product item displayed in an inventory list on a web page.
 * This class extends {@link BaseComponent} and encapsulates the elements and
 * actions related to an individual product, such as its name, price, and an
 * "Add to Cart" or "Remove" button.
 */
@Slf4j
public class InventoryItem extends BaseComponent {

    private static final String PRICE_CSS = ".inventory_item_price";
    private static final String NAME_CSS = ".inventory_item_name";
    private static final String BUTTON_TAG = "button";

    private final Text priceText;
    private final Text nameText;
    private final Button addRemoveButton;

    /**
     * Constructs a new {@link InventoryItem} instance.
     * Initializes the item with its given name and associates it with a specific
     * {@link SelenideElement} on the page that represents the entire inventory item.
     * It also locates and initializes the price, name, and add/remove button within this item's element.
     *
     * @param name The name of the product item.
     * @param element The {@link SelenideElement} that represents the container of this inventory item on the UI.
     */
    public InventoryItem(String name, SelenideElement element) {
        this.name = name;
        this.element = element;

        priceText = new Text("Price", element.$(byCssSelector(PRICE_CSS)));
        nameText = new Text("Product name", element.$(byCssSelector(NAME_CSS)));
        addRemoveButton = new Button("Add/Remove", element.$(byTagName(BUTTON_TAG)));
    }

    /**
     * Retrieves the text representing the price of the inventory item.
     *
     * @return A {@link String} containing the price of the item.
     */
    @Step("Get price")
    public String getPrice() {
        return priceText.getText();
    }

    /**
     * Retrieves the text representing the name of the inventory item.
     *
     * @return A {@link String} containing the name of the item.
     */
    @Step("Get name")
    public String getName() {
        return nameText.getText();
    }

    /**
     * Clicks the "Add to cart" button associated with this inventory item.
     * This action typically adds the product to the shopping cart. Note that this
     * button may also serve as a "Remove" button depending on the item's state.
     */
    @Step("Click 'Add to cart' button")
    public void clickAddToCartButton() {
        addRemoveButton.click();
    }

    /**
     * Clicks the "Remove" button associated with this inventory item.
     * This action typically removes the product from the shopping cart. Note that this
     * button may also serve as an "Add to cart" button depending on the item's state.
     */
    @Step("Click 'Remove' button")
    public void clickRemoveButton() {
        addRemoveButton.click();
    }

    /**
     * Retrieves the current text displayed on the "Add to Cart" or "Remove" button.
     * This can be used to determine the current state of the button (e.g., whether it says "Add" or "Remove").
     *
     * @return A {@link String} containing the button's text.
     */
    @Step("Get button text")
    public String getButtonText() {
        return addRemoveButton.getText();
    }

    /**
     * Clicks on the product's name text, which typically navigates to the product's
     * detailed information page.
     */
    @Step("Click product")
    public void click() {
        nameText.click();
    }
}