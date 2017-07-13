package com.jelenide;

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

/**
 * Created by Alex on 7/9/2017.
 */
public class GoogleSearchTest {

  @Test
  public void b() {

    setDriver(new ChromeDriver());
    getDriver().manage().window().maximize();


    getDriver().get("https:/google.com/ncr");

    Jelement jelement = $("#lst-ib");
    jelement.val("Selenium automates browsers").pressEnter();

    $$(".g .r").filter(visible()).shouldHave(size(10))
            .first().shouldHave(text("Selenium - Web Browser Automation"))
            .find("a").click();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));

    getDriver().close();
    getDriver().quit();
  }


  GoogleSearchField search = $("#lst-ib", new GoogleSearchField());
  Jelements<GoogleSearchResult> results = $$(byCss(".g .r"), GoogleSearchResult.class);

  @Test
  public void reflectionSearch() {

    setDriver(new ChromeDriver());
    getDriver().manage().window().maximize();
    getDriver().get("https:/google.com/ncr");

    search.searchFor("Selenium automates browsers");

    results.filter(visible())
            .shouldHave(size(10))
            .first()
            .shouldHaveTitle("Selenium - Web Browser Automation")
            .clickLink();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));

    getDriver().close();
    getDriver().quit();
  }
}
