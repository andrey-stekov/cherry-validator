package org.andrey.cherry.validator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractRule<T> implements IRule<T> {
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Set<String> getPrerequisites() {
        return Collections.emptySet();
    }

    protected static Set<String> buildPrerequisites(Class... prerequisites) {
        return Arrays
                .stream(prerequisites)
                .map(Class::getSimpleName)
                .collect(Collectors.toSet());
    }
}
