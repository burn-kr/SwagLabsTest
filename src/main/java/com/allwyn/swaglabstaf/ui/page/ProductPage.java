package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.ui.component.module.Header;
import com.allwyn.swaglabstaf.ui.component.pageControl.Text;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selectors.byXpath;

/**
 * Represents the Product Details page, which displays detailed information about a single product.
 * Users can typically view the product's name, description, price, and interact with it (e.g., add to cart).
 */
@Slf4j
@Component
public class ProductPage extends BasePage {

    private static final String PRODUCT_NAME_XPATH = "//div[@data-test = 'inventory-item-name']";

    private final Text productNameText;

    @Getter
    private final Header header;

    /**
     * Constructs a new {@link ProductPage} instance.
     * Initializes the page by setting up the product name text element and associating
     * the page with the common header component.
     *
     * @param header The {@link Header} component instance associated with this page.
     */
    public ProductPage(Header header) {
        this.header = header;

        productNameText = new Text("Product name", byXpath(PRODUCT_NAME_XPATH));
    }

    /**
     * Retrieves the title (name) of the product displayed on the product details page.
     *
     * @return A {@link String} containing the product's name.
     */
    public String getProductNameTitle() {
        return productNameText.getText();
    }
}