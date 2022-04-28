#language: es
Característica: Automatizacion Test SQUADMAKERS

  Antecedentes: 
    Dado un usuario que realiza un login correcto con usuario "standard_user" y password "secret_sauce"

  @ComprarArticulos
  Esquema del escenario: Reservar vehiculo
    Entonces se seleccionan los productos a comprar
      | producto                |
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |
    Y se seleccion el carrito y se continua la compra
    Y se registra el cliente con nombres "Daniel Andres" apellidos "Abogado Guzman" y codigo postal "424852"
    Entonces se verifica la suma de los articulos y el impuesto
    Y valida la creacion de la orden

    Ejemplos: 
      | nombre  | celular     | correo                  |
      | Thainer | 31334211083 | Thainer.perez@gmail.com |
      
      
    @logout
    Escenario: salir de la pagina
	  Cuando presione el boton del menu
	  Entonces Presiona el boton logout y permite cerrar sesion
