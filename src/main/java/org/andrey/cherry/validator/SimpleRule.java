package org.andrey.cherry.validator;

import java.util.Set;
import java.util.function.Predicate;

public class SimpleRule<T> implements IRule<T> {
    private final String name;
    private final Set<String> prerequisites;
    private final Predicate<T> predicate;
    private final IValidationError<T> error;

    public SimpleRule(String name, Set<String> prerequisites, Predicate<T> predicate, String errorMessage) {
        this.name = name;
        this.prerequisites = prerequisites;
        this.predicate = predicate;
        this.error = obj -> errorMessage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<String> getPrerequisites() {
        return prerequisites;
    }

    @Override
    public Predicate<T> getPredicate() {
        return predicate;
    }

    @Override
    public IValidationError<T> getError() {
        return error;
    }
}
