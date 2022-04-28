package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestPages {
	WebDriver driver;
	
	// elementos del landing page					
			public By textUserName;
			public By textPassword;
			public By btnLogin;
			public By carritoCompras;
			public By checkout;
			public By firstName;
			public By lastName;
			public By postalCode;
			public By btnContinuar;
			public By precioElementos;
			public By summarySubtotal;
			public By summaryTax;
			public By btnFinish;
			public By menu;	
			public By logout;
			
			
		
		public TestPages(WebDriver driver) {
			this.driver = driver;					
			
			textUserName = By.id("user-name");
			textPassword = By.id("password");
			btnLogin = By.id("login-button");
			carritoCompras = By.xpath("//a[@class='shopping_cart_link']");
			checkout = By.id("checkout");
			firstName = By.name("firstName");
			lastName = By.name("lastName");
			postalCode = By.name("postalCode");
			btnContinuar = By.id("continue");
			precioElementos = By.xpath("//div[@class='inventory_item_price']");
			summarySubtotal = By.className("summary_subtotal_label");
			summaryTax = By.className("summary_tax_label");
			btnFinish = By.id("finish");			
            menu = By.id("react-burger-menu-btn");
			logout = By.id("logout_sidebar_link");
			
		}	
}
