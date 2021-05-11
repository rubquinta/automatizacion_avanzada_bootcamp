Feature: Modulo de traslados G2
  Modulo Gherking que contiene todos los escenarios de Traslados. Siempre con el punto
  de vista del usuario.

 

  @G2 @Traslados @Baja
    Scenario: Despliegue de mensaje de datos imprecisos
     Given Un usuario que entra al modulo de Traslados de JetSmart Chile
      When Solicito un traslado de "Buenos Aires" a "Cordoba"
      And elijo "14-MAYO-2021" como fecha de ida y "21-MAYO-2021" como fecha de vuelta
      And Selecciono 1 pasajeros
      And Clickeo en buscar
      And Selecciono cambiar busqueda
      And Cambio la moneda a "ARS"
      Then Se muestran los precios en pesos argentinos


  @G2 @Traslados @Media
  Scenario: Despliegue de mensaje de datos imprecisos
    Given Un usuario que entra al modulo de Traslados de JetSmart Chile
    When Solicito un traslado solo de ida desde "Ezeiza" a "Urgench, Uzbekistán"
    And elijo "14-MAYO-2021" como fecha de ida y "" como fecha de vuelta
    And Selecciono 3 pasajeros
    And Clickeo en buscar
    Then Se muestra cartel pidiendo precisar datos

  @G2 @Traslados @Alta
  Scenario: Despliegue de mensaje de datos imprecisos
    Given Un usuario que entra al modulo de Traslados de JetSmart Chile
    When Solicito un traslado de "Ezeiza" a "Cordoba"
    And elijo "14-MAYO-2021" como fecha de ida y "21-MAYO-2021" como fecha de vuelta
    And Selecciono 3 pasajeros
    And Clickeo en buscar
    Then Hago click en Reservar ahora