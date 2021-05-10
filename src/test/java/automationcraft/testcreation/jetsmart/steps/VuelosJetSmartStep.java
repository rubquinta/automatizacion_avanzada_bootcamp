package automationcraft.testcreation.jetsmart.steps;

import automationcraft.testcreation.jetsmart.pages.JetSmartHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class VuelosJetSmartStep {
    WebDriver driver;
    JetSmartHomePage objPage = new JetSmartHomePage(driver);
    @Given("el usuario ingresa a jetsmart")
    public void el_usuario_ingresa_a_jetsmart() {
        objPage.goToUrl("www.jetsmart.com");

    }

    @When("compra diez vuelos")
    public void compra_diez_vuelos() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @And("intentamos comprar un vuelo mas")
    public void intentamos_comprar_un_vuelo_mas() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("la web debe responder un mensaje de error")
    public void la_web_debe_responder_un_mensaje_de_error() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
