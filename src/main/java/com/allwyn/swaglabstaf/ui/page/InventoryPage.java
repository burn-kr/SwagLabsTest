package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.ui.component.module.Header;
import com.allwyn.swaglabstaf.ui.component.module.InventoryItem;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Represents the Inventory (Products) page of the application, where a list of available products is displayed.
 * This page allows users to view product details, add items to the cart, and sort products.
 */
@Slf4j
@Component
public class InventoryPage extends BasePage {

    private static final String PRODUCT_CARD_CSS = ".inventory_item";
    private static final String PRODUCT_DESCRIPTION_CSS = ".inventory_item_desc";
    private static final String PRODUCT_PRICE_CSS = ".inventory_item_price";

    @Getter
    private final Header header;

    /**
     * Constructs a new {@link InventoryPage} instance.
     * Sets the specific URL path for the inventory page and initializes
     * the page's header component.
     *
     * @param header The {@link Header} component instance associated with this page.
     */
    public InventoryPage(Header header) {
        pageUrl = "/inventory.html";
        this.header = header;
    }

    /**
     * Opens the inventory page directly by navigating to its URL.
     */
    @Step("Open the inventory page by URL")
    public void open() {
        log.info("Opening the Products page");
        Selenide.open(pageUrl);
    }

    /**
     * Retrieves an {@link InventoryItem} component representing a specific product on the page by its name.
     * This allows for interaction with individual product cards.
     *
     * @param itemName The name of the product to retrieve.
     * @return An {@link InventoryItem} object corresponding to the specified product name.
     */
    @Step("Get the product by it's name: {0}")
    public InventoryItem getProductByName(String itemName) {
        var elem = $$(PRODUCT_NAME_CSS)
                .findBy(Condition.text(itemName))
                .closest(PRODUCT_CARD_CSS);

        return new InventoryItem(itemName, elem);
    }

    /**
     * Retrieves a list of names for all products currently displayed on the inventory page.
     *
     * @return A {@link List} of {@link String}s, where each string is the name of a product.
     */
    @Step("Get all the products' names")
    public List<String> getProductsNames() {
        log.info("Getting products names");
        return $$(PRODUCT_NAME_CSS).texts();
    }

    /**
     * Retrieves a list of descriptions for all products currently displayed on the inventory page.
     *
     * @return A {@link List} of {@link String}s, where each string is the description of a product.
     */
    @Step("Get all the products' decriptions")
    public List<String> getProductsDescriptions() {
        log.info("Getting products descriptions");
        return $$(PRODUCT_DESCRIPTION_CSS).texts();
    }

    /**
     * Retrieves a list of prices for all products currently displayed on the inventory page.
     * The prices are extracted from their text representation (e.g., "$29.99") and converted to {@code double}.
     *
     * @return A {@link List} of {@link Double}s, where each double is the price of a product.
     */
    @Step("Get all the products' prices")
    public List<Double> getProductsPrices() {
        log.info("Getting products prices");
        return $$(PRODUCT_PRICE_CSS).texts()
                .stream()
                .map(price -> price.substring(1)) // Remove the '$' sign
                .map(Double::parseDouble)
                .toList();
    }
}