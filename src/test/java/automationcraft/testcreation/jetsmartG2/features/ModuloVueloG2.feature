#Feature: Home Page Features

 # Scenario: Datos faltante en formulario
 #  Given un usuario desea buscar vuelo
 #   When envía el formulario con algún dato faltante
 #   Then aparece un alerta de dato faltante

  Feature: Modulo Vuelos Grupo 2
    Documento Gherkin que contiene todos los test para modulo vuelos en pagina JetSmart.
  Todos basados en el punto de vista del cliente.

    @G2 @Vuelos @Baja
  Scenario: Ingreso formulario vuelo imcompleto
    Given Que soy un usuario e ingreso a la web <JetSmart>
    When En el formulario ingreso origen <Santiago>
    And Selecciono destino <Buenos Aires>
    And Presiono en el boton buscar
    Then App muestra mensaje de alerta