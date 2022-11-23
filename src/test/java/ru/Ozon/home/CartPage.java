package ru.Ozon.home;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }
    @FindBy(xpath = "//div//a[@href = \"/cart\"]")
    private WebElement basket;
    @FindBy(xpath = "//span[contains(text(), 'Ваша корзина')]//..//span[2]")
    private WebElement weightElement;
    @FindBy(xpath = "//span[contains(text(), 'Ваша корзина')]//..//..//div[4]//span[2]")
    private WebElement priceElement;
    @FindBy(xpath = "//div[@role = 'listbox']//div//div//input")
    private WebElement qTY;
    public void Cart() throws InterruptedException {
        basket.click();

        String weight = weightElement.getText();
        String price = priceElement.getText();

        qTY.click();
        qTY.sendKeys(Keys.DOWN, Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Ваша корзина')]//..//span[2]")));
        Thread.sleep(3000);

        int expectedWeight = Integer.parseInt(weight.substring(10, 13)) * 2;
        int expectedPrice = Integer.parseInt(price.substring(0, 5).replace(" ", "")) * 2;

        String updatedWeight = weightElement.getText();
        String updatedPrice = priceElement.getText();
        int actualWeight = Integer.parseInt(updatedWeight.substring(11, 14).replace(" ", ""));
        int actualPrice = Integer.parseInt(updatedPrice.substring(0, 5).replace(" ", ""));

        Assert.assertEquals(expectedWeight, actualWeight);
        Assert.assertEquals(expectedPrice, actualPrice);
        Assert.assertEquals(2, Integer.parseInt(driver.findElement(By.xpath("//div[@role = 'listbox']//div//div//div")).getText()));
    }


}
