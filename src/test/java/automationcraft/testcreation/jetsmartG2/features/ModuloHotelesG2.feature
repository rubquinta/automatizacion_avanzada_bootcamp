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
      Given Que soy un usuario e ingreso a la web <JetSmart>
      When Ingreso a Formulario de Hoteles
      And Selecciono buscar alojamiento
      And Filtro resultados por 5 estrellas
      And Filtro resultados por tramo mas economico
      And Presiono mostrar ordenados por precios mas bajo primero
      Then Se muestras los valores ordenados de menor a mayor en el rango solicitado

    @G2 @Hoteles @Media
      Scenario: Verifico alerta al modificar fecha a rango mayor del permitido.
        Given Que soy un usuario e ingreso a la web <JetSmart>
        When Ingreso a Formulario de Hoteles
        And Selecciono buscar alojamiento
        And Selecciono la primer opcion de hotel disponible
        And Abro ventana de Modificar en Pagina Siguiente
        And Selecciono CheckOut en rango mayor a 30 noches
        And Busco disponibilidad
        Then Se muestra alerta que solo puede ser fecha maxima 30 noches

    @G2 @Hoteles @Alta
      Scenario: Verifico alerta de datos de pago fantantes
        Given Que soy un usuario e ingreso a la web <JetSmart>
        When Ingreso a Formulario de Hoteles
        And Elijo "Miami" como lugar de alojamiento
        And Selecciono fecha Hoy y Mañana como fechas checkInOut respectivamente
        And Selecciono buscar alojamiento
        And Selecciono la primer opcion de hotel disponible
        And Selecciono 1 Habitacion la primera de las opciones
        And Presiono en Reservare
        And Lleno formulario de huesped datos nombre: "Ruben", quintana: "Quintana", email: "rubemo05@gmail.com"
        And Presiono Siguiente Ultimos Datos
        And Presiono Completar reserva sin llenar datos de pago
        Then Se muestra alerta que faltan de datos de pago
