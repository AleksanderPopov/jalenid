package com.jelenide.conditions;


import com.jelenide.Jelement;
import com.jelenide.Jelements;

import java.util.function.Function;

/**
 * Created by Alex on 7/9/2017.
 */
public interface JelementsCondition<T extends Jelement> extends Function<Jelements<T>, Jelements<T>> {
}
