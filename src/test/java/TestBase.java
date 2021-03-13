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

        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.startMaximized = true;
        //a string if I need it "https://user1:1234@selenoid.autotests.cloud:4444/wd/hub"
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud:4444/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        //bot qa_guru_9_0 https://t.me/qa_guru_9_0_bot 1680109489:AAHh4xdHrFDcaEi0IMOoGP1tCcgH8UoRGn8
        //chat id -1001424584591 - for telegram

    }

    @AfterEach
    public void afterEach(){
        attachScreenshot("last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();
    }

}