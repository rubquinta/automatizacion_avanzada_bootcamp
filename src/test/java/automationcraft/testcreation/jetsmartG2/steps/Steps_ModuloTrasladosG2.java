package automationcraft.testcreation.jetsmartG2.steps;

import automationcraft.engine.selenium.DriverFactory;
import automationcraft.testcreation.jetsmartG2.pages.JetSmartHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps_ModuloTrasladosG2 {
   private JetSmartHomePage jsHomePage = new JetSmartHomePage(DriverFactory.getDriver());
    /*
    @Given("Un usuario que entra al modulo de Traslados de JetSmart Chile")
    public void un_usuario_que_entra_al_modulo_de_Traslados_de_JetSmart_Chile() throws InterruptedException {
        jsHomePage.goToUrl("https://jetsmart.com/ar/es/");
        jsHomePage.esperaCamuflada(2000);
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.irATraslados();
    }

    @When("Solicito un traslado de {String} a {String1}")
    public void solicito_un_traslado_de_bsas_a_cba(String string,String string1) throws InterruptedException {
        jsHomePage.formularioTrasladoOrigen("aa","aa","aa");
    }
*/
   @Given("Un usuario que entra al modulo de Traslados de JetSmart Chile")
   public void un_usuario_que_entra_al_modulo_de_traslados_de_jet_smart_chile() throws InterruptedException {
       jsHomePage.goToUrl("https://jetsmart.com/ar/es/");
       jsHomePage.esperaCamuflada(2000);
       jsHomePage.cerrarModuloSuscribete();
       jsHomePage.irATraslados();
   }

    @When("Solicito un traslado de {string} a {string}")
    public void solicito_un_traslado_de_a(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("elijo la {string} y la {string}")
    public void elijo_la_y_la(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("Clickeo en buscar")
    public void clickeo_en_buscar() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("Selecciono cambiar busqueda")
    public void selecciono_cambiar_busqueda() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("Cambio la moneda a pesos argentinos")
    public void cambio_la_moneda_a_pesos_argentinos() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("Se muestran los precios en pesos argentinos")
    public void se_muestran_los_precios_en_pesos_argentinos() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
