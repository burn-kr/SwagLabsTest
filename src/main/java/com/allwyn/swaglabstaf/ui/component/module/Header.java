package com.allwyn.swaglabstaf.ui.component.module;

import com.allwyn.swaglabstaf.ui.component.BaseComponent;
import com.allwyn.swaglabstaf.ui.component.pageControl.Button;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
@Component
public class Header extends BaseComponent {

    private static final String HEADER_ID = "header_container";
    private static final String MAIN_MENU_BUTTON_ID = "react-burger-menu-btn";

    private final MainMenu mainMenu;
    private final Button mainMenuButton;

    public Header(MainMenu mainMenu) {
        name = "Header";
        element = $(byId(HEADER_ID));

        this.mainMenu = mainMenu;
        mainMenuButton = new Button("Main menu", byId(MAIN_MENU_BUTTON_ID));
    }

    public MainMenu clickMainMenuButton() {
        mainMenuButton.click();
        return mainMenu;
    }
}
