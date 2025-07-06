package com.allwyn.swaglabstaf.ui.component.module;

import com.allwyn.swaglabstaf.constant.SortingSelectOption;
import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.allwyn.swaglabstaf.ui.component.pageControl.Link;
import com.allwyn.swaglabstaf.ui.component.pageControl.Select;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Represents the header section of a web page, typically containing navigation elements
 * like a main menu button, a sorting dropdown, and a shopping cart link with a badge.
 * This class provides methods to interact with these header elements.
 */
@Slf4j
@Component
public class Header extends BaseComponent {

    private static final String HEADER_ID = "header_container";
    private static final String MAIN_MENU_BUTTON_ID = "react-burger-menu-btn";
    private static final String SORTING_SELECT_TAG = "select";
    private static final String CART_LINK_CSS = ".shopping_cart_link";
    private static final String CART_BADGE_CSS = ".shopping_cart_badge";

    private final MainMenu mainMenu; // Assuming MainMenu is another component
    private final Button mainMenuButton;
    private final Select sortingSelect;
    private final Link shoppingCartLink;

    /**
     * Constructs a new {@link Header} component.
     * Initializes the header by locating its main element and setting up references
     * to its sub-components like the main menu button, sorting dropdown, and shopping cart link.
     *
     * @param mainMenu An instance of {@link MainMenu} which is controlled by the main menu button.
     */
    public Header(MainMenu mainMenu) {
        name = "Header";
        element = $(byId(HEADER_ID));

        this.mainMenu = mainMenu;
        mainMenuButton = new Button("Main menu", byId(MAIN_MENU_BUTTON_ID));
        sortingSelect = new Select("Sorting", byTagName(SORTING_SELECT_TAG));
        shoppingCartLink = new Link("Shopping cart", byCssSelector(CART_LINK_CSS));
    }

    /**
     * Clicks the main menu (burger) button in the header.
     * This action typically opens or closes a side navigation menu.
     *
     * @return The {@link MainMenu} component, allowing for further interactions with the menu.
     */
    @Step("Click main menu button")
    public MainMenu clickMainMenuButton() {
        mainMenuButton.click();
        return mainMenu;
    }

    /**
     * Selects a specified sorting option from the sorting dropdown in the header.
     * This changes the order in which items are displayed on the page.
     *
     * @param sortingSelectOption The {@link SortingSelectOption} to be selected (e.g., A_TO_Z, LOW_TO_HIGH).
     */
    @Step("Select sorting order")
    public void selectSortingOrder(SortingSelectOption sortingSelectOption) {
        log.info("Sorting products by {}", sortingSelectOption.getName());
        sortingSelect.selectOption(sortingSelectOption.getName());
    }

    /**
     * Retrieves the integer value displayed on the shopping cart badge.
     * This value typically indicates the number of items currently in the cart.
     *
     * @return The integer value of the shopping cart badge. Returns 0 if the badge is
     * not present or not displayed.
     */
    @Step("Get badge value")
    public int getBadgeValue() {
        log.info("Getting the shopping cart badge value");
        var elem = $(byCssSelector(CART_BADGE_CSS));

        if (elem.exists() && elem.isDisplayed()) {
            var badgeValue = elem.getText();
            return Integer.parseInt(badgeValue);
        } else {
            log.debug("The shopping cart badge either does not exist or is not displayed");
            return 0;
        }
    }

    /**
     * Clicks the shopping cart icon/link in the header.
     * This action typically navigates the user to the shopping cart page.
     */
    @Step("Click the shopping cart link")
    public void clickShoppingCartLink() {
        shoppingCartLink.click();
    }
}