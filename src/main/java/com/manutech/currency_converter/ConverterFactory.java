package com.manutech.currency_converter;


import org.jetbrains.annotations.NotNull;

/**
 * Factory for creating an instance of {@link Converter}
 */
public final class ConverterFactory {
    /**
     * Create an instance of {@link Converter}
     * @return the new instance
     */
    @NotNull
    private static Converter createConverter(){
        return new ConverterImpl();
    }
}
