package com.jelenide.v2;

import com.jelenide.v2.conditions.Have;
import com.jelenide.v2.jelements.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.jelenide.v2.Jelenide.$;
import static com.jelenide.v2.Jelenide.$$;
import static com.jelenide.v2.Jelenide.Wait;
import static com.jelenide.v2.Selectors.byCss;
import static com.jelenide.v2.conditions.Be.visible;
import static com.jelenide.v2.webdriver.WebDriverRunner.getDriver;
import static com.jelenide.v2.webdriver.WebDriverRunner.setDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class GoogleSearchTest {

  @BeforeClass
  public static void beforeAll() {
    setDriver(new ChromeDriver());
    getDriver().manage().window().maximize();
  }

  @AfterClass
  public static void afterAll() {
    getDriver().close();
    getDriver().quit();
  }

  @Before
  public void beforeEach() {
    getDriver().get("https:/google.com/ncr");
  }

  @Test
  public void usualJelementsTest() {

    $("#lst-ib").val("Selenium automates browsers").pressEnter();

    $$(".g .r").filter(visible()).should(Have.size(10))
            .first().should(Have.text("Selenium - Web Browser Automation"))
            .find(byCss("a")).click();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));

  }

  @Test
  public void typedJelementsTest() {

    $("#lst-ib").as(GoogleSearchField.class).searchFor("Selenium automates browsers");

    $$(".g .r").as(GoogleSearchResult.class)
            .filter(visible()).should(Have.size(10))
            .first().shouldHaveTitle("Selenium - Web Browser Automation").clickLink();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));

  }

}
