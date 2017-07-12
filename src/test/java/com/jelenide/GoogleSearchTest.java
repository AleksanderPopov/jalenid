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

  public static void pause(int mills) {
    try {
      Thread.sleep(mills);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void b() {

    setDriver(new ChromeDriver());
    getDriver().manage().window().maximize();


    getDriver().get("https:/google.com/ncr");

    $("#lst-ib").val("Selenium automates browsers").pressEnter();

    $$(".g .r").filter(visible()).shouldHave(size(10))
            .first().shouldHave(text("Selenium - Web Browser Automation"))
            .find("a").click();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));

    pause(1000);
    getDriver().close();
    getDriver().quit();
  }


  GoogleSearchField search = $("#lst-ib", new GoogleSearchField());

  @Test
  public void reflectionSearch() throws NoSuchFieldException, IllegalAccessException {

    setDriver(new ChromeDriver());
    getDriver().manage().window().maximize();
    getDriver().get("https:/google.com/ncr");

    $(byCss("#lst-ib"), new GoogleSearchField()).searchFor("Selenium automates browsers");

    $$(".g .r").filter(visible()).shouldHave(size(10))
            .first().shouldHave(text("Selenium - Web Browser Automation"))
            .find("a").click();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));

    pause(1000);
    getDriver().close();
    getDriver().quit();
  }

}
