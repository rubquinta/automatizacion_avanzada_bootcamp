package bctsoft.equipo2jetsmart;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

public class g2JetSmartTrasladosTest {
    g2JetSmartTraslados traslado;

    @Before
    public void iniciarDriver(){

        WebDriverManager.chromedriver().setup();

        traslado = new g2JetSmartTraslados();
        traslado.driver.get("https://jetsmart.com/ar/es/");
    }
    @Test
    public void atc010TrasladoBajo() throws InterruptedException{
        String resultado = traslado.atc010TrasladoBaja("Buenos Aires","Cordoba","5-MAYO-2021","8-MAYO-2021","5:00 PM","7:00 PM");
        String esperado="Exito";
        Assert.assertEquals(esperado,resultado);
    }
    @Test
    public void atc011TrasladosMedio() throws InterruptedException {
        String resultado = traslado.atc011TrasladoMedia("Buenos Aires","Urgench, Uzbekistán","5-MAYO-2021","8-MAYO-2021","5:00 PM","7:00 PM");
        String esperado="Exito";
        Assert.assertEquals(esperado,resultado);
    }
    @Test
    public void atc012TrasladosComplejo() throws ParseException, InterruptedException {
        String resultado = traslado.atc012TrasladoComplejo("Buenos Aires","Cordoba","5-MAYO-2021","12-MAYO-2021","5:00 PM","7:00 PM");
        String esperado="Exito";
        Assert.assertEquals(esperado,resultado);
    }
    @After
    public void after(){
      traslado.driver.quit();

    }

}
