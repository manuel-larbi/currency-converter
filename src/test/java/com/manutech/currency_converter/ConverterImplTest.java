package com.manutech.currency_converter;

import junit.framework.TestCase;
import org.json.JSONObject;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;


public class ConverterImplTest extends TestCase{
    private String baseCurrency = "USD";

    public void testApiConfig() throws IOException, URISyntaxException {

        ConverterImpl converter = new ConverterImpl();

        JSONObject response = converter.apiConfig(baseCurrency);

        assertEquals(JSONObject.class, response.getClass());
        assertNotNull(response);
    }

    public void testConvert() throws IOException, URISyntaxException {
        String base = "USD";

        ConverterImpl converter = new ConverterImpl();

        JSONObject response = converter.apiConfig(base);

        BigDecimal convertedAmount = converter.convert(response, "GHS", 1000);

        assertNotNull(response);
        assertNotNull(convertedAmount);
    }

}