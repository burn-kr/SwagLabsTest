package com.allwyn.swaglabstaf.ui.component.module;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import com.allwyn.swaglabstaf.ui.component.pageControl.Link;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Represents the main navigation menu (often referred to as a "burger" menu)
 * typically found in the header of a web application.
 * This component provides methods to interact with various links within the menu,
 * such as "All Items", "About", "Logout", and "Reset App State".
 */
@Slf4j
@Component
public class MainMenu extends BaseComponent {

    private static final String MAIN_MENU_CSS = ".bm-menu";

    private static final String ALL_MENU_ITEMS_CSS = ".bm-item-list>a";
    private static final String ALL_ITEMS_LINK_ID = "inventory_sidebar_link";
    private static final String ABOUT_LINK_ID = "about_sidebar_link";
    private static final String LOGOUT_LINK_ID = "logout_sidebar_link";
    private static final String RESET_LINK_ID = "reset_sidebar_link";
    private static final String CLOSE_MENU_ID = "react-burger-cross-btn";

    private final Link allItemsLink;
    private final Link aboutLink;
    private final Link logoutLink;
    private final Link resetLink;
    private final Button closeMenuButton;

    /**
     * Constructs a new {@link MainMenu} component.
     * Initializes the component by locating the main menu element and setting up
     * references to all its interactive links.
     */
    public MainMenu() {
        element = $(MAIN_MENU_CSS);

        allItemsLink = new Link("All items", byId(ALL_ITEMS_LINK_ID));
        aboutLink = new Link("About", byId(ABOUT_LINK_ID));
        logoutLink = new Link("Logout", byId(LOGOUT_LINK_ID));
        resetLink = new Link("Reset", byId(RESET_LINK_ID));
        closeMenuButton = new Button("Close menu", byId(CLOSE_MENU_ID));
    }

    /**
     * Clicks the "All Items" link within the main menu.
     * This action typically navigates the user to the inventory or products page.
     */
    @Step("Click 'All Items' menu item")
    public void clickAllItemsLink() {
        allItemsLink.click();
    }

    /**
     * Clicks the "About" link within the main menu.
     * This action typically navigates the user to an "About Us" or information page.
     */
    @Step("Click 'About' menu item")
    public void clickAboutLink() {
        aboutLink.click();
    }

    /**
     * Clicks the "Logout" link within the main menu.
     * This action logs out the current user and typically redirects to the login page.
     */
    @Step("Click 'Logout' menu item")
    public void clickLogoutLink() {
        logoutLink.click();
    }

    /**
     * Clicks the "Reset App State" link within the main menu.
     * This action typically clears any saved application state, such as items in the shopping cart.
     *
     * @return The current {@link MainMenu} instance, allowing for method chaining.
     */
    @Step("Click 'Reset' menu item")
    public MainMenu clickResetLink() {
        resetLink.click();
        return this;
    }

    @Step("Click the close menu button")
    public void clickCloseMenuButton() {
        closeMenuButton.click();
    }

    /**
     * Retrieves a list of names for all visible menu items within the main menu.
     *
     * @return A {@link List} of {@link String}s, where each string is the text of a menu item.
     */
    @Step("Get all the menu items' names")
    public List<String> getMenuItemsNames() {
        return $$(ALL_MENU_ITEMS_CSS).texts();
    }
}