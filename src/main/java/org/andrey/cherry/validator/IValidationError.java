package org.andrey.cherry.validator;

public interface IValidationError<T> {
    String renderMessage(T obj);
}
