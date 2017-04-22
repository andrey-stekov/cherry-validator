package org.andrey.cherry.validator;

import java.util.*;
import java.util.function.Predicate;

import static java.util.Arrays.asList;

/**
 * Validation rules bean
 *
 * @param <T> Class that will be validated
 */
public class ValidationRules<T> {

    private List<IRule<T>> rules;

    private ValidationRules(List<IRule<T>> rules) {
        this.rules = Collections.unmodifiableList(rules);
    }

    /**
     * @param <T> Class that will be validated
     * @return rules builder
     */
    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    /**
     * @return list of rules
     */
    public List<IRule<T>> getRules() {
        return rules;
    }

    /**
     * Validation rules builder
     *
     * @param <T> Class that will be validated
     */
    public static class Builder<T> {
        private List<IRule<T>> rules = new ArrayList<>();

        /**
         * Add instance of SimpleRule
         *
         * @param name name of rule
         * @param errorMessage error message
         * @param predicate predicate
         * @param prerequisites names of rules that are prerequisites for current
         * @return builder
         */
        public Builder<T> addRule(String name, String errorMessage,
                                  Predicate<T> predicate, String... prerequisites) {
            Set<String> prerequisitesSet = new HashSet<>(asList(prerequisites));
            rules.add(new SimpleRule<>(name, prerequisitesSet, predicate, errorMessage));
            return this;
        }

        /**
         * Add rule object
         *
         * @param rule rule object
         * @return builder
         */
        public Builder<T> addRule(IRule<T> rule) {
            rules.add(rule);
            return this;
        }

        /**
         * Build validation rules bean
         *
         * @return Validation rules bean
         */
        public ValidationRules<T> build() {
            return new ValidationRules<>(rules);
        }
    }
}
