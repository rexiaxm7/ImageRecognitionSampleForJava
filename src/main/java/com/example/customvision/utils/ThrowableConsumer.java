package com.example.customvision.utils;

public interface ThrowableConsumer<T> {
    void accept(T t) throws Exception;
}
