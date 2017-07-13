package com.jelenide;

import com.jelenide.conditions.JelementConditions;

public class GoogleSearchResult extends Jelement {

  public GoogleSearchResult shouldHaveTitle(String text) {
    this.shouldHave(JelementConditions.text(text));
    return this;
  }

  public void clickLink() {
    this.find("a").click();
  }

}
