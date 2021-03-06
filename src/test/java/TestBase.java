import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;

public class TestBase {

    @BeforeAll
    static void setup() {

        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        String address = System.getProperty("address", "selenoid.autotests.cloud");

        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.startMaximized = true;
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud:4444/wd/hub";
        Configuration.remote = "https://user1:1234@"+address+":4444/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;




    }

    @AfterEach
    public void afterEach(){
        attachScreenshot("last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();
    }

}