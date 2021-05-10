package automationcraft.testcreation.jetsmartG2.steps;

import automationcraft.engine.selenium.DriverFactory;
import automationcraft.testcreation.jetsmartG2.pages.JetSmartHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Steps_ModuloVueloG2 {
    //WebDriver driver;
    private JetSmartHomePage jsHomePage = new JetSmartHomePage(DriverFactory.getDriver());

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

    @And("Presiono en el boton buscar")
    public void presiono_en_el_boton_buscar() {
        jsHomePage.buscarVuelo();
    }

    @Then("App muestra mensaje de alerta")
    public void app_muestra_mensaje_de_alerta() {
        Assert.assertEquals("Si Funciona el Alert por falta de fecha",jsHomePage.sinFecha());
    }

}
