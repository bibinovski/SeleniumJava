package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by emilbibinovski on 11/08/2016.
 */
public class TestFirstPOM {

    private static WebElement element = null;

    public static String url = "http://smartgym.dev/";
    public static String username = "rgsuperadmin@rewardgateway.com";
    public static String password = "passworD1";
    public static String landingPageHeaderTextIS = "Dashboard";


    public static WebElement usernameField(WebDriver driver){
        element = driver.findElement(By.id("email"));
        return element;
    }

    public static WebElement passwordField(WebDriver driver){
        element = driver.findElement(By.id("password"));
        return element;
    }

    public static WebElement logInButton(WebDriver driver){
        element = driver.findElement(By.cssSelector("button[type='submit']"));
        return element;
    }

    public static WebElement logInPageHeader(WebDriver driver){
        element = driver.findElement(By.cssSelector(".page-heading"));
        return element;
    }




}
