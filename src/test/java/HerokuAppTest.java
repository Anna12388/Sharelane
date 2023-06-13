import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class HerokuAppTest extends BaseTest {
    @Test
    public void addRemoveElements() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addElementButton = driver.findElement(By.cssSelector("[onclick='addElement()']"));
        addElementButton.click();
        addElementButton.click();

       List<WebElement> deleteButtonsList = driver.findElements(By.cssSelector("[onclick='deleteElement()']"));
        assertEquals(deleteButtonsList.size(),2, "Элемента не два");

        deleteButtonsList.get(0).click();
        driver.findElements(By.cssSelector("[onclick='deleteElement()']"));
        assertEquals(deleteButtonsList.size(),1,"Элемента не 2");

    }
}
