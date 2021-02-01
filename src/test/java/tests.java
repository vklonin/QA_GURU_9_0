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
        $(byText("Male")).click();
        $(byId("userEmail")).setValue("asdf@asdf.ru");
        $(byId("userNumber")).setValue("9876543210");

        $(byId("dateOfBirthInput")).click();
                //.setValue("03 Apr 1970");
        $(byClassName("react-datepicker__month-select")).selectOption(3);
        $(byClassName("react-datepicker__year-select")).selectOptionByValue("1970");
        $(byClassName("react-datepicker__day--022")).click();

        $(byId("subjectsInput")).setValue("Accounting").pressEnter();
        $(byId("subjectsInput")).setValue("Maths").pressTab();

        $(byText("Sports")).click();
        $(byText("Reading")).click();
        $(byText("Music")).click();

        $(byId("uploadPicture")).uploadFile(new File("/Users/vladimirklonin/Desktop/image.png"));

        $(byId("currentAddress")).setValue("Red square Moscow");

        $(byId("react-select-3-input")).setValue("NCR").pressTab();
        $(byId("react-select-4-input")).setValue("Delhi").pressEnter();

        //press button

        $(byId("submit")).click();

        //check result

        ElementsCollection collection = $$x("//table/tbody/tr");

        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[1]/td[2]" ).shouldHave(text(firstName + " " + lastName));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[2]/td[2]" ).shouldHave(text("asdf@asdf.ru"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[3]/td[2]" ).shouldHave(text("Male"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[4]/td[2]" ).shouldHave(text("9876543210"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[5]/td[2]" ).shouldHave(text("22 April,1970"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[6]/td[2]" ).shouldHave(text("Accounting" + ", " + "Maths"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[7]/td[2]" ).shouldHave(text("Sports" + ", " + "Reading"  + ", " + "Music"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[8]/td[2]" ).shouldHave(text("image.png"));


        //$x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[1]/td[2]" ).shouldHave(text(asdf@asdf.ru));


        //$(byId("firstName")).shouldHave(text(firstName));
        //$(byId("firstName")).shouldHave(text(lastName));

    }

}
