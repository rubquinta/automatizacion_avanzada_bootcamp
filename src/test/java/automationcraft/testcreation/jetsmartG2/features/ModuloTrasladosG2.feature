Feature: Modulo de traslados G2
  Modulo Gherking que contiene todos los escenarios de Traslados. Siempre con el punto
  de vista del usuario.

  @G2 @Traslados @Baja
  Scenario: Ver precios paquetes en pesos argentinos
    Given Un usuario que entra al modulo de Traslados de JetSmart Chile
    When Solicito un traslado de "Buenos Aires" a "Cordoba"
    And elijo la "Fecha de ida" y la "Fecha de Vuelta"
    And Clickeo en buscar
    And Selecciono cambiar busqueda
    And Cambio la moneda a pesos argentinos
    Then Se muestran los precios en pesos argentinos