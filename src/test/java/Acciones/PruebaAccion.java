package Acciones;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import CommonFuntions.BaseTest;
import Pages.TestPages;
import io.cucumber.datatable.DataTable;



public class PruebaAccion extends BaseTest {
	
	WebDriver driver;
	private static Logger log = Logger.getLogger(PruebaAccion.class);
	TestPages testPage = new TestPages(driver);
		
	public PruebaAccion(WebDriver driver) {
		super(driver);
	}
	
	
	public void realizaLogin(String user,String password) {
		log.info("************ Login *********");
		try {	
			esperaExplicita(testPage.btnLogin);			
			EscribirElemento(testPage.textUserName, user);
			EscribirElemento(testPage.textPassword, password);
			hacerClick(testPage.btnLogin);	
			adjuntarCaptura("PantallaInicialLogin");
		} catch (Exception e) {
			log.error("####### ERROR PruebaAccion - realizaLogin() ##########"+ e);
			assertTrue("####### ERROR PruebaAccion - realizaLogin() ##########"+ e,false);
		}
		
	}
	
	public void seleccionarProducto(DataTable tabla) throws InterruptedException {
		log.info("************ Seleccionar productos *********");
		List<Map<String, String>> data = tabla.asMaps(String.class, String.class);	
		try {
		for (Map<String, String> dataFeature : data) {
			hacerClickString(dataFeature.get("producto"));			
		}
		adjuntarCaptura("SeleccionProductos");
		} catch (Exception e) {
			log.error("####### ERROR PruebaAccion - seleccionarProducto() ##########"+ e);
			assertTrue("####### ERROR PruebaAccion - seleccionarProducto() ##########"+ e,false);
		}
				
	}
	
	public void continuarComprar() throws InterruptedException {
		log.info("************ continuar Comprar *********");		
		
		try {	
			esperaExplicita(testPage.carritoCompras);			
			hacerClick(testPage.carritoCompras);	
			esperaExplicita(testPage.checkout);	
			hacerClick(testPage.checkout);
			adjuntarCaptura("ContinuarCompra");
		} catch (Exception e) {
			log.error("####### ERROR PruebaAccion - continuarComprar() ##########"+ e);
			assertTrue("####### ERROR PruebaAccion - continuarComprar() ##########"+ e,false);
		}
		
				
	}
	
	public void registroCliente(String nombres, String apellidos, String codigo) throws InterruptedException {
		log.info("************ Registro Cliente *********");		
		
		try {	
			esperaExplicita(testPage.firstName);
			EscribirElemento(testPage.firstName,nombres);
			EscribirElemento(testPage.lastName,apellidos);
			EscribirElemento(testPage.postalCode,codigo);	
			hacerClick(testPage.btnContinuar);
			adjuntarCaptura("RegistroClientes");
		} catch (Exception e) {
			log.error("####### ERROR PruebaAccion - registroCliente() ##########"+ e);
			assertTrue("####### ERROR PruebaAccion - registroCliente() ##########"+ e,false);
		}
		
				
	}
	
	
	public void verificarValores(){
		log.info("************ Verificar Valores *********");		
		
		try {	
			List<Double> valores = returnDoubleListelement(testPage.precioElementos);
			double sumaValores=0;
			for (Double datos : valores) {
				sumaValores+= datos;
			}
			double impuestos=sumaValores*0.08;							
			assertEquals(Double.parseDouble(extraerTextoElemento(testPage.summarySubtotal).replaceAll("(?:(?!\\d|Free)(?s:.))*(\\d+(?:[.,]\\d+)*|Free)?","$1")),sumaValores,0.1);
			assertEquals(Double.parseDouble(extraerTextoElemento(testPage.summaryTax).replaceAll("(?:(?!\\d|Free)(?s:.))*(\\d+(?:[.,]\\d+)*|Free)?","$1")),impuestos,0.1);
			hacerClick(testPage.btnFinish);
			adjuntarCaptura("VerificarValores");
		} catch (Exception e) {
			log.error("####### ERROR PruebaAccion - verificarValores() ##########"+ e);
			assertTrue("####### ERROR PruebaAccion - verificarValores() ##########"+ e,false);
		}
	}
		
		
		public void creacionOrden(){
			log.info("************ Creacion de la orden *********");		
			
			try {										
				BuscarTextoPage("THANK YOU FOR YOUR ORDER");
				adjuntarCaptura("VerificarValores");
			} catch (Exception e) {
				log.error("####### ERROR PruebaAccion - creacionOrden() ##########"+ e);
				assertTrue("####### ERROR PruebaAccion - creacionOrden() ##########"+ e,false);
			}
		
				
	}
		
		public void clickMenu() {
			log.info("************ clickMenu *********");
			try {	
				esperaExplicita(testPage.menu);				
				hacerClick(testPage.menu);		

			} catch (Exception e) {
				log.error("####### ERROR PruebaAccion - clickMenu() ##########"+ e);
				assertTrue("####### ERROR PruebaAccion - clickMenu() ##########"+ e,false);
			}
		}	
		
		
		public void cerrarSesion() {
			log.info("************ cerrarSesion*********");
			try {				
				
				esperaExplicita(testPage.logout);
				hacerClick(testPage.logout);
	
			} catch (Exception e) {
				log.error("####### ERROR PruebaAccion - cerrarSesion() ##########"+ e);
				assertTrue("####### ERROR PruebaAccion - cerrarSesion() ##########"+ e,false);
			}
		}
	


}
