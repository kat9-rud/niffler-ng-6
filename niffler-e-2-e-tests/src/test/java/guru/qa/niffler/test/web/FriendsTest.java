package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.extension.BrowserExtension;
import guru.qa.niffler.jupiter.extension.UsersQueueExtension;
import guru.qa.niffler.jupiter.extension.UsersQueueExtension.UserType;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static guru.qa.niffler.jupiter.extension.UsersQueueExtension.StaticUser;
import static guru.qa.niffler.jupiter.extension.UsersQueueExtension.UserType.Type.*;

@ExtendWith(BrowserExtension.class)
public class FriendsTest {
  private static final Config CFG = Config.getInstance();

  @Test
  @ExtendWith(UsersQueueExtension.class)
  void friendShouldBePresentInFriendsTable(@UserType(WITH_FRIEND) StaticUser user) {
    Selenide.open(CFG.frontUrl(), LoginPage.class)
            .login(user.username(), user.password())
            .openFriends()
            .checkThatFriendIsPresentInFriendsTable(user.friend());
  }

  @Test
  @ExtendWith(UsersQueueExtension.class)
  void friendsTableShouldBeEmptyForNewUser(@UserType(EMPTY) StaticUser user) {
    Selenide.open(CFG.frontUrl(), LoginPage.class)
            .login(user.username(), user.password())
            .openFriends()
            .checkThatFriendsTableIsEmpty();

  }

  @Test
  @ExtendWith(UsersQueueExtension.class)
  void incomeInvitationShouldBePresentInFriendsTable(@UserType(WITH_INCOME_REQUEST) StaticUser user) {
    Selenide.open(CFG.frontUrl(), LoginPage.class)
            .login(user.username(), user.password())
            .openFriends()
            .checkThatIncomeInvitationIsPresentInFriendsTable(user.income());
  }

  @Test
  @ExtendWith(UsersQueueExtension.class)
  void outcomeInvitationShouldBePresentInAllPeopleTable(@UserType(WITH_OUTCOME_REQUEST) StaticUser user) {
    Selenide.open(CFG.frontUrl(), LoginPage.class)
            .login(user.username(), user.password())
            .openFriends()
            .openAllPeopleTab()
            .checkThatOutcomeInvitationIsPresentInAllPeopleTable(user.outcome());
  }
}