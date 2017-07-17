package com.jelenide.v2;

import com.jelenide.v2.conditions.Have;
import com.jelenide.v2.webdriver.JelenideDriver;
import com.jelenide.v2.webdriver.WebDriverRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.jelenide.v2.Jelenide.$;
import static com.jelenide.v2.Jelenide.$$;
import static com.jelenide.v2.Jelenide.Wait;
import static com.jelenide.v2.Selectors.byCss;
import static com.jelenide.v2.conditions.Be.visible;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class GoogleSearchTest {

//  static JelenideDriver driver;

//  @BeforeClass
//  public static void beforeAll() {
//    driver = WebDriverRunner.getDriver();
//    driver.manage().window().maximize();
//    driver.pollingInterval = 100;
//    driver.timeout = 4_000;
//  }
//
//  @AfterClass
//  public static void afterAll() {
//    driver.close();
//    driver.quit();
//  }
//
//  @Before
//  public void beforeEach() {
//    driver.get("https:/google.com/ncr");
//  }
//
//  @Test
//  public void usualJelementsTest() {
//
//    driver.$("#lst-ib").val("Selenium automates browsers").pressEnter();
//
//    driver.$$(".g .r").filter(visible()).should(Have.size(10))
//            .first().should(Have.text("Selenium - Web Browser Automation"))
//            .find(byCss("a")).click();
//
//    driver.Wait().until(urlToBe("http://www.seleniumhq.org/"));
//
//  }
//
//  @Test
//  public void typedJelementsTest() {
//
//    $("#lst-ib").as(GoogleSearchField.class).searchFor("Selenium automates browsers");
//
//    $$(".g .r").as(GoogleSearchResult.class)
//            .filter(visible()).should(Have.size(10))
//            .first().shouldHaveTitle("Selenium - Web Browser Automation").clickLink();
//
//    Wait().until(urlToBe("http://www.seleniumhq.org/"));
//
//  }

  @Test
  public void twoBrowsersTest() {
    JelenideDriver driver1 = new JelenideDriver(new ChromeDriver());
    driver1.timeout = 10_000;
    driver1.pollingInterval = 1_000;
    driver1.maximize().open("https:/google.com/ncr");

    JelenideDriver driver2 = new JelenideDriver(new ChromeDriver());
    driver2.timeout = 10_000;
    driver2.pollingInterval = 1_000;
    driver2.open("https:/google.com/ncr");

    driver1.$("#lst-ib").val("Selenium automates browsers").pressEnter();
    driver2.$("#lst-ib").val("Selenium automates browsers").pressEnter();

    driver1.$$(".g .r").filter(visible()).should(Have.size(10))
            .first().should(Have.text("Selenium - Web Browser Automation"))
            .find(byCss("a")).click();
    driver2.$$(".g .r").filter(visible()).should(Have.size(10))
            .first().should(Have.text("Selenium - Web Browser Automation"))
            .find(byCss("a")).click();

    driver1.Wait().until(urlToBe("http://www.seleniumhq.org/"));
    driver2.Wait().until(urlToBe("http://www.seleniumhq.org/"));

    driver1.close();
    driver2.close();
    driver1.quit();
    driver2.quit();

  }

}
