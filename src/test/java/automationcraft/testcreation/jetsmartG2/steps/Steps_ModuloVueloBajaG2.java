package automationcraft.testcreation.jetsmartG2.steps;

import automationcraft.engine.selenium.DriverFactory;
import automationcraft.testcreation.jetsmartG2.pages.JetSmartHomePage;
import automationcraft.testcreation.jetsmartG2.pages.JetSmartSeleccionVuelos;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Steps_ModuloVueloBajaG2 {
    //WebDriver driver;
    private JetSmartHomePage jsHomePage = new JetSmartHomePage(DriverFactory.getDriver());
    private JetSmartSeleccionVuelos jsVuelos = new JetSmartSeleccionVuelos(DriverFactory.getDriver());

    @Given("Que soy un usuario e ingreso a la web <JetSmart>")
    public void que_soy_un_usuario_e_ingreso_a_la_web() {
        System.out.println("Ingreso a la web");
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        System.out.println("Cierro PopUp");
        jsHomePage.cerrarModuloSuscribete();
    }

    @When("En el formulario ingreso origen <Santiago>")
    public void en_el_formulario_ingreso_origen() {
        jsHomePage.seleccionVueloOrigenSantiago();
    }

    @And("Selecciono destino <Buenos Aires>")
    public void selecciono_destino() {
        jsHomePage.seleccionVueloDestinoArgentina();
    }

    @And("Ingreso fecha de ida y vuelta")
    public void ingreso_fecha_de_ida_y_vuelta() {
        jsHomePage.fechasVuelo();
    }

    @And("Selecciono destino <Arica>")
    public void selecciono_destino_arica() {
        jsHomePage.seleccionVueloDestinoArica();
    }

    @And("Presiono en el boton buscar")
    public void presiono_en_el_boton_buscar() {
        jsHomePage.buscarVuelo();
    }

    @And("Presiono en continuar, sin seleccionar vuelos.")
    public void presiono_en_continuar_sin_seleccionar_vuelos() {
        jsVuelos.continuar();
    }

    @Then("App muestra mensaje de alerta")
    public void app_muestra_mensaje_de_alerta() {
        Assert.assertEquals("Si Funciona el Alert por falta de fecha", jsHomePage.sinFecha());
    }

    @Then("App muestra mensaje de error")
    public void app_muestra_mensaje_de_error() {
        Assert.assertEquals("Caso existoso, debes presionar los vuelos",jsVuelos.msjError());
    }


}
