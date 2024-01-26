package com.manutech.currency_converter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *The {@link ConverterImpl} class is responsible for converting currency using an external API.
 * It has two methods: {@code apiConfig} and {@code convert}. The {@code apiConfig} method retrieves the conversion rates from the API based on a given base currency.
 * The {@code convert} method takes the {@code conversion rates} and performs the currency conversion based on a target currency and amount.
 *
 * <p><strong>{@code Example Usage}</strong></p>
 *     <pre>
 *  ConverterImpl converter = new ConverterImpl();
 *  String baseCurrency = "USD";
 *  JSONObject response = converter.apiConfig(baseCurrency);
 *  BigDecimal convertedAmount = converter.convert(response, "GHS", 1000);
 *     </pre>
 *
 * <hr/>
 *
 * <p><strong>{@code Code Analysis}</strong></p>
 * <p>{@code Inputs}</p>
 * <pre>
 * {@code rateObject}: A JSONObject containing the conversion rates for different currencies.
 * {@code currency}: A String representing the target currency for conversion.
 * {@code amount}: A double representing the amount to be converted.
 * </pre>
 * <hr/>
 * <p><strong>{@code Flow}</strong></p>
 * <pre>
 1. The apiConfig method takes a base currency as input and constructs the API URL.
 2. It sends a GET request to the API URL using OkHttp client.
 3. The response body is converted to a String.
 4. The String response is parsed into a JSONObject.
 5. The conversion rates are extracted from the JSONObject.
 6. The convert method takes the conversion rates, target currency, and amount as input.
 7. It retrieves the conversion rate for the target currency from the rateObject.
 8. The conversion rate is multiplied by the amount to get the converted amount.
 * </pre>
 *<hr/>
 * <p><strong>{@code Outputs}</strong></p>
 * <pre>
 *     {@code apiConfig}: A JSONObject containing the conversion rates for different currencies.
 *     {@code convert}: A BigDecimal representing the converted amount.
 * </pre>
 */


public class ConverterImpl implements Converter{

    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public ConverterImpl() {
        //
    }

    @Override
    public JSONObject apiConfig(String base) throws IOException, URISyntaxException {
        if (base == null || base.isEmpty()) {
            throw new IllegalArgumentException("Base currency cannot be null or empty");
        }

        String apiUrl = getConfiguredApiUrl(base);

        Request request = new Request.Builder()
                .url(apiUrl)
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String apiResponse = response.body().string();
        JSONObject responseObject = new JSONObject(apiResponse);

        return responseObject.getJSONObject("conversion_rates");
    }

    @Override
    public String getConfiguredApiUrl(String base) throws URISyntaxException {
        return new URI("https://v6.exchangerate-api.com/v6/ba93f03b60b1542ff656702e/latest/"+ base).toString();
    }

    @Override
    public BigDecimal convert(@NotNull JSONObject rateObject, @NotNull String currency, double amount) {
        if (currency.isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }

        BigDecimal convertRate = rateObject.getBigDecimal(currency.toUpperCase());

        return convertRate.multiply(BigDecimal.valueOf(amount));
    }
}
