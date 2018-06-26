package com.example.customvision.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExceptionUtils {
    public static <T> Consumer <T> toUnchecked(ThrowableConsumer<T> consumer){
        return arg -> {
            try{
                consumer.accept(arg);
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        };
    }

    public static <T> Supplier <T> toUnchecked(ThrowableSupplier<T> supplier){
        return () -> {
            try{
                return supplier.get();
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        };
    }


    public static Runnable toUnchecked(ThrowableRunnable runnable ){
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
