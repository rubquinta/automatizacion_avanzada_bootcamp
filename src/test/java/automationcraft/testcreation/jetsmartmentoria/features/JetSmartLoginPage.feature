Feature: Login Page Features
  Scenario: login page title
    Given user is on login page
    When user gets the title of the page
    Then page title should be "Vuelos Baratos – JetSMART Aviones Nuevos | Sitio Oficial"

  Scenario: Forgot password link login page
    Given user is on login page
    Then forgot message link should be displayed

  #Scenario: Login with correct credentials