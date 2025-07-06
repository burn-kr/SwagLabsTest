package com.allwyn.swaglabstaf;

import com.allwyn.swaglabstaf.ui.page.*;
import io.qameta.allure.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.allwyn.swaglabstaf.constant.AssertionMessage.TEXT_INCORRECT;
import static com.allwyn.swaglabstaf.constant.TestGroup.ALL;
import static com.allwyn.swaglabstaf.constant.TestGroup.CHECKOUT_FINISH;
import static com.allwyn.swaglabstaf.constant.UserName.STANDARD_USER;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@Test(groups = {ALL, CHECKOUT_FINISH}, testName = "Checkout finish Test")
public class CheckoutFinishTest extends BaseTest {

    private static final String GREEN_MARK_NOT_DISPLAYED =
            "Expected the success green mark to be displayed but it was not";
    private static final String MESSAGE_TITLE = "Thank you for your order!";
    private static final String MESSAGE_CONTENT =
            "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

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
        checkoutOverviewPage.clickFinishButton();
    }

    @Test(description = "Finish page Test")
    @Description("Verifies that the information regarding successful order is displayed")
    public void finishPageTest() {
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(finishPage.isGreenCheckmarkDisplayed())
                    .as(GREEN_MARK_NOT_DISPLAYED)
                    .isTrue();
            softAssertions.assertThat(finishPage.getMessageTitleText())
                    .as(TEXT_INCORRECT)
                    .isEqualTo(MESSAGE_TITLE);
            softAssertions.assertThat(finishPage.getMessageContentText())
                    .as(TEXT_INCORRECT)
                    .isEqualTo(MESSAGE_CONTENT);
        });
    }

    @Test(description = "'Back Home' button Test")
    @Description("Verifies that the 'Back Home' button takes the user to the Products page")
    public void backHomeButtonTest() {
        finishPage.clickBackHomeButton();

        validatePageUrl(inventoryPage.getPageUrl());
    }
}
