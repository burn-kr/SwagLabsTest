package com.allwyn.swaglabstaf.ui.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$$;

@Slf4j
@Component
public class CartPage extends BasePage {

    private static final String CART_ITEMS_CSS = ".cart_item";

    public int getNumberOfCartItems() {
        log.info("Getting the number of cart items");
        return $$(CART_ITEMS_CSS).size();
    }
}
