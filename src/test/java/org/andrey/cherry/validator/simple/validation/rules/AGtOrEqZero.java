package org.andrey.cherry.validator.simple.validation.rules;

import org.andrey.cherry.validator.AbstractRule;
import org.andrey.cherry.validator.IValidationError;
import org.andrey.cherry.validator.simple.validation.TestBean;

import java.util.function.Predicate;

public class AGtOrEqZero extends AbstractRule<TestBean> {
    @Override
    public Predicate<TestBean> getPredicate() {
        return x -> x.getA() >= 0;
    }

    @Override
    public IValidationError<TestBean> getError() {
        return x -> "Expected a >= 0, actual: " + x.getA();
    }
}
