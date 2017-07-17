package com.jelenide.v1.conditions;


import org.openqa.selenium.WebElement;

import java.util.function.Function;

/**
 * Created by Alex on 7/9/2017.
 */
public interface JelementCondition extends Function<WebElement, WebElement> {
}
