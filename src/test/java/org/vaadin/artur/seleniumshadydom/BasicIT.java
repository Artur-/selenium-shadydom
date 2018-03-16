package org.vaadin.artur.seleniumshadydom;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasicIT {

    private FirefoxDriver driver;
    private WebElement myElement;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
        driver.get("http://localhost:8080/testpage.html");
        myElement = driver.findElementByTagName("my-element");
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void accessOuterAsElement() {
        WebElement element = (WebElement) ((JavascriptExecutor) driver)
                .executeScript(
                        "return arguments[0].shadowRoot.querySelector('#outer')",
                        myElement);
        Assert.assertNotNull(element);
    }

    @Test
    public void accessOuterAsList() {
        List<WebElement> element = (List<WebElement>) ((JavascriptExecutor) driver)
                .executeScript(
                        "return [arguments[0].shadowRoot.querySelector('#outer')]",
                        myElement);
        Assert.assertNotNull(element);
    }

}