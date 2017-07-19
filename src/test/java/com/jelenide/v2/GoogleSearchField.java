package com.jelenide.v2;


import com.jelenide.v2.conditions.Be;
import com.jelenide.v2.finders.Finder;
import com.jelenide.v2.jelements.AbstractJelement;
import com.jelenide.v2.webdriver.JelenideDriver;

public class GoogleSearchField extends AbstractJelement {
  public GoogleSearchField(Finder finder, JelenideDriver driver) {
    super(finder, driver);
  }

  public GoogleSearchField searchFor(String text) {
    this.should(Be.visible()).val(text).pressEnter();
    return this;
  }
}
