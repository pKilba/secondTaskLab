package com.epam.esm.validator;

public interface Validator<T> {

    boolean isValid(T entity);
}
