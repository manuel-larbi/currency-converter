package com.manutech.currency_converter;

import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

/**
 * Interface to handle the api configuration and currency conversions
 */
public interface Converter {

    /**
     * @param base {@link String} - the currency code <code>eg. USD</code> to convert from.
     * @throws IOException message
     */
    JSONObject apiConfig(String base) throws IOException, URISyntaxException;


    /**
     * @param base {@link String} - the currency code <code>eg. USD</code> to convert from.
     * @return {@link String} the full api url with the base currency
     * @throws URISyntaxException message
     */
    String getConfiguredApiUrl(String base) throws URISyntaxException;

    /**
     * @param rateObject {@link JSONObject} - the object that contains the rates of the other currencies returned from the api response.
     * @param currency {@link String} - the currency code <code>eg. GHS</code> to convert to.
     * @param amount {@link Double} - the amount to convert.
     * @return {@link BigDecimal} - the converted amount.
     */
    BigDecimal convert(JSONObject rateObject, String currency, double amount);
}
