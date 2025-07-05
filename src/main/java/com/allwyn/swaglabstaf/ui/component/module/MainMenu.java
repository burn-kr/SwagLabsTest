package com.allwyn.swaglabstaf.ui.component.module;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import com.allwyn.swaglabstaf.ui.component.pageControl.Link;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
@Component
public class MainMenu extends BaseComponent {

    private static final String MAIN_MENU_CSS = ".bm-menu";

    private static final String LOGOUT_LINK_ID = "logout_sidebar_link";

    private final Link logoutLink;

    public MainMenu() {
        element = $(MAIN_MENU_CSS);

        logoutLink = new Link("Logout", byId(LOGOUT_LINK_ID));
    }

    public void clickLogoutLink() {
        logoutLink.click();
    }
}
