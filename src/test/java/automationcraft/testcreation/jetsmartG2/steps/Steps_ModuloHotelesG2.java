package automationcraft.testcreation.jetsmartG2.steps;

import automationcraft.engine.selenium.DriverFactory;
import automationcraft.testcreation.jetsmartG2.pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.protobuf.MapEntry;
import org.testng.Assert;

public class Steps_ModuloHotelesG2 {

    protected JetSmartHomePage jsHomePage = new JetSmartHomePage(DriverFactory.getDriver());

    protected JetSmartSeleccionHoteles jsSeleccionHotelesPage;

    protected JetSmartSeleccionHabitacionPage jsSeleccionHabitacionPage;

    protected JetSmartDatosHuespedPage jsDatosHuespedPage;

    protected JetSmartHotelesPaymentPage jsPaymentPage;

    //Comlejidad Baja

    @When("Ingreso a Formulario de Hoteles")
    public void click_funcionalidad_hoteles() {
        jsHomePage.entrarFormularioHoteles();
    }

    @And("Selecciono buscar alojamiento")
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

    //Complejidad ALta

    @And("Elijo {string} como lugar de alojamiento")
    public void formularioLugarAlojamiento(String lugarDestino) throws InterruptedException{
        jsHomePage.formularioHotelesLugar(lugarDestino);
    }

    @And("Selecciono fecha Hoy y Mañana como fechas checkInOut respectivamente")
    public void formularioFechasAlojamiento() throws InterruptedException{
        jsHomePage.formularioHotelesFechas();
    }

    @And("Presiono Buscar Hoteles")
    public void btnBuscarHoteles(){
        jsHomePage.buscarAlojamiento();
    }


    @And("Selecciono 1 Habitacion la primera de las opciones")
    public void seleccionHabitacion() throws InterruptedException{
        jsSeleccionHabitacionPage.seleccionaUnaHabitacion();
    }

    @And("Presiono en Reservare")
    public void seleccionReservar() throws InterruptedException{
        jsSeleccionHabitacionPage.clickReservare();
    }

    @And("Lleno formulario de huesped datos nombre: {string}, quintana: {string}, email: {string}")
    public void formularioDatosHuesped(String nombre, String apellido, String correo) throws InterruptedException{
        jsDatosHuespedPage = new JetSmartDatosHuespedPage(DriverFactory.getDriver());
        jsDatosHuespedPage.cambioNewWindow(DriverFactory.getDriver());
        jsDatosHuespedPage.esperaImplicita(10);
        jsDatosHuespedPage.formularioDatosHab(nombre, apellido, correo);
    }

    @And("Presiono Siguiente Ultimos Datos")
    public void btnSiguienteUltimosDatos(){
        jsDatosHuespedPage.clickReservare();
    }

    @And("Presiono Completar reserva sin llenar datos de pago")
    public void btnCompletarPaymentPage(){
        jsPaymentPage = new JetSmartHotelesPaymentPage(DriverFactory.getDriver());
        jsPaymentPage.cambioNewWindow(DriverFactory.getDriver());
        jsPaymentPage.clickRealizarReserva();
    }

    @Then("Se muestra alerta que faltan de datos de pago")
    public void verificoAlertaPorFataDeDatosPayment(){
        jsPaymentPage.presenciaDeAlert();
    }






}
