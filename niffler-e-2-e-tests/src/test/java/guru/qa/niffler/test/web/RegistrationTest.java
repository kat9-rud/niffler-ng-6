package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;

public class RegistrationTest {
    private static final Config CFG = Config.getInstance();
    @Test
    void newUserShouldBeSuccessfullyRegistered(){
        String username = "sauron";
        String password = "12345";

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .createNewAccount()
                .setUsername(username)
                .setPassword(password)
                .setPasswordSubmit(password)
                .submitRegistration()
                .proceedToLogin()
                .login(username, password)
                .checkMainPageIsCorrectlyDisplayed();
    }

    @Test
    void newUserWithExistingUsernameShouldNotBeRegistered(){
        String username = "galadriel";
        String password = "12345";

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .createNewAccount()
                .setUsername(username)
                .setPassword(password)
                .setPasswordSubmit(password)
                .submitRegistration()
                .proceedToLogin()
                .createNewAccount()
                .setUsername(username)
                .setPassword(password)
                .setPasswordSubmit(password)
                .submitRegistration()
                .checkErrorMessageIsDisplayed("Username `" + username + "` already exists");
    }

    @Test
    void errorMessageShouldBeDisplayedIfPasswordAndConfirmPasswordAreNotIdentical(){
        String username = "harry";
        String password = "12345";

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .createNewAccount()
                .setUsername(username)
                .setPassword(password)
                .setPasswordSubmit("5555")
                .submitRegistration()
                .checkErrorMessageIsDisplayed("Passwords should be equal");
    }

    @Test
    void newUserRegisterShouldFailIfRequiredFieldsAreEmpty(){
        String username = "snape";
        String password = "12345";

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .createNewAccount()
                .setUsername("")
                .setPassword(password)
                .setPasswordSubmit(password)
                .submitRegistration()
                .checkSignUpButtonIsDisplayed();
    }
}