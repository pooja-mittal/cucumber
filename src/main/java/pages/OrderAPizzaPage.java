package pages;

import java.util.Arrays;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import util.CommonPage;
import util.WebLogger;

public class OrderAPizzaPage {

	WebDriver driver;
	WebLogger logger;
	CommonPage commomPage;
	Alert alert;
	Double initalPriceBeforeAddingPepsi;

	public OrderAPizzaPage(WebDriver driver) {
		this.driver = driver;
		logger = new WebLogger();
		commomPage = new CommonPage(driver);
	}

	public String deliveryPopup = "//div//span[text()='Delivery']";
	public String sendDeliveryLocation = "//div//input[@placeholder='Enter your location for delivery']";
	public String selectOneOption = "//div[contains(@class,'overflow-scrolling-touch')]//button[2]";
	public String verifyOrderDetailPage = "//div//p//span[text()='Ordering for']";
	public String verifyVegRadioBtn = "//div[contains(@class,'side-menu')]//div//div//span[contains(@class, 'cursor-pointer bg-grey-light')]";
	public String clickOnPizzasTab = "//div[contains(@class,'side-menu')]//a//span[text()='Pizzas']";
	public String addAPizza = "//div//a[@class='list-item list-item--pizza '][1]//div//span[text()='Add']";
	public String verifyBasket = "//div//h2//span[text()='Your Basket']";
	public String verifyPizzaInBasket = "//div[contains(@class,'basket-upsell-carousel-card-wrapper')]";
	public String validatePizzaPrice = "//div//span[text()='Subtotal']/parent::span/following-sibling::span";
	public String vlidateTax = "//div//span[text()='Total Tax']/parent::span/following-sibling::span";
	public String validateItemOnCheckout = "//div[@class='relative']//span[contains(text(),'item')]";
	public String initalPrice = "//div[@data-synth='delivery-minimum-top']//span";
	public String clickRightArrowOnBasket = "//div//button[contains(@class,'basket-upsell-carousel-right-btn')]";
//	public String addPepsi = "//div//span[contains(@class,'basket-upsell-carousel-add-btn')]";
	public String addPepsi ="//div//span[text()='Pepsi']/parent::div/parent::div/parent::div/parent::button//span[contains(@class,'basket-upsell-carousel-add-btn')]";
	public String itemAfterAddingPepsi = "//a[contains(@class,'button button--primary')]//span//span[contains(text(),'item')]";
	public String amountAfteradding = "//span[@class='amountdue']";
	public String removePizza = "//div//button[contains(@class,'icon-close')]";
	public String checkOutAfterRemovingPizza = "//div//a[@data-synth='link--checkout']//span[contains(@class,'absolute')]";
	public String amountAfterRemovingPizza="//div[@data-synth='delivery-minimum-top']//span";
	public String clickOnCheckOut = "//div[@class='relative']//span[text()='Checkout']";
	public String validateMinPopup = "//div//a//span[@class='logo']";
	public String itemAfterRemovingPepsi="//button[contains(@class,'button button--primary')]//span//span[contains(text(),'item')]";

	public void navigateToPizzahut(String url) {
		logger.info(".....navigate to pizza hut url....");
		// WebDriverManager.chromedriver().setup();
		// ChromeOptions options=new ChromeOptions();
		// options.setExperimentalOption("excludeSwitches",
		// Arrays.asList("disable-popup-blocking"));
		//// options.addArguments("--disable-popup-blocking");
		// DesiredCapabilities capabilities = new DesiredCapabilities();
		// capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		// driver = new ChromeDriver(options);
		driver.get(url);
		driver.manage().window().maximize();
	}

	// public void handlePrompt() {
	// logger.info(".....User wait for auto location black pop up screen ....");
	// driver.switchTo().alert();
	// String alertMessage= driver.switchTo().alert().getText();
	// System.out.println(alertMessage);
	// }
	//
	// public void closeThePopUpScreen() {
	// logger.info(".....close the popup ....");
	// alert.accept();
	// }

	public boolean deliveryPopupPresent() {
		logger.info(".....verify deliver popup is present or not ....");
		if (commomPage.getWebElementByXpath(deliveryPopup).isDisplayed()) {
			return true;
		} else
			return false;
	}

	public void sendLocation(String location) {
		logger.info(".....enter the location ....");
		commomPage.getWebElementByXpath(sendDeliveryLocation).sendKeys(location);
	}

	public void selectOneOption() {
		logger.info(".....select one location from the dropdown ....");
		commomPage.getWebElementByXpath(selectOneOption).click();
	}

	public boolean verifyOrderDetailPage() {
		logger.info(".....verify user navigated to detial page ....");
		if (commomPage.getWebElementByXpath(verifyOrderDetailPage).isDisplayed()) {
			return true;
		} else
			return false;
	}

	public boolean verifyVegRadioButtonDisabled() {
		logger.info(".....verify vegetarian radio button is disabled by defualt ....");
		if (commomPage.getListOfWebElementByXpath(verifyOrderDetailPage).get(0).isDisplayed()) {
			return true;
		} else
			return false;
	}

	public void clickOnPizzaTab() {
		logger.info(".....click on pizza tab ....");
		commomPage.getWebElementByXpath(clickOnPizzasTab).click();
	}

	public void addOnePizza() {
		logger.info(".....user add one pizza ....");
		commomPage.getListOfWebElementByXpath(addAPizza).get(0).click();
	}

	public boolean verifyPizzaIsAddedInTheBasket() {
		logger.info(".....verify pizza is added in the basket....");
		if (commomPage.getWebElementByXpath(verifyBasket).isDisplayed()) {
			if (commomPage.getListOfWebElementByXpath(verifyPizzaInBasket).get(0).isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} else
			return false;
	}

	public boolean verifyPizzaAndTaxInTheBasket() {
		logger.info(".....validate pizza and tax price in the basket....");
		if (commomPage.getWebElementByXpath(validatePizzaPrice).isDisplayed()) {
			if (commomPage.getListOfWebElementByXpath(vlidateTax).get(0).isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} else
			return false;
	}

	public boolean validateItemOnCheckoutBtn() {
		logger.info(".....validate checkout button contains item ....");
		String totalItems = commomPage.getWebElementByXpath(validateItemOnCheckout).getText();
		String[] totalItemsArray = totalItems.split(" ");
		initalPriceBeforeAddingPepsi = Double.parseDouble((totalItemsArray)[0]);
		if (commomPage.getWebElementByXpath(validateItemOnCheckout).isDisplayed()) {
			return true;
		} else
			return false;
	}

	public boolean validateInitalPrice() {
		logger.info(".....validate inital price on the checkout ....");

		if (commomPage.getWebElementByXpath(initalPrice).isDisplayed()) {
			return true;
		} else
			return false;
	}

	public void clickOnDrinkInBasket() throws InterruptedException {
		logger.info(".....click on pepsi ....");
		for (int i = 0; i < 2; i++) {
			commomPage.getWebElementByXpath(clickRightArrowOnBasket).click();
			Thread.sleep(1000);
		}

	}

	public void addPepsiInBasket() {
		logger.info(".....add pepsi in the bakset ....");
		commomPage.getWebElementByXpath(addPepsi).click();

	}

	public int validateItemAfterAddingPepsi() {
		logger.info(".....validate User see 2 items are showing under checkout button ....");
		String totalItems = commomPage.getWebElementByXpath(itemAfterAddingPepsi).getText();
		String[] totalItemsArray = totalItems.split(" ");
		int totalNo = Integer.parseInt((totalItemsArray)[0]);
		return totalNo;
	}

	public boolean amountIsHighAfterAddingPepsi() {
		logger.info(".....validate amount is increased after adding pepsi....");
		Double finalPrice = Double.parseDouble(commomPage.getWebElementByXpath(amountAfteradding).getText().substring(1));
		if (finalPrice > initalPriceBeforeAddingPepsi) {
			System.out.println("...amount is high after adding pepsi...");
			return true;
		} else
			return false;
	}

	public void removePizza() {
		logger.info(".....remove pizza from the basket....");
		commomPage.getListOfWebElementByXpath(removePizza).get(0).click();
	}

	public boolean verifyPriceremovedFromCheckout() {
		logger.info(".....validate user see Price tag got removed from the checkout button....");
		if (commomPage.getWebElementByXpath(amountAfterRemovingPizza).isDisplayed()) {
			return true;
		}
		 else
			return false;
	}

	public int validateItemAfterRemovingPizza() {
		logger.info(".....validate total items on checkout after removing pizzaa ....");
		String totalItems = commomPage.getWebElementByXpath(itemAfterRemovingPepsi).getText();
		String[] totalItemsArray = totalItems.split(" ");
		int totalNo = Integer.parseInt((totalItemsArray)[0]);
		return totalNo;
	}

	public void clickOnCheckout() {
		logger.info(".....click on checkout button ....");
		commomPage.getWebElementByXpath(clickOnCheckOut);
	}
	
	public boolean verifyMinPopUp() {
		logger.info(".....User see minimum order required pop up is getting displayed ....");
		if (commomPage.getWebElementByXpath(validateMinPopup).isDisplayed()) {
			return true;
		} else
			return false;
	}

}
