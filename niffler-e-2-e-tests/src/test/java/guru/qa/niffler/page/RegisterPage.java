package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
  private final SelenideElement usernameInput = $("input#username");
  private final SelenideElement passwordInput = $("input#password");
  private final SelenideElement passwordSubmitInput = $("input#passwordSubmit");
  private final SelenideElement signUpButton = $("button[type='submit']");
  private final SelenideElement signInButton = $("a[class='form_sign-in']");
  public RegisterPage setUsername(String username){
    usernameInput.setValue(username);
    return this;
  }
  public RegisterPage setPassword(String password){
    passwordInput.setValue(password);
    return this;
  }
  public RegisterPage setPasswordSubmit(String password){
    passwordSubmitInput.setValue(password);
    return this;
  }
  public RegisterPage submitRegistration(){
    signUpButton.click();

    return this;
  }

  public LoginPage proceedToLogin(){
    signInButton.click();
    return new LoginPage();
  }

  public void checkErrorMessageIsDisplayed(String errorMessage) {
    $(byText(errorMessage)).should(visible);
  }

  public void checkSignUpButtonIsDisplayed() {
    signUpButton.should(visible);
  }
}