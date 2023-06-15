import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class SignUpZipCodeTest extends BaseTest {
    String zipCodeInputLocator = "zip_code";
    String continueButtonLocator = "[value=Continue]";
    String errorMessageLocator = "[class=error_message]";

    @Test
    public void zipCodeShouldAccept5Digits() {
        driver.get(BASE_URL + "cgi-bin/register.py");
        driver.findElement(By.name(zipCodeInputLocator)).sendKeys("12345");
        driver.findElement(By.cssSelector(continueButtonLocator)).click();
        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();

        assertTrue(isDisplayed, "Нужная страница не открылась");
    }

    @Test
    public void enter4DigitsInTheZipCodeField() {
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
        driver.findElement(By.name(zipCodeInputLocator)).sendKeys("");
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

