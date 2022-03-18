package exercise4;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Exercise4 {
	WebDriver driver;
	String user = "joseira";
	String password = "Test_654123";
	ExtentReports report;
	ExtentTest testLog;

	@BeforeMethod(groups = { "Shop", "Cart", "Contact" })
	public void startUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
		report = ExtentFactory.getInstance();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	/*
	 * user uses the search bar, in the right, to search a term related with the
	 * name of an article
	 */
	@Test(groups = { "Shop" })
	public void tc_REQ001_001() {
		testLog = report.startTest("tc_REQ001_001");
		driver.get("https://practice.automationbro.com/shop/");
		testLog.log(LogStatus.INFO, "Entering in the page.");

		String searchTerm = "converse";
		WebElement searchInput = driver.findElement(By.xpath("//*[@id='woocommerce-product-search-field-0']"));
		searchInput.sendKeys(searchTerm);
		WebElement searchButton = driver.findElement(By.xpath("//*[@id='woocommerce_product_search-1']/form/button"));
		searchButton.click();
		testLog.log(LogStatus.INFO, "Serching the term " + searchTerm);

		int products = driver.findElements(By.xpath("//*[@id='primary']/ul//li")).size();
		String searchResult;
		for (int i = 1; i < products; i++) {
			searchResult = driver.findElement(By.xpath("//*[@id='primary']/ul//li[" + i + "]/a/h2")).getText();
			System.out.println("Result " + searchResult);
			Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()));
			testLog.log(LogStatus.INFO, "Comparing the results");
			// Thread.sleep(2000);
		}
		testLog.log(LogStatus.PASS, "Results are equivalent to search terms");
	}

	/*
	 * the user navigates in the shop pages looking the products 9 by 9
	 */
	@Test(groups = { "Shop" })
	public void tc_REQ003_001() {
		testLog = report.startTest("tc_REQ003_001");
		// enter to: https://practice.automationbro.com/shop/
		driver.get("https://practice.automationbro.com/shop/");
		testLog.log(LogStatus.INFO, "Entering in the page.");
		// check how many products are displayed
		testLog.log(LogStatus.INFO, "check how many products are displayed");
		int products = driver.findElements(By.xpath("//*[@id='primary']/ul//li")).size();
		Assert.assertTrue(products <= 9);
		testLog.log(LogStatus.PASS, "User navigates in the shop pages looking the products 9 by 9");
	}

	/*
	 * check behavior when a registered user views a product and after looks for
	 * recently viewed products
	 */
	@Test(groups = { "Shop" })
	public void tc_REQ005_001() throws InterruptedException {
		testLog = report.startTest("tc_REQ005_001");
		driver.get("https://practice.automationbro.com/my-account/");
		testLog.log(LogStatus.INFO, "Entering in the page.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		// login
		WebElement userInput = driver.findElement(By.xpath("//*[@id='username']"));
		WebElement passwordInput = driver.findElement(By.xpath("//*[@id='password']"));
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id='customer_login']/div[1]/form/p[3]/button"));
		userInput.sendKeys(user);
		passwordInput.sendKeys(password);
		loginBtn.click();
		testLog.log(LogStatus.INFO, "Login performed.");
		// go to shop
		Thread.sleep(5000);
		WebElement shopMenu = driver.findElement(By.xpath("//*[@id='menu-item-567']/a"));
		shopMenu.click();
		testLog.log(LogStatus.INFO, "In the shop.");
		// open a product
		int products = driver.findElements(By.xpath("//*[@id='primary']/ul//li")).size();
		int getProduct = (int) (Math.random() * products + 1);
		System.out.println(getProduct);
		WebElement product = driver.findElement(By.xpath("//*[@id='primary']/ul//li[" + getProduct + "]/a/h2"));
		String productSelected = product.getText();
		product.click();

		testLog.log(LogStatus.INFO, "Product opened.");
		// return to shop
		Thread.sleep(5000);
		shopMenu = driver.findElement(By.xpath("//*[@id='menu-item-567']/a"));
		shopMenu.click();
		testLog.log(LogStatus.INFO, "Returned to the shop.");
		// check if the item is displayed in recent files
		WebElement recentProduct = driver
				.findElement(By.xpath("//*[@id='woocommerce_recently_viewed_products-1']/ul/li[1]/a/span"));
		System.out.println("selected " + productSelected);
		System.out.println("recent " + recentProduct.getText());
		wait.until(ExpectedConditions.visibilityOf(recentProduct));
		Assert.assertEquals(productSelected, recentProduct.getText());
		testLog.log(LogStatus.PASS, "item is displayed in recent files.");
	}

	/* check behavior when all fields are correctly entered */
	@Test(groups = { "Contact" })
	public void tc_REQ007_001() {
		testLog = report.startTest("tc_REQ007_001");
		driver.get("https://practice.automationbro.com/contact/");
		testLog.log(LogStatus.INFO, "the page is displayed.");
		// Enter data
		WebElement nameField = driver.findElement(By.xpath("//*[@id='evf-277-field_ys0GeZISRs-1']"));
		WebElement emailField = driver.findElement(By.xpath("//*[@id='evf-277-field_LbH5NxasXM-2']"));
		WebElement phoneField = driver.findElement(By.xpath("//*[@id='evf-277-field_66FR384cge-3']"));
		WebElement messageField = driver.findElement(By.xpath("//*[@id='evf-277-field_yhGx3FOwr2-4']"));
		WebElement submitBtn = driver.findElement(By.xpath("//*[@id='evf-submit-277']"));
		nameField.sendKeys("Jose");
		emailField.sendKeys("jose@gmail.com");
		phoneField.sendKeys("1234567891");
		messageField.sendKeys("Hola mundo");
		testLog.log(LogStatus.INFO, "RData entered.");
		// click on submit
		submitBtn.click();
		testLog.log(LogStatus.INFO, "Info submited.");
		// verify message
		WebElement submitMessage = driver.findElement(By.xpath(
				"//*[@id='primary']/div/div/div/section[3]/div/div/div/div/div/section[2]/div/div/div[2]/div/div/div/div/div/div/div"));
		System.out.println(submitMessage.getText());
		Assert.assertEquals(submitMessage.getText(), "Thanks for contacting us! We will be in touch with you shortly");
		testLog.log(LogStatus.PASS, "The message was sended");
	}

	/* check behavior when name is empty */
	@Test(groups = { "Contact" })
	public void tc_REQ007_002() {
		testLog = report.startTest("tc_REQ007_002");
		driver.get("https://practice.automationbro.com/contact/");
		testLog.log(LogStatus.INFO, "Entering in the page.");

		// Enter data
		WebElement emailField = driver.findElement(By.xpath("//*[@id='evf-277-field_LbH5NxasXM-2']"));
		WebElement phoneField = driver.findElement(By.xpath("//*[@id='evf-277-field_66FR384cge-3']"));
		WebElement messageField = driver.findElement(By.xpath("//*[@id='evf-277-field_yhGx3FOwr2-4']"));
		WebElement submitBtn = driver.findElement(By.xpath("//*[@id='evf-submit-277']"));

		emailField.sendKeys("jose@gmail.com");
		phoneField.sendKeys("1234567891");
		messageField.sendKeys("Hola mundo");
		submitBtn.click();
		testLog.log(LogStatus.INFO, "Data entered");


		WebElement fieldRequired = driver.findElement(By.xpath(
				"//*[@id='primary']/div/div/div/section[3]/div/div/div/div/div/section[2]/div/div/div[2]/div/div/div/div/div/div/div"));
		Assert.assertTrue(fieldRequired.isDisplayed());
		testLog.log(LogStatus.PASS, "No name error mesage displayed");

	}

	/* check behavior when email is in wrong format */
	@Test(groups = { "Contact" })
	public void tc_REQ007_003() throws InterruptedException {
		testLog = report.startTest("tc_REQ007_003");
		driver.get("https://practice.automationbro.com/contact/");
		Actions action = new Actions(driver);
		testLog.log(LogStatus.INFO, "Page is displayed.");

		// Enter data
		WebElement nameField = driver.findElement(By.xpath("//*[@id='evf-277-field_ys0GeZISRs-1']"));
		WebElement emailField = driver.findElement(By.xpath("//*[@id='evf-277-field_LbH5NxasXM-2']"));
		WebElement phoneField = driver.findElement(By.xpath("//*[@id='evf-277-field_66FR384cge-3']"));
		WebElement messageField = driver.findElement(By.xpath("//*[@id='evf-277-field_yhGx3FOwr2-4']"));
		WebElement submitBtn = driver.findElement(By.xpath("//*[@id='evf-submit-277']"));

		action.moveToElement(nameField).build().perform();
		nameField.sendKeys("Jose");
		emailField.sendKeys("jose@");
		phoneField.sendKeys("1234567891");

		testLog.log(LogStatus.INFO, "Entering the data.");

		Thread.sleep(5000);
		WebElement errorMessage = driver.findElement(By.xpath("//*[@id='evf-277-field_LbH5NxasXM-2-error']"));
		messageField.sendKeys("Hola mundo");
		// click on submit
		submitBtn.click();
		Assert.assertEquals(errorMessage.getText(), "Please enter a valid email address.");
		testLog.log(LogStatus.PASS, "Error is correctly displayed");

	}

	/*
	 * Validate that products are added to the cart after click on add to cart
	 * button
	 */
	@Test(groups = { "Cart" })
	public void tc_REQ008_001() throws InterruptedException {
		testLog = report.startTest("tc_REQ008_001");
		// enter to: https://practice.automationbro.com/shop/
		driver.get("https://practice.automationbro.com/shop/");
		testLog.log(LogStatus.INFO, "Page is displayed.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// choose a random product to click on add to cart button
		int products = driver.findElements(By.xpath("//*[@id='primary']/ul//li")).size();
		int getProduct = (int) (Math.random() * products + 1);
		WebElement addBtn = driver
				.findElement(By.xpath("//*[@id='primary']/ul//li[" + getProduct + "]//a[text()='Add to cart']"));
		String selectedProduct = driver.findElement(By.xpath("//*[@id='primary']/ul//li[" + getProduct + "]/a/h2"))
				.getText();
		addBtn.click();
		Thread.sleep(2000);
		testLog.log(LogStatus.INFO, "Random product added to the cart.");
		// click on any "view cart" link
		WebElement cartBtn = driver.findElement(By.xpath("//*[@id='primary-menu']/li[9]/a/i"));
		wait.until(ExpectedConditions.visibilityOf(cartBtn));
		cartBtn.click();
		testLog.log(LogStatus.INFO, "Cart page is displayed.");
		// verify if the item in the cart is the same previosly added
		String nameOfProduct = driver
				.findElement(By.xpath("//*[@id='post-7']/div/div[2]/form/table/tbody/tr[1]/td[3]/a")).getText();
		Assert.assertTrue(selectedProduct.equals(nameOfProduct));
		testLog.log(LogStatus.PASS, "the item in the cart is the same previosly added.");
	}

	/*
	 * validate that the cart icon reflects when user adds a product to the cart
	 */
	@Test(groups = { "Cart" })
	public void tc_REQ009_001() throws InterruptedException {
		testLog = report.startTest("tc_REQ009_001");
		// enter to: https://practice.automationbro.com/shop/
		driver.get("https://practice.automationbro.com/shop/");

		testLog.log(LogStatus.INFO, "Page displayed.");
		// choose a random number of products to click on add to cart button
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		int products = driver.findElements(By.xpath("//*[@id='primary']/ul//li")).size();
		int numberOfProducts = (int) (Math.random() * 5 + 1);
		int getProduct = 1;
		WebElement addBtn;
		for (int i = 0; i < numberOfProducts; i++) {
			getProduct = (int) (Math.random() * products + 1);
			addBtn = driver
					.findElement(By.xpath("//*[@id='primary']/ul//li[" + getProduct + "]//a[text()='Add to cart']"));
			addBtn.click();
			Thread.sleep(2000);
		}
		testLog.log(LogStatus.INFO, "Random number of products selected.");
		// validate if the cart icon is displayed in the top of the page
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='primary-menu']/li[9]/a/i")).isDisplayed());
		testLog.log(LogStatus.INFO, "Cart icons is displayed.");
		// validate the number of products in the cart matches with the icon number
		Assert.assertTrue(Integer.parseInt(
				driver.findElement(By.xpath("//*[@id='primary-menu']/li[9]/a/span")).getText()) == numberOfProducts);
		testLog.log(LogStatus.PASS, "The number of products in the cart matches with the icon number.");
	}

	/* validate when user removes a product from the cart */
	@Test(groups = { "Cart" })
	public void tc_REQ010_001() throws InterruptedException {
		testLog = report.startTest("tc_REQ010_001");
		driver.get("https://practice.automationbro.com/shop/");
		testLog.log(LogStatus.INFO, "Page opened");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		int products = driver.findElements(By.xpath("//*[@id='primary']/ul//li")).size();
		int getProduct = 1;
		WebElement addBtn;
		getProduct = (int) (Math.random() * products + 1);
		addBtn = driver.findElement(By.xpath("//*[@id='primary']/ul//li[" + getProduct + "]//a[text()='Add to cart']"));
		addBtn.click();
		testLog.log(LogStatus.INFO, "Random product added");
		
		Thread.sleep(2000);
		WebElement cartBtn = driver.findElement(By.xpath("//*[@id='primary-menu']/li[9]/a/i"));
		wait.until(ExpectedConditions.visibilityOf(cartBtn));
		cartBtn.click();

		testLog.log(LogStatus.INFO, "Cartpage is displayed.");
		int columns = driver.findElements(By.xpath("//*[@id='post-7']/div/div[2]/form/table/tbody//tr")).size();
		System.out.println(columns);
		String nameOfProduct;
		
		WebElement deletedProduct;
		for (int i = columns - 1; i > 0; i--) {
			nameOfProduct = driver
					.findElement(By.xpath("//*[@id='post-7']/div/div[2]/form/table/tbody/tr[" + i + "]/td[3]/a"))
					.getText();
			driver.findElement(By.xpath("//*[@id='post-7']/div/div[2]/form/table/tbody/tr[" + i + "]/td/a")).click();
			Thread.sleep(4000);
			System.out.println(driver.findElement(By.xpath("//*[@id='post-7']/div/div[2]/div[1]/div")).getText());
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='post-7']/div/div[2]/div[1]/div")).getText()
					.contains(nameOfProduct));

			testLog.log(LogStatus.INFO, "Product correctly deleted");
		}

		testLog.log(LogStatus.PASS, "all elements were correctly deleted");
	}

	/*
	 * Validate a registered user is able to checkout by filling the fields with the
	 * proper values
	 */
	@Test(groups = { "Cart" })
	public void tc_REQ011_001() throws InterruptedException {
		testLog = report.startTest("tc_REQ011_001");
		// enter to: https://practice.automationbro.com/my-account/
		driver.get("https://practice.automationbro.com/my-account/");
		testLog.log(LogStatus.INFO, "Page is displayed");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// login
		WebElement userInput = driver.findElement(By.xpath("//*[@id='username']"));
		WebElement passwordInput = driver.findElement(By.xpath("//*[@id='password']"));
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id='customer_login']/div[1]/form/p[3]/button"));
		userInput.sendKeys(user);
		passwordInput.sendKeys(password);
		loginBtn.click();
		testLog.log(LogStatus.INFO, "Success Login.");
		// click on shop menu
		WebElement shopMenu = driver.findElement(By.xpath("//*[@id='menu-item-567']/a"));
		shopMenu.click();
		testLog.log(LogStatus.INFO, "Shop page is displayed");
		// choose a random number of products to click on add to cart button
		int products = driver.findElements(By.xpath("//*[@id='primary']/ul//li")).size();
		int numberOfProducts = (int) (Math.random() * 5 + 1);
		int getProduct;
		WebElement addBtn;
		for (int i = 0; i < numberOfProducts; i++) {
			getProduct = (int) (Math.random() * products + 1);
			addBtn = driver
					.findElement(By.xpath("//*[@id='primary']/ul//li[" + getProduct + "]//a[text()='Add to cart']"));
			addBtn.click();
			// wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='primary']/ul//li["+getProduct+"]//a[text()='View
			// cart']"))));
			Thread.sleep(2000);
		}
		testLog.log(LogStatus.INFO, "Random products added to the cart");
		// click on any "view cart" link
		WebElement cartBtn = driver.findElement(By.xpath("//*[@id='primary-menu']/li[9]/a/i"));
		wait.until(ExpectedConditions.visibilityOf(cartBtn));
		cartBtn.click();
		testLog.log(LogStatus.INFO, "Cart displayed");
		// click on "proceed to checkout"

		testLog.log(LogStatus.INFO, "Check out page displayed");
		
		driver.findElement(By.xpath("//*[@id='post-7']/div/div[2]/div[2]/div/div/a")).click();
		// fill first name field
		driver.findElement(By.xpath("//*[@id='billing_first_name']")).sendKeys("Phillip");
		// fill last name field
		driver.findElement(By.xpath("//*[@id='billing_last_name']")).sendKeys("Sherman");
		// fill country/region field
		driver.findElement(By.xpath("//*[@id='select2-billing_country-container']")).click();
		

		WebElement dropdownInput = driver.findElement(By.xpath("/html/body/span[2]/span/span[1]/input"));
		dropdownInput.sendKeys("aus");
		dropdownInput.sendKeys(Keys.ENTER);
	

		// fill street address field
		driver.findElement(By.xpath("//*[@id='billing_address_1']")).sendKeys("Calle Wallaby");

		// fill suburb field
		driver.findElement(By.xpath("//*[@id='billing_city']")).sendKeys("Hornsby Heights NSW");

		// fill state field
		driver.findElement(By.xpath("//*[@id='select2-billing_state-container']")).click();
		
		WebElement stateDropInput = driver.findElement(By.xpath("/html/body/span[2]/span/span[1]/input"));
		stateDropInput.sendKeys("new");
		stateDropInput.sendKeys(Keys.ENTER);

		// fill postal code field
		driver.findElement(By.xpath("//*[@id='billing_postcode']")).sendKeys("2077");

		// fill phone field
		driver.findElement(By.xpath("//*[@id='billing_phone']")).sendKeys("1234567891");

		testLog.log(LogStatus.INFO, "Fields Filled Correctly");
		
		// click on place order
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='place_order']")).click();

		// assert order message
		Thread.sleep(5000);
		WebElement messageElement = driver.findElement(
				By.xpath("//*[@id='post-8']/div/div/div/div/section/div/div/div/div/div/div/div/div/div[2]/div/p"));
		// wait.until(ExpectedConditions.visibilityOf(messageElement));
		String messageString = messageElement.getText();
		Assert.assertEquals(messageString, "Thank you. Your order has been received.");
		testLog.log(LogStatus.PASS, "Thanks Message is displayed");
		
	}

	@AfterMethod(groups = { "Shop", "Cart", "Contact" })
	public void tearDown(ITestResult testResult) throws InterruptedException, IOException {
		System.out.println("EXIT");
		if (testResult.getStatus() == ITestResult.FAILURE) {
			String path = ScreenshotToTest.takeScreenshot(driver, testResult.getName());
			String imgPath = testLog.addScreenCapture(path);
			testLog.log(LogStatus.FAIL, "Test Failed", imgPath);
		}
		report.endTest(testLog);
		report.flush();
		Thread.sleep(5000);
		driver.quit();
	}
	//
}
