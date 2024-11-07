package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.Category;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;

public class ProfileTest {
    private static final Config CFG = Config.getInstance();

    @Test
    @Category(
            username = "duck",
            archived = true
    )
    void archivedCategoryShouldPresentInCategoriesList(CategoryJson category) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "1234")
                .openProfile()
                .enableShowArchived()
                .checkThatCategoryPresentInCategoriesList(category.name())
                .unarchiveCategory(category.name())
                .checkThatCategoryPresentInCategoriesList(category.name());
    }
    @Test
    @Category(
            username = "duck",
            archived = false
    )
    void activeCategoryShouldPresentInCategoriesList(CategoryJson category) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "1234")
                .openProfile()
                .enableShowArchived()
                .checkThatCategoryPresentInCategoriesList(category.name())
                .archiveCategory(category.name())
                .checkThatCategoryPresentInCategoriesList(category.name());
    }

    @Test
    void userShouldBeAbleToUploadNewPicture(){
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "1234")
                .openProfile()
                .uploadNewPicture();
    }
}