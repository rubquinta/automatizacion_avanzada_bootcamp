package bctsoft.equipo2jetsmart.testCases;

import bctsoft.equipo2jetsmart.pageobject.pages.*;
import org.junit.Assert;
import org.junit.Test;

public class AutomaticTestHoteles extends TestBase {

    protected JetSmartHomePage jsHomePage;

    protected JetSmartSeleccionHoteles jsSeleccionHotelesPage;
    protected JetSmartSeleccionHabitacionPage jsSeleccionHabitacionPage;
    protected JetSmartDatosHuespedPage jsDatosHuespedesPage;
    protected JetSmartHotelesPaymentPage jsDatosPagoPage;

    @Test
    public void atc04_test_Verificación_filtros_ordenar_resultados() throws InterruptedException {
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.formularioHoteles();
        jsSeleccionHotelesPage = new JetSmartSeleccionHoteles(driver);
        jsSeleccionHotelesPage.cambioNewWindow(driver);
        jsSeleccionHotelesPage.setFiltroCincoEstrellasHoteles();
        jsSeleccionHotelesPage.setFiltroSegundoTramoPriceHoteles();
        jsSeleccionHotelesPage.setFiltroOrdenarPorPrecio();

        Assert.assertEquals("Correcto orden",
                jsSeleccionHotelesPage.comparaOrdenPreciosHoteles
                        (jsSeleccionHotelesPage.imprimePreciosHoteles()));
    }

    @Test
    public void atc05_test_Verificación_alerta_advertencia_en_ventana_emergente(){
        //precondiciones
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        //paso 1 y 2
        jsHomePage.formularioHoteles();
        jsSeleccionHotelesPage = new JetSmartSeleccionHoteles(driver);
        jsSeleccionHotelesPage.cambioNewWindow(driver);
        jsSeleccionHotelesPage.esperaImplicita(10);
        //paso 3
        jsSeleccionHotelesPage.seleccionarPrimerHotelEnPantalla();
        jsSeleccionHabitacionPage = new JetSmartSeleccionHabitacionPage(driver);
        jsSeleccionHabitacionPage.cambioNewWindow(driver);
        jsSeleccionHabitacionPage.esperaImplicita(10);
        jsSeleccionHabitacionPage.setFechaRegresoPasada();
        //paso 4
        jsSeleccionHabitacionPage.clickearModificarBusqueda();
        //pasos 5-7

        Assert.assertEquals("Alerta de fecha muy lejana SI funciona",
                jsSeleccionHabitacionPage.seleccionarFechaDeVuelta());


    }

    @Test
    public void atc06_test_Verificación_de_alerta_para_datos_Payment_faltantes_reserva_de_habitación(){
        //precondiciones
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();

        //paso 1 al 8
        jsHomePage.formularioHoteles("Miami");

        //paso 9
        jsSeleccionHotelesPage = new JetSmartSeleccionHoteles(driver);
        jsSeleccionHotelesPage.cambioNewWindow(driver);
        jsSeleccionHotelesPage.seleccionarPrimerHotelEnPantalla();

        //paso10
        jsSeleccionHabitacionPage = new JetSmartSeleccionHabitacionPage(driver);
        jsSeleccionHabitacionPage.cambioNewWindow(driver);

        //Paso 11
        jsSeleccionHabitacionPage.seleccionaUnaHabitacion();

        //Paso12
        jsSeleccionHabitacionPage.clickReservare();

        //Paso 13 - 14 - 15 - 16
        jsDatosHuespedesPage = new JetSmartDatosHuespedPage(driver);
        jsDatosHuespedesPage.cambioNewWindow(driver);
        jsDatosHuespedesPage.esperaImplicita(10);
        jsDatosHuespedesPage.formularioDatosHab("Ruben", "Quintana",
                "rubemo05@gmail.com");
        //Paso 17
        jsDatosHuespedesPage.clickReservare();
        //Paso 18
        jsDatosPagoPage = new JetSmartHotelesPaymentPage(driver);
        jsDatosPagoPage.cambioNewWindow(driver);
        jsDatosPagoPage.clickRealizarReserva();

        //Resultado Esperado
        Assert.assertEquals("Si funciona alert",jsDatosPagoPage.presenciaDeAlert());

    }


}
