package bctsoft.mentoria.pageobject.base;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Clase Base de Selenium, para poder enmascarar
 * la version de Selenium
 * @author eduardo.araya
 */
public class SeleniumBase {

    //atributos
    protected WebDriver driver;

    //Constructor Base
    public SeleniumBase(WebDriver driver){
        this.driver = driver;
    }

    //Envoltorios Wrapper
    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> findElements (By locator){
        return driver.findElements(locator);
    }

    public String getText (By locator){
        return driver.findElement(locator).getText();
    }

    public void type(String inputText, By locator){
        driver.findElement(locator).sendKeys(inputText);
    }
    public void click(By locator){
        driver.findElement(locator).click();
    }

    public void closeDriver(){
        driver.close();
    }

    public Boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void goToUrl(String url){
        driver.get(url);
    }

    public String getTitle(){
        return driver.getTitle();
    }




}
