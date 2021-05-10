package bctsoft.equipo2jetsmart.testCases;

import bctsoft.equipo2jetsmart.pageobject.pages.JetSmartHomePage;
import bctsoft.equipo2jetsmart.pageobject.pages.JetSmartSeleccionVuelos;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AutomaticTestVuelos extends TestBase {

    protected JetSmartHomePage jsHomePage;

    protected JetSmartSeleccionVuelos jsSecodPage;

    @Test
    public void atc01_test_verificacion_formulario_incompleto() throws InterruptedException {
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.formularioVuelo();
        jsHomePage.buscarVuelo();
        Assert.assertEquals("Si Funciona el Alert por falta de fecha",jsHomePage.sinFecha());

    }

    @Test
    public void atc02_test_error_al_continuar_sin_seleccion_de_vuelos() {
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.formularioVuelo_atc_02();
        jsHomePage.fechasVuelo();
        jsHomePage.buscarVuelo();
        jsSecodPage = new JetSmartSeleccionVuelos(driver);
        jsSecodPage.cambioNewWindow(driver);
        jsSecodPage.continuar();
        jsSecodPage.msjError();
        Assert.assertEquals("Caso existoso, debes presionar los vuelos",jsSecodPage.msjError());


    }

    @Test
    public void atc03_test_verificacion_alerta_datos_faltantes(){
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.formularioVuelo();
        jsHomePage.fechasVuelo();
        jsHomePage.buscarVuelo();
        jsSecodPage = new JetSmartSeleccionVuelos(driver);
        jsSecodPage.cambioNewWindow(driver);
        jsSecodPage.llenaVuelos();
        jsSecodPage.continuar();
        jsSecodPage.ingresaDatosPasajero();
        jsSecodPage.continuar2();
        jsSecodPage.msjError2();
        Assert.assertEquals("Caso existoso, debes completar todos los campos",jsSecodPage.msjError2());

    }


}
