package com.allwyn.swaglabstaf.ui.page;

import com.allwyn.swaglabstaf.ui.component.module.CartItem;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.allwyn.swaglabstaf.ui.component.pageControl.Text;
import com.codeborne.selenide.Condition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$$;

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

    public CheckoutOverviewPage() {
        pageUrl = "/checkout-step-two.html";

        itemTotalPriceText = new Text("Item total", byCssSelector(ITEM_TOTAL_CSS));
        taxText = new Text("Tax", byCssSelector(TAX_CSS));
        totalPriceText = new Text("Total price", byCssSelector(TOTAL_PRICE_CSS));
        finishButton = new Button("Finish", byId(FINISH_BUTTON_ID));
        cancelButton = new Button("Cancel", byId(CANCEL_BUTTON_ID));
    }

    public double getItemTotalPriceValue() {
        var itemTotalText = this.itemTotalPriceText.getText();
        return Double.parseDouble(itemTotalText.substring(itemTotalText.indexOf("$") + 1));
    }

    public double getTaxValue() {
        var taxText = this.taxText.getText();
        return Double.parseDouble(taxText.substring(taxText.indexOf("$") + 1));
    }

    public double getTotalPriceValue() {
        var totalPriceText = this.totalPriceText.getText();
        return Double.parseDouble(totalPriceText.substring(totalPriceText.indexOf("$") + 1));
    }

    public void clickFinishButton() {
        finishButton.click();
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

    public List<String> getCheckoutItemsNames() {
        return $$(PRODUCT_NAME_CSS).texts();
    }

    public CartItem getCheckoutItemByName(String itemName) {
        var elem = $$(PRODUCT_NAME_CSS)
                .findBy(Condition.text(itemName))
                .closest(CART_ITEM_CSS);

        return new CartItem(itemName, elem);
    }
}
