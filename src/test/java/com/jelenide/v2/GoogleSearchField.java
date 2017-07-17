package com.jelenide.v2;


import com.jelenide.v2.conditions.Be;
import com.jelenide.v2.finders.Finder;
import com.jelenide.v2.jelements.AbstractJelement;

public class GoogleSearchField extends AbstractJelement {
  public GoogleSearchField(Finder finder) {
    super(finder);
  }

  public GoogleSearchField searchFor(String text) {
    this.should(Be.visible()).val(text).pressEnter();
    return this;
  }
}
