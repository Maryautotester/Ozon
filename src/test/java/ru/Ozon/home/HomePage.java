package ru.Ozon.HomeOzon;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    @FindBy(xpath = "//*[@placeholder='Искать на Ozon']")
    private WebElement product;
    @FindBy(xpath = "//div//a[contains(@class, 'suggestions-item type-history tsBodyL')]")
    private WebElement selectItem;
    public void open() {
        driver.get("https://www.ozon.ru/");
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(true, title.equals("OZON — интернет-магазин. Миллионы товаров по выгодным ценам"));
    }
    public void search() {
        product.sendKeys("зонт");
        product.sendKeys(Keys.DOWN);
    }
    public void select() {
        WebElement selectItem = driver.findElement(By.xpath("]"));
        selectItem.isSelected();
        selectItem.click();
    }
}
