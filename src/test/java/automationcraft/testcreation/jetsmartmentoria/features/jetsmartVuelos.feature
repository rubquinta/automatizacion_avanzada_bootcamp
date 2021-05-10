Feature: Funcionalidad Vuelo en Pagina JetSmart
  este feature diseña todos los escenarios necesarios para dar cobertura a la funcionalidad Vuelo

  Scenario: validar compra de pasajes a mas de 10 pasajeros
    Given el usuario ingresa a jetsmart
    When compra diez vuelos
    And  intentamos comprar un vuelo mas
    Then la web debe responder un mensaje de error