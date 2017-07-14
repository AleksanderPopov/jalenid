package com.jelenide;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.jelenide.Jelenide.*;
import static com.jelenide.Selectors.byCss;
import static com.jelenide.conditions.JelementConditions.text;
import static com.jelenide.conditions.JelementConditions.visible;
import static com.jelenide.conditions.JelementsConditions.size;
import static com.jelenide.webdriver.WebDriverRunner.getDriver;
import static com.jelenide.webdriver.WebDriverRunner.setDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class GoogleSearchTest {

  @BeforeClass
  public static void beforeAll() {
    setDriver(new ChromeDriver());
    getDriver().manage().window().maximize();
  }

  @Before
  public void beforeEach() {
    getDriver().get("https:/google.com/ncr");
  }

  @AfterClass
  public static void afterAll() {
    getDriver().close();
    getDriver().quit();
  }

  @Test
  public void usualJelementsTest() {

    $("#lst-ib").val("Selenium automates browsers").pressEnter();

    $$(".g .r").filter(visible()).shouldHave(size(10))
            .first().shouldHave(text("Selenium - Web Browser Automation"))
            .find("a").click();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));

  }

  @Test
  public void usualJelementsWithFindTest() {

    $("#gs_lc0").find("#lst-ib").val("Selenium automates browsers").pressEnter();

    $$(".g .r").filter(visible()).shouldHave(size(10))
            .first().shouldHave(text("Selenium - Web Browser Automation"))
            .find("a").click();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));

  }

  @Test
  public void usualJelementsWithFindAllTest() {

    $("#lst-ib").val("Selenium automates browsers").pressEnter();

    $(".srg").findAll(".g .r").filter(visible()).shouldHave(size(10))
            .first().shouldHave(text("Selenium - Web Browser Automation"))
            .find("a").click();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));

  }

  @Test
  public void typedJelementsTest() {

    $("#lst-ib", GoogleSearchField.class).searchFor("Selenium automates browsers");

    $$(".g .r", GoogleSearchResult.class).filter(visible()).shouldHave(size(10))
            .first().shouldHaveTitle("Selenium - Web Browser Automation")
            .clickLink();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));

  }

  @Test
  public void usualJelementsWithFindTypedTest() {

    $("#gs_lc0").findTyped("#lst-ib", GoogleSearchField.class).searchFor("Selenium automates browsers");

    $$(".g .r").filter(visible()).shouldHave(size(10))
            .first().shouldHave(text("Selenium - Web Browser Automation"))
            .find("a").click();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));

  }

  @Test
  public void usualJelementsWithFindAllTypedTest() {

    $("#lst-ib").val("Selenium automates browsers").pressEnter();

    $(".srg").findAllTyped(".g .r", GoogleSearchResult.class).filter(visible()).shouldHave(size(10))
            .first().shouldHaveTitle("Selenium - Web Browser Automation")
            .clickLink();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));

  }
}
