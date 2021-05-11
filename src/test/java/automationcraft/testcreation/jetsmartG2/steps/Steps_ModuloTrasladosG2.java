package automationcraft.testcreation.jetsmartG2.steps;

import automationcraft.engine.selenium.DriverFactory;
import automationcraft.testcreation.jetsmartG2.pages.JetSmartHomePage;
import automationcraft.testcreation.jetsmartG2.pages.JetSmartSeleccionTraslados;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Steps_ModuloTrasladosG2 {

   private JetSmartHomePage jsHomePage = new JetSmartHomePage(DriverFactory.getDriver());
   private JetSmartSeleccionTraslados jsSecondPage;
   private static String resultado;


   @Given("Un usuario que entra al modulo de Traslados de JetSmart Chile")
   public void un_usuario_que_entra_al_modulo_de_traslados_de_jet_smart_chile() throws InterruptedException {
       jsHomePage.goToUrl("https://jetsmart.com/ar/es/");
       jsHomePage.esperaCamuflada(2000);
       jsHomePage.cerrarModuloSuscribete();
       jsHomePage.irATraslados();
   }

    @When("Solicito un traslado de {string} a {string}")
    public void solicito_un_traslado_de_a(String origen, String destino) throws InterruptedException {
        jsHomePage.seleccionarOrigenYDestino(origen, destino);

    }

    @And("elijo {string} como fecha de ida y {string} como fecha de vuelta")
    public void elijo_como_fecha_de_ida_y_como_fecha_de_vuelta(String fechaIda, String fechaVuelta) throws InterruptedException {
        jsHomePage.seleccionarFechasDeIdayVuelta(fechaIda, fechaVuelta, "5:00 PM", "7:00 PM");

    }

    @And("Clickeo en buscar")
    public void clickeo_en_buscar() throws InterruptedException {
        jsHomePage.buscarTraslado();
        jsSecondPage = new JetSmartSeleccionTraslados(DriverFactory.getDriver());
        jsSecondPage.cambioNewWindow(DriverFactory.getDriver());
    }

    @And("Selecciono cambiar busqueda")
    public void selecciono_cambiar_busqueda() {
        jsSecondPage.modificarBusqueda();
    }

    @And("Cambio la moneda a {string}")
    public void cambio_la_moneda_a_pesos_argentinos(String moneda) {
        resultado = jsSecondPage.cambioMoneda(moneda);
    }

    @Then("Se muestran los precios en pesos argentinos")
    public void se_muestran_los_precios_en_pesos_argentinos() {
       Assert.assertEquals("Exito",resultado);
    }


    //prueba 2
    @When("Solicito un traslado solo de ida desde {string} a {string}")
    public void solicito_un_traslado_solo_de_ida_desde_a(String origen, String destino) throws InterruptedException {
        jsHomePage.clickearSoloIda();
        jsHomePage.seleccionarOrigenYDestino(origen, destino);

    }

    @And("Selecciono {int} pasajeros")
    public void selecciono_pasajeros(int pasajeros) {
       jsHomePage.seleccionarPasajeros(pasajeros);
    }

    @Then("Se muestra cartel pidiendo precisar datos")
    public void se_muestra_carteñ_pidiendo_precisar_datos(){
        resultado = jsSecondPage.verificarAlertaOrigen();
        Assert.assertEquals("Exito",resultado);
    }


    @Then("Hago click en Reservar ahora")
    public void hago_click_en_reservar_ahora(){
        jsSecondPage.seleccionarTraslado();
        resultado = jsSecondPage.verificarSeleccionDeTraslado();
        Assert.assertEquals("Exito", resultado);
    }

}
