package automationcraft.testcreation.jetsmartG2.steps;

import automationcraft.testcreation.jetsmartG2.pages.JetSmartHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class StepsModuloVueloG2 {
    WebDriver driver;
    JetSmartHomePage jsHomePage;

    @Given("que soy un usuario e ingreso a la web {string}")
    public void que_soy_un_usuario_e_ingreso_a_la_web(String string) {
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
    }
    @When("ingreso al formulario de vuelo")
    public void ingreso_al_formulario_de_vuelo() {
        jsHomePage.formularioVuelo();
    }

    @When("selecciono {string} en el campo Origen")
    public void selecciono_en_el_campo_origen(String string) {
        jsHomePage.buscarVuelo();
        Assert.assertEquals("Si Funciona el Alert por falta de fecha",jsHomePage.sinFecha());
    }

    @When("selecciono {string} en el campo Destino")
    public void selecciono_en_el_campo_destino(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("presiono BuscarSmart")
    public void presiono_buscar_smart() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("aparece alerta de dato faltante")
    public void aparece_alerta_de_dato_faltante() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
