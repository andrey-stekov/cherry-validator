package org.andrey.cherry.validator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Validator engine
 *
 * @param <T> Class that will be validated
 */
public class Validator<T> {
    private Queue<IRule<T>> rulesQueue = new LinkedList<>();
    private Set<String> drivers = new HashSet<>();
    private Set<IRule<T>> rules;
    private volatile List<String> errors;
    private final T obj;

    /**
     * Validator engine constructor
     *
     * @param rules Validation rules bean
     * @param obj validation target
     *
     * @throws IllegalArgumentException if rules is null
     */
    public Validator(ValidationRules<T> rules, T obj) {
        if (rules == null) {
            throw new IllegalArgumentException("Validation rules can't be null.");
        }

        this.rules = new HashSet<>(rules.getRules());
        this.obj = obj;
    }

    private void addToQueue() {
        List<IRule<T>> found = rules
                .stream()
                .filter(rule -> drivers.containsAll(rule.getPrerequisites()))
                .collect(Collectors.toList());
        rules.removeAll(found);
        rulesQueue.addAll(found);
    }

    private void doValidate(T obj) {
        List<String> errors = new ArrayList<>();

        addToQueue();
        while (!rulesQueue.isEmpty()) {
            while (!rulesQueue.isEmpty()) {
                IRule<T> rule = rulesQueue.poll();

                if (rule.getPredicate().test(obj)) {
                    drivers.add(rule.getName());
                } else {
                    errors.add(rule.getError().buildMessage(obj));
                }
            }

            addToQueue();
        }

        this.errors = errors; // only in the end
    }

    private boolean isValid() {
        return errors != null && errors.isEmpty();
    }

    /**
     * Validation method
     *
     * @return validation result
     */
    public boolean validate() {
        if (errors != null) {
            return isValid();
        }

        synchronized (this) {
            if (errors == null) {
                doValidate(obj);
            }
        }
        return isValid();
    }

    /**
     * @return list of errors
     *
     * @throws IllegalStateException in case if you didn't call validate method first
     */
    public List<String> errors() {
        if (errors == null) {
            throw new IllegalStateException("You should call \"validate\" first.");
        }

        return errors;
    }

    /**
     * @return object, validation target
     */
    public T getObj() {
        return obj;
    }
}
