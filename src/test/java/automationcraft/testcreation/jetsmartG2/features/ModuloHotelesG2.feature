#Feature: Home Page Features

 # Scenario: Datos faltante en formulario
 #  Given un usuario desea buscar vuelo
 #   When envía el formulario con algún dato faltante
 #   Then aparece un alerta de dato faltante

  Feature: Modulo Hoteles Grupo 2
    Documento Gherkin que contiene todos los test para modulo hoteles en pagina JetSmart.
  Todos basados en el punto de vista del cliente.

    @G2 @Hoteles @Baja
    Scenario: Filtro y ordenos resultados de Hoteles por Precio y Estrellas
      Given Que soy un usuario e ingreso a la web
      When Ingreso a Formulario de Hoteles
      And Selecciono buscar con lugar y fechas por defecto
      And Filtro resultados por 5 estrellas
      And Filtro resultados por tramo mas economico
      And Presiono mostrar ordenados por precios mas bajo primero
      Then Se muestras los valores ordenados de menor a mayor en el rango solicitado

    @G2 @Hoteles @Media
      Scenario: Verifico alerta al modificar fecha a rango mayor del permitido.
        Given Que soy un usuario e ingreso a la web
        When Ingreso a Formulario de Hoteles
        And Selecciono buscar con lugar y fechas por defecto
        And Selecciono la primer opcion de hotel disponible
        And Abro ventana de Modificar en Pagina Siguiente
        And Selecciono CheckOut en rango mayor a 30 noches
        And Busco disponibilidad
        Then Se muestra alerta que solo puede ser fecha maxima 30 noches

