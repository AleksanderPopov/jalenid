package com.jelenide;

import com.jelenide.elements.Jelement;
import com.jelenide.elements.Jelements;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

import static com.jelenide.elements.Jelenide.$;
import static com.jelenide.elements.Jelenide.$$;
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

  @BeforeClass
  public static void beforeClass() {
    setDriver(new ChromeDriver());
    getDriver().manage().window().maximize();
  }

  @AfterClass
  public static void afterClass() {

    pause(1000);
    getDriver().close();
    getDriver().quit();
  }

  @Test
  public void searchAndFollowFirstResult() {
    getDriver().get("https:/google.com/ncr");

    $("#lst-ib").val("Selenium automates browsers").pressEnter();

    $$(".g .r").filter(visible()).shouldHave(size(10))
            .first().shouldHave(text("Selenium - Web Browser Automation"))
            .find("a").click();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));
  }

  public FluentWait<WebDriver> Wait() {
    return new FluentWait<WebDriver>(getDriver()).withTimeout(Configuration.timeout, TimeUnit.MILLISECONDS).pollingEvery(Configuration.pollingInterval, TimeUnit.MILLISECONDS);
  }

}
