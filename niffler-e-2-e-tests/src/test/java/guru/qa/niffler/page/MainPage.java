package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
  private final ElementsCollection tableRows = $("#spendings tbody").$$("tr");
  private final SelenideElement menuButton = $("button[aria-label='Menu']");
  private final ElementsCollection menuItems = $$("#account-menu li");

  public EditSpendingPage editSpending(String spendingDescription) {
    tableRows.find(text(spendingDescription)).$$("td").get(5).click();
    return new EditSpendingPage();
  }

  public void checkThatTableContainsSpending(String spendingDescription) {
    tableRows.find(text(spendingDescription)).should(visible);
  }

  public ProfilePage openProfile(){
    menuButton.click();
    menuItems.find(text("Profile")).$("a").click();
    return new ProfilePage();
  }

  public void checkPageIsCorrectlyDisplayed() {
    $(byText("Statistics")).should(visible);
    $(byText("History of Spendings")).should(visible);
  }

  public void checkPageIsNotDisplayed() {
    $(byText("Statistics")).shouldNot(exist);
    $(byText("History of Spendings")).shouldNot(exist);
  }
}
