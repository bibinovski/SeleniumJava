package Tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import PageObjects.TestFirstPOM;
import static PageObjects.TestFirstPOM.url;


/**
 * Created by emilbibinovski on 09/08/2016.
 */
public class TestFirst {

    private WebDriver driver;


    //before
    @Before
    public void setUp(){
        //code executed before every test
        this.driver = new FirefoxDriver();
        this.driver
                .manage()
                .timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
        this.driver
                .manage()
                .window()
                .maximize();
    }

    //test
    @Test
    public void logIn_validateCredentials_expectedNavigation(){

        this.driver.get(url);

        WebElement usernameField = TestFirstPOM.usernameField(driver);
        WebElement passwordField = TestFirstPOM.passwordField(driver);

        usernameField.sendKeys(TestFirstPOM.username);
        passwordField.sendKeys(TestFirstPOM.password);

        WebElement logInButton = TestFirstPOM.logInButton(driver);
        logInButton.click();

        WebElement landingPageHeader = TestFirstPOM.logInPageHeader(driver);

        String LandingPageText = landingPageHeader.getText().trim();
        boolean expectedTitle = LandingPageText.contains(TestFirstPOM.landingPageHeaderTextIS);
        Assert.assertTrue(expectedTitle);
    }


    @Test
    public void testGymListingForConsistance(){

        //this.logIn_validateCredentials_expectedNavigation();

        WebElement manageMenu
                = this.driver
                .findElement(By.xpath("//a[.='Manage ']"));

        String manageMenuText = manageMenu.getText().trim();
        boolean expectedManageText = manageMenuText.contains("Manage");
        Assert.assertTrue(expectedManageText);

        manageMenu.click();
        int dropDownItems = this.driver.findElements(By.cssSelector(".sub-menu .item")).size();
        Assert.assertEquals(dropDownItems, 8);

        WebElement gymsoption = this.driver.findElement(By.cssSelector(".sub-menu .item:nth-child(1)"));
        gymsoption.click();

        WebElement gymPageTitle = this.driver.findElement(By.cssSelector(".page-heading"));
        String gymPageTitleText = gymPageTitle.getText().trim();
        Assert.assertEquals("Gyms", gymPageTitleText);

        boolean gymCatalogExists = this.driver.findElements(By.cssSelector(".generic-box")).size() > 0;
        Assert.assertTrue(gymCatalogExists);

    }

    @Test
    public void addGym(){

        this.testGymListingForConsistance();

        this.driver.get("http://smartgym.dev/gym");
        WebElement addGymbutton = this.driver.findElement(By.cssSelector(".create-btn"));
        addGymbutton.click();

        Assert.assertTrue(driver.getPageSource().contains("Add Gym"));

        WebElement statusActive = this.driver.findElement(By.xpath("//span[ .= \"Active\"]"));
        statusActive.click();

        String gymName = "Test Gym 1";
        WebElement gymNameField = this.driver.findElement(By.id("name"));
        gymNameField.sendKeys(gymName);

        WebElement uploadButton = this.driver.findElement(By.id("logoUpload"));
        uploadButton.sendKeys("/Volumes/RewardGateway/smartgym/tests/_data/1.jpg");

        String address1Name = "bul. Hristo Botev 31";
        WebElement address1 = this.driver.findElement(By.id("address_1"));
        address1.sendKeys(address1Name);

        String city = "Plovidv";
        WebElement cityField = this.driver.findElement(By.name("city"));
        cityField.sendKeys(city);

        String phoneNumber = "123456987";
        WebElement phoneField = this.driver.findElement(By.name("phone"));
        phoneField.sendKeys(phoneNumber);


        WebElement companyDropdown = this.driver.findElement(By.cssSelector(".selectBox-dropdown"));
        companyDropdown.click();

        WebElement companyOptionRG = this.driver.findElement(By.cssSelector(".selectBox-dropdown-menu li:nth-child(2) a"));
        companyOptionRG.click();

    }



    //after
    @After
    public void tearDown(){
        this.driver.quit();
    }
}
