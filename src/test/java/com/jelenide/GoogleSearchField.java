package com.jelenide;

import static com.jelenide.conditions.JelementConditions.visible;

public class GoogleSearchField extends Jelement {
  public GoogleSearchField searchFor(String text) {
    this.shouldBe(visible()).val(text).pressEnter();
    return this;
  }
}
