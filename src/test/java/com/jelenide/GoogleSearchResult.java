package com.jelenide;

import com.jelenide.conditions.JelementConditions;
import org.openqa.selenium.WebElement;

/**
 * Created by apop on 7/12/2017.
 */
public class GoogleSearchResult extends DefaultJelement {

//  public GoogleSearchResult(WebElement element) {
//    super(element);
//  }

  public GoogleSearchResult shouldHaveTitle(String text) {
    this.shouldHave(JelementConditions.text(text));
    return this;
  }

  public void clickLink() {
    this.find("a").click();
  }

}
