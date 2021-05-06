package bctsoft.equipo2jetsmart.pageobject.testCases;

import bctsoft.equipo2jetsmart.pageobject.pages.JetSmartHomePage;
import bctsoft.equipo2jetsmart.pageobject.pages.JetSmartSeleccionTraslados;
import org.junit.Assert;
import org.junit.Test;

public class AutomaticTestTraslados extends TestBase{


        protected JetSmartHomePage jsHomePage;

        protected JetSmartSeleccionTraslados jsSecodPage;

        @Test
        public void atc10_verPaquetesEnPesosArgentinos() throws InterruptedException {
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/ar/es/");
        Thread.sleep(2000);
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.formularioTraslado("Buenos Aires","Cordoba","12-MAYO-2021","19-MAYO-2021","5:00 PM","7:00 PM");
        jsSecodPage = new JetSmartSeleccionTraslados(driver);
        jsSecodPage.cambioNewWindow(driver);
        String resultado = jsSecodPage.cambioMoneda("ARS");
        Assert.assertEquals("Exito",resultado);
        }
    @Test
    public void atc11_MsgSugerenciaTrasladoADestinoPocaInfomacion() throws InterruptedException {
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/ar/es/");
        Thread.sleep(2000);
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.formularioTraslado("Buenos Aires","Urgench, Uzbekistán","12-MAYO-2021","","5:00 PM","");
        jsSecodPage = new JetSmartSeleccionTraslados(driver);
        jsSecodPage.cambioNewWindow(driver);
        String resultado = jsSecodPage.verificarAlertaOrigen();
        Assert.assertEquals("Exito",resultado);
    }
    @Test
    public void atc11_SolicitarTraslado() throws InterruptedException {
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/ar/es/");
        Thread.sleep(2000);
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.formularioTraslado("Buenos Aires","Cordoba","12-MAYO-2021","19-MAYO-2021","5:00 PM","7:00 PM");
        jsSecodPage = new JetSmartSeleccionTraslados(driver);
        jsSecodPage.cambioNewWindow(driver);
        String resultado = jsSecodPage.seleccionarTrasladoEstandar();
        Assert.assertEquals("Exito",resultado);
    }

}
