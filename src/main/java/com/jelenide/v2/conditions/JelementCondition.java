package com.jelenide.v2.conditions;


import com.jelenide.v2.jelements.Jelement;

import java.util.function.Function;

/**
 * Created by Alex on 7/17/2017.
 */
public interface JelementCondition extends Function<Jelement, Jelement> {

  String errorMessage();
}
