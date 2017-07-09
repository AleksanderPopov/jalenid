package com.jelenide;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.jelenide.Helper.pause;
import static com.jelenide.Jelenide.$;
import static com.jelenide.WebDriverRunner.getDriver;
import static com.jelenide.WebDriverRunner.setDriver;

/**
 * Created by Alex on 7/9/2017.
 */
public class integration {
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
  public void addTasks() {
    getDriver().get("http://todomvc.com/examples/backbone/");

//    $(".new-todo").val("task 1").pressEnter();
    $(".header").find(".new-todo").val("task 1").pressEnter();

  }

}
