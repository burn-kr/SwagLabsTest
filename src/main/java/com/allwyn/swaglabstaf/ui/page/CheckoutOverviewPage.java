package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.allwyn.swaglabstaf.ui.component.pageControl.Text;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Represents the "Checkout: Overview" page, which is the second step in the checkout process.
 * This page typically summarizes the order details, including item prices, tax, and total,
 * before final confirmation.
 */
@Slf4j
@Component
public class CheckoutOverviewPage extends BasePage {

    private static final String ITEM_TOTAL_CSS = ".summary_subtotal_label";
    private static final String TAX_CSS = ".summary_tax_label";
    private static final String TOTAL_PRICE_CSS = ".summary_total_label";
    private static final String FINISH_BUTTON_ID = "finish";
    private static final String CANCEL_BUTTON_ID = "cancel";

    private final Text itemTotalPriceText;
    private final Text taxText;
    private final Text totalPriceText;
    private final Button finishButton;
    private final Button cancelButton;

    /**
     * Constructs a new {@link CheckoutOverviewPage} instance.
     * Sets the specific URL path for the overview page and initializes
     * page control elements such as price summaries and action buttons.
     */
    public CheckoutOverviewPage() {
        pageUrl = "/checkout-step-two.html";

        itemTotalPriceText = new Text("Item total", byCssSelector(ITEM_TOTAL_CSS));
        taxText = new Text("Tax", byCssSelector(TAX_CSS));
        totalPriceText = new Text("Total price", byCssSelector(TOTAL_PRICE_CSS));
        finishButton = new Button("Finish", byId(FINISH_BUTTON_ID));
        cancelButton = new Button("Cancel", byId(CANCEL_BUTTON_ID));
    }

    /**
     * Retrieves the numerical value of the item total price displayed on the page.
     * The method parses the price from a string that typically includes a currency symbol.
     *
     * @return A {@code double} representing the total price of items before tax.
     */
    @Step("Get the item total price value")
    public double getItemTotalPriceValue() {
        var itemTotalText = this.itemTotalPriceText.getText();
        return Double.parseDouble(itemTotalText.substring(itemTotalText.indexOf("$") + 1));
    }

    /**
     * Retrieves the numerical value of the tax displayed on the page.
     * The method parses the tax amount from a string that typically includes a currency symbol.
     *
     * @return A {@code double} representing the tax amount.
     */
    @Step("Get the tax value")
    public double getTaxValue() {
        var taxText = this.taxText.getText();
        return Double.parseDouble(taxText.substring(taxText.indexOf("$") + 1));
    }

    /**
     * Retrieves the numerical value of the grand total price (items + tax) displayed on the page.
     * The method parses the total from a string that typically includes a currency symbol.
     *
     * @return A {@code double} representing the final total price.
     */
    @Step("Get the total price value")
    public double getTotalPriceValue() {
        var totalPriceText = this.totalPriceText.getText();
        return Double.parseDouble(totalPriceText.substring(totalPriceText.indexOf("$") + 1));
    }

    /**
     * Clicks the "Finish" button on the checkout overview page.
     * This action typically confirms the order and completes the checkout process.
     */
    @Step("Click the 'Finish' button")
    public void clickFinishButton() {
        finishButton.click();
    }

    /**
     * Clicks the "Cancel" button on the checkout overview page.
     * This action typically aborts the checkout process and might return the user to the cart or products page.
     */
    @Step("Click the 'Cancel' button")
    public void clickCancelButton() {
        cancelButton.click();
    }

    /**
     * Retrieves a list of names for all products listed in the checkout overview.
     * This is useful for verifying the items being purchased.
     *
     * @return A {@link List} of {@link String}s, where each string is the name of a product.
     */
    @Step("Get the checkout items' na,es")
    public List<String> getCheckoutItemsNames() {
        return $$(PRODUCT_NAME_CSS).texts();
    }
}