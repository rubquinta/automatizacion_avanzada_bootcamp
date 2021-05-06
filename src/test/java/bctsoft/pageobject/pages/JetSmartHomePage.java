package bctsoft.pageobject.pages;

import bctsoft.pageobject.base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * URL: https://jetsmart.com/cl/es/
 * HomePage para automatizacion de casos de Pruebas
 */
public class JetSmartHomePage extends SeleniumBase {

    public JetSmartHomePage(WebDriver driver) {
        super(driver);
    }

    //repositorio objetos JetSmartHomePAge
    private By btnPopUpClose = By.xpath("//div[@class='modal-header']//button[@type='button']");
    private By txtNombreSuscribete = By.xpath("//input[@id='name']");
    private By txtApellidoSuscribete = By.xpath("//input[@id='lastname']");
    private By txtEmailSuscribete = By.xpath("//input[@id='email']");
    private By btnSuscribete = By.xpath("//span[contains(text(),'Suscríbete')]");



    //keyword Driven
    public void cerrarModuloSuscribete(){
        if(isDisplayed(btnPopUpClose)){
            click(btnPopUpClose);
        }
    }
    public void suscribirseEnPopUp(){
        if (isDisplayed(txtNombreSuscribete)){
            type("nombre",txtNombreSuscribete);
            type("apellido",txtApellidoSuscribete);
            type("email@email.em",txtEmailSuscribete);
            click(btnSuscribete);
        }
    }

}
