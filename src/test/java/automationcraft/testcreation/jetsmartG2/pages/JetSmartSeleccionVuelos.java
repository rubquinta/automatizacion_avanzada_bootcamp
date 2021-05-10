package automationcraft.testcreation.jetsmartG2.pages;

import bctsoft.equipo2jetsmart.pageobject.base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JetSmartSeleccionVuelos extends SeleniumBase {

    public JetSmartSeleccionVuelos(WebDriver driver) {
        super(driver);
    }


    //repositorio objetos JetSmartSeleccionVuelos
    private By btnContinuar = By.xpath("(//button[@class='rounded-primary-btn booking'])[1]");
    private By alertaErrorVueloNoSeleccionado = By.xpath("//div[@class='form-error-message ts-flight-select-error']");
    private By vueloIda = By.xpath("(//div[@class='text-center nowrap'])[1]");
    private By vueloLigeroIda = By.xpath("(//button[@class='bundle-button'])[3]");
    private By vueloVuelta = By.xpath("(//div[@class='text-center nowrap'])[2]");
    private By vueloLigeroVuelta = By.xpath("(//button[@class='bundle-button'])[7]");
    private By nombrePasajero = By.xpath("//input[@name='jetSmartPassengers[0].Name.First']");
    private By apellidoPasajero = By.xpath("//input[@name='jetSmartPassengers[0].Name.Last']");
    private By btnContinuar2 = By.xpath("(//button[contains(text(),'Continuar')])[1]");
    private By alertaErrorCompletarCampos = By.xpath("(//div[contains(text(),'Debes completar todos los campos.')])[1]");

    public void continuar(){
        System.out.println("Damos Click en continuar");
        click(btnContinuar);
    }
    public void continuar2(){
        System.out.println("Damos Click en continuar");
        click(btnContinuar2);
    }

    public String msjError(){
        if(getText(alertaErrorVueloNoSeleccionado).equals("Para continuar debes seleccionar tu vuelo")){
            return "Caso existoso, debes presionar los vuelos";
        }else{
            return "Test Case Failed";
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

    public void llenaVuelos(){
        esperaExplicitaElToBeClickleable30s(vueloIda);
        System.out.println("Ingresamos Vuelo de IDA");
        click(vueloIda);

        esperaExplicitaElToBeClickleable30s(vueloLigeroIda);
        System.out.println("Ingresamos Vuelo Ligero de IDA");
        click(vueloLigeroIda);

        esperaExplicitaElToBeClickleable30s(vueloVuelta);
        System.out.println("Ingresamos Vuelo de VUELTA");
        click(vueloVuelta);

        esperaExplicitaElToBeClickleable30s(vueloLigeroVuelta);
        System.out.println("Ingresamos Vuelo Ligero de VUELTA");
        click(vueloLigeroVuelta);
    }

    public void ingresaDatosPasajero(){

        if (isDisplayed(nombrePasajero)){
            esperaExplicitaElToBeClickleable30s(nombrePasajero);
            System.out.println("Ingresamos nombre de pasajero");
            type("ivan",nombrePasajero);

            esperaExplicitaElToBeClickleable30s(apellidoPasajero);
            System.out.println("Ingresamos apellido de pasajero");
            type("flores",apellidoPasajero);
        }

    }
    public String msjError2(){
        esperaExplicitaPresencia30s(alertaErrorCompletarCampos);
        if(getText(alertaErrorCompletarCampos).equals("Debes completar todos los campos.")){
            return "Caso existoso, debes completar todos los campos";
        }else{
            return "Test Case Failed";
        }
    }


}