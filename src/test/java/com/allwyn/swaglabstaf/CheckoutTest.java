package com.allwyn.swaglabstaf;

import com.allwyn.swaglabstaf.ui.page.CartPage;
import com.allwyn.swaglabstaf.ui.page.CheckoutOverviewPage;
import com.allwyn.swaglabstaf.ui.page.CheckoutPage;
import com.allwyn.swaglabstaf.ui.page.InventoryPage;
import io.qameta.allure.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.allwyn.swaglabstaf.constant.AssertionMessage.BADGE_VALUE_INCORRECT;
import static com.allwyn.swaglabstaf.constant.TestGroup.ALL;
import static com.allwyn.swaglabstaf.constant.TestGroup.CHECKOUT;
import static com.allwyn.swaglabstaf.constant.UserName.STANDARD_USER;
import static org.assertj.core.api.Assertions.assertThat;

@Test(groups = {ALL, CHECKOUT}, testName = "Checkout Test")
public class CheckoutTest extends BaseTest {

    private static final String CHECKOUT_OVERVIEW_TITLE = "Checkout: Overview";

    @Autowired
    private InventoryPage inventoryPage;

    @Autowired
    private CartPage cartPage;

    @Autowired
    private CheckoutPage checkoutPage;

    @Autowired
    private CheckoutOverviewPage checkoutOverviewPage;

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
    }

    @Test(description = "Checkout Test")
    @Description("Verifies that the Checkout button click leads to the Checkout Overview page")
    public void checkoutTest() {
        checkoutPage
                .inputFirstName(faker.name().firstName())
                .inputFLastName(faker.name().lastName())
                .inputPostalCode(faker.address().zipCode())
                .clickContinueButton();

        validatePageUrl(checkoutOverviewPage.getPageUrl());
        validatePageTitle(checkoutOverviewPage.getPageTitle(), CHECKOUT_OVERVIEW_TITLE);
    }

    @Test(description = "Cancel button Test")
    @Description("Verifies that the Cancel button leads to the previous page and keeps the added products in the cart")
    public void cancelButtonTest() {
        // we added only one product
        var expectedBadgeValue = 1;

        checkoutPage.clickCancelButton();

        var cartBadgeValue = cartPage
                .getHeader()
                .getBadgeValue();

        validatePageUrl(cartPage.getPageUrl());
        validatePageTitle(cartPage.getPageTitle(), CART_PAGE_TITLE);
        assertThat(cartBadgeValue)
                .as(BADGE_VALUE_INCORRECT)
                .isEqualTo(expectedBadgeValue);
    }

    @DataProvider
    public Object[][] userInfoProvider() {
        return new Object[][] {
                {"", faker.name().lastName(), faker.address().zipCode(), "Error: First Name is required"},
                {faker.name().firstName(), "", faker.address().zipCode(), "Error: Last Name is required"},
                {faker.name().firstName(), faker.name().lastName(), "", "Error: Postal Code is required"},
                // TODO: clarify what kind of error should be displayed
                {faker.name().firstName(), faker.name().lastName(), "invalid",
                        "Error: Postal Code should consist of number only"},
        };
    }

    @Test(description = "Missing user information Test", dataProvider = "userInfoProvider")
    @Description("Verifies that the user receives an error in case of any user information field is blank or invalid")
    public void missingUserInfoTest(String firstName, String lastName, String zipCode, String expectedErrorMessage) {
        checkoutPage
                .inputFirstName(firstName)
                .inputFLastName(lastName)
                .inputPostalCode(zipCode)
                .clickContinueButton();

        validateError(checkoutPage.getErrorMessageContainer().getErrorMessage(), expectedErrorMessage);
    }
}
