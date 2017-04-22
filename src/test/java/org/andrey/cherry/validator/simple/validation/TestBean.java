package org.andrey.cherry.validator.simple.validation;

import lombok.Data;

@Data
public class TestBean {
    private int a;
    private int b;

    public TestBean(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
