package pageobjects;

import tests.TestBase;
import tests.TestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormRegistrationPage extends TestBase {

    TestData testData = new TestData();

    public PracticeFormRegistrationPage openPage() {
        open(baseUrl);
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    public PracticeFormRegistrationPage fillForm() {
        $("#firstName").setValue(testData.firstName);
        $("#lastName").setValue(testData.lastName);
        $("#userEmail").setValue(testData.userEmail);
        $(byText(testData.gender)).click();
        $("#userNumber").setValue(testData.userNumber);

        setBirthdayDate(testData.birthdayMonth, testData.birthdayYear);

        $("#subjectsInput").setValue(testData.subject).pressTab();
        $(byText(testData.hobbies)).click();
        $("#uploadPicture").uploadFromClasspath("img/" + testData.file);

        setCityState(testData.currentAddress, testData.state, testData.city);

        return this;
    }

    private void setCityState(String currentAddress, String state, String city) {
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
    }

    private void setBirthdayDate(String birthdayMonth, String birthdayYear) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText(birthdayMonth);
        $(".react-datepicker__year-select").selectOptionByValue(birthdayYear);
        $(".react-datepicker__day--001").click();
    }

    public PracticeFormRegistrationPage submitForm() {
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        return this;
    }

    public PracticeFormRegistrationPage assertData() {
        $(".table-responsive").shouldHave(
                text(testData.firstName + " " + testData.lastName),
                text(testData.userEmail),
                text(testData.gender),
                text(testData.birthdayMonth),
                text(testData.birthdayYear),
                text(testData.subject),
                text(testData.hobbies),
                text(testData.file),
                text(testData.currentAddress),
                text(testData.state + " " + testData.city));

        return this;
    }
}
