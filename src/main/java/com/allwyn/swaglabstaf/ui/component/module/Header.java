package com.allwyn.swaglabstaf.ui.component.module;

import com.allwyn.swaglabstaf.constant.SortingSelectOption;
import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.allwyn.swaglabstaf.ui.component.pageControl.Link;
import com.allwyn.swaglabstaf.ui.component.pageControl.Select;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
@Component
public class Header extends BaseComponent {

    private static final String HEADER_ID = "header_container";
    private static final String MAIN_MENU_BUTTON_ID = "react-burger-menu-btn";
    private static final String SORTING_SELECT_TAG = "select";
    private static final String CART_LINK_CSS = ".shopping_cart_link";
    private static final String CART_BADGE_CSS = ".shopping_cart_badge";

    private final MainMenu mainMenu;
    private final Button mainMenuButton;
    private final Select sortingSelect;
    private final Link shoppingCartLink;

    public Header(MainMenu mainMenu) {
        name = "Header";
        element = $(byId(HEADER_ID));

        this.mainMenu = mainMenu;
        mainMenuButton = new Button("Main menu", byId(MAIN_MENU_BUTTON_ID));
        sortingSelect = new Select("Sorting", byTagName(SORTING_SELECT_TAG));
        shoppingCartLink = new Link("Shopping cart", byCssSelector(CART_LINK_CSS));
    }

    public MainMenu clickMainMenuButton() {
        mainMenuButton.click();
        return mainMenu;
    }

    public void selectSortingOrder(SortingSelectOption sortingSelectOption) {
        log.info("Sorting products by {}", sortingSelectOption.getName());
        sortingSelect.selectOption(sortingSelectOption.getName());
    }

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
}
