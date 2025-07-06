package com.allwyn.swaglabstaf;

import com.allwyn.swaglabstaf.ui.page.CartPage;
import com.allwyn.swaglabstaf.ui.page.InventoryPage;
import io.qameta.allure.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.allwyn.swaglabstaf.constant.AssertionMessage.*;
import static com.allwyn.swaglabstaf.constant.ProductItemButtonText.REMOVE;
import static com.allwyn.swaglabstaf.constant.TestGroup.ALL;
import static com.allwyn.swaglabstaf.constant.TestGroup.CART;
import static com.allwyn.swaglabstaf.constant.UserName.STANDARD_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@Test(groups = {ALL, CART}, testName = "Checkout cart Test")
public class CheckoutCartTest extends BaseTest {

    private static final String CART_PAGE_TITLE = "Your Cart";

    @Autowired
    private InventoryPage inventoryPage;

    @Autowired
    private CartPage cartPage;

    @BeforeMethod
    public void login() {
        loginPage.login(credentials.getUser(STANDARD_USER));
    }

    @Test(description = "Cart page navigate Test")
    @Description("Verifies that the cart icon click leads to the Cart page")
    public void cartPageNavigateTest() {
        inventoryPage
                .getHeader()
                .clickShoppingCartLink();

        validatePageUrl(cartPage.getPageUrl());
        assertThat(cartPage.getPageTitle())
                .as(PAGE_TITLE_INCORRECT)
                .isEqualTo(CART_PAGE_TITLE);
    }

    @Test(description = "Add to cart Test")
    @Description("Verifies that a user can add products to the checkout card")
    public void addToCartTest() {
        var backpackProduct = inventoryPage.getProductByName(BACKPACK_PRODUCT_NAME);
        var onesieProduct = inventoryPage.getProductByName(ONESIE_PRODUCT_NAME);

        backpackProduct.clickAddToCartButton();
        onesieProduct.clickAddToCartButton();

        inventoryPage
                .getHeader()
                .clickShoppingCartLink();

        var cartItemsNames = cartPage.getCartItemNames();

        assertThat(cartItemsNames)
                .as(ELEM_LIST_INCORRECT)
                .containsExactly(BACKPACK_PRODUCT_NAME, ONESIE_PRODUCT_NAME);
    }

    @Test(description = "Remove from cart Test")
    @Description("Verifies that a product can be successfully remove from the shopping cart")
    public void removeFromCartTest() {
        // first we add two products and then remove one
        var expectedCartBadgeValue = 1;

        var backpackProduct = inventoryPage.getProductByName(BACKPACK_PRODUCT_NAME);
        var onesieProduct = inventoryPage.getProductByName(ONESIE_PRODUCT_NAME);

        backpackProduct.clickAddToCartButton();
        onesieProduct.clickAddToCartButton();

        inventoryPage
                .getHeader()
                .clickShoppingCartLink();
        cartPage
                .getCartItemByName(ONESIE_PRODUCT_NAME)
                .clickRemoveButton();

        var cartItemsNames = cartPage.getCartItemNames();
        var cartBadgeValue = inventoryPage
                .getHeader()
                .getBadgeValue();

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(cartItemsNames)
                    .as(ELEM_LIST_INCORRECT)
                    .containsExactly(BACKPACK_PRODUCT_NAME);
            softAssertions.assertThat(cartBadgeValue)
                    .as(BADGE_VALUE_INCORRECT)
                    .isEqualTo(expectedCartBadgeValue);
        });
    }

    @Test(description = "Empty cart checkout button Test")
    @Description("Verifies that the Checkout button should be disabled in case there are no items in the shopping cart")
    public void emptyCartCheckoutButtonTest() {
        inventoryPage
                .getHeader()
                .clickShoppingCartLink();

        var checkoutButtonState = cartPage.isCheckoutButtonEnabled();

        assertThat(checkoutButtonState)
                .as(BUTTON_STATE_INCORRECT)
                .isFalse();
    }

    @Test(description = "'Continue shopping' button test")
    @Description("Verifies that when a user clicks the 'Continue shopping' button " +
            "it takes him back to the Products page and keeps the added products in the shopping cart")
    public void continueShoppingButtonTest() {
        // we add only one product to the cart
        var expectedCartBadgeValue = 1;

        var backpackProduct = inventoryPage.getProductByName(BACKPACK_PRODUCT_NAME);
        backpackProduct.clickAddToCartButton();

        inventoryPage
                .getHeader()
                .clickShoppingCartLink();
        cartPage.clickContinueShoppingButton();

        var cartBadgeValue = inventoryPage
                .getHeader()
                .getBadgeValue();
        var backpackProductButtonText = backpackProduct.getButtonText();

        validatePageUrl(inventoryPage.getPageUrl());
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(cartBadgeValue)
                    .as(BADGE_VALUE_INCORRECT)
                    .isEqualTo(expectedCartBadgeValue);
            softAssertions.assertThat(backpackProductButtonText)
                    .as(BUTTON_TEXT_INCORRECT)
                    .isEqualTo(REMOVE.getText());
        });
    }
}
