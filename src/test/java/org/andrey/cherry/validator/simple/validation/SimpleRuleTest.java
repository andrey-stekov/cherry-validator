package org.andrey.cherry.validator.simple.validation;

import org.andrey.cherry.validator.ValidationRules;
import org.andrey.cherry.validator.Validator;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class SimpleRuleTest {
    private ValidationRules<TestBean> rules;

    @Before
    public void init() {
        rules = ValidationRules
                .<TestBean>builder()
                .addRule("aNotZero", "a=0", x -> x.getA() != 0)
                .addRule("aIsOne", "a!=1", x -> x.getA() == 1, "aNotZero")
                .build();
    }

    @Test
    public void testValidation() {
        Validator<TestBean> validator = new Validator<>(rules, new TestBean(2, 0));
        assertFalse(validator.validate());
        assertFalse(validator.errors().isEmpty());
        assertThat(validator.errors(), is(asList("a!=1")));
        assertEquals(validator.getObj().getA(), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgument() {
        new Validator<>(null, null);
    }

    @Test(expected = IllegalStateException.class)
    public void testIllegalState() {
        Validator<TestBean> validator = new Validator<>(rules, null);
        validator.errors();
    }
}
