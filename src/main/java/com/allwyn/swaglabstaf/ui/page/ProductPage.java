package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.ui.component.module.Header;
import com.allwyn.swaglabstaf.ui.component.pageControl.Text;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selectors.byXpath;

@Slf4j
@Component
public class ProductPage extends BasePage {

    private static final String PRODUCT_NAME_XPATH = "//div[@data-test = 'inventory-item-name']";

    private final Text productNameText;

    @Getter
    private final Header header;

    public ProductPage(Header header) {
        this.header = header;

        productNameText = new Text("Product name", byXpath(PRODUCT_NAME_XPATH));
    }

    public String getProductNameTitle() {
        return productNameText.getText();
    }
}
