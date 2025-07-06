package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.ui.component.module.CartItem;
import com.allwyn.swaglabstaf.ui.component.module.Header;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Represents the shopping cart page of the application.
 * This page displays the items currently added to the cart and provides options
 * to proceed to checkout or continue shopping.
 */
@Slf4j
@Component
public class CartPage extends BasePage {

    private static final String CHECKOUT_BUTTON_ID = "checkout";
    private static final String CONTINUE_SHOPPING_BUTTON_ID = "continue-shopping";

    private final Button checkoutButton;
    private final Button continueShoppingButton;

    @Getter
    private final Header header;

    /**
     * Constructs a new {@link CartPage} instance.
     * Sets the specific URL path for the cart page and initializes the page's
     * interactive elements like the checkout and continue shopping buttons,
     * as well as the page header component.
     *
     * @param header The {@link Header} component instance associated with this page.
     */
    public CartPage(Header header) {
        pageUrl = "/cart.html";
        this.header = header;

        checkoutButton = new Button("Checkout", byId(CHECKOUT_BUTTON_ID));
        continueShoppingButton = new Button("Continue shopping", byId(CONTINUE_SHOPPING_BUTTON_ID));
    }

    /**
     * Retrieves the total number of items currently displayed in the shopping cart.
     *
     * @return An {@code int} representing the count of cart items.
     */
    @Step("Get the number of the shopping cart items")
    public int getNumberOfCartItems() {
        log.info("Getting the number of cart items");
        return $$(CART_ITEM_CSS).size();
    }

    /**
     * Retrieves a list of names for all products currently in the shopping cart.
     *
     * @return A {@link List} of {@link String}s, where each string is the name of a product in the cart.
     */
    @Step("Get the shopping cart items' names")
    public List<String> getCartItemNames() {
        log.info("Getting shopping cart items names");
        return $$(PRODUCT_NAME_CSS).texts();
    }

    /**
     * Retrieves a {@link CartItem} component representing a specific item in the cart by its name.
     * This allows for interaction with individual items in the cart (e.g., clicking its remove button).
     *
     * @param itemName The name of the product item to retrieve.
     * @return A {@link CartItem} object corresponding to the specified item name.
     */
    @Step("Get a shopping cart item by name: {0}")
    public CartItem getCartItemByName(String itemName) {
        var elem = $$(PRODUCT_NAME_CSS)
                .findBy(Condition.text(itemName))
                .closest(CART_ITEM_CSS);

        return new CartItem(itemName, elem);
    }

    /**
     * Clicks the "Checkout" button on the cart page.
     * This action typically initiates the checkout process.
     */
    @Step("Click the 'Checkout' button")
    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    /**
     * Clicks the "Continue Shopping" button on the cart page.
     * This action typically navigates back to the product inventory page.
     */
    @Step("Click the 'Continue shopping' button")
    public void voidclickContinueShoppingButton() {
        continueShoppingButton.click();
    }

    /**
     * Checks if the "Checkout" button is currently enabled and clickable.
     *
     * @return {@code true} if the checkout button is enabled, {@code false} otherwise.
     */
    @Step("Check if the 'Checkout' button is enabled")
    public boolean isCheckoutButtonEnabled() {
        return checkoutButton.isEnabled();
    }
}