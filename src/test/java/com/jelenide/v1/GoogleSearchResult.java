package com.jelenide.v1;

import com.jelenide.v1.conditions.JelementConditions;

public class GoogleSearchResult extends Jelement {

  public GoogleSearchResult shouldHaveTitle(String text) {
    this.shouldHave(JelementConditions.text(text));
    return this;
  }

  public void clickLink() {
    this.find("a").click();
  }

}
