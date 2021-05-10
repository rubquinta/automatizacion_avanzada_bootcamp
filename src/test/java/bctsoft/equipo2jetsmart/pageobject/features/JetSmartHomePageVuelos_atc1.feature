
Feature: Modulo Vuelo de web JetSmart
  Scenario: Datos faltante en formulario
    Given un usuario desea buscar vuelo
    When envía el formulario con algún dato faltante
    Then aparece un alerta de dato faltante
 