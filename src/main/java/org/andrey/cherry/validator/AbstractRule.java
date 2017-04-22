package org.andrey.cherry.validator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Abstract rule
 *
 * @param <T> Class that will be validated
 */
public abstract class AbstractRule<T> implements IRule<T> {
    /**
     * @return name of rule as a simple name of class
     */
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * @return уmpty set by default
     */
    @Override
    public Set<String> getPrerequisites() {
        return Collections.emptySet();
    }

    /**
     * Util method.
     * Allow to build list of string prerequisites from array of classes.
     *
     * @param prerequisites list of prerequisites
     * @return set of prerequisites
     */
    protected static Set<String> buildPrerequisites(Class... prerequisites) {
        return Arrays
                .stream(prerequisites)
                .map(Class::getSimpleName)
                .collect(Collectors.toSet());
    }
}
