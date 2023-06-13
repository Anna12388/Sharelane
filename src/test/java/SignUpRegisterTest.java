import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SignUpRegisterTest extends BaseTest {
    @Test
    public void registerUserWithValidData(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("Irina");
        driver.findElement(By.name("last_name")).sendKeys("Petrova");
        driver.findElement(By.name("email")).sendKeys("ira@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value='Register']")).click();
        boolean confirmMessage = driver.findElement(By.cssSelector("[class='confirmation_message']")).isDisplayed();

        assertTrue(confirmMessage,"Пользователь не перешел на страницу confirmation_message");
    }
}
