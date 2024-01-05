package stepdefination;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.OrderAPizzaPage;
import util.WebDriverFactory;

public class OrderAPizzaStepDef {
	OrderAPizzaPage orderAPizza;

	WebDriverFactory webDriverFactory = new WebDriverFactory();
	WebDriver driver = webDriverFactory.initalizeDriver();

	public OrderAPizzaStepDef() {
		orderAPizza = new OrderAPizzaPage(driver);
	}

	@Given("User launch Pizzahut application with {string}")
	public void user_launch_pizzahut_application_with(String expectedUrl) throws InterruptedException {
		orderAPizza.navigateToPizzahut(expectedUrl);
		Thread.sleep(2000);

	}

	@When("User wait for auto location black pop up screen")
	public void user_wait_for_auto_location_black_pop_up_screen() throws InterruptedException {
		System.out.println("User wait for auto location black pop up screen");
		// Thread.sleep(1000);

	}

	@Then("User close the pop up screen")
	public void user_close_the_pop_up_screen() throws InterruptedException {
		System.out.println("popup is closed");
		// Thread.sleep(1000);

	}

	@Then("User see pop up for delivery asking for enter location")
	public void user_see_pop_up_for_delivery_asking_for_enter_location() {
		Assert.assertTrue(orderAPizza.deliveryPopupPresent());

	}

	@Then("User type address as {string}")
	public void user_type_address_as(String string) throws InterruptedException {
		orderAPizza.sendLocation(string);
		Thread.sleep(2000);
	}

	@Then("User select first auto populate drop down option")
	public void user_select_first_auto_populate_drop_down_option() throws InterruptedException {
		orderAPizza.selectOneOption();
		Thread.sleep(3000);

	}

	@When("User navigate to deails page")
	public void user_navigate_to_deails_page() throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertTrue(orderAPizza.verifyOrderDetailPage());

	}

	@Then("User validate vegetarian radio button flag is off")
	public void user_validate_vegetarian_radio_button_flag_is_off() {
		Assert.assertTrue(orderAPizza.verifyVegRadioButtonDisabled());
	}

	@Then("User clicks on Pizzas menu bar option")
	public void user_clicks_on_pizzas_menu_bar_option() throws InterruptedException {
		orderAPizza.clickOnPizzaTab();
		Thread.sleep(2000);

	}

	@When("User select add button of any pizza from Recommended")
	public void user_select_add_button_of_any_pizza_from_recommended() throws InterruptedException {
		orderAPizza.addOnePizza();
		Thread.sleep(3000);

	}

	@Then("User see that the pizza is getting added under Your Basket")
	public void user_see_that_the_pizza_is_getting_added_under_your_basket() {
		Assert.assertTrue(orderAPizza.verifyPizzaIsAddedInTheBasket());
	}

	@Then("User validate pizza price plus Tax is checkout price")
	public void user_validate_pizza_price_plus_tax_is_checkout_price() {
		Assert.assertTrue(orderAPizza.verifyPizzaAndTaxInTheBasket());
	}

	@Then("User validate checkout button contains Item count")
	public void user_validate_checkout_button_contains_item_count() {
		Assert.assertTrue(orderAPizza.validateItemOnCheckoutBtn());
	}

	@Then("User validate checkout button contains total price count")
	public void user_validate_checkout_button_contains_total_price_count() {
		Assert.assertTrue(orderAPizza.validateInitalPrice());

	}

	@Then("User clicks on Drinks option")
	public void user_clicks_on_drinks_option() throws InterruptedException {
		orderAPizza.clickOnDrinkInBasket();
		Thread.sleep(2000);
	}

	@Then("User select Pepsi option to add into the Basket")
	public void user_select_pepsi_option_to_add_into_the_basket() throws InterruptedException {
		orderAPizza.addPepsiInBasket();
		Thread.sleep(2000);
	}

	@Then("User see {int} items are showing under checkout button")
	public void user_see_items_are_showing_under_checkout_button(Integer int1) {
		double delta = 0.000001d;
		// assertThat(int1 == orderAPizza.validateItemAfterAddingPepsi()).isTrue();
		Assert.assertEquals(int1, orderAPizza.validateItemAfterAddingPepsi(), delta);

	}

	@Then("User see total price is now more than before")
	public void user_see_total_price_is_now_more_than_before() {
		orderAPizza.amountIsHighAfterAddingPepsi();

	}

	@Then("User remove the Pizza item from Basket")
	public void user_remove_the_pizza_item_from_basket() throws InterruptedException {
		orderAPizza.removePizza();
		Thread.sleep(2000);

	}

	@Then("see Price tag got removed from the checkout button")
	public void see_price_tag_got_removed_from_the_checkout_button() {
		orderAPizza.verifyPriceremovedFromCheckout();
	}

	@Then("User see {int} item showing in checkout button")
	public void user_see_item_showing_in_checkout_button(Integer int1) {
		double delta = 0.000001d;
		Assert.assertEquals(int1, orderAPizza.validateItemAfterRemovingPizza(), delta);

	}

	@Then("User Clicks on Checkout button")
	public void user_clicks_on_checkout_button() throws InterruptedException {
		orderAPizza.clickOnCheckout();
		Thread.sleep(3000);
	}

	@Then("User see minimum order required pop up is getting displayed")
	public void user_see_minimum_order_required_pop_up_is_getting_displayed() {
		Assert.assertTrue(orderAPizza.verifyMinPopUp());

	}

	@After
	public void closeBrowser() {
		driver.quit();
	}

}
