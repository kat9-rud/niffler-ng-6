package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import guru.qa.niffler.page.MainPage;
import org.junit.jupiter.api.Test;

public class LoginWebTest {
    private static final Config CFG = Config.getInstance();
    @Test
    void mainPageShouldBeDisplayedAfterSuccessfulLogin(){
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "1234")
                .checkMainPageIsCorrectlyDisplayed();
    }

    @Test
    void userShouldStayOnLoginPageAfterLoginWithBadCredentials(){
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("111", "111")
                .checkMainPageIsNotDisplayed();
    }
}
