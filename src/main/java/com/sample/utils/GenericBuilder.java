package com.sample.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GenericBuilder<T> {

    private List<Consumer<T>> instanceModifiers = new ArrayList<>();

    private final Supplier<T> instantiator;

    private GenericBuilder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    public static <T> GenericBuilder<T> of(Supplier<T> instantiator) {
        return new GenericBuilder<>(instantiator);
    }

    public <U> GenericBuilder<T> with(BiConsumer<T, U> biConsumer, U value) {
        Consumer<T> consumer = instance -> biConsumer.accept(instance, value);
        this.instanceModifiers.add(consumer);
        return this;
    }

    public T build() {
        T instance = this.instantiator.get();
        this.instanceModifiers.forEach(modifier -> modifier.accept(instance));
        this.instanceModifiers.clear();
        return instance;
    }

}
