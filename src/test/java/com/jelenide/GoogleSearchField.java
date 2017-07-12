package com.jelenide;

import static com.jelenide.conditions.JelementConditions.visible;

/**
 * Created by apop on 7/12/2017.
 */
public class GoogleSearchField extends DefaultJelement {
  public GoogleSearchField searchFor(String text) {
    this.shouldBe(visible()).val(text).pressEnter();
    return this;
  }
}
