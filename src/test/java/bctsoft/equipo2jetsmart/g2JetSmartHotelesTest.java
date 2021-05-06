package bctsoft.equipo2jetsmart;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class g2JetSmartHotelesTest {

    g2JetSmartHoteles hoteles;

    @Before
    public void iniciarDriver(){

        WebDriverManager.chromedriver().setup();
        hoteles = new g2JetSmartHoteles();

        hoteles.driver.get("https://jetsmart.com/cl/es/");
    }

    @Test
    public void atc04HotelesBaja() throws InterruptedException{

        String resultado = hoteles.atc_04_Hoteles();

        String esperado = "Correcto orden";

        Assert.assertEquals(esperado,resultado);

    }

    @Test
    public void atc05HotelesMedia() throws InterruptedException{


        String resultado = hoteles.atc_05_Hoteles();

        String esperado = "Alerta de fecha muy lejana SI funciona";

        Assert.assertEquals(esperado,resultado);

    }

    @Test
    public void atc06HotelesAlta() throws InterruptedException{

        String resultado = hoteles.atc_06_Hoteles("Miami",hoteles.driver);

        String esperado = "Si funciona alert";

        Assert.assertEquals(esperado,resultado);



    }

    @After
    public void finDriver(){


        hoteles.driver.quit();
    }
}
