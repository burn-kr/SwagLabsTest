package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.ui.component.module.CartItem;
import com.allwyn.swaglabstaf.ui.component.module.Header;
import com.allwyn.swaglabstaf.ui.component.module.InventoryItem;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.codeborne.selenide.Condition;
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

    public int getNumberOfCartItems() {
        log.info("Getting the number of cart items");
        return $$(CART_ITEM_CSS).size();
    }

    public List<String> getCartItemNames() {
        log.info("Getting shopping cart items names");
        return $$(PRODUCT_NAME_CSS).texts();
    }

    public CartItem getCartItemByName(String itemName) {
        var elem = $$(PRODUCT_NAME_CSS)
                .findBy(Condition.text(itemName))
                .closest(CART_ITEM_CSS);

        return new CartItem(itemName, elem);
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public void clickContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public boolean isCheckoutButtonEnabled() {
        return checkoutButton.isEnabled();
    }
}
