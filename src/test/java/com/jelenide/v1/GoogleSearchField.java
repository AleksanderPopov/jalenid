package com.jelenide.v1;

import static com.jelenide.v1.conditions.JelementConditions.visible;

public class GoogleSearchField extends Jelement {
  public GoogleSearchField searchFor(String text) {
    this.shouldBe(visible()).val(text).pressEnter();
    return this;
  }
}
