package org.andrey.cherry.validator;

import java.util.*;
import java.util.stream.Collectors;

public class Validator<T> {
    private Queue<IRule<T>> rulesQueue = new LinkedList<>();
    private Set<String> drivers = new HashSet<>();
    private Set<IRule<T>> rules;
    private volatile List<String> errors;
    private final T obj;

    public Validator(ValidationRules<T> rules, T obj) {
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
                    errors.add(rule.getError().renderMessage(obj));
                }
            }

            addToQueue();
        }

        this.errors = errors; // only in the end
    }

    private boolean isValid() {
        return errors != null && errors.isEmpty();
    }

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

    public List<String> errors() {
        return errors;
    }
}
