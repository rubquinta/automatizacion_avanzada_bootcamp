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
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
    }
    @When("En el formulario ingreso origen <Santiago>")
    public void en_el_formulario_ingreso_origen(){
        jsHomePage.seleccionVueloOrigenSantiago();
    }
    @And("Selecciono destino <Buenos Aires>")
    public void selecciono_destino() {
        jsHomePage.seleccionVueloDestinoArgentina();
    }
    @And("Selecciono destino <Arica>")
    public void selecciono_destino_arica() {
        jsHomePage.seleccionVueloDestinoArica();
    }
    @And("Ingreso fecha de ida y vuelta")
    public void ingreso_fecha_de_ida_y_vuelta() {
        jsHomePage.fechasVuelo();
    }
    @And("Presiono en el boton buscar")
    public void presiono_en_el_boton_buscar() {
        jsHomePage.buscarVuelo();
    }
    @And("Selecciono mis vuelos")
    public void selecciono_mis_vuelos() {
        jsVuelos.llenaVuelos();
    }
    @And("Presiono en continuar")
    public void presiono_en_continuar() {
        jsVuelos.continuar();
    }

    @And("Ingreso datos de pasajero sin fecha de nac.")
    public void ingreso_datos_de_pasajero_sin_fecha_de_nac() {
        jsVuelos.ingresaDatosPasajero();
    }
    @And("Hacemos click en continuar")
    public void hacemos_click_en_continuar() {
        jsVuelos.continuar2();
    }
    @Then("App muestra mensaje de alerta")
    public void app_muestra_mensaje_de_alerta() {
        Assert.assertEquals("Si Funciona el Alert por falta de fecha", jsHomePage.sinFecha());
    }
    @Then("App muestra mensaje de error")
    public void app_muestra_mensaje_de_error() {
        Assert.assertEquals("Caso existoso, debes presionar los vuelos",jsVuelos.msjError());
    }
    @Then("App muestra alerta por datos faltantes")
    public void app_muestra_alerta_por_datos_faltantes() {
        Assert.assertEquals("Caso existoso, debes completar todos los campos",jsVuelos.msjError2());
    }


}
