package ru.appline.sberbank;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParametrizeTest extends Setup{

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new Object[][]{
                {"Иванов", "Иван", "Иванович", "IVAN IVANOV", "04.06.2000", "email1@email.ru", "(123) 456-78-91"},
                {"Афонина", "Алена", "Петровна", "ALENA PETROVNA", "04.06.1999", "email2@email.ru", "(123) 456-78-92"},
                {"Лысенко", "Олег", "Владимирович", "OLEG LISENKO", "04.06.1998", "email3@email.ru", "(123) 456-78-93"},
        });
    }

    @Parameterized.Parameter(0)
    public String lastName;

    @Parameterized.Parameter(1)
    public String name;

    @Parameterized.Parameter(2)
    public String middleName;

    @Parameterized.Parameter(3)
    public String cardName;

    @Parameterized.Parameter(4)
    public String birthDate;

    @Parameterized.Parameter(5)
    public String email;

    @Parameterized.Parameter(6)
    public String phoneNumber;


    @Test
    public void scenario(){
        driver.findElement(By.xpath("//label[contains(text(), 'Карты')]")).click();
        driver.findElement(By.xpath("//li[@class='kitt-top-menu__item']/a[contains(text(), 'Дебетовые')]")).click();
        assertEquals("Check header 1", driver.findElement(By.xpath("//div[@class='kit-row']/h2")).getText(),
                "Дебетовые карты");
        driver.findElement(By.xpath("//label[text()='Молодежная']")).click();
        driver.findElement(By.xpath("//h3[text()='Молодежная карта']/../../..//b")).click();
        driver.findElement(By.xpath("//h1[text()='Молодёжная карта']"));
        WebElement header = driver.findElement(By.xpath("//h1[text()='Молодёжная карта']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", header);
        assertEquals("Check head 2", header.getText(), "Молодёжная карта");
        driver.findElement(By.xpath("//span[text()='Оформить онлайн']/..")).click();

        WebElement lastNameElement =  driver.findElement(By.xpath("//input[@placeholder='Иванов']"));
        lastNameElement.sendKeys(lastName);
        WebElement nameElement = driver.findElement(By.xpath("//input[@placeholder='Иван']"));
        nameElement.sendKeys(name);
        WebElement middleNameElement = driver.findElement(By.xpath("//input[@placeholder='Иванович']"));
        middleNameElement.sendKeys(middleName);
        WebElement cardNameElement = driver.findElement(By.xpath("//input[@placeholder='IVAN IVANOV']"));
        cardNameElement.clear();
        cardNameElement.sendKeys(cardName);
        WebElement birthDateElement = driver.findElement(By.id("odc-personal__birthDate"));
        birthDateElement.sendKeys(birthDate);
        WebElement emailElement = driver.findElement(By.xpath("//input[@placeholder='example@example.com']"));
        emailElement.sendKeys(email);
        WebElement phoneNumberElement = driver.findElement(By.id("odc-personal__phone"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", phoneNumberElement);
        phoneNumberElement.click();
        phoneNumberElement.sendKeys(phoneNumber);

        assertEquals("Check lastName", lastNameElement.getAttribute("value"), lastName);
        assertEquals("Check name", nameElement.getAttribute("value"), name);
        assertEquals("Check middleName", middleNameElement.getAttribute("value"), middleName );
        assertEquals("Check cardName", cardNameElement.getAttribute("value"), cardName);
        assertEquals("Check birthDate", birthDateElement.getAttribute("value"), birthDate);
        assertEquals("Check Email", emailElement.getAttribute("value"), email);
        assertEquals("Check phoneNumber", phoneNumberElement.getAttribute("value"), "+7 " + phoneNumber);

        driver.findElement(By.xpath("//span[text()='Далее']/..")).click();


        assertEquals("Check Error message for series",
                driver.findElement(By.xpath("//label[text()='Серия']/../div[@class='odcui-error__text']")).getText(),
                "Обязательное поле");
        assertEquals("Check Error message for number",
                driver.findElement(By.xpath("//label[text()='Номер']/../div[@class='odcui-error__text']")).getText(),
                "Обязательное поле");
        assertEquals("Check Error message for date of issue",
                driver.findElement(By.xpath("//label[text()='Дата выдачи']/../div[@class='odcui-error__text']")).getText(),
                "Обязательное поле");
        assertEquals("Check Error message for accept",
                driver.findElement(By.xpath("//div[text()='Я соглашаюсь на']/div[@class='odcui-error__text']")).getText(),
                "Обязательное поле");

    }


}
