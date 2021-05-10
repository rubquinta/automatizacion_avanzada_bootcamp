#Feature Grupo 2: Vuelo
  Feature: Modulo Vuelo Grupo2
    Archivo Gherkin que contiene todas las casuisticas del modulo Vuelo de la web JetSmart
  estos escenarios estan basados en el punto de vista del usuario final.

  @bootcamp @G2
  Scenario: ingreso formulario vuelo incompleto
    Given que soy un usuario e ingreso a la web "JetSmart"
    When ingreso al formulario de vuelo
    And selecciono "Santiago de Chile" en el campo Origen
    And selecciono "Buenos Aires" en el campo Destino
    And presiono BuscarSmart
    Then aparece alerta de dato faltante
