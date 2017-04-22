package org.andrey.cherry.validator;

/**
 * Validation error interface
 *
 * @param <T> Class that will be validated
 */
public interface IValidationError<T> {
    /**
     * Method should to build string error message.
     *
     * @param obj object which was validated
     * @return error message
     */
    String buildMessage(T obj);
}
