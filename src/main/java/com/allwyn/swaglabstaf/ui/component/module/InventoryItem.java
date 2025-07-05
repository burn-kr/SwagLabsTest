package com.allwyn.swaglabstaf.ui.component.module;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.allwyn.swaglabstaf.ui.component.pageControl.Text;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selectors.*;

@Slf4j
public class InventoryItem extends BaseComponent {

    private static final String PRICE_CSS = ".inventory_item_price";
    private static final String NAME_CSS = ".inventory_item_name";
    private static final String BUTTON_TAG = "button";

    private final Text priceText;
    private final Text nameText;
    private final Button addButton;
    private final Button removeButton;

    public InventoryItem(String name, SelenideElement element) {
        this.name = name;
        this.element = element;

        priceText = new Text("Price", element.$(byCssSelector(PRICE_CSS)));
        nameText = new Text("Product name", element.$(byCssSelector(NAME_CSS)));
        addButton = new Button("Add to cart", element.$(byTagName(BUTTON_TAG)));
        removeButton = new Button("Remove", element.$(byTagName(BUTTON_TAG)));
    }

    public String getPrice() {
        return priceText.getText();
    }

    public String getName() {
        return nameText.getText();
    }

    public void clickAddToCartButton() {
        addButton.click();
    }

    public void clickRemoveButton() {
        removeButton.click();
    }
}
