import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
public class SignUpRegisterTest extends BaseTest {
    @Test
    public void registerUserWithValidData(){
        driver.get(BASE_URL +"cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("Irina");
        driver.findElement(By.name("last_name")).sendKeys("Petrova");
        driver.findElement(By.name("email")).sendKeys("ira@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value='Register']")).click();
        boolean confirmMessage = driver.findElement(By.cssSelector("[class='confirmation_message']")).isDisplayed();

        assertTrue(confirmMessage,"Пользователь не перешел на страницу confirmation_message");
    }
    @Test
    public void doNotEnterFirstName(){
        driver.get(BASE_URL +"cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("");
        driver.findElement(By.name("last_name")).sendKeys("Petrova");
        driver.findElement(By.name("email")).sendKeys("ira@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value='Register']")).click();
        WebElement errorMessage = driver.findElement(By.cssSelector("[value=classErrorMessage]"));
        String text = errorMessage.getText();

        assertEquals(text, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Другое сообщение");
    }
    @Test
    public void doNotEnterLastName(){
        driver.get(BASE_URL +"cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("Irina");
        driver.findElement(By.name("last_name")).sendKeys("");
        driver.findElement(By.name("email")).sendKeys("ira@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value='Register']")).click();
        WebElement errorMessage = driver.findElement(By.cssSelector("[value=classErrorMessage]"));
        String text = errorMessage.getText();

        assertEquals(text, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Другое сообщение");
    }
    @Test
    public void doNotEnterEmail(){
        driver.get(BASE_URL +"cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("Irina");
        driver.findElement(By.name("last_name")).sendKeys("Petrova");
        driver.findElement(By.name("email")).sendKeys("");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value='Register']")).click();
        WebElement errorMessage = driver.findElement(By.cssSelector("[value=classErrorMessage]"));
        String text = errorMessage.getText();

        assertEquals(text, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Другое сообщение");
    }
    @Test
    public void doNotEnterPassword1(){
        driver.get(BASE_URL +"cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("Irina");
        driver.findElement(By.name("last_name")).sendKeys("Petrova");
        driver.findElement(By.name("password1")).sendKeys("");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value='Register']")).click();
        WebElement errorMessage = driver.findElement(By.cssSelector("[value=classErrorMessage]"));
        String text = errorMessage.getText();

        assertEquals(text, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Другое сообщение");
    }
    @Test
    public void doNotEnterPassword2(){
        driver.get(BASE_URL +"cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("Irina");
        driver.findElement(By.name("last_name")).sendKeys("Petrova");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("");
        driver.findElement(By.cssSelector("[value='Register']")).click();
        WebElement errorMessage = driver.findElement(By.cssSelector("[value=classErrorMessage]"));
        String text = errorMessage.getText();

        assertEquals(text, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Другое сообщение");
    }
    @Test
    public void doNoFillAnyFields(){
        driver.get(BASE_URL +"cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("");
        driver.findElement(By.name("last_name")).sendKeys("");
        driver.findElement(By.name("email")).sendKeys("");
        driver.findElement(By.name("password1")).sendKeys("");
        driver.findElement(By.name("password2")).sendKeys("");
        driver.findElement(By.cssSelector("[value='Register']")).click();
        WebElement errorMessage = driver.findElement(By.cssSelector("[value=classErrorMessage]"));
        String text = errorMessage.getText();

        assertEquals(text, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Другое сообщение");
    }

    }



