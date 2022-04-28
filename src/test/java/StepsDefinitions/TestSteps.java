package StepsDefinitions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import Acciones.PruebaAccion;
import CommonFuntions.BaseTest;

import cucumber.api.PendingException;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import io.cucumber.datatable.DataTable;

public class TestSteps {

	WebDriver driver;
	Logger log = Logger.getLogger(TestSteps.class);
	BaseTest baseTest;
	PruebaAccion pruebaAccion;

	public TestSteps() {
		// super(driver);
		this.driver = Driver.driver;
		pruebaAccion = new PruebaAccion(driver);
	}

	@Dado("^un usuario que realiza un login correcto con usuario \"([^\"]*)\" y password \"([^\"]*)\"$")
	public void dadoUnUsuarioQueRealizaUnLoginCorrectoConUsuarioPassword(String usuario, String password)
			throws Throwable {
		pruebaAccion.realizaLogin(usuario, password);
	}

	@Entonces("se seleccionan los productos a comprar")
	public void seSeleccionanLosProductosComprar(DataTable table) throws InterruptedException {
		pruebaAccion.seleccionarProducto(table);
	}

	@Y("se seleccion el carrito y se continua la compra")
	public void seSeleccionElCarritoComprasVerificaCompraProductos() throws InterruptedException {
		pruebaAccion.continuarComprar();
	}

	@Y("^se registra el cliente con nombres \"([^\"]*)\" apellidos \"([^\"]*)\" y codigo postal \"([^\"]*)\"$")
	public void seRegistraClienteNombresApellidoCodigoPostal(String nombres, String apellidos, String codigo)
			throws InterruptedException {
		pruebaAccion.registroCliente(nombres, apellidos, codigo);
	}

	@Entonces("se verifica la suma de los articulos y el impuesto")
	public void seVerificaSumaArticulosImpuesto() {
		pruebaAccion.verificarValores();
	}

	@Y("valida la creacion de la orden")
	public void validaLaCreacionOrden() {
		pruebaAccion.creacionOrden();
	}

	@Cuando("^presione el boton del menu$")
	public void presioneElBotonDelMenu() throws Throwable {
		pruebaAccion.clickMenu();
	}

	@Entonces("^Presiona el boton logout y permite cerrar sesion$")
	public void presionaElBotonLogoutYPermiteCerrarSesion() throws Throwable {
		pruebaAccion.cerrarSesion();
	}


}
