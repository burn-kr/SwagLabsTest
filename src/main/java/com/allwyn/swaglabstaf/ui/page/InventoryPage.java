package com.allwyn.swaglabstaf.ui.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryPage extends BasePage {

    public InventoryPage() {
        pageUrl = "/inventory.html";
    }
}
