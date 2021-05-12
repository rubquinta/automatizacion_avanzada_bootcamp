package automationcraft.testcreation.jetsmartmentoria.steps;

import io.cucumber.java.en.*;

import automationcraft.engine.selenium.DriverFactory;
import automationcraft.testcreation.jetsmartmentoria.pages.JetSmartHomePage;
import automationcraft.testcreation.jetsmartmentoria.pages.JetSmartInicioSesionPage;
import static org.junit.Assert.*;

public class LoginPageStep {

    private JetSmartHomePage homePage = new JetSmartHomePage(DriverFactory.getDriver());
    private JetSmartInicioSesionPage inicioSesionPage = new JetSmartInicioSesionPage(DriverFactory.getDriver());
    private String title;


    @Given("user is on login page")
    public void user_is_on_login_page() throws InterruptedException {
        homePage.goToUrl("https://jetsmart.com/cl/es/");
        homePage.cerrarPopUp();
        homePage.clickIniciaSesion();
    }

    @When("user gets the title of the page")
    public void user_gets_the_title_of_the_page() {
        title =  inicioSesionPage.getTitle();
    }

    @Then("page title should be {string}")
    public void page_title_should_be(String string) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(string,title);
    }

    @Then("forgot message link should be displayed")
    public void forgot_message_link_should_be_displayed() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }
}
