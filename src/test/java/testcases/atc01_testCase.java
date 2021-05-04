package testcases;

import org.testng.annotations.Test;
import pageobject.pages.JetSmartHomePage;

public class atc01_testCase extends TestBase{

    protected JetSmartHomePage jsHomePage;

    @Test
    public void atc01_test(){
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
    }


}
