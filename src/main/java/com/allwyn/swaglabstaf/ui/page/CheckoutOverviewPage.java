package com.allwyn.swaglabstaf.ui.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage() {
        pageUrl = "/checkout-step-two.html";
    }
}
