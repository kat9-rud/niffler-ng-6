package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FriendsPage {
  private final SelenideElement friendsTab = $$(".MuiTab-root").findBy(text("Friends"));
  private final SelenideElement allPeopleTab = $$(".MuiTab-root").findBy(text("All people"));
  private final SelenideElement searchBox = $("div input");
  private final ElementsCollection allPeopleOutcomeRequestsItems = $$("#all tr");
  private final ElementsCollection friendsIncomeRequestsItems = $$("#requests tr");
  private final ElementsCollection friendsItems = $$("#friends tr");


  public FriendsPage openFriendsTab(){
    friendsTab.click();
    friendsTab.shouldHave(attribute("aria-selected", "true"));

    return this;
  }

  public FriendsPage openAllPeopleTab(){
    allPeopleTab.click();
    allPeopleTab.shouldHave(attribute("aria-selected", "true"));

    return this;
  }

  public void checkThatFriendIsPresentInFriendsTable(String friend) {
    friendsItems.findBy(text(friend)).should(exist, Duration.ofSeconds(5));
  }

  public void checkThatFriendsTableIsEmpty() {
    friendsItems.shouldHave(size(0));
  }

  public void checkThatIncomeInvitationIsPresentInFriendsTable(String income) {
    friendsIncomeRequestsItems.findBy(text(income)).should(exist, Duration.ofSeconds(5));
  }

  public void checkThatOutcomeInvitationIsPresentInAllPeopleTable(String outcome) {
    allPeopleOutcomeRequestsItems.findBy(text(outcome)).should(exist, Duration.ofSeconds(5));
  }
}
