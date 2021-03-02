import com.github.javafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class Tests extends TestBase {

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String emailAddress = faker.internet().emailAddress();
    String mPhone = faker.number().digits(10);
    String fullAddress = faker.address().fullAddress();
    String picture = "1.png";

    @Test
    void formFillingTest(){

        //open an address
        step("Open a student registration form", () -> open("https://demoqa.com/automation-practice-form"));

        //fill
        step("Fill a student registration form", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $(byText("Male")).click();
            $("#userEmail").setValue(emailAddress);
            $("#userNumber").setValue(mPhone);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(3); // month -1
            $(".react-datepicker__year-select").selectOptionByValue("1970");
            $(".react-datepicker__day--022").click(); // 3 last digits - a day in a month
            $("#subjectsInput").setValue("Accounting").pressEnter();
            $("#subjectsInput").setValue("Maths").pressTab();
            $(byText("Sports")).click();
            $(byText("Reading")).click();
            $(byText("Music")).click();
            $("#uploadPicture").uploadFromClasspath("img/" + picture);
            $("#currentAddress").setValue(fullAddress);
            $("#react-select-3-input").setValue("NCR").pressTab();
            $("#react-select-4-input").setValue("Delhi").pressEnter();
        });

        //press button
        step("Send data", () -> {
            $("#submit").pressEnter();
        });

        //check result
        step("Check results", () -> {
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[1]/td[2]" ).shouldHave(text(firstName + " " + lastName));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[2]/td[2]" ).shouldHave(text(emailAddress));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[3]/td[2]" ).shouldHave(text("Male"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[4]/td[2]" ).shouldHave(text(mPhone));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[5]/td[2]" ).shouldHave(text("22 April,1970"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[6]/td[2]" ).shouldHave(text("Accounting" + ", " + "Maths"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[7]/td[2]" ).shouldHave(text("Sports" + ", " + "Reading"  + ", " + "Music"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[8]/td[2]" ).shouldHave(text(picture));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[9]/td[2]" ).shouldHave(text(fullAddress));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[10]/td[2]" ).shouldHave(text("NCR" + " " + "Delhi"));
        });
    }

    @Test
    @Tag("negative")
    void negativeFormFillingTest(){

        //open an address
        step("Open a student registration form", () -> open("https://demoqa.com/automation-practice-form"));

        //fill
        step("Fill a student registration form", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $(byText("Male")).click();
            $("#userEmail").setValue(emailAddress);
            $("#userNumber").setValue("=="+mPhone);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(3); // month -1
            $(".react-datepicker__year-select").selectOptionByValue("1970");
            $(".react-datepicker__day--022").click(); // 3 last digits - a day in a month
            $("#subjectsInput").setValue("Accounting").pressEnter();
            $("#subjectsInput").setValue("Maths").pressTab();
            $(byText("Sports")).click();
            $(byText("Reading")).click();
            $(byText("Music")).click();
            $("#uploadPicture").uploadFromClasspath("img/" + picture);
            $("#currentAddress").setValue(fullAddress);
            $("#react-select-3-input").setValue("NCR").pressTab();
            $("#react-select-4-input").setValue("Delhi").pressEnter();
        });

        //press button
        step("Send data", () -> {
            $("#submit").pressEnter();
        });

        //check result
        step("Check results", () -> {
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[1]/td[2]" ).shouldHave(text(firstName + " " + lastName));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[2]/td[2]" ).shouldHave(text(emailAddress));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[3]/td[2]" ).shouldHave(text("Male"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[4]/td[2]" ).shouldHave(text(mPhone));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[5]/td[2]" ).shouldHave(text("22 April,1970"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[6]/td[2]" ).shouldHave(text("Accounting" + ", " + "Maths"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[7]/td[2]" ).shouldHave(text("Sports" + ", " + "Reading"  + ", " + "Music"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[8]/td[2]" ).shouldHave(text(picture));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[9]/td[2]" ).shouldHave(text(fullAddress));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[10]/td[2]" ).shouldHave(text("NCR" + " " + "Delhi"));
        });
    }


}
