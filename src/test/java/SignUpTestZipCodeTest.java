import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;


public class SignUpTestZipCodeTest extends BaseTest {
    String zipCodeInputLocator = "zip_code";
    String continueButtonLocator = "[value=Continue]";
    String errorMessageLocator = "[class=error_message]";

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

    }

    @Test
    public void zipCodeShouldAccept5Digits() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name(zipCodeInputLocator)).sendKeys("12345");
        driver.findElement(By.cssSelector(continueButtonLocator)).click();
        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();

        assertTrue(isDisplayed, "Нужная страница не открылась");
    }

    @Test
    public void enter4DigitsInTheZip_codeField() {
        driver.get(BASE_URL + "cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name(zipCodeInputLocator));
        zipCodeInput.sendKeys("1234");
        WebElement continueButton = driver.findElement(By.cssSelector(continueButtonLocator));
        continueButton.click();
        WebElement errorMessage = driver.findElement(By.cssSelector("[value=classErrorMessage]"));
        String text = errorMessage.getText();

        assertEquals(text, "Oops, error on page. ZIP code should have 5 digits", "Сообщение не отображается");
    }

    @Test
    public void leaveZipCodeFieldEmpty(){
        driver.get(BASE_URL + "cgi-bin/register.py");
        driver.findElement(By.cssSelector(continueButtonLocator)).click();
        String text = driver.findElement(By.cssSelector(errorMessageLocator)).getText();

        assertEquals(text, "Oops, error on page. ZIP code should have 5 digits","Другой текст");
    }

    @Test
    public void zipCodeShouldNotAccept6Digits(){
        driver.get(BASE_URL + "cgi-bin/register.py");
        driver.findElement(By.name(zipCodeInputLocator)).sendKeys("123456");
        driver.findElement(By.cssSelector(continueButtonLocator)).click();
        String text = driver.findElement(By.cssSelector(errorMessageLocator)).getText();

        assertEquals(text, "Oops, error on page. ZIP code should have 5 digits","Сообщение не отображается");

    }
    @Test
    public void zipCodeShouldNotAcceptLess5Digits(){
        driver.get(BASE_URL + "cgi-bin/register.py");
        driver.findElement(By.name(zipCodeInputLocator)).sendKeys("333");
        driver.findElement(By.cssSelector(continueButtonLocator)).click();
        String text = driver.findElement(By.cssSelector(errorMessageLocator)).getText();

        assertEquals(text, "Oops, error on page. ZIP code should have 5 digits", "Другое сообщение");
    }
    @Test
    public void zipCodeShouldNotAcceptLetters(){
        driver.get(BASE_URL + "cgi-bin/register.py");
        driver.findElement(By.name(zipCodeInputLocator)).sendKeys("ghdks");
        driver.findElement(By.cssSelector(continueButtonLocator)).click();
        String text = driver.findElement(By.cssSelector(errorMessageLocator)).getText();

        assertEquals(text, "Oops, error on page. ZIP code should have 5 digits", "Другое сообщение");
    }
    @Test
    public void zipCodeShouldAcceptOnlyNumbersWithoutLetters(){
        driver.get(BASE_URL + "cgi-bin/register.py");
        driver.findElement(By.name(zipCodeInputLocator)).sendKeys("1111f");
        driver.findElement(By.cssSelector(continueButtonLocator)).click();
        String text = driver.findElement(By.cssSelector(errorMessageLocator)).getText();

        assertEquals(text, "Oops, error on page. ZIP code should have 5 digits", "Другое сообщение");
    }
}

