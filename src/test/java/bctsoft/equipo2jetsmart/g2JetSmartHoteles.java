package bctsoft.equipo2jetsmart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class g2JetSmartHoteles {


    public String atc_04_Hoteles() throws InterruptedException {

        //Se Buscar en formulario principal
        formularioHoteles();

        //Pasamos a segunda pagina
        FuncionesComunes.cambioNewWindow(driver);

        //Espera Explicita y carga de 2da Ventana
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //PASO 3. Se actualiza el componente de hoteles disponibles con 5 estrellas unicamente.
        //Click Filtro 5 Estrellas
        Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("(//div[@id='filter_class']//span[contains(text(),'5 estrellas')])[1]")))).click();

        //PASO 4  Hoteles disponibles con precios que cumplen con el rango seleccionad
        //Click en Filtro primer tramo
        Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//div[@class='filteroptions']//a[@data-id='pri-2']")))).click();

        // PASO 5.Se ordenan de manera ascendente en función del precio sin sobrepasar el rango especificado.
        //Click en ordenar por precio
        Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Precio (más bajo primero)')]"))).click();

        Thread.sleep(3000);
        List<WebElement> precios = driver.findElements(By.xpath("//div[@id='hotellist_inner']/div//div[contains(@class,'bui-price-display__value')]"));

        Iterator<WebElement> iterator = precios.iterator();

        List<Double> prices = new ArrayList<Double>();
        for (WebElement precio: precios) {
            Double valor = Double.parseDouble(precio.getText().split(" ")[1]);
            prices.add(valor);
            System.out.println(Double.parseDouble(precio.getText().split(" ")[1]));
        }
        for (int i = 1; i < prices.size(); i++) {
            if(prices.get(i-1)>prices.get(i)){
                return "No se ordenó correctamente";
            }
        }

        return "Correcto orden";
    }

    public String atc_05_Hoteles() throws InterruptedException {

        //Se Buscar en formulario principal
        formularioHoteles();

        //Pasamos a segunda pagina
        FuncionesComunes.cambioNewWindow(driver);

        //Espera Implicita y carga de 2da Ventana
        WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

        //PASO 3. Click primera opcion de Hotel
        wait.until(elementToBeClickable((By.xpath("//div[contains(@class,'sr_item_default')][1]" +
                        "//span[contains(@class,'hotel__name')]")))).click();

        //Espera a la nueva ventana o pestaña Muevo enfoque Drive a 3ra ventana
        FuncionesComunes.cambioNewWindow(driver);

        //PASO 4.Click en Boton "Modificar búsqueda".
        //Aparece Ventana Emergente con fechas actuales a modificar.
        wait.until(elementToBeClickable((By.xpath("//span[contains(text(),'Modificar búsqueda')]")))).click();

        //PASO 5. Click en datapicker checkOut.
        driver.findElement(By.xpath("//div[contains(@class,'date_picker_checkout')]//span")).click();

        //PASO 6. Click en fecha mayor a 30 noches a partir de hoy.
        //click primera arrow
        driver.findElement(By.xpath("//form[@id='hotelpage_availform']//div[contains(@data-bui-ref,'calendar-next')]")).click();
        //click en fecha mas de 30 noches
        String fecha30N = condBordeFechaOut();
        wait.until(elementToBeClickable(By.xpath("//form[@id='hotelpage_availform']//td[contains(@data-date,'"+fecha30N+"')]"))).click();

        //PASO 7. Click en "Ver Disponibilidad".
        driver.findElement(By.xpath("//form[@id='hotelpage_availform']//button[@type='submit']")).click();

        //RESULTADO ESPERADO: alerta "Lo sentimos, no es posible realizar reservas para más de 30 noches."
        wait.until(elementToBeClickable(By.xpath("//form[@id='hotelpage_availform']" +
                "//div[contains(text(),'Lo sentimos')]")));

        boolean seMuestra= driver.findElement(By.xpath("//form[@id='hotelpage_availform']" +
                "//div[contains(text(),'Lo sentimos')]")).isDisplayed();

        if(seMuestra){
            return "Alerta de fecha muy lejana SI funciona";

        }else {

            return "Alerta de fecha muy lejana NO funciona";
        }
    }


    public String atc_06_Hoteles(String lugarDestino, WebDriver driver) throws InterruptedException {

        //Se Buscar en formulario principal
        formularioHoteles(lugarDestino);

        //Pasamos a segunda pagina
        FuncionesComunes.cambioNewWindow(driver);

        //Espera Implicita y carga de 2da Ventana
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //PASO 9. Click en primera opción de Hotel disponible.
        wait.until(elementToBeClickable((By.xpath("//div[contains(@class,'sr_item_default')][1]" +
                "//span[contains(@class,'hotel__name')]")))).click();

        //PASO 10-Click en primer Select: "nr_rooms" del primer tipo de habitación disponible.
        FuncionesComunes.cambioNewWindow(driver);
        wait.until(elementToBeClickable((By.xpath("//tr[contains(@class,'js-rt-block-row')][1]" +
                "//select[@class='hprt-nos-select js-hprt-nos-select']"))));

        //PASO 11. Click en "1".
        Select hab = new Select(driver.findElement(By.xpath("//tr[contains(@class,'js-rt-block-row')][1]" +
                "//select[@class='hprt-nos-select js-hprt-nos-select']")));
        hab.selectByIndex(1);

        //PASO 12.Click en reservaré.
        driver.findElement(By.xpath("//button[contains(@class,'txp-bui-main')]")).click();

        //Espera a la nueva ventana o pestaña Muevo enfoque Drive a 4ta ventana
        FuncionesComunes.cambioNewWindow(driver);

        //PASO 13. Envío dato "Ruben" a input nombre.
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Ruben");

        //PASO 14. Envío dato "Quintana" a input apellido.
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Quintana");

        //PASO 15. Envío dato "ruben.quintana@tsoftlatam.com" a input E-mail
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("rubemo05@gmail.com");

        //PASO 16. Envío dato "ruben.quintana@tsoftlatam.com" a input Confirmar dirección E-mail.
        driver.findElement(By.xpath("//input[@id='email_confirm']")).sendKeys("rubemo05@gmail.com");

        //PASO 17. Click en "Siguiente: últimos datos".
        driver.findElement(By.xpath("//button[@type='submit' and @name='book']")).click();

        //Espera a la nueva ventana o pestaña Muevo enfoque Drive a 5ta ventana
        FuncionesComunes.cambioNewWindow(driver);

        //PASO 18. Click en "Completa la reserva".(sin ingresar datos)
        driver.findElement(By.xpath("//div[contains(@class,'bui-group__item')]//span[contains(text(),'Completa la reserva')]")).click();

        //RESULTADO: Carga de nuevo la misma página pero con alerta.
        wait.until(elementToBeClickable(By.xpath("(//div[contains(@class,'bui-alert')])[4]")));

        boolean muestraAlert = driver.findElement(By.xpath("(//div[contains(@class,'bui-alert')])[4]")).isDisplayed();

        if (muestraAlert) {
            return "Si funciona alert";
        } else {
            return "No funciona alert";
        }

    }


    public void formularioHoteles(String lugarDestino) throws InterruptedException {

        Thread.sleep(3000);
        //PRE-CONDICION: Debe cerrarse los PopUp inicial: Cerrar ventana de suscripción
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[@class='modal-header']//button[@class='close modal-close']" +
                        "//span"))).click();

        //PASO 1. Click en Funcionalidad Hoteles

        driver.findElement(By.xpath("//nav[contains(@class,'selector')]" +
                "//span[contains(text(),'Hoteles')]")).click();

        //Entro en Frame de Formulario Hoteles renderizado por Booking
        driver.switchTo().frame(0);

        //PASO 2. Enviar dato "Miami" en input lugar de alojamiento
        driver.findElement(By.xpath("//input[@id='b_destination']")).clear();
        driver.findElement(By.xpath("//input[@id='b_destination']")).sendKeys(lugarDestino);

        //PASO 3. Click en primera opción del dropdown dinámico.
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//li[contains(@id,'ui-id-2')]")))).click();

        //PASO 4.Click en datapicker checkIn
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//input[@id='checkInDate']")))).click();

        //PASO 5. Click en fecha exacta de checkIn
        String hoy = condBordeFechaHoy().split("-")[2];
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("(//a[contains(text(),'"+hoy+"')])[1]")))).click();

        //PASO 6.Click en datapicker checkOut
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//input[@id='checkOutDate']")))).click();

        //PASO 7. Click en fecha exacta checkOut (no mas de 30 noches de checkin). Mañana
        String manana = condBordeFechaManana().split("-")[2];
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("(//a[contains(text(),'"+manana+"')])[1]")))).click();

        //PASO 8. Click en Buscar.
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//input[@title='Buscar']")))).click();

    }

    public void formularioHoteles() throws InterruptedException {

        Thread.sleep(3000);
        //PRE-CONDICION: Debe cerrarse los PopUp inicial: Cerrar ventana de suscripción
        //Cerrar popUp de Subscripcion
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[@class='modal-header']//button[@class='close modal-close']" +
                        "//span"))).click();

        //PASO 1. Click en Funcionalidad Hoteles
        driver.findElement(By.xpath("//nav[contains(@class,'selector')]" +
                "//span[contains(text(),'Hoteles')]")).click();
        //Entro en Frame de Formulario Hoteles renderizado por Booking
        driver.switchTo().frame(0);

        //PASO 2. Click en buscar se abre nueva ventana
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//input[@title='Buscar']")))).click();

    }

    public String condBordeFechaOut() {
        //Fecha hoy
        Calendar after = Calendar.getInstance();

        String dia = Integer.toString(after.get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(after.get(Calendar.MONTH)+2);
        String anho = Integer.toString(after.get(Calendar.YEAR));

        boolean largoMesV = mes.length() < 2;
        boolean largoDiaV = Integer.parseInt(dia) < 10;

        String fechaFin = anho+"-"+(largoMesV ? "0" + mes : mes)+"-"+(largoDiaV ? "0"+dia : dia);
        System.out.println(fechaFin);

        return fechaFin;

    }

    public String condBordeFechaHoy() {
        //Fecha hoy
        Calendar after = Calendar.getInstance();

        String dia = Integer.toString(after.get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(after.get(Calendar.MONTH)+1);
        String anho = Integer.toString(after.get(Calendar.YEAR));

        boolean largoMesV = mes.length() < 2;
        boolean largoDiaV = Integer.parseInt(dia) < 10;

        String fecha = anho+"-"+(largoMesV ? "0" + mes : mes)+"-"+(dia);
        System.out.println(fecha);

        return fecha;

    }

    public String condBordeFechaManana() {
        //Fecha manana
        Calendar after = Calendar.getInstance();

        String dia = Integer.toString(after.get(Calendar.DAY_OF_MONTH)+1);
        String mes = Integer.toString(after.get(Calendar.MONTH));
        String anho = Integer.toString(after.get(Calendar.YEAR));

        boolean largoMesV = mes.length() < 2;
        boolean largoDiaV = Integer.parseInt(dia) < 10;

        String fecha = anho+"-"+(largoMesV ? "0" + mes : mes)+"-"+(dia);
        System.out.println(fecha);

        return fecha;

    }

    WebDriver driver;

    public g2JetSmartHoteles() {
        this.driver = new ChromeDriver();
    }

    public WebDriver getdriver() {
        return driver;
    }

    public void setdriver(WebDriver driver) {
        this.driver = driver;
    }
}



