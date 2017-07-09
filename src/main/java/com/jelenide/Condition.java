package com.jelenide;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

/**
 * Created by Alex on 7/9/2017.
 */
public interface Condition extends Function<By, WebElement> {
}
