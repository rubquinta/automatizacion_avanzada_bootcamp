package automationcraft.testcreation.jetsmartG2.pages;


import bctsoft.equipo2jetsmart.pageobject.base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;

public class JetSmartHomePage extends SeleniumBase {


    public JetSmartHomePage(WebDriver driver) {
        super(driver);
    }

    //repositorio objetos JetSmartHomePage Vuelos
    private By btnPopUpClose = By.xpath("//div[@class='modal-header']//button[@type='button']");
    private By origenVuelo = By.xpath("//input[@placeholder='Origen']");
    private By destinoVuelo = By.xpath("//input[@placeholder='Destino']");
    private By chile = By.xpath("//li[contains(@class,'dg-country-selector-list-item ') " +
            "and @data-country='CL']");
    private By santiago = By.xpath("//li[contains(@class,'dg-city-selector-list-item ')" +
            " and contains(text(),'Santiago')]");
    private By arica = By.xpath("//li[contains(@class,'dg-city-selector-list-item ')"+
            "                and contains(text(),'Arica')]");
    private By argentina = By.xpath("//li[contains(@class,'dg-country-selector-list-item ') " +
            "and @data-country='AR']");
    private By buenosAires = By.xpath("//li[contains(@class,'dg-city-selector-list-item ')" +
            " and contains(text(),'Buenos Aires')]");
    private By btnbuscarVuelo = By.xpath("//button[@id='rt_button']//i");
    private By alertSinFechaOrigen = By.xpath("//input[@class='flatpickr-input error']");
    private By fechaVueloIda = By.xpath("//div[@class='dg-calendar open']//span[@aria-label='Mayo 19, 2021']");
    private By fechaVueloVuelta = By.xpath("//div[@class='dg-calendar open']//span[@aria-label='Mayo 25, 2021']");
    private By boxCiudades = By.xpath("(//div[contains(@class,'hidden-xs')])[2]");

    //repositorio objetos JetSmartHombePage Hoteles
    private By hoteles = By.xpath("//nav[contains(@class,'selector')]//span[contains(text(),'Hoteles')]");
    private By buscarHoteles = By.xpath("//input[@title='Buscar']");
    private By lugarHospedaje = By.xpath("//input[@id='b_destination']");
    private By primerSelectLugarHospedaje = By.xpath("//li[contains(@id,'ui-id-2')]");
    private By fechaCheckIn = By.xpath("//input[@id='checkInDate']");
    //Fecha Hoy
    String hoy = condBordeFechaHoy().split("-")[2];
    private By fechaHoy = By.xpath("(//a[contains(text(),'"+hoy+"')])[1]");
    private By fechaCheckOut = By.xpath("//input[@id='checkOutDate']");
    //Fecha Mañana
    String manana = condBordeFechaManana().split("-")[2];
    private By fechaManana = By.xpath("(//a[contains(text(),'"+manana+"')])[1]");

    //Hoteles Test Complejidad Media
    private By modificarFechaBusqueda = By.xpath("//span[contains(text(),'Modificar búsqueda')]");
    private By dataPickerCheckOutVentanaModFecha = By.xpath("//div[contains(@class,'date_picker_checkout')]//span");
    //private By flechaDerechaVentanaModFecha = By.xpath("")

    //repositorio objetos JetSmartHombePage Traslados
    private By traslados = By.xpath("//span[contains(text(),'Traslados')]");
    private By soloIda = By.xpath("//span[contains(text(),'Ida')]");
    private By campoOrigenTraslado = By.cssSelector("#input-pickup");
    private By opcionPrimeraTraslado = By.cssSelector("#item-0-0");
    private By getCampoDestinoTraslado = By.cssSelector("#input-dropoff");


    //ojo con este objero se le esta pasando un xpath pero el By es x CSS SELECTOR
    private By fechaCalendario = By.xpath("//div[@class='ctc-calendar__title']");
    private By flechaDerechaCalendario = By.xpath("//div[@title='Next month']");
    private By flechaIzqCalendario = By.xpath("//div[@title='Previous month']");
    private By opcionHoraIda = By.xpath("//ul[contains(@id,'pick-up')]");
    private By horaIda ;
    private By opcionHoraVuelta = By.xpath("//ul[contains(@id,'drop-off')]");
    private By horaVuelta ;
    private By unPasajero = By.xpath("//ul[@class='ct-list-container']//li[contains(text(),' 1 pasajero')]");

    //ojo con este objero se le esta pasando un CSS SELECTOR pero el By es por xpath
    private By buscarTraslado = By.cssSelector(".ct-btn");
    private By seleccionDia ;//FALTA PARAMETRO HORA VUELTA*/
    //keyword driven
    public void cerrarModuloSuscribete(){
        System.out.println("Cerramos Pop Up de Subscrpipcion");
        if(isDisplayed(btnPopUpClose)){
            esperaExplicitaElToBeClickleable30s(btnPopUpClose);
            click(btnPopUpClose);
        }
    }
    public void formularioTraslado(String origen, String destino, String fechaIda, String fechaVuelta, String horaIda, String horaVuelta) throws InterruptedException {
        click(traslados);
        switchFrameByIndex(2);


        if(fechaVuelta.isEmpty()){
            //PRESIONA EL CAMPO IDA
            click(soloIda);
        }
        //PASO 2 completar el campo origen
        type(origen,campoOrigenTraslado);

        //PASO 3 seleccionar la primer opción
        esperaExplicitaElToBeClickleable30s(opcionPrimeraTraslado);
        click(opcionPrimeraTraslado);
        //PASO 4 completar el campo destino
        type(destino,getCampoDestinoTraslado);
        //PASO 5 seleccionar la primer opción
        esperaExplicitaElToBeClickleable30s(opcionPrimeraTraslado);
        click(opcionPrimeraTraslado);
        //SELECCIONAR FECHA PARTICA PASO 6 y 7
        seleccionarFecha(fechaIda, driver, "pickup", horaIda);
        if(!fechaVuelta.isEmpty()){
            //SELECCIONAR FECHA REGRESO PASO 8 y 9
            seleccionarFecha(fechaVuelta, driver, "return", horaVuelta);
        }
        //PASO 10 Seleccionar 1 pasajero
        click(unPasajero);

        //PASO 11 Click en buscar
        click(buscarTraslado);

    }
    //keyword Driven Vuelos
    public void seleccionVueloOrigenSantiago(){

        if (isDisplayed(boxCiudades)) {
            System.out.println("Click en Origen");
            esperaExplicitaElToBeClickleable30s(origenVuelo);
            click(origenVuelo);

            System.out.println("Se despliega listado de paises");
            esperaExplicitaPresencia30s(chile);
            click(chile);

            esperaExplicitaPresencia30s(santiago);
            System.out.println("Click en Santiago (SCL)");
            click(santiago);
        }

    }
    public void seleccionVueloDestinoArgentina(){

        if (isDisplayed(boxCiudades)) {
            esperaExplicitaPresencia30s(argentina);
            System.out.println("Click en Argentina (ARG)");
            click(argentina);

            esperaExplicitaElToBeClickleable30s(buenosAires);
            System.out.println("Click en Buenos Aires (BUE)");
            click(buenosAires);
        }

    }



    public void fechasVuelo(){

        System.out.println("Ingresamos fecha de IDA");
        esperaExplicitaElToBeClickleable30s(fechaVueloIda);
        click(fechaVueloIda);

        System.out.println("Ingresamos fecha de VUELTA");
        esperaExplicitaElToBeClickleable30s(fechaVueloVuelta);
        click(fechaVueloVuelta);
    }

    public void seleccionVueloDestinoArica(){

        if (isDisplayed(boxCiudades)){
            esperaExplicitaPresencia30s(chile);
            click(chile);

            esperaExplicitaPresencia30s(arica);
            System.out.println("Click en Arica (ARI)");
            click(arica);

        }
    }

    public void buscarVuelo(){
        click(btnbuscarVuelo);
    }

    public String sinFecha(){
        if (isDisplayed(alertSinFechaOrigen)){
            return "Si Funciona el Alert por falta de fecha";
        }else {
            return "No funciona Alert por falta de fecha";
        }
    }

    //Logica Hoteles
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

    //Logica Traslado
    public  void seleccionarFecha(String fecha, WebDriver driver, String idaOVuelta, String hora) throws InterruptedException {
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
            mesCalendario=getText(fechaCalendario);
        //    mesCalendario = driver.findElement(By.xpath("//div[@class='ctc-calendar__title']")).getText();
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
                click(flechaDerechaCalendario);
               ;
            }
            //si el mes que muestra el calendario de la pagina esta despues del mes ingresado
            if (indiceMesActual > indiceMesIngresado) {
                //click en la flecha del mes anterior
                click(flechaIzqCalendario);

            }
            //si el mes que muestra el calendario es igual al ingresado
            if (indiceMesActual == indiceMesIngresado) {
                //Selecciono el dia de la fecha ingresada
               this.seleccionDia=By.xpath("//td[@id='input-" + idaOVuelta + "-date-calendar-" + fechaSeparada[0] + "']");
                click(seleccionDia);

                bandera = false;
            }
        }


        if (idaOVuelta.equals("pickup")) {
            //Espera que la pagina liste las horas de ida

            esperaExplicitaElToBeClickleable30s(opcionHoraIda);

            //Selecciona la hora de ida ingresada
            this.horaIda=By.xpath("//ul[contains(@id,'pick-up')]//li[contains(text(),'" + hora + "')]");
            click(horaIda);

        } else {
            //Espera que la pagina liste las horas de vuelta
            esperaExplicitaElToBeClickleable30s(opcionHoraVuelta);

            //Seleccionar la hora de vuelta ingresada
            this.horaVuelta = (By.xpath("//ul[contains(@id,'drop-off')]//li[contains(text(),'" + hora + "')]"));
            click(horaVuelta);
        }

    }


    public void entrarFormularioHoteles(){

        //PASO 1. Click funcionalidad Hoteles
        click(hoteles);
        switchFrameByIndex(0);
    }

    public void formularioHotelesBuscar(){

        //Paso 2. Click en buscar se abre nueva ventana
        esperaExplicitaElToBeClickleable30s(buscarHoteles);
        click(buscarHoteles);


    }

    //Keyword driven
    public void formularioHoteles(String lugarDestino){

        //PASO 1. Click funcionalidad Hoteles
        click(hoteles);
        //Entro en Frame de Formulario Hoteles renderizado por Booking
        switchFrameByIndex(0);

        //PASO 2. Enviar dato "Miami" en input lugar de alojamiento
        limpiar(lugarHospedaje);
        type(lugarDestino,lugarHospedaje);

        //PASO 3. Click en primera opción del dropdown dinámico.
        esperaExplicitaElToBeClickleable30s(primerSelectLugarHospedaje);
        click(primerSelectLugarHospedaje);

        //PASO 4.Click en datapicker checkIn
        esperaExplicitaElToBeClickleable30s(fechaCheckIn);
        click(fechaCheckIn);

        //PASO 5. Click en fecha exacta de checkIn
        esperaExplicitaElToBeClickleable30s(fechaHoy);
        click(fechaHoy);

        //PASO 6.Click en datapicker checkOut
        esperaExplicitaElToBeClickleable30s(fechaCheckOut);
        click(fechaCheckOut);

        //PASO 7. Click en fecha exacta checkOut (no mas de 30 noches de checkin). Mañana
        esperaExplicitaElToBeClickleable30s(fechaManana);
        click(fechaManana);

        //PASO 8. Click en Buscar.
        esperaExplicitaElToBeClickleable30s(buscarHoteles);
        click(buscarHoteles);


    }











}
