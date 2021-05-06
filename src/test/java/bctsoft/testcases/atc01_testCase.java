package bctsoft.testcases;

import bctsoft.pageobject.pages.JetSmartHomePage;
import org.testng.annotations.Test;

public class atc01_testCase extends TestBase{

    protected JetSmartHomePage jsHomePage;

    @Test
    public void atc01_test(){
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
    }


}
