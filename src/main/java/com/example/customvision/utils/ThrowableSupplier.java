package com.example.customvision.utils;

public interface ThrowableSupplier<T> {
    T get() throws Exception;
}
