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

@Slf4j
@Component
public class InventoryPage extends BasePage {


    private static final String PRODUCT_CARD_CSS = ".inventory_item";
    private static final String PRODUCT_DESCRIPTION_CSS = ".inventory_item_desc";
    private static final String PRODUCT_PRICE_CSS = ".inventory_item_price";

    @Getter
    private final Header header;

    public InventoryPage(Header header) {
        pageUrl = "/inventory.html";
        this.header = header;
    }

    @Step("Open the inventory page by URL")
    public void open() {
        log.info("Opening the Products page");
        Selenide.open(pageUrl);
    }

    @Step("Get the product by it's name: {0}")
    public InventoryItem getProductByName(String itemName) {
        var elem = $$(PRODUCT_NAME_CSS)
                .findBy(Condition.text(itemName))
                .closest(PRODUCT_CARD_CSS);

        return new InventoryItem(itemName, elem);
    }

    @Step("Get all the products' names")
    public List<String> getProductsNames() {
        log.info("Getting products names");
        return $$(PRODUCT_NAME_CSS).texts();
    }

    @Step("Get all the products' decriptions")
    public List<String> getProductsDescriptions() {
        log.info("Getting products descriptions");
        return $$(PRODUCT_DESCRIPTION_CSS).texts();
    }

    @Step("Get all the products' prices")
    public List<Double> getProductsPrices() {
        log.info("Getting products prices");
        return $$(PRODUCT_PRICE_CSS).texts()
                .stream()
                .map(price -> price.substring(1))
                .map(Double::parseDouble)
                .toList();
    }
}
