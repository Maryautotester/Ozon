package ru.Ozon.home;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
        js = (JavascriptExecutor)driver;
    }
    @FindBy(xpath = "//*[@placeholder='Искать на Ozon']")
    private WebElement product;
    @FindBy(xpath = "//div//a[contains(@class, 'suggestions-item type-history tsBodyL')]")
    private WebElement selectItem;
    @FindBy(xpath = "//span[contains(text(), 'Зонт Vogue')]")
    private WebElement umbrellaVogue;
    @FindBy(xpath = "//button//span[contains(text(), 'Добавить в корзину')]//..//..//..//..//..//..//..")
    private WebElement addTo;
    @FindBy(xpath = "//div[@role = 'listbox']//div//div//input")
    private WebElement comboboxSelect;

    public void open() {
        driver.get("https://www.ozon.ru/");
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(true, title.equals("OZON — интернет-магазин. Миллионы товаров по выгодным ценам"));
    }
    public void search() {
        product.sendKeys("зонт");
        product.sendKeys(Keys.DOWN);
        selectItem.isSelected();
        selectItem.click();
    }
    public void changeSorting() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Популярные')]")));
        comboboxSelect.click();
        comboboxSelect.sendKeys(Keys.DOWN, Keys.DOWN, Keys.ENTER);
    }

    public void chooseVogue() {
        umbrellaVogue.click();
    }
    public void addVogueToCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button//span[contains(text(), 'Добавить в корзину')]")));
        addTo.isDisplayed();
        addTo.click();
    }
}
