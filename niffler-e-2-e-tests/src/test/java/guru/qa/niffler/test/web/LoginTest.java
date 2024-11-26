package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.annotation.meta.WebTest;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;

import static guru.qa.niffler.utils.RandomDataUtils.randomUsername;

@WebTest
public class LoginTest {
    private static final Config CFG = Config.getInstance();

    @Test
    void mainPageShouldBeDisplayedAfterSuccessfulLogin(){
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "1234")
                .checkPageIsCorrectlyDisplayed();
    }

    @Test
    void userShouldStayOnLoginPageAfterLoginWithBadCredentials(){
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login(randomUsername(), "BAD")
                .checkPageIsNotDisplayed();
    }
}