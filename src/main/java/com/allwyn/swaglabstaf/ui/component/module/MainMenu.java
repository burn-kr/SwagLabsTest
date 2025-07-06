package com.allwyn.swaglabstaf.ui.component.module;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import com.allwyn.swaglabstaf.ui.component.pageControl.Link;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
@Component
public class MainMenu extends BaseComponent {

    private static final String MAIN_MENU_CSS = ".bm-menu";

    private static final String ALL_MENU_ITEMS_CSS = ".bm-item-list>a";
    private static final String ALL_ITEMS_LINK_ID = "inventory_sidebar_link";
    private static final String ABOUT_LINK_ID = "about_sidebar_link";
    private static final String LOGOUT_LINK_ID = "logout_sidebar_link";
    private static final String RESET_LINK_ID = "reset_sidebar_link";

    private final Link allItemsLink;
    private final Link aboutLink;
    private final Link logoutLink;
    private final Link resetLink;

    public MainMenu() {
        element = $(MAIN_MENU_CSS);

        allItemsLink = new Link("All items", byId(ALL_ITEMS_LINK_ID));
        aboutLink = new Link("About", byId(ABOUT_LINK_ID));
        logoutLink = new Link("Logout", byId(LOGOUT_LINK_ID));
        resetLink = new Link("Reset", byId(RESET_LINK_ID));
    }

    @Step("Click 'All Items' menu item")
    public void clickAllItemsLink() {
        allItemsLink.click();
    }

    @Step("Click 'About' menu item")
    public void clickAboutLink() {
        aboutLink.click();
    }

    @Step("Click 'Logout' menu item")
    public void clickLogoutLink() {
        logoutLink.click();
    }

    @Step("Click 'Reset' menu item")
    public MainMenu clickResetLink() {
        resetLink.click();
        return this;
    }

    @Step("Get all the menu items' names")
    public List<String> getMenuItemsNames() {
        return $$(ALL_MENU_ITEMS_CSS).texts();
    }
}
