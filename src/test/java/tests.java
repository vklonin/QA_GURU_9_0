import com.codeborne.selenide.Command;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class tests {

    String firstName = "Vladimir";
    String lastName = "Lenin";
    String gender = "gender-radio-1";


    @Test
    void formFillingTest(){
        //open address

        open("https://demoqa.com/automation-practice-form");

        //check is it a right page

        //fill form

        SelenideElement firstName1 = $(byId("firstName")).setValue(firstName);
        SelenideElement lastName1 = $(byId("lastName")).setValue(lastName);
        //SelenideElement selenideElement1 = $(byId(gender)).selectRadio("Male");
        $(byText("Male")).click();
        //SelenideElement selenideElement1 = $(byName("gender")).selectRadio("Male");
        $(byId("userEmail")).setValue("asdf@asdf.ru");
        $(byId("userNumber")).setValue("9876543210");

        $(byId("dateOfBirthInput")).click();
                //.setValue("22 Apr 1970");
        $(byClassName("react-datepicker__month-select")).selectOption(3);
        $(byClassName("react-datepicker__year-select")).selectOptionByValue("1970");
        $(byClassName("react-datepicker__day--003")).click();

        //SelenideElement autoComplete = $(byClassName("subjects-auto-complete__value-container"));
        //autoComplete.click();
        //autoComplete.sendKeys("a");

        $(byId("subjectsInput")).setValue("Accounting").pressEnter();
        $(byId("subjectsInput")).setValue("Maths").pressTab();

        $(byText("Sports")).click();
        $(byText("Reading")).click();
        $(byText("Music")).click();

        $(byId("uploadPicture")).uploadFile(new File("/Users/vladimirklonin/Desktop/image.png"));

        $(byId("currentAddress")).setValue("Red square Moscow");

        $(byId("react-select-3-input")).setValue("NCR").pressTab();
        $(byId("react-select-4-input")).setValue("Delhi").pressEnter();

        $(byId("submit")).click();

        //hobby1.toWebElement().click();
        //$(byId("hobbies-checkbox-2")).click();





        //subjectsInput


        //.setValue("Accounting");

        //subjects-auto-complete__value-container subjects-auto-complete__value-container--is-multi css-1hwfws3

                //.setValue("2");

        //$(byId(gender)).click();

        //press button

        //check result

        ElementsCollection collection = $$x("//table/tbody/tr");

        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[1]/td[2]" ).shouldHave(text(firstName));
        //$x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[1]/td[2]" ).shouldHave(text(asdf@asdf.ru));


        //$(byId("firstName")).shouldHave(text(firstName));
        //$(byId("firstName")).shouldHave(text(lastName));

    }

}
