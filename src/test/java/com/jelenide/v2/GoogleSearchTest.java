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

    $$(".g .r").as(GoogleSearchResult.class)
            .filter(visible()).should(Have.size(10))
            .first().shouldHaveTitle("Selenium - Web Browser Automation").clickLink();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));

  }

//  @Test
//  public void usualJelementsWithFindTest() {
//
//    $("#gs_lc0").webelement("#lst-ib").val("Selenium automates browsers").pressEnter();
//
//    $$(".g .r").filter(visible()).shouldHave(size(10))
//            .first().shouldHave(text("Selenium - Web Browser Automation"))
//            .webelement("a").click();
//
//    Wait().until(urlToBe("http://www.seleniumhq.org/"));
//
//  }
//
//  @Test
//  public void usualJelementsWithFindAllTest() {
//
//    $("#lst-ib").val("Selenium automates browsers").pressEnter();
//
//    $(".srg").findAllRaw(".g .r").filter(visible()).shouldHave(size(10))
//            .first().shouldHave(text("Selenium - Web Browser Automation"))
//            .webelement("a").click();
//
//    Wait().until(urlToBe("http://www.seleniumhq.org/"));
//
//  }
//
//  @Test
//  public void typedJelementsTest() {
//
//    $("#lst-ib", GoogleSearchField.class).searchFor("Selenium automates browsers");
//
//    $$(".g .r", GoogleSearchResult.class).filter(visible()).shouldHave(size(10))
//            .first().shouldHaveTitle("Selenium - Web Browser Automation")
//            .clickLink();
//
//    Wait().until(urlToBe("http://www.seleniumhq.org/"));
//
//  }
//
//  @Test
//  public void usualJelementsWithFindTypedTest() {
//
//    $("#gs_lc0").findTyped("#lst-ib", GoogleSearchField.class).searchFor("Selenium automates browsers");
//
//    $$(".g .r").filter(visible()).shouldHave(size(10))
//            .first().shouldHave(text("Selenium - Web Browser Automation"))
//            .webelement("a").click();
//
//    Wait().until(urlToBe("http://www.seleniumhq.org/"));
//
//  }
//
//  @Test
//  public void usualJelementsWithFindAllTypedTest() {
//
//    $("#lst-ib").val("Selenium automates browsers").pressEnter();
//
//    $(".srg").findAllTyped(".g .r", GoogleSearchResult.class).filter(visible()).shouldHave(size(10))
//            .first().shouldHaveTitle("Selenium - Web Browser Automation")
//            .clickLink();
//
//    Wait().until(urlToBe("http://www.seleniumhq.org/"));
//
//  }
}
