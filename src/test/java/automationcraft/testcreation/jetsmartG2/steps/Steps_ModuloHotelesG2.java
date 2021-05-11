package automationcraft.testcreation.jetsmartG2.steps;

import automationcraft.engine.selenium.DriverFactory;
import automationcraft.testcreation.jetsmartG2.pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Steps_ModuloHotelesG2 {

    protected JetSmartHomePage jsHomePage = new JetSmartHomePage(DriverFactory.getDriver());

    protected JetSmartSeleccionHoteles jsSeleccionHotelesPage;

    protected JetSmartSeleccionHabitacionPage jsSeleccionHabitacionPage;

    //Comlejidad Baja

    @Given("Que soy un usuario e ingreso a la web")
    public void que_soy_un_usuario_e_ingreso_a_la_web() {
        System.out.println("Ingreso a la web");
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        System.out.println("Cierro PopUp");
        jsHomePage.cerrarModuloSuscribete();
    }

    @When("Ingreso a Formulario de Hoteles")
    public void click_funcionalidad_hoteles() {
        jsHomePage.entrarFormularioHoteles();
    }

    @And("Selecciono buscar con lugar y fechas por defecto")
    public void buscar_alojamiento_por_defecto() {
        jsHomePage.formularioHotelesBuscar();
        jsSeleccionHotelesPage = new JetSmartSeleccionHoteles(DriverFactory.getDriver());
        jsSeleccionHotelesPage.cambioNewWindow(DriverFactory.getDriver());
    }

    @And("Filtro resultados por 5 estrellas")
    public void filtro_cinco_Estrellas() throws InterruptedException {
        jsSeleccionHotelesPage.setFiltroCincoEstrellasHoteles();
    }

    @And("Filtro resultados por tramo mas economico")
    public void filtro_primer_tramo() throws InterruptedException {
        jsSeleccionHotelesPage.setFiltroSegundoTramoPriceHoteles();
    }

    @And("Presiono mostrar ordenados por precios mas bajo primero")
    public void orden_de_resultados() throws InterruptedException {
        jsSeleccionHotelesPage.setFiltroOrdenarPorPrecio();
    }

    @Then("Se muestras los valores ordenados de menor a mayor en el rango solicitado")
    public void verifico_orden_de_precios() throws InterruptedException {
        jsSeleccionHotelesPage.comparaOrdenPreciosHoteles
                (jsSeleccionHotelesPage.imprimePreciosHoteles());
    }

    //Complejidad Media



    @And("Selecciono la primer opcion de hotel disponible")
    public void selecciono_primera_opcion_Hotel() throws InterruptedException {

        jsSeleccionHotelesPage.esperaImplicita(10);
        jsSeleccionHotelesPage.seleccionarPrimerHotelEnPantalla();
        jsSeleccionHabitacionPage = new JetSmartSeleccionHabitacionPage(DriverFactory.getDriver());
        jsSeleccionHabitacionPage.cambioNewWindow(DriverFactory.getDriver());
    }

    @And("Abro ventana de Modificar en Pagina Siguiente")
    public void btn_modificar_busqueda() throws InterruptedException {
        jsSeleccionHabitacionPage.esperaImplicita(10);
        jsSeleccionHabitacionPage.clickearModificarBusqueda();
    }

    @And("Selecciono CheckOut en rango mayor a 30 noches")
    public void selecciono_fecha_mayor30noches() throws InterruptedException {
        jsSeleccionHabitacionPage.setFechaRegresoPasada();
        jsSeleccionHabitacionPage.seleccionarFechaDeVuelta();
    }

    @And("Busco disponibilidad")
    public void btn_ver_disponibilidad() throws InterruptedException {

        jsSeleccionHabitacionPage.btnVerDisponibilidadVentanaCambioFecha();


    }

    @Then("Se muestra alerta que solo puede ser fecha maxima 30 noches")
    public void verificoAlertaFechaIncorrecta() throws InterruptedException {
        Assert.assertEquals("Alerta de fecha muy lejana SI funciona",
                jsSeleccionHabitacionPage.verificoAlertaEsperadaPorMasDe30Noches());

    }



}
