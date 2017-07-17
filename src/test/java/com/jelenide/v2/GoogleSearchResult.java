package com.jelenide.v2;


import com.jelenide.v2.conditions.Have;
import com.jelenide.v2.finders.Finder;
import com.jelenide.v2.jelements.AbstractJelement;

import static com.jelenide.v2.Selectors.byCss;

public class GoogleSearchResult extends AbstractJelement {

  public GoogleSearchResult(Finder finder) {
    super(finder);
  }

  public GoogleSearchResult shouldHaveTitle(String text) {
    this.should(Have.text(text));
    return this;
  }

  public void clickLink() {
    this.find(byCss("a")).click();
  }

}
