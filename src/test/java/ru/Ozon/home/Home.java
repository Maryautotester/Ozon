package ru.Ozon.home;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
        public WebDriver driver;
        public WebDriverWait wait;
        public JavascriptExecutor js;

        @Before
        public void SetUp() {
            System.setProperty("webdriver.chrome.driver", "src/ChromeDriver/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--user-data-dir=C:\\Users\\test\\AppData\\Local\\Google\\Chrome\\User Data\\", "--profile-directory=Profile 2");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, 20);
            js = (JavascriptExecutor)driver;
        }
        @Test
        public void home() throws InterruptedException {
            HomePage homePage = PageFactory.initElements(driver, HomePage.class);
            FilterPage filterPage = PageFactory.initElements(driver, FilterPage.class);
            CartPage cartPage = PageFactory.initElements(driver, CartPage.class);

            homePage.open();
            homePage.search();
            filterPage.setAllFilters();
            homePage.changeSorting();
            
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
            
            homePage.chooseVogue();
            homePage.addVogueToCart();

            cartPage.Cart();
        }
        @After
        public void close() {
            driver.quit();
            System.out.println("test finished");
        }
    }

