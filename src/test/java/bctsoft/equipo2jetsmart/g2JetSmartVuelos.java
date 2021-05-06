package bctsoft.equipo2jetsmart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class g2JetSmartVuelos {
    /* Pre-condiciones
    * 1.- Debe cerrase los PopUp Inicial: Cerrar ventan de subscripcion
    * 2.- No aceptar notificaciones. PopUp Inicial
    * */

    public void formularioVuelos(String lugarOrigen,String lugarDestino) throws InterruptedException {

        //Cerrar popUp de Subscripcion
        System.out.println("Cerramos ventana de subscripcion");
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[@class='modal-header']//button[@class='close modal-close']" +
                        "//span"))).click();

        // 1.- Click en Origen
        System.out.println("Seleccionamos Origen");
        driver.findElement(By.xpath("//input[@placeholder='Origen']")).click();
        Thread.sleep(4000);

        // 2.- Seleccionamos "Santiago de Chile"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(@class,'dg-country-selector-list-item ')" +
                " and @data-country='CL']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(@class,'dg-city-selector-list-item ')" +
                " and contains(text(),'"+lugarOrigen+"')]"))).click();

        // 3.- Seleccionamos "Argentina"
        System.out.println("Seleccionamos Destino");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@placeholder='Destino']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(@class,'dg-country-selector-list-item ')" +
                " and @data-country='AR']"))).click();

        // 4.- Seleccionamos "Buenos Aires"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(@class,'dg-city-selector-list-item ')" +
                " and contains(text(),'"+lugarDestino+"')]"))).click();
    }

    public String atc_02_Vuelos(String lugarOrigen, String lugarDestino) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Cerrar popUp de Subscripcion
        System.out.println("Cerramos ventana de subscripcion");
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[@class='modal-header']//button[@class='close modal-close']" +
                        "//span"))).click();

        // 1.- Seleccionamos Origen
        System.out.println("Damos click en Origen");
        driver.findElement(By.xpath("//input[@placeholder='Origen']")).click();
        Thread.sleep(3000);

        // 2.- Indicamos "Santiago (SCL)"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(@class,'dg-country-selector-list-item ')" +
                " and @data-country='CL']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(@class,'dg-city-selector-list-item ')" +
                " and contains(text(),'"+lugarOrigen+"')]"))).click();

        // 3.- Seleccionamos Destino "Arica (ARI)"
        System.out.println("Damos click en Destino");
        driver.findElement(By.xpath("//input[@placeholder='Destino']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(@class,'dg-country-selector-list-item ')" +
                " and @data-country='CL']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(@class,'dg-city-selector-list-item ')" +
                " and contains(text(),'"+lugarDestino+"')]"))).click();

        Thread.sleep(3000);

        // 4.- Indicamos Fecha de ida
        System.out.println("Damos click en en fecha de ida");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='dg-calendar open']//span[@aria-label='Mayo 19, 2021']"))).click();


        // 5.- indicamos Fecha de Vuelta
        Thread.sleep(3000);
        System.out.println("Damos click en en fecha de vuelta");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='dg-calendar open']//span[@aria-label='Mayo 25, 2021']"))).click();

        // 6.- Presionamos en Boton "Buscar Smart"

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='rt_button']//span"))).click();

        // 7.- Se despliega listado de vuelos posibles

        // 8.- Presionamos Boton "Continuar"
        System.out.println("Damos click en continuar");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@class='rounded-primary-btn booking'])[1]"))).click();

        System.out.println("Mensaje de Error");
        String msjError = driver.findElement(By.xpath("//div[@class='form-error-message ts-flight-select-error']")).getText();
        System.out.println(msjError);

        if(msjError.equals("Para continuar debes seleccionar tu vuelo")){
            return "Caso existoso, debes presionar los vuelos";
        }else{
            return "Test Case Failed";
        }
    }

    public String atc_03_Vuelos(String lugarOrigen, String lugarDestino) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // 1.- Click en Origen
        // 2.- Seleccionamos "Chile"
        // 3.- Seleccionar "Santiago"
        // 4.- Seleccionar "Argentina"
        // 5.- Seleccionar "Buenos Aires"
        formularioVuelos(lugarOrigen, lugarDestino);

        // 6.- Seleccionar "19 de Mayo de 2021"
        System.out.println("Damos click en en fecha de ida");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='dg-calendar open']//span[@aria-label='Mayo 19, 2021']"))).click();

        // 7.- Seleccionar "01 de Junio de 2021"
        System.out.println("Damos click en en fecha de vuelta");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='dg-calendar open']//span[@aria-label='Mayo 25, 2021']"))).click();


        // 8.- Click en el boton "buscar Smart"
        System.out.println("Damos click en buscar Smart");
        driver.findElement(By.xpath("//button[@id='rt_button']//i")).click();

        // 9.- Seleccionamos la primera opcion del paquete de IDA
        System.out.println("Seleccionamos el Vuelo de IDA");
        driver.findElement(By.xpath("(//div[@class='text-center nowrap'])[1]")).click();

        // 10.- Click en "lo quiero" en la seccion de vuelo ligero
        System.out.println("Seleccionamos opcion para vuelo ligero");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='bundle-button'])[3]"))).click();
      //  driver.findElement(By.xpath("(//button[@class='bundle-button'])[3]")).click();

        // 11.- Seleccionar la primera opcion del paquete de VUELTA
        System.out.println("Seleccionamos el vuelo de VUELTA");
        //driver.findElement(By.xpath("(//div[@class='text-center nowrap'])[2]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='text-center nowrap'])[2]"))).click();

        // 12.- Click en "lo quiero" en la seccion de vuelo ligero
        System.out.println("Seleccionamos opcion para vuelo ligero");
        //driver.findElement(By.xpath("(//button[@class='bundle-button'])[7]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='bundle-button'])[7]"))).click();


        // 13.- Click en Continuar
        System.out.println("Damos click en boton continuar");
        driver.findElement(By.xpath("(//button[@class='rounded-primary-btn booking'])[1]")).click();


        // 14.- Ingresar "Ivan" en el campo Nombre
        System.out.println("Ingresamos nombre de pasajero");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Continuar')])[1]")));


        driver.findElement(By.xpath("//input[@name='jetSmartPassengers[0].Name.First']")).sendKeys("Ivan");

        // 15.- Ingresar "Flores" en el campo Apellido
        System.out.println("Ingresamos apellido de pasajero");
        driver.findElement(By.xpath("//input[@name='jetSmartPassengers[0].Name.Last']")).sendKeys("Flores");

        // 16.- Click en Continuar
        System.out.println("Damos click en boton continuar");
        driver.findElement(By.xpath("(//button[contains(text(),'Continuar')])[1]")).click();


        boolean inputAlert = driver.findElement(By.xpath("(//div[contains(text(),'Debes completar todos los campos.')])[1]")).isDisplayed();

        if (inputAlert){

            return "Si Funciona el Alert por falta de fecha";
        }else {

            return "No Funciona el Alert por falta de fecha";
        }
    }
    public String atc_01_Vuelos(String lugarOrigen, String lugarDestino) throws InterruptedException {

        formularioVuelos(lugarOrigen,lugarDestino);
        Thread.sleep(3000);

        // 5.- Seleccionamos Buscar
        System.out.println("Seleccionamos en Buscar Smart");
        driver.findElement(By.xpath("//button[@id='rt_button']//i")).click();

        boolean inputAlert = driver.findElement(By.xpath("//input[@class='flatpickr-input error']")).isDisplayed();

        if (inputAlert){
            driver.quit();
            return "Si Funciona el Alert por falta de fecha";
        }else {
            driver.quit();
            return "No funciona Alert por falta de fecha";
        }
    }

    WebDriver driver;

    public g2JetSmartVuelos() {
        this.driver = new ChromeDriver();
    }

    public WebDriver getdriver() {
        return driver;
    }

    public void setdriver(WebDriver driver) {
        this.driver = driver;
    }
}
