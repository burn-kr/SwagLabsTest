package com.allwyn.swaglabstaf;

import com.allwyn.swaglabstaf.ui.page.*;
import io.qameta.allure.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.allwyn.swaglabstaf.constant.AssertionMessage.BADGE_VALUE_INCORRECT;
import static com.allwyn.swaglabstaf.constant.AssertionMessage.ELEM_LIST_INCORRECT;
import static com.allwyn.swaglabstaf.constant.TestGroup.ALL;
import static com.allwyn.swaglabstaf.constant.TestGroup.CHECKOUT_OVERVIEW;
import static com.allwyn.swaglabstaf.constant.UserName.STANDARD_USER;
import static org.assertj.core.api.Assertions.assertThat;

@Test(groups = {ALL, CHECKOUT_OVERVIEW}, testName = "Checkout overview Test")
public class CheckoutOverviewTest extends BaseTest {

    private static final String TOTAL_PRICE_INCORRECT = "Total price value is not as expected";

    private static final String FINISH_PAGE_TITLE = "Checkout: Complete!";

    @Autowired
    private InventoryPage inventoryPage;

    @Autowired
    private CartPage cartPage;

    @Autowired
    private CheckoutPage checkoutPage;

    @Autowired
    private CheckoutOverviewPage checkoutOverviewPage;

    @Autowired
    private FinishPage finishPage;

    @BeforeMethod
    public void testSetUp() {
        loginPage.login(credentials.getUser(STANDARD_USER));
        inventoryPage
                .getProductByName(BACKPACK_PRODUCT_NAME)
                .clickAddToCartButton();
        inventoryPage
                .getHeader()
                .clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage
                .inputFirstName(faker.name().firstName())
                .inputFLastName(faker.name().lastName())
                .inputPostalCode(faker.address().zipCode())
                .clickContinueButton();
    }

    @Test(description = "Checkout Test")
    @Description("Verifies that the finish")
    public void checkoutTest() {
        // checkout should clear the cart
        var expectedBadgeValue = 0;
        checkoutOverviewPage.clickFinishButton();

        var cartBadgeValue = cartPage
                .getHeader()
                .getBadgeValue();

        validatePageUrl(finishPage.getPageUrl());
        validatePageTitle(finishPage.getPageTitle(), FINISH_PAGE_TITLE);
        assertThat(cartBadgeValue)
                .as(BADGE_VALUE_INCORRECT)
                .isEqualTo(expectedBadgeValue);
    }

    @Test(description = "Checkout overview items Test")
    @Description("Verifies that the Checkout overview page contains the added products")
    public void checkoutOverviewItemsTest() {
        var checkoutItemsNames = checkoutOverviewPage.getCheckoutItemsNames();

        assertThat(checkoutItemsNames)
                .as(ELEM_LIST_INCORRECT)
                .containsExactly(BACKPACK_PRODUCT_NAME);
    }

    @Test(description = "Cancel checkout Test")
    @Description("Verifies that the Cancel button leads to the previous page and keeps the added products in the cart")
    public void cancelCheckoutTest() {
        // we added one product
        var expectedBadgeValue = 1;
        checkoutOverviewPage.clickCancelButton();

        var cartBadgeValue = cartPage
                .getHeader()
                .getBadgeValue();

        validatePageUrl(checkoutPage.getPageUrl());
        validatePageTitle(checkoutPage.getPageTitle(), CHECKOUT_PAGE_TITLE);
        assertThat(cartBadgeValue)
                .as(BADGE_VALUE_INCORRECT)
                .isEqualTo(expectedBadgeValue);
    }

    @Test(description = "Total price Test")
    @Description("Verifies that the total price is calculated correctly")
    public void totalPriceTest() {
        var itemTotal = checkoutOverviewPage.getItemTotalPriceValue();
        var tax = checkoutOverviewPage.getTaxValue();
        var expectedTotalPrice = itemTotal + tax;

        var actualTotalPrice = checkoutOverviewPage.getTotalPriceValue();

        assertThat(actualTotalPrice)
                .as(TOTAL_PRICE_INCORRECT)
                .isEqualTo(expectedTotalPrice);
    }
}
