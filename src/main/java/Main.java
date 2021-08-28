import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.wildberries.ru/catalog/16747008/detail.aspx");

        WebElement addToCart = driver.findElement(By.xpath("//button[@data-link=\"class{merge: !showAddToBasketBtn()" +
                " toggle='hide'}{on $adult.proceedIfAdultConfirmed productCard.adult addToBasket #data}\"]"));
        WebElement moveToCart = driver.findElement(By.xpath("//a[@href='/lk/basket']"));

        String countPlus = "//button[@class=\"count__plus plus\"]";
        addToCart.click();
        moveToCart.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(countPlus)));
        driver.findElement(By.xpath(countPlus)).click();
        Thread.sleep(2000);
        WebElement quantity = driver.findElement(By.xpath("//span[@class = \"navbar-pc__notify\"]"));

        if (quantity.getText().equals("2")) { //Замена assertTrue
            System.out.println("Тест успешно завершен. Количество товаров - 2");
        } else {
            throw new Exception("Количество товаров не совпало. Ожидаемый результат - 2, фактический - "
                    + quantity.getText());
        }

        Thread.sleep(5000);
        driver.close();
    }
}