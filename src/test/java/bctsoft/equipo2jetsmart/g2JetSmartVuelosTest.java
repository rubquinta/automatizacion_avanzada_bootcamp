package bctsoft.equipo2jetsmart;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class g2JetSmartVuelosTest {

    g2JetSmartVuelos vuelos;

    @Before
    public void iniciarDriver(){

        WebDriverManager.chromedriver().setup();
        vuelos = new g2JetSmartVuelos();
        vuelos.driver.get("https://jetsmart.com/cl/es/");
    }

    @Test
    public void atc01VuelosBaja() throws InterruptedException{

        Thread.sleep(3000);
        String resultado = vuelos.atc_01_Vuelos("Santiago", "Buenos Aires");
        String esperado = "Si Funciona el Alert por falta de fecha";
        Assert.assertEquals(esperado,resultado);
    }
    @Test
    public void atc02VuelosMedia() throws InterruptedException{

        Thread.sleep(3000);
        String resultado = vuelos.atc_02_Vuelos("Santiago", "Arica");
        String esperado = "Caso existoso, debes presionar los vuelos";
        Assert.assertEquals(esperado,resultado);
    }
    @Test
    public void atc03VuelosCompleja() throws InterruptedException{

        Thread.sleep(3000);
        String resultado = vuelos.atc_03_Vuelos("Santiago", "Buenos Aires");
        String esperado = "Si Funciona el Alert por falta de fecha";
        Assert.assertEquals(esperado,resultado);
    }
    @After
    public void after(){
        vuelos.driver.quit();
    }
}
