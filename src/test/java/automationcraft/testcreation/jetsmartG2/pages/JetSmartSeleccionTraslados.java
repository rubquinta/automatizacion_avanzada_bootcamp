package automationcraft.testcreation.jetsmartG2.pages;

import bctsoft.equipo2jetsmart.pageobject.base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JetSmartSeleccionTraslados extends SeleniumBase {
    public JetSmartSeleccionTraslados(WebDriver driver) {
        super(driver);
    }
    //repositorio objetos JetSmartSeleccionTraslados
    private By cambiarBusqueda = By.cssSelector(".ct-btn-transp");
    private By listaMoneda = By.id("currency-code");
    private By btnRealizarBusqueda = By.cssSelector(".ct-btn-s");
    private By preciosTraslados = By.cssSelector(".ct-clearfix");
    private By alertaOrigenNoPreciso = By.cssSelector(".ct-show .gt-location-alert__tooltip");
    private By btnSeleccionEstandar = By.xpath("//button[contains(@class,'ct-btn-p')]");
    private By logEstadoTraslado = By.cssSelector(".ct-active");

    public String seleccionarTrasladoEstandar(){
        esperaExplicitaElToBeClickleable30s(btnSeleccionEstandar);
        click(btnSeleccionEstandar);
          if (getText(logEstadoTraslado).contains("INFORMACIÓN")) {
            return "Exito";
        }
        return "Falló";
    }

    public String verificarAlertaOrigen()  {
        //Espera que se cargue la pagina
        esperaExplicitaElToBeClickleable30s(cambiarBusqueda);
        //Captura la alerta
        String texto = getText(alertaOrigenNoPreciso);
        if(texto.contains("Por favor, introduce la dirección completa para recibir un precio exacto.")){
            return "Exito";
        }
        return "Falló";

    }


    public String cambioMoneda(String moneda) {
        int contador = 0;
        esperaExplicitaElToBeClickleable30s(cambiarBusqueda);
        click(cambiarBusqueda);
        seleccionarByValueListaEstatica(listaMoneda, moneda);
        click(btnRealizarBusqueda);
        esperaExplicitaElToBeClickleable30s(cambiarBusqueda);
        List<WebElement> preciosProductos = findElements(preciosTraslados);
        for (WebElement precio : preciosProductos) {
            if (precio.getText().contains(moneda)) {
                System.out.println(precio.getText());
                contador++;
            }
        }
        if (contador == preciosProductos.size()) {
            return "Exito";
        } else {
            return "fallo";
        }
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
}
