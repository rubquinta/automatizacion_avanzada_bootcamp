package automationcraft.testcreation.jetsmartG2.pages;

import bctsoft.equipo2jetsmart.pageobject.base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JetSmartDatosHuespedPage extends SeleniumBase {

    //Repositorio de objetos Pagina 3 ya habitación seleccionada
    private By inputNombreHabitacion = By.xpath("//input[@id='firstname']");
    private By inputApellidoHabitacion = By.xpath("//input[@id='lastname']");
    private By inputEmailHab = By.xpath("//input[@id='email']");
    private By inputConfirmEmailHab = By.xpath("//input[@id='email_confirm']");
    private By btnUltimosDatosNextPage = By.xpath("//button[@type='submit' and @name='book']");

    public void formularioDatosHab(String nombre, String apellido, String correo){

        //PASO 13. Envío dato "Ruben" a input nombre.
        esperaExplicitaPresencia30s(inputNombreHabitacion);
        type(nombre,inputNombreHabitacion);

        //PASO 14. Envío dato "Quintana" a input apellido.
        esperaExplicitaPresencia30s(inputApellidoHabitacion);
        type(apellido, inputApellidoHabitacion);

        //PASO 15. Envío dato "ruben.quintana@tsoftlatam.com" a input E-mail
        esperaExplicitaPresencia30s(inputEmailHab);
        type(correo, inputEmailHab);

        //PASO 16. Envío dato "ruben.quintana@tsoftlatam.com" a input Confirmar dirección E-mail.
        esperaExplicitaPresencia30s(inputConfirmEmailHab);
        type(correo,inputConfirmEmailHab);

    }

    public void clickReservare(){
        //Paso 17
        click(btnUltimosDatosNextPage);
    }

    public JetSmartDatosHuespedPage(WebDriver driver) {
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
