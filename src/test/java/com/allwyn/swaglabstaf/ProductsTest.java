package com.allwyn.swaglabstaf;

import com.allwyn.swaglabstaf.constant.SortingSelectOption;
import com.allwyn.swaglabstaf.ui.page.InventoryPage;
import io.qameta.allure.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import static com.allwyn.swaglabstaf.constant.AssertionMessage.*;
import static com.allwyn.swaglabstaf.constant.ProductItemButtonText.ADD_TO_CART;
import static com.allwyn.swaglabstaf.constant.ProductItemButtonText.REMOVE;
import static com.allwyn.swaglabstaf.constant.SortingSelectOption.*;
import static com.allwyn.swaglabstaf.constant.TestGroup.ALL;
import static com.allwyn.swaglabstaf.constant.TestGroup.PRODUCTS;
import static com.allwyn.swaglabstaf.constant.UserName.STANDARD_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@Test(groups = {ALL, PRODUCTS}, testName = "Products Test")
public class ProductsTest extends BaseTest {

    private static final String INVALID_TEXT = "Text contains invalid characters";

    @Autowired
    private InventoryPage inventoryPage;

    private Pattern forbiddenCombinationPattern;

    @BeforeMethod
    public void login() {
        var forbiddenCombination = "^(?!.*\\.[\\s\\S]*?\\(\\)).*$";
        forbiddenCombinationPattern = Pattern.compile(forbiddenCombination);

        loginPage.login(credentials.getUser(STANDARD_USER));
    }

    @Test(description = "Product names Test")
    @Description("Verifies that the product names do not contain any system names/characters")
    public void productNamesTest() {
        var productNames = inventoryPage.getProductsNames();

        validateTexts(productNames);
    }

    @Test(description = "Product descriptions Test")
    @Description("Verifies that the product descriptions do not contain any system names/characters")
    public void productDescriptionTest() {
        var productDescriptions = inventoryPage.getProductsDescriptions();

        validateTexts(productDescriptions);
    }

    @DataProvider
    public Object[][] selectOptionProvider() {
        return new Object[][]  {
                {A_TO_Z, Comparator.naturalOrder()},
                {Z_TO_A, Comparator.reverseOrder()},
                {LOW_TO_HIGH, Comparator.naturalOrder()},
                {HIGH_TO_LOW, Comparator.reverseOrder()}
        };
    }

    @Test(description = "Product sorting Test", dataProvider = "selectOptionProvider")
    @Description("Verifies that the products get sorted according to the selected option in the sorting select")
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> void productSortingTest(SortingSelectOption sortingSelectOption, Comparator<T> comparator) {
        inventoryPage.getHeader().selectSortingOrder(sortingSelectOption);

        var actualProductsOrder = switch (sortingSelectOption) {
            case A_TO_Z, Z_TO_A -> (List<T>) inventoryPage.getProductsNames();
            case HIGH_TO_LOW, LOW_TO_HIGH -> (List<T>) inventoryPage.getProductsPrices();
        };

        var expectedProductsOrder = new ArrayList<>(actualProductsOrder);
        expectedProductsOrder.sort(comparator);

        assertThat(actualProductsOrder)
                .as(ELEM_LIST_INCORRECT)
                .containsExactlyElementsOf(expectedProductsOrder);
    }

    @Test(description = "Cart badge value Test")
    @Description("Verifies that the shopping cart badge correctly displays the number of the products in the cart")
    public void cartBadgeValueTest() {
        // expected value is 1 as we add 2 items to the shopping cart and then remove 1
        var expectedBadgeValue = 1;
        var initialBadgeValue = inventoryPage.getHeader().getBadgeValue();

        var backpackProduct = inventoryPage.getProductByName(BACKPACK_PRODUCT_NAME);
        var onesieProduct = inventoryPage.getProductByName(ONESIE_PRODUCT_NAME);
        backpackProduct.clickAddToCartButton();
        onesieProduct.clickAddToCartButton();
        backpackProduct.clickRemoveButton();

        var badgeValue = inventoryPage.getHeader().getBadgeValue();

        assertThat(badgeValue - initialBadgeValue)
                .as(BADGE_VALUE_INCORRECT)
                .isEqualTo(expectedBadgeValue);
    }

    @Test(description = "Product button Test")
    @Description("Verifies that the product button text changes depending on the product status: " +
            "added/removed to/from the shopping cart")
    public void productButtonTest() {
        var backpackProduct = inventoryPage.getProductByName(BACKPACK_PRODUCT_NAME);
        backpackProduct.clickAddToCartButton();
        var addedProductButtonText = backpackProduct.getButtonText();

        backpackProduct.clickRemoveButton();
        var removedProductButtonText = backpackProduct.getButtonText();

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(addedProductButtonText)
                    .as(BUTTON_TEXT_INCORRECT)
                    .isEqualTo(REMOVE.getText());
            softAssertions.assertThat(removedProductButtonText)
                    .as(BUTTON_TEXT_INCORRECT)
                    .isEqualTo(ADD_TO_CART.getText());
        });
    }

    private void validateTexts(List<String> textsToValidate) {
        assertThat(textsToValidate)
                .as(INVALID_TEXT)
                .allMatch(name -> forbiddenCombinationPattern.matcher(name).matches());
    }
}
