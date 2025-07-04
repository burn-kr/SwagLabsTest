package com.allwyn.swaglabstaf.config.env;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.allwyn.swaglabstaf.constant.AppConstant.*;

@Slf4j
public class CustomProvider implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        var isHeadless = Boolean.parseBoolean(System.getenv(BROWSER_HEADLESS));
        var secondaryScreenPosition =
                Optional.ofNullable(System.getenv(BROWSER_SECONDARY_SCREEN)).map(String::toUpperCase).orElse(null);
        var chromeUserDataDir = Optional.ofNullable(System.getenv(CHROME_USER_DATA_DIR)).map(File::new).orElse(null);

        String downloadFilepath = SystemUtils.IS_OS_WINDOWS ? "%Temp%" : "/tmp";
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);

        log.info("Setting up the local Chrome driver");

        WebDriverManager.chromedriver().capabilities(capabilities);
        WebDriverManager.chromedriver().setup();

        var browserSizeParam = isHeadless ? "--window-size=1920,1080" : "--start-maximized";
        var options = new ChromeOptions().setAcceptInsecureCerts(true)
                .addArguments(browserSizeParam)
                .addArguments("--remote-allow-origins=*")
                .addArguments("--no-sandbox")
                .addArguments("--disable-dev-shm-usage")
                .setExperimentalOption("prefs", chromePrefs);

        if (isHeadless) {
            options.addArguments("--headless");
        }

        if (secondaryScreenPosition != null) {
            var offset = "LEFT".equals(secondaryScreenPosition) ? -2000 : 2000;
            options.addArguments(String.format("--window-position=%d,0", offset));
        }

        if (System.getProperty("os.name").startsWith("Mac OS")) {
            options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        }

        if (chromeUserDataDir != null) {
            chromeUserDataDir.mkdir();
            options.addArguments(String.format("user-data-dir=%s", chromeUserDataDir.getAbsolutePath()));
        }

        ChromeDriver chromeDriver = new ChromeDriver((ChromeOptions) options);

        if (!isHeadless) {
            chromeDriver.manage().window().maximize();
        }

        log.info("Local web driver was successfully set");
        return chromeDriver;
    }
}
