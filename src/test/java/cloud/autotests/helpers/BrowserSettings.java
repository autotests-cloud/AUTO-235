package cloud.autotests.helpers;

import cloud.autotests.config.Project;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class BrowserSettings {

    public static void configureDriver() {
        Configuration.browser = Project.config.browser();
        Configuration.browserVersion = Project.config.browserVersion();
        Configuration.browserSize = Project.config.browserSize();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (DriverHelper.isWebMobile()) { // for chrome only
            ChromeOptions chromeOptions = new ChromeOptions();
            Map<String, Object> mobileDevice = new HashMap<>();
            mobileDevice.put("deviceName", Project.config.browserMobileView());
            chromeOptions.setExperimentalOption("mobileEmulation", mobileDevice);
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }

        if (DriverHelper.isRemoteWebDriver()) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.remote = DriverHelper.getWebRemoteDriver();
        }

        Configuration.browserCapabilities = capabilities;
    }
}
