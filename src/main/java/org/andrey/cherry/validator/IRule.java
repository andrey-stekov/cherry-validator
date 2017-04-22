package org.andrey.cherry.validator;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Rule interface
 *
 * @param <T> Class that will be validated
 */
public interface IRule<T> {
    /**
     * @return rule name
     */
    String getName();

    /**
     * @return set of names of rules that are prerequisites for current
     */
    Set<String> getPrerequisites();

    /**
     * @return rule predicate
     */
    Predicate<T> getPredicate();

    /**
     * @return error object (in case if predicate return <b>false</b>)
     */
    IValidationError<T> getError();
}
