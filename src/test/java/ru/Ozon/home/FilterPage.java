package ru.Ozon.home;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    public FilterPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
        js = (JavascriptExecutor)driver;
    }
    @FindBy(xpath = "//button//..//span[contains(text(), 'Все фильтры')]")
    private WebElement allFilters;
    @FindBy(xpath = "//span[contains(text(), 'Цвет')]//..//..//..")
    private WebElement colour;
    @FindBy(xpath = "//span[contains(text(), 'Цвет')]//..//..//..//..//span[contains(text(), 'Посмотреть все')]")
    private WebElement expandList;
    @FindBy(xpath = "//div[@style='background-color: rgb(40, 122, 65);']//..//..//..//div")
    private WebElement checkboxColour;
    @FindBy(xpath = "//div[@class = 'tsBodyLBold']//span[contains(text(), 'Пол')]")
    private WebElement genderlist;
    @FindBy(xpath = "//span[contains(text(), 'Пол')]//..//..//..//..//span[contains(text(), 'Женский')]")
    private WebElement gender;
    @FindBy(xpath = "//span[contains(text(), 'Цена')]")
    private WebElement priceList;
    @FindBy(xpath = "//span[contains(text(), 'Цена')]//..//..//..//..//p[contains(text(), 'от')]//..//input")
    private WebElement priceMin;
    @FindBy(xpath = "//span[contains(text(), 'Цена')]//..//..//..//..//p[contains(text(), 'до')]//..//input")
    private WebElement priceMax;
    @FindBy(xpath = "//button//span[contains(text(), 'Применить')]")
    private WebElement applyBtn;

    public void setAllFilters() {
        WebElement filters = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button//..//span[contains(text(), 'Все фильтры')]")));
        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -60);", allFilters);
        allFilters.click();
        WebElement applyBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button//span[contains(text(), 'Применить')]")));

        colour.click();
        expandList.click();
        checkboxColour.click();
        WebElement colourBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button//span[contains(text(), 'Цвет: зеленый')]")));
        colour.click();
        genderlist.click();
        gender.click();
        genderlist.click();
        WebElement genderBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button//span[contains(text(), 'Пол: Женский')]")));
        priceList.click();
        priceMin.click();
        priceMin.sendKeys(Keys.CONTROL, "a");
        priceMin.sendKeys("2000");

        priceMax.click();
        priceMax.sendKeys(Keys.CONTROL, "a");
        priceMax.sendKeys("4000");
        priceList.click();
        applyBtn.click();
    }
}
