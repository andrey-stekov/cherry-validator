package org.andrey.cherry.validator;

import java.util.*;
import java.util.function.Predicate;

import static java.util.Arrays.asList;

public class ValidationRules<T> {

    private List<IRule<T>> rules;

    private ValidationRules(List<IRule<T>> rules) {
        this.rules = Collections.unmodifiableList(rules);
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public List<IRule<T>> getRules() {
        return rules;
    }

    public static class Builder<T> {
        private List<IRule<T>> rules = new ArrayList<>();

        public Builder<T> addRule(String name, String errorMessage,
                                  Predicate<T> predicate, String... prerequisites) {
            Set<String> prerequisitesSet = new HashSet<>(asList(prerequisites));
            rules.add(new SimpleRule<>(name, prerequisitesSet, predicate, errorMessage));
            return this;
        }

        public Builder<T> addRule(IRule<T> rule) {
            rules.add(rule);
            return this;
        }

        public ValidationRules<T> build() {
            return new ValidationRules<>(rules);
        }
    }
}
