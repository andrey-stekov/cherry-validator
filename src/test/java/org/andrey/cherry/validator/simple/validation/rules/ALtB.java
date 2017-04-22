package org.andrey.cherry.validator.simple.validation.rules;

import org.andrey.cherry.validator.AbstractRule;
import org.andrey.cherry.validator.IValidationError;
import org.andrey.cherry.validator.simple.validation.TestBean;

import java.util.Set;
import java.util.function.Predicate;

public class ALtB extends AbstractRule<TestBean> {
    @Override
    public Set<String> getPrerequisites() {
        return buildPrerequisites(AGtOrEqZero.class, BLtOrEqSeven.class);
    }

    @Override
    public Predicate<TestBean> getPredicate() {
        return x -> x.getA() < x.getB();
    }

    @Override
    public IValidationError<TestBean> getError() {
        return x -> "Expected a < b, actual: " + x.getA() + " >= " + x.getB();
    }
}
