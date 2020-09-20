package ru.appline.sberbank;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParametrizeTest extends Setup{



    @Test
    public void scenario() throws InterruptedException {
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
        birthDate.sendKeys("04061991");
        WebElement email = driver.findElement(By.xpath("//input[@placeholder='example@example.com']"));
        email.sendKeys("ya@ya.ru");
        WebElement phoneNumber = driver.findElement(By.id("odc-personal__phone"));

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", phoneNumber);
        phoneNumber.click();
        phoneNumber.sendKeys("1234567890");

        Thread.sleep(5000);


    }


}
