package com.jelenide.v1.conditions;


import com.jelenide.v1.Jelement;
import com.jelenide.v1.Jelements;

import java.util.function.Function;

/**
 * Created by Alex on 7/9/2017.
 */
public interface JelementsCondition<T extends Jelement> extends Function<Jelements<T>, Jelements<T>> {
}
