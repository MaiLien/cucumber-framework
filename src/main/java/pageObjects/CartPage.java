package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	private WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[@class = 'cart-button']")
	private WebElement btn_Cart;

	@FindBy(xpath="//a[contains(@class,'checkout-button')]")
	private WebElement btn_ContinueToCheckout;

	public void moveMouseOverCartIcon() {
//		btn_Cart.click();
		Actions builder = new Actions(driver);
		Action moveMouseOverCartIcon = builder.moveToElement(btn_Cart).build();
		moveMouseOverCartIcon.perform();
	}

	public void clickOn_ContinueToCheckout() {
		btn_ContinueToCheckout.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
	}

}