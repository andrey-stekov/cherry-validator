package org.andrey.cherry.validator.simple.validation;

import org.andrey.cherry.validator.ValidationRules;
import org.andrey.cherry.validator.Validator;
import org.andrey.cherry.validator.simple.validation.rules.AGtOrEqZero;
import org.andrey.cherry.validator.simple.validation.rules.ALtB;
import org.andrey.cherry.validator.simple.validation.rules.BLtOrEqSeven;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleValidationTest {
    public static final String ERROR_A_LT_0 = "Expected a >= 0, actual: -1";
    public static final String ERROR_B_GT_7 = "Expected b <= 7, actual: 9";
    public static final String ERROR_A_EQ_B = "Expected a < b, actual: 1 >= 1";
    private ValidationRules<TestBean> rules;

    @Before
    public void init() {
        rules = ValidationRules
                .<TestBean>builder()
                .addRule(new AGtOrEqZero())
                .addRule(new BLtOrEqSeven())
                .addRule(new ALtB())
                .build();
    }

    @Test
    public void testValidBean() {
        Validator<TestBean> validator = new Validator<>(rules, new TestBean(1, 2));
        assertTrue(validator.validate());
        assertTrue(validator.validate());
        assertTrue(validator.errors().isEmpty());
    }

    @Test
    public void testALessThenZero() {
        Validator<TestBean> validator = new Validator<>(rules, new TestBean(-1, 2));
        assertFalse(validator.validate());
        assertThat(validator.errors(), is(asList(ERROR_A_LT_0)));
    }

    @Test
    public void testBGreatThenSeven() {
        Validator<TestBean> validator = new Validator<>(rules, new TestBean(1, 9));
        assertFalse(validator.validate());
        assertThat(validator.errors(), is(asList(ERROR_B_GT_7)));
    }

    @Test
    public void testALessThenZeroAndBGreatThenSeven() {
        Validator<TestBean> validator = new Validator<>(rules, new TestBean(-1, 9));
        assertFalse(validator.validate());
        assertThat(validator.errors(), is(asList(ERROR_A_LT_0, ERROR_B_GT_7)));
    }

    @Test
    public void testALessThenB() {
        Validator<TestBean> validator = new Validator<>(rules, new TestBean(1, 1));
        assertFalse(validator.validate());
        assertThat(validator.errors(), is(asList(ERROR_A_EQ_B)));
    }
}
