package bctsoft.equipo2jetsmart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class g2JetSmartTraslados {
    WebDriver driver;

    public g2JetSmartTraslados() {
        this.driver = new ChromeDriver();
    }

    public String atc010TrasladoBaja(String origen, String destino, String fechaIda, String fechaVuelta, String horaIda, String horaVuelta) throws InterruptedException {
       int contador = 0;
        WebDriverWait d = new WebDriverWait(driver, 10);
       //Realiza la precondición
        formularioTraslado(origen, destino, fechaIda, fechaVuelta, horaIda, horaVuelta);
        //Realiza el cambio de ventana
        FuncionesComunes.cambioNewWindow(driver);
        //Espera que se listen los resultados y PASO1
        d.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ct-btn-transp"))).click();
        //PASO 2 seleccionar ARG como moneda
        Select s = new Select(driver.findElement(By.id("currency-code")));
        s.selectByValue("ARS");
        //PASO 3 en buscar
        driver.findElement(By.cssSelector(".ct-btn-s")).click();
        //espera que se carguen los resultados
        d.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ct-btn-transp")));
        //guarda los precios de los productos
        List<WebElement> preciosProductos = driver.findElements(By.cssSelector(".ct-clearfix"));
        //verifica que los precios de los productos esten valuados en pesos argentinos.
        for(WebElement precio: preciosProductos){
            if (precio.getText().contains("ARS")) {
                System.out.println(precio.getText());
                contador++;
            }
        }
        if(contador== preciosProductos.size()){
            return "Exito";
        }
        return "Falló";
    }

    public String atc011TrasladoMedia(String origen, String destino, String fechaIda, String fechaVuelta, String horaIda, String horaVuelta) throws InterruptedException {
        //COMPLETA FORMULARIO IFRAME
        formularioTraslado(origen,destino, fechaIda, "", horaIda, horaVuelta);
        WebDriverWait d = new WebDriverWait(driver, 10);
        FuncionesComunes.cambioNewWindow(driver);
        //Espera que se cargue la pagina
        d.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ct-btn-transp")));
        //Captura la alerta
        String texto = driver.findElement(By.cssSelector(".ct-show .gt-location-alert__tooltip")).getText();
        if(texto.contains("Por favor, introduce la dirección completa para recibir un precio exacto.")){
            return "Exito";
        }
        return "Falló";

    }

    public String atc012TrasladoComplejo(String origen, String destino, String fechaIda, String fechaVuelta, String horaIda, String horaVuelta) throws InterruptedException {
        //COMPLETA FORMULARIO IFRAME
        formularioTraslado(origen, destino, fechaIda, fechaVuelta, horaIda, horaVuelta);
        WebDriverWait d = new WebDriverWait(driver, 10);
        //Realiza el cambio de ventan
        FuncionesComunes.cambioNewWindow(driver);
        //PASO13 click en reservar
        d.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'ct-btn-p')]"))).click();
        if (driver.findElement(By.cssSelector(".ct-active")).getText().contains("INFORMACIÓN")) {

            return "Exito";
        }
        return "Falló";
    }

    public void formularioTraslado(String origen, String destino, String fechaIda, String fechaVuelta, String horaIda, String horaVuelta) throws InterruptedException {
       Thread.sleep(2000);
        WebDriverWait d = new WebDriverWait(driver, 15);
        //precondicion
        d.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id='modalReg'] button.close span"))).click();

        //PASO 1 selecionar la opción traslado
        driver.findElement(By.xpath("//span[contains(text(),'Traslados')]")).click();
        driver.switchTo().frame(2);

        if(fechaVuelta.isEmpty()){
            //PRESIONA EL CAMPO IDA
            driver.findElement(By.xpath("//span[contains(text(),'Ida')]")).click();
        }
        //PASO 2 completar el campo origen
        driver.findElement(By.cssSelector("#input-pickup")).sendKeys(origen);
        //PASO 3 seleccionar la primer opción
        d.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#item-0-0"))).click();
        //PASO 4 completar el campo destino
        driver.findElement(By.cssSelector("#input-dropoff")).sendKeys(destino);
        //PASO 5 seleccionar la primer opción
        d.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#item-0-0"))).click();
        //SELECCIONAR FECHA PARTICA PASO 6 y 7
        seleccionarFecha(fechaIda, driver, "pickup", horaIda);
        if(!fechaVuelta.isEmpty()){
            //SELECCIONAR FECHA REGRESO PASO 8 y 9
            seleccionarFecha(fechaVuelta, driver, "return", horaVuelta);
        }
        //PASO 10 Seleccionar 1 pasajero
        driver.findElement(By.xpath("//ul[@class='ct-list-container']//li[contains(text(),' 1 pasajero')]")).click();
        //PASO 11 Click en buscar
        driver.findElement(By.cssSelector(".ct-btn")).click();
    }

    public static void seleccionarFecha(String fecha, WebDriver driver, String idaOVuelta, String hora) throws InterruptedException {
        //Declaracion de variables
        String[] meses = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO",
                "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
        String[] fechaSeparada = fecha.split("-");
        boolean bandera = true;
        int indiceMesActual = 15;
        int indiceMesIngresado = 14;
        String mesCalendario;
        String[] mesAnoCalendario;
        WebDriverWait d = new WebDriverWait(driver, 10);

        while (bandera) {
            //Obtiene el titulo del calendario actual
            mesCalendario = driver.findElement(By.xpath("//div[@class='ctc-calendar__title']")).getText();
            //Separa el titulo en mes y año
            mesAnoCalendario = mesCalendario.split(" ");
            for (int i = 0; i < 12; i++) {
                //pregunta cual es el indice del mes que muestra la pagina
                if (mesAnoCalendario[0].equals(meses[i])) {
                    indiceMesActual = i;
                }
                //pregunta cual es el indice del mes ingresado
                if (fechaSeparada[1].equals(meses[i])) {
                    indiceMesIngresado = i;
                }
            }
            //si el mes que muestra el calendario de la pagina esta antes del mes ingresado
            if (indiceMesActual < indiceMesIngresado) {
                //click en la flecha del siguiente mes
                driver.findElement(By.xpath("//div[@title='Next month']")).click();
            }
            //si el mes que muestra el calendario de la pagina esta despues del mes ingresado
            if (indiceMesActual > indiceMesIngresado) {
                //click en la flecha del mes anterior
                driver.findElement(By.xpath("//div[@title='Previous month']")).click();
            }
            //si el mes que muestra el calendario es igual al ingresado
            if (indiceMesActual == indiceMesIngresado) {
                //Selecciono el dia de la fecha ingresada
                driver.findElement(By.xpath("//td[@id='input-" + idaOVuelta + "-date-calendar-" + fechaSeparada[0] + "']")).click();
                bandera = false;
            }
        }



         if (idaOVuelta.equals("pickup")) {
             //Espera que la pagina liste las horas de ida
              d.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@id,'pick-up')]")));
            //Selecciona la hora de ida ingresada
            driver.findElement(By.xpath("//ul[contains(@id,'pick-up')]//li[contains(text(),'" + hora + "')]")).click();
        } else {
             //Espera que la pagina liste las horas de vuelta
            d.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@id,'drop-off')]")));
            //Seleccionar la hora de vuelta ingresada
            driver.findElement(By.xpath("//ul[contains(@id,'drop-off')]//li[contains(text(),'" + hora + "')]")).click();
        }

    }


}