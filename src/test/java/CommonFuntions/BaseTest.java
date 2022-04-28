package CommonFuntions;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class BaseTest {

	public WebDriver driver;
	private static Properties pro = new Properties();
	private static InputStream in = BaseTest.class.getResourceAsStream("../test.properties");
	private static Logger log = Logger.getLogger(BaseTest.class);
	
	public BaseTest(WebDriver driver) {
		this.driver = driver;
		
	}
	/*********** INICIO FUNC BASICAS SELENIUM ******************/

	public void Clear(By locator) {
		driver.findElement(locator).clear();
	}

	public void LimpiarConTeclado (By locator) {
		 driver.findElement(locator).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END));
	}
	
	public void EscribirElemento(By locator, String texto) {
		driver.findElement(locator).sendKeys(texto);
	}

	public void hacerClick(By locator) {
		driver.findElement(locator).click();
	}
	
	public void hacerClickString(String elemento) {
		driver.findElement(By.xpath("//div[text()='"+elemento+"']/following::div[2]/child::button")).click();
	}

	public String TextoElemento(By locator) {
		return driver.findElement(locator).getAttribute("value");
	}
	
	public Boolean assertEstaPresenteElemento (By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
			}catch (Exception e) {
			return false;
		}
		
	}
	
	public void assertvalidarEquals (String a, String b) {
		assertEquals(a,b);
	}
	
	public void selectByVisibleText (By locator, String valor) {
		
		Select se = new Select(driver.findElement(locator));
		se.selectByVisibleText(valor);
		;
	}
		
		
	public void EnviarEnter(By locator) {
		driver.findElement(locator).sendKeys(Keys.ENTER);
	}
	
		
	/*********** FIN FUNC BASICAS SELENIUM ******************/	
	
	/************* FUNC Assert Selenium ****************/
	public void assertTextoelemento (By locator, String Comparar) {
		
		 assertEquals(driver.findElement(locator).getText(),Comparar);
			
		}

	public void BuscarTextoPage(String Texto) {
		String pageText = driver.findElement(By.tagName("body")).getText();
		assertThat("Texto no encontrado", pageText, containsString(Texto));

	}	
	
	/************* FIN FUNC Assert Selenium ****************/
	

	/************ INICIO FUNC JAVASCRIPT ************/
	public void hacerScrollAbajo() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10)");
	}
	
	//metodo que usa JavaScrip para hacer Scroll
	public void Hacer_scroll(By locator) throws InterruptedException {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement Element = driver.findElement(locator);
			js.executeScript("arguments[0].scrollIntoView();", Element);
		}

	
	/************ FIN FUNC JAVASCRIPT ************/

	/************ INICIO DE ESPERAS ************/
	public void esperaExplicita(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void esperaExplicitaTexto(String texto) {
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"),texto));

	}
	
	public void esperaExplicitaNopresente(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public String leerPropiedades(String valor) {
		try {
			pro.load(in);
		} catch (Exception e) {
			log.error("====== ERROR LEYENDO EL ARCHIVO DE PROPIEDADES========= " + e);
		}
		return pro.getProperty(valor);
	}
	
	
	public String extraerTextoElemento(By locator) {
		return driver.findElement(locator).getText();
	}
	
	public void abrirNuevaVentana() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://github.com/login?return_to=https%3A%2F%2Fgithub.com%2Fjikan-me%2Fjikan");
	}
	
	 /*
     * Metodo que selecciona el tipo de captura que se va a realizar (Reporte o
     * Local) Modificar el archivo test.properties el valor "TipoCaptura"
     */
    public void adjuntarCaptura(String descripcion) {
        String tipoCaptura = leerPropiedades("TipoCaptura");
        if (tipoCaptura.equals("Local")) {
            adjuntarCapturaLocal(descripcion);
        } else {
            adjuntarCapturaReporte(descripcion);
        }
    }

    /*
     * Accion que se ejecuta y guarda agrega las imagenes en los reportes de Allure
     */
    public byte[] adjuntarCapturaReporte(String descripcion) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMMM-yyyy hh.mm.ss");
        byte[] captura = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        // log.info("**************** Evidencia Tomada Reporte:" + descripcion +
        // dateFormat.format(GregorianCalendar.getInstance().getTime())
        // +"**************");
        Allure.addAttachment(descripcion + dateFormat.format(GregorianCalendar.getInstance().getTime()),
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        return captura;
    }

    /*
     * Accion que guarda las capturas de imagenes en la siguiente ruta
     * src/test/resources/Data/Capturas
     */
    public void adjuntarCapturaLocal(String descripcion) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMMM-yyyy hh.mm.ss");
            String imageNombre = leerPropiedades("CapturasPath") + "\\" + descripcion
                    + dateFormat.format(GregorianCalendar.getInstance().getTime());
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // log.info("**************** Evidencia Tomada Local:" + descripcion +
            // dateFormat.format(GregorianCalendar.getInstance().getTime())
            // +"**************");
            FileUtils.copyFile(scrFile, new File(String.format("%s.png", imageNombre)));
        } catch (Exception e) {
            log.error("############## ERROR,  BaseTest - adjuntarCapturaLocal() #########" + e);
        }

    }
	
	public void selectValorLista(By lista, String Texto) throws InterruptedException {

        List<WebElement> ListaElement = driver.findElements(lista);
        for (WebElement webElement : ListaElement) {
            String str = limpiarCadena(webElement.getText());
            if (str.toUpperCase().contains(limpiarCadena(Texto.toUpperCase()))) {
            	hacerScrollAbajo();
            	webElement.click();             
                break;
            }
        }

    }
	
	public void selectValorListaValue(By lista,int value) throws InterruptedException {
		int contador = 1;
        List<WebElement> ListaElement = driver.findElements(lista);
        for (WebElement webElement : ListaElement) {
            String str = limpiarCadena(webElement.getText());
            if (contador==value) {            	
            	hacerScrollAbajo();
            	webElement.click();             
                break;
            }
            contador++;
        }

    }	
	
	public List<Double> returnDoubleListelement(By list){
		List<WebElement> ListaElement = driver.findElements(list);
		List<Double> result = new ArrayList();
		for (WebElement webElement : ListaElement) {
            String str = webElement.getText().replace("$", "");
            result.add(Double.parseDouble(str));
        }
		return result;
	}
	
	
	// Metodo que limpia los caracteres de las opciones quitando tildes
    public String limpiarCadena(String cadena) {
        cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);
        cadena = cadena.replaceAll("[^\\p{ASCII}]", "");
        return cadena;
    }
    
    public void Refrescar() {
        driver.navigate().refresh();
    }
    
    public String separarFecha(String fecha, String tipo) {
        String result = "";
        String[] cortarString = fecha.split("/");
        switch (tipo) {
            case "dia":
                result = cortarString[0];
                break;
            case "mes":
                result = cortarString[1];
                break;
            case "ano":
                result = cortarString[2];
                break;
        }
        return result;
    }
    
   
}
    

