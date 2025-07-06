package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.ui.component.module.CartItem;
import com.allwyn.swaglabstaf.ui.component.module.Header;
import com.allwyn.swaglabstaf.ui.component.module.InventoryItem;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
@Component
public class CartPage extends BasePage {

    private static final String CHECKOUT_BUTTON_ID = "checkout";
    private static final String CONTINUE_SHOPPING_BUTTON_ID = "continue-shopping";

    private final Button checkoutButton;
    private final Button continueShoppingButton;

    @Getter
    private final Header header;

    public CartPage(Header header) {
        pageUrl = "/cart.html";
        this.header = header;

        checkoutButton = new Button("Checkout", byId(CHECKOUT_BUTTON_ID));
        continueShoppingButton = new Button("Continue shopping", byId(CONTINUE_SHOPPING_BUTTON_ID));
    }

    @Step("Get the number of the shopping cart items")
    public int getNumberOfCartItems() {
        log.info("Getting the number of cart items");
        return $$(CART_ITEM_CSS).size();
    }

    @Step("Get the shopping cart items' names")
    public List<String> getCartItemNames() {
        log.info("Getting shopping cart items names");
        return $$(PRODUCT_NAME_CSS).texts();
    }

    @Step("Get a shopping cart item by name: {0}")
    public CartItem getCartItemByName(String itemName) {
        var elem = $$(PRODUCT_NAME_CSS)
                .findBy(Condition.text(itemName))
                .closest(CART_ITEM_CSS);

        return new CartItem(itemName, elem);
    }

    @Step("Click the 'Checkout' button")
    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    @Step("Click the 'Continue shopping' button")
    public void clickContinueShoppingButton() {
        continueShoppingButton.click();
    }

    @Step("Check if the 'Checkout' button is enabled")
    public boolean isCheckoutButtonEnabled() {
        return checkoutButton.isEnabled();
    }
}
