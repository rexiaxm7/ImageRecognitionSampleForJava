package com.example.customvision.utils;

public interface ThrowableSupplier<T> {
    public T get() throws Exception;
}
