package com.allwyn.swaglabstaf;

import com.allwyn.swaglabstaf.ui.page.CartPage;
import com.allwyn.swaglabstaf.ui.page.InventoryPage;
import com.allwyn.swaglabstaf.ui.page.ProductPage;
import io.qameta.allure.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.allwyn.swaglabstaf.constant.AssertionMessage.*;
import static com.allwyn.swaglabstaf.constant.ProductItemButtonText.ADD_TO_CART;
import static com.allwyn.swaglabstaf.constant.TestGroup.ALL;
import static com.allwyn.swaglabstaf.constant.TestGroup.BURGER;
import static com.allwyn.swaglabstaf.constant.UserName.STANDARD_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@Test(groups = {ALL, BURGER}, testName = "Burger menu Test")
public class BurgerMenuTest extends BaseTest {

    private static final String ABOUT_URL = "https://saucelabs.com/";

    @Autowired
    private InventoryPage inventoryPage;

    @Autowired
    private ProductPage productPage;

    @Autowired
    private CartPage cartPage;

    @BeforeMethod
    public void login() {
        loginPage.login(credentials.getUser(STANDARD_USER));
    }

    @Test(description = "Menu items Test")
    @Description("Verifies that the list of menu items of 4 predefined elements: " +
            "All Items, About, Logout, Reset App State")
    public void menuItemsTest() {
        var expectedMenuItemsNames = List.of("All Items", "About", "Logout", "Reset App State");
        var actualMainMenuItems = inventoryPage
                .getHeader()
                .clickMainMenuButton()
                .getMenuItemsNames();

        assertThat(actualMainMenuItems)
                .as(ELEM_LIST_INCORRECT)
                .containsExactlyElementsOf(expectedMenuItemsNames);
    }

    @Test(description = "'All Items' menu item Test")
    @Description("Verifies that the 'All Items' menu item takes the user to the Products page")
    public void allItemsMenuItemTest() {
        inventoryPage
                .getProductByName(BACKPACK_PRODUCT_NAME)
                .click();

        productPage
                .getHeader()
                .clickMainMenuButton()
                .clickAllItemsLink();

        validatePageUrl(inventoryPage.getPageUrl());
        validatePageTitle(inventoryPage.getPageTitle(), PRODUCTS_PAGE_TITLE);
    }

    @Test(description = "'About' menu item Test")
    @Description("Verifies that the 'About' menu item takes the user to the SauceLabs homepage")
    public void aboutMenuItemTest() {
        inventoryPage
                .getHeader()
                .clickMainMenuButton()
                .clickAboutLink();

        validatePageUrl(ABOUT_URL);
    }

    @Test(description = "'Logout' menu item Test")
    @Description("Verifies that the 'Logout' menu item logs out the user and takes him to the Login page")
    public void logoutMenuItemTest() {
        inventoryPage
                .getHeader()
                .clickMainMenuButton()
                .clickLogoutLink();

        validatePageUrl(loginPage.getPageUrl());
    }

    // TODO: clarify if the menu should be closed after clicking the 'Reset App State' link
    @Test(description = "'Reset App State' menu item Test")
    @Description("Verifies that the 'Reset App State' menu item removes all the products from the cart")
    public void resetMenuItemTest() {
        // 'Reset App State' should remove everything from the checkout
        var expectedNumberOfItemsToCheckout = 0;

        var backpackProduct = inventoryPage.getProductByName(BACKPACK_PRODUCT_NAME);
        var onesieProduct = inventoryPage.getProductByName(ONESIE_PRODUCT_NAME);

        backpackProduct.clickAddToCartButton();
        onesieProduct.clickAddToCartButton();

        inventoryPage
                .getHeader()
                .clickMainMenuButton()
                .clickResetLink()
                .clickCloseMenuButton();
        var cartBadgeValue = inventoryPage
                .getHeader()
                .getBadgeValue();
        var backpackProductButtonText = backpackProduct.getButtonText();
        var onesieProductButtonText = onesieProduct.getButtonText();

        inventoryPage
                .getHeader()
                .clickShoppingCartLink();
        var numberOfCheckoutCartItems = cartPage.getNumberOfCartItems();

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(cartBadgeValue)
                    .as(BADGE_VALUE_INCORRECT)
                    .isEqualTo(expectedNumberOfItemsToCheckout);
            softAssertions.assertThat(backpackProductButtonText)
                    .as(BUTTON_TEXT_INCORRECT)
                    .isEqualTo(ADD_TO_CART.getText());
            softAssertions.assertThat(onesieProductButtonText)
                    .as(BUTTON_TEXT_INCORRECT)
                    .isEqualTo(ADD_TO_CART.getText());
            softAssertions.assertThat(numberOfCheckoutCartItems)
                    .as(CART_ITEMS_NUMBER_INCORRECT)
                    .isEqualTo(expectedNumberOfItemsToCheckout);
        });
    }
}
