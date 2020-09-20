package ru.appline.sberbank;


import org.junit.Test;
import org.openqa.selenium.*;

import static org.junit.Assert.assertEquals;



public class SberbankMainTest extends Setup {



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

        WebElement lastName =  driver.findElement(By.xpath("//input[@placeholder='Иванов']"));
        lastName.sendKeys("Фамилия");
        WebElement name = driver.findElement(By.xpath("//input[@placeholder='Иван']"));
        name.sendKeys("Имя");
        WebElement middleName = driver.findElement(By.xpath("//input[@placeholder='Иванович']"));
        middleName.sendKeys("Отчество");
        WebElement cardName = driver.findElement(By.xpath("//input[@placeholder='IVAN IVANOV']"));
        cardName.clear();
        cardName.sendKeys("IMYA FAMILIYA");
        WebElement birthDate = driver.findElement(By.id("odc-personal__birthDate"));
        birthDate.sendKeys("04.06.2000");
        WebElement email = driver.findElement(By.xpath("//input[@placeholder='example@example.com']"));
        email.sendKeys("ya@ya.ru");
        WebElement phoneNumber = driver.findElement(By.id("odc-personal__phone"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", phoneNumber);
        phoneNumber.click();
        phoneNumber.sendKeys("(123) 456-78-90");

        assertEquals("Check lastName", lastName.getAttribute("value"), "Фамилия");
        assertEquals("Check name", name.getAttribute("value"), "Имя");
        assertEquals("Check middleName", middleName.getAttribute("value"),"Отчество" );
        assertEquals("Check cardName", cardName.getAttribute("value"), "IMYA FAMILIYA");
        assertEquals("Check birthDate", birthDate.getAttribute("value"), "04.06.2000");
        assertEquals("Check Email", email.getAttribute("value"), "ya@ya.ru");
        assertEquals("Check phoneNumber", phoneNumber.getAttribute("value"), "+7 (123) 456-78-90");

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
