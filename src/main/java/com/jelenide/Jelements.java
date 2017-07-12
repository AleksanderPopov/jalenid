package com.jelenide;

import com.jelenide.conditions.JelementCondition;
import com.jelenide.conditions.JelementsCondition;
import com.jelenide.conditions.JelementsConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

import static com.jelenide.webdriver.WebDriverRunner.getDriver;
import static java.util.stream.Collectors.toList;

/**
 * Created by Alex on 7/10/2017.
 */
public interface Jelements extends Collection<Jelement> {

  Jelements filter(JelementCondition condition);

  Jelements shouldHave(JelementsCondition condition);

  Jelement first();

  Collection<WebElement> find();

}
