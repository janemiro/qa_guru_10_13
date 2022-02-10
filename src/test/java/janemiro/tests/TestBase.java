package janemiro.tests;

import com.codeborne.selenide.Configuration;
import janemiro.config.Project;
import janemiro.helpers.AllureAttachments;
import janemiro.helpers.DriverSettings;
import janemiro.helpers.DriverUtils;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith({AllureJunit5.class})
public class TestBase {

    String URL = "https://www.petshop.ru";
    String item = "поводок";
    String itemName = "Комо";
    String phone = "9160000000";

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DriverSettings.configure();

        Configuration.startMaximized = true;
    }


    @AfterEach
    public void addAttachments() {
        String sessionId = DriverUtils.getSessionId();

        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
//        AllureAttachments.attachNetwork(); // todo
        AllureAttachments.addBrowserConsoleLogs();

        Selenide.closeWebDriver();

        if (Project.isVideoOn()) {
            AllureAttachments.addVideo(sessionId);
        }
    }
}
