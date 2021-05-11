package automationcraft.testcreation.jetsmartG2.pages;

import bctsoft.equipo2jetsmart.pageobject.base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JetSmartHotelesPaymentPage extends SeleniumBase {

    //Repositorio obejtos Ultima pagina de pago
    private By btnCompletaReserva = By.xpath("//div[contains(@class,'bui-group__item')]//span[contains(text(),'Completa la reserva')]");
    private By alertFaltaDeDatos = By.xpath("(//div[contains(@class,'bui-alert')])[4]");

    //KeyWord Driven de Ultima pagina de reserva
    public void clickRealizarReserva(){
        click(btnCompletaReserva);
    }

    public void presenciaDeAlert(){
        esperaExplicitaPresencia30s(alertFaltaDeDatos);
        boolean muestraAlert = isDisplayed(alertFaltaDeDatos);
        boolean alertOk= false;
        if (muestraAlert) {
            alertOk = true;
        }

        Assert.assertTrue(alertOk);
    }





    public JetSmartHotelesPaymentPage(WebDriver driver) {
        super(driver);
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
}
