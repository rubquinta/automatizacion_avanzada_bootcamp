package bctsoft.equipo2jetsmart.pageobject.pages;

import bctsoft.equipo2jetsmart.pageobject.base.SeleniumBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JetSmartSeleccionHoteles  extends SeleniumBase {

    public JetSmartSeleccionHoteles(WebDriver driver) {
        super(driver);
    }


    //Objetos Hoteles Test Baja Comp.
    private By filtroCincoEstrellasHoteles = By.xpath("(//div[@id='filter_class']//span[contains(text(),'5 estrellas')])[1]");
    private By filtroSegundoTramoPriceHoteles = By.xpath("//div[@class='filteroptions']//a[@data-id='pri-2']");
    private By filtroOrdenarPorPrecio = By.xpath("//a[contains(text(),'Precio (más bajo primero)')]");
    private By listaPreciosHoteles = By.xpath("//div[@id='hotellist_inner']/div//div[contains(@class,'bui-price-display__value')]");

    //Objetos Hoteles Test Media Comp.
    private By btnPrimerHotel = By.xpath("//div[contains(@class,'sr_item_default')][1] //span[contains(@class,'hotel__name')]");

    //Keyword Driven Hoteles

    public List<Double> imprimePreciosHoteles() throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> precios = findElements(listaPreciosHoteles);
        Iterator<WebElement> iterator = precios.iterator();
        List<Double> prices = new ArrayList<Double>();
        for (WebElement precio: precios) {
            Double valor = Double.parseDouble(precio.getText().split(" ")[1]);
            prices.add(valor);
            System.out.println(Double.parseDouble(precio.getText().split(" ")[1]));
        }

        return prices;

    }

    public void comparaOrdenPreciosHoteles(List<Double> prices) throws InterruptedException {
        Thread.sleep(3000);
        Boolean desordenado = false;

        for (int i = 1; i < prices.size(); i++) {

            if(prices.get(i-1)>prices.get(i)){
                desordenado = true;
            }
        }

        Assert.assertFalse(desordenado);
    }


    public void setFiltroCincoEstrellasHoteles() throws InterruptedException {
        //Click Filtro 5 Estrellas
        Thread.sleep(3000);
        esperaExplicitaPresencia30s(filtroCincoEstrellasHoteles);
        click(filtroCincoEstrellasHoteles);
    }

    public void setFiltroSegundoTramoPriceHoteles() throws InterruptedException {
        //Click Filtro segundo tramo precios
        Thread.sleep(3000);
        esperaExplicitaPresencia30s(filtroSegundoTramoPriceHoteles);
        click(filtroSegundoTramoPriceHoteles);
    }

    public void setFiltroOrdenarPorPrecio() throws InterruptedException {
        //Click en boton ordenar precio mas bajo
        Thread.sleep(3000);
        esperaExplicitaPresencia30s(filtroOrdenarPorPrecio);
        click(filtroOrdenarPorPrecio);
    }



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

    public void seleccionarPrimerHotelEnPantalla(){
        click(btnPrimerHotel);
    }


}
