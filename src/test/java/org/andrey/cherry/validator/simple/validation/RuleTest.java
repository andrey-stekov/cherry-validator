package org.andrey.cherry.validator.simple.validation;

import org.andrey.cherry.validator.simple.validation.rules.ALtB;
import org.junit.Test;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class RuleTest {
    @Test
    public void test() {
        ALtB rule = new ALtB();
        assertEquals(rule.getName(), "ALtB");
        assertThat(rule.getPrerequisites(), is(new HashSet<>(asList("AGtOrEqZero", "BLtOrEqSeven"))));
    }
}
