package org.andrey.cherry.validator;

import java.util.Set;
import java.util.function.Predicate;

public interface IRule<T> {
    String getName();

    Set<String> getPrerequisites();

    Predicate<T> getPredicate();

    IValidationError<T> getError();
}
