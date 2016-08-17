package Execute;

import Tests.TestFirst;
import org.openqa.selenium.WebDriver;

/**
 * Created by emilbibinovski on 11/08/2016.
 */
public class Execute {


    public static void main(String[] arg){

        TestFirst actor = new TestFirst();
        actor.setUp();
        actor.logIn_validateCredentials_expectedNavigation();
        actor.testGymListingForConsistance();
        actor.tearDown();
    }

}
