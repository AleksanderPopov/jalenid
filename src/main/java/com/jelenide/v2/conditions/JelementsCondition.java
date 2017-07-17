package com.jelenide.v2.conditions;

import com.jelenide.v2.jelements.Jelement;
import com.jelenide.v2.jelements.Jelements;

import java.util.function.Function;

/**
 * Created by Alex on 7/17/2017.
 */
public interface JelementsCondition<T extends Jelement> extends Function<Jelements<T>, Jelements<T>> {
}
