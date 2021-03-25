package pageobjects;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import com.github.javafaker.Faker;

public class PracticeFormRegistrationPage {

    Faker faker = new Faker();

    String baseUrl = "https://demoqa.com/automation-practice-form";

    String firstName = "Paul",
            lastName = "McCartney",
            userEmail = "paul.mccartney@yahoo.com",
            gender = "Male",
            userNumber = "2173999999",
            subject = "Maths",
            birthdayMonth = "May",
            birthdayYear = "1990",
            hobbies = "Music",
            file = "maxresdefault.jpg",
            currentAddress = "38 Circle Road str., 333 apt.",
            city = "Lucknow",
            state = "Uttar Pradesh";

    public PracticeFormRegistrationPage openPage() {
        open(baseUrl);
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    public PracticeFormRegistrationPage fillForm() {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);

        setBirthdayDate(birthdayMonth, birthdayYear);

        $("#subjectsInput").setValue(subject).pressTab();
        $(byText(hobbies)).click();
        $("#uploadPicture").uploadFromClasspath("img/" + file);

        setCityState(currentAddress, state, city);

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
                text(firstName + " " + lastName),
                text(userEmail),
                text(gender),
                text(birthdayMonth),
                text(birthdayYear),
                text(subject),
                text(hobbies),
                text(file),
                text(currentAddress),
                text(state + " " + city));

        return this;
    }
}
