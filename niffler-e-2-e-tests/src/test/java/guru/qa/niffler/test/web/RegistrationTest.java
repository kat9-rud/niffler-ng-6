package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.annotation.meta.WebTest;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@WebTest
public class RegistrationTest {
    private static final Config CFG = Config.getInstance();
    private static final Faker faker = new Faker();

    @Test
    void newUserShouldBeSuccessfullyRegistered(){
        String newUsername = faker.name().username();
        String password = "12345";

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .createNewAccount()
                .setUsername(newUsername)
                .setPassword(password)
                .setPasswordSubmit(password)
                .submitRegistration()
                .proceedToLogin()
                .login(newUsername, password)
                .checkPageIsCorrectlyDisplayed();
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
        String newUsername = faker.name().username();
        String password = "12345";

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .createNewAccount()
                .setUsername(newUsername)
                .setPassword(password)
                .setPasswordSubmit("5555")
                .submitRegistration()
                .checkErrorMessageIsDisplayed("Passwords should be equal");
    }

    @Test
    void newUserRegisterShouldFailIfRequiredFieldsAreEmpty(){
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