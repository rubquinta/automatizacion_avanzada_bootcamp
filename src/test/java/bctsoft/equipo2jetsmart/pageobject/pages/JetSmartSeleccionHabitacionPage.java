package bctsoft.equipo2jetsmart.pageobject.pages;

import bctsoft.equipo2jetsmart.pageobject.base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class JetSmartSeleccionHabitacionPage extends SeleniumBase {

    public JetSmartSeleccionHabitacionPage(WebDriver driver){
        super(driver);
    }

    //repositorio Objetos Test Media complejidad
    private By btnModificarBusqueda = By.xpath("//span[contains(text(),'Modificar búsqueda')]");
    private By btnSeleccionarFechaVuelta = By.xpath("//div[contains(@class,'date_picker_checkout')]//span");
    private By btnFlechita = By.xpath("//form[@id='hotelpage_availform']//div[contains(@data-bui-ref,'calendar-next')]");
    private By fechaRegresoPasada;
    private By btnVerDisponibilidad = By.xpath("//form[@id='hotelpage_availform']//button[@type='submit']");
    private By msgErrorEsperado = By.xpath("//form[@id='hotelpage_availform'] //div[contains(text(),'Lo sentimos')]");

    //repositorio Objetos alta complejidad
    private By selectCantHabitaciones = By.xpath("//tr[contains(@class,'js-rt-block-row')][1]" +
            "//select[@class='hprt-nos-select js-hprt-nos-select']");
    private By btnReservare = By.xpath("//button[contains(@class,'txp-bui-main')]");


    public void setFechaRegresoPasada() {
        String fecha30N = condBordeFechaOut();
        this.fechaRegresoPasada = By.xpath("//form[@id='hotelpage_availform']//td[contains(@data-date,'"+fecha30N+"')]");
    }

    public void clickearModificarBusqueda(){
        esperaExplicitaElToBeClickleable30s(btnModificarBusqueda);
        click(btnModificarBusqueda);
    }

    public String seleccionarFechaDeVuelta(){
        //paso 5
        click(btnSeleccionarFechaVuelta);
        //paso 6
        click(btnFlechita);
        esperaExplicitaElToBeClickleable30s(fechaRegresoPasada);
        click(fechaRegresoPasada);
        //paso 7
        click(btnVerDisponibilidad);

        esperaExplicitaElToBeClickleable30s(msgErrorEsperado);
        if(isDisplayed(msgErrorEsperado))
            return "Alerta de fecha muy lejana SI funciona";
        else
            return "Alerta de fecha muy lejana NO funciona";
    }

    //FuncionesAuxiliares
    public void cambioNewWindow(WebDriver driver) {

        //Imprimo foco de driver actual
        System.out.println("\nDriver en posición: " + getTitle());
        System.out.println("Ventana 1: " + ventanasAbierta());

        //Almacena el ID de la ventana original
        Set<String> windowHandles = ventanasAbiertas();

        //Total de ventanas
        int cantWindows = cantidadVentanas();

        //Las almacena enumeradas
        Iterator<String> iterator = windowHandles.iterator();

        List<String> handlesArray = new ArrayList<String>();

        for (String handle : windowHandles) {
            System.out.println(handle);
            handlesArray.add(handle);
        }
        cambiarVentana(handlesArray.get(cantWindows - 1));

        System.out.println("\nDriver en posición actual : " + getTitle() +
                "\n" + handlesArray.get(cantWindows - 1) + " " + (cantWindows - 1));

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

    public void seleccionaUnaHabitacion(){
        Select hab = new Select(findElement(selectCantHabitaciones));
        hab.selectByIndex(1);
    }

    public void clickReservare(){
        click(btnReservare);
    }


}
