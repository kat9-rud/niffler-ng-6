package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {
  private final SelenideElement usernameInput = $("input#username]");
  private final SelenideElement nameInput = $("input#name]");
  private final SelenideElement saveChangesButton = $("button[type='submit']");
  private final SelenideElement uploadNewPictureButton = $("[for='image__input'] > span");
  private final SelenideElement showArchivedCheckbox = $("[type='checkbox']");
  private final SelenideElement addNewCategoryInput = $("input#category");
  private final ElementsCollection categories = $$("form:has(#category)~div");
  private final SelenideElement editCategoryInput = $("input[placeholder='Edit category']");
  private final SelenideElement closeEditCategoryButton = $("button[aria-label='close']");

  public ProfilePage setName(String name){
    nameInput.setValue(name);
    return this;
  }
  public ProfilePage saveChanges(){
    saveChangesButton.click();
    return this;
  }
  public ProfilePage enableShowArchived(){
    if (showArchivedCheckbox.is(not(checked)))
      showArchivedCheckbox.click();

    return this;
  }

  public ProfilePage disableShowArchived(){
    if (showArchivedCheckbox.is(checked))
      showArchivedCheckbox.click();

    return this;
  }

  public ProfilePage addNewCategory(String category){
    addNewCategoryInput.setValue(category);
    addNewCategoryInput.pressEnter();
    return this;
  }

  public ProfilePage editCategory(String category){
    categories.find(text(category)).$("button[aria-label='Edit category']").click();
    return this;
  }

  public ProfilePage setNewCategoryName(String newName){
    editCategoryInput.setValue(newName);
    return this;
  }

  public ProfilePage archiveCategory(String category){
    categories.find(text(category)).$("button[aria-label='Archive category']").click();
    $(byText("Archive")).click();
    return this;
  }

  public ProfilePage unarchiveCategory(String category){
    categories.find(text(category)).$("button[aria-label='Unarchive category']").click();
    $(byText("Unarchive")).click();
    return this;
  }

  public ProfilePage uploadNewPicture(String file){

    return this;
  }

  public void checkThatCategoryPresentInCategoriesList(String name) {
  }
}
