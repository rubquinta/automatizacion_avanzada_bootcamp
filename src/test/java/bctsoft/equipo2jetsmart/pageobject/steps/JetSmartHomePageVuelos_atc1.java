package bctsoft.equipo2jetsmart.pageobject.steps;

import bctsoft.equipo2jetsmart.pageobject.base.*;
import bctsoft.equipo2jetsmart.pageobject.pages.JetSmartHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class JetSmartHomePageVuelos_atc1 {

    private JetSmartHomePage jsHomePage = new JetSmartHomePage(DriverFactory.getDriver());


    @Given("un usuario desea buscar vuelo")
    public void Home_Page_Cierre_Pop_Ups_Entrada() throws InterruptedException {
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
    }

    @When("envía el formulario con algún dato faltante")
    public void Formulario_Enter_Sin_Datos_Fecha() {
        jsHomePage.formularioVuelo();
        jsHomePage.buscarVuelo();
    }

    @Then("aparece un alerta de dato faltante")
    public void Alerta_Dato_Faltante_Debería_Aparecer() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals("Si Funciona el Alert por falta de fecha",jsHomePage.sinFecha());
    }
}
