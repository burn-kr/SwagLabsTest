package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.ui.component.module.InventoryItem;
import com.codeborne.selenide.Condition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$$;

@Slf4j
@Component
public class InventoryPage extends BasePage {

    public InventoryPage() {

        pageUrl = "/inventory.html";
    }

    public InventoryItem getProductByName(String itemName) {
        var elem = $$(".inventory_item_name")
                .findBy(Condition.text(itemName))
                .closest(".inventory_item");

        return new InventoryItem(itemName, elem);
    }
}
