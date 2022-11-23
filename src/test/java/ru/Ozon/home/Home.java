package ru.Ozon.HomeOzon;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
    public class FirstTest {
        public WebDriver driver;
        public WebDriverWait wait;

        @Before
        public void SetUp() {
            System.setProperty("webdriver.chrome.driver", "src/ChromeDriver/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--user-data-dir=C:\\Users\\test\\AppData\\Local\\Google\\Chrome\\User Data\\", "--profile-directory=Profile 2");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, 20);
        }
        @Test
        public void home() throws InterruptedException {
            HomePage homePage = PageFactory.initElements(driver, HomePage.class);

            homePage.open();
            homePage.search();
            homePage.select();

            WebElement filters = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button//..//span[contains(text(), 'Все фильтры')]")));
            WebElement allFilters = driver.findElement(By.xpath("//button//..//span[contains(text(), 'Все фильтры')]"));
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -60);", allFilters);
            allFilters.click();

            WebElement applyBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button//span[contains(text(), 'Применить')]")));
            WebElement colour = driver.findElement(By.xpath("//span[contains(text(), 'Цвет')]//..//..//.."));
            colour.click();

            WebElement expandList = driver.findElement(By.xpath("//span[contains(text(), 'Цвет')]//..//..//..//..//span[contains(text(), 'Посмотреть все')]"));
            expandList.click();

            WebElement checkboxColour = driver.findElement(By.xpath("//div[@style='background-color: rgb(40, 122, 65);']//..//..//..//div"));
            checkboxColour.click();

            WebElement colourBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button//span[contains(text(), 'Цвет: зеленый')]")));
            colour.click();

            WebElement genderlist = driver.findElement(By.xpath("//div[@class = 'tsBodyLBold']//span[contains(text(), 'Пол')]"));
            genderlist.click();
            WebElement gender = driver.findElement(By.xpath("//span[contains(text(), 'Пол')]//..//..//..//..//span[contains(text(), 'Женский')]"));
            gender.click();
            genderlist.click();
            WebElement genderBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button//span[contains(text(), 'Пол: Женский')]")));

            WebElement priceList = driver.findElement(By.xpath("//span[contains(text(), 'Цена')]"));
            priceList.click();
            WebElement priceMin = priceList.findElement(By.xpath("//span[contains(text(), 'Цена')]//..//..//..//..//p[contains(text(), 'от')]//..//input"));
            priceMin.click();
            priceMin.sendKeys(Keys.CONTROL, "a");
            priceMin.sendKeys("2000");
            WebElement priceMax = priceList.findElement(By.xpath("//span[contains(text(), 'Цена')]//..//..//..//..//p[contains(text(), 'до')]//..//input"));
            priceMax.click();
            priceMax.sendKeys(Keys.CONTROL, "a");
            priceMax.sendKeys("4000");
            priceList.click();

            applyBtn.click();

            WebElement combo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Популярные')]")));
            WebElement comboboxSelect = driver.findElement(By.xpath("//div[@role = 'listbox']//div//div//input"));
            comboboxSelect.click();
            comboboxSelect.sendKeys(Keys.DOWN, Keys.DOWN, Keys.ENTER);

            int i = 0;
            do {
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                WebElement next = driver.findElement(By.xpath("//div[contains(text(), 'Дальше')]"));
                js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", next);
                new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Дальше')]")));
                next.click();
                i++;
                System.out.println(i);
                if (driver.findElements(By.xpath("//span[contains(text(), 'Зонт Vogue')]")).size() > 0) {
                    break;
                }
            } while (i < 11);

            WebElement umbrellaVogue = driver.findElement(By.xpath("//span[contains(text(), 'Зонт Vogue')]"));
            umbrellaVogue.click();

            WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button//span[contains(text(), 'Добавить в корзину')]")));
            WebElement addTo = driver.findElement(By.xpath("//button//span[contains(text(), 'Добавить в корзину')]//..//..//..//..//..//..//.."));

            addTo.isDisplayed();
            addTo.click();

            WebElement basket = driver.findElement(By.xpath("//div//a[@href = \"/cart\"]"));
            basket.click();

            WebElement weightElement = driver.findElement(By.xpath("//span[contains(text(), 'Ваша корзина')]//..//span[2]"));
            WebElement priceElement = driver.findElement(By.xpath("//span[contains(text(), 'Ваша корзина')]//..//..//div[4]//span[2]"));

            String weight = weightElement.getText();
            String price = priceElement.getText();
            WebElement qTY = driver.findElement(By.xpath("//div[@role = 'listbox']//div//div//input"));
            qTY.click();
            qTY.sendKeys(Keys.DOWN, Keys.ENTER);

            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Ваша корзина')]//..//span[2]")));
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
        @After
        public void close() {
            driver.quit();
            System.out.println("test finished");
        }
    }

}
