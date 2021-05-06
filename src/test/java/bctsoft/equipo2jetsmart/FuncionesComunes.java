package bctsoft.equipo2jetsmart;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FuncionesComunes {
    public static void cambioNewWindow(WebDriver driver){

        //Imprimo foco de driver actual
        System.out.println("\nDriver en posición: " + driver.getTitle());
        System.out.println("Ventana 1: " + driver.getWindowHandle());

        //Almacena el ID de la ventana original
        Set<String> windowHandles = driver.getWindowHandles();

        //Total de ventanas
        int cantWindows = driver.getWindowHandles().size();

        //Las almacena enumeradas
        Iterator<String> iterator = windowHandles.iterator();

        List<String> handlesArray = new ArrayList<String>();

        for (String handle: windowHandles) {
            System.out.println(handle);
            handlesArray.add(handle);

        }

        driver.switchTo().window(handlesArray.get(cantWindows-1));

        System.out.println("\nDriver en posición actual : " + driver.getTitle() +
                "\n" + handlesArray.get(cantWindows-1) + " " + (cantWindows-1));

    }

}
