package com.epam.consumer.services;

import com.epam.common.model.Quote;
import com.epam.infra.ApplicationContext;
import com.epam.infra.JavaConfig;
import com.epam.producer.services.QuoteProducer;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConvertQuoteToJsonTest {

    @Test
    public void convert() {
        ApplicationContext context = new ApplicationContext(JavaConfig.builder().packagesToScan("com.epam").build());
        QuoteProducer producer = context.getObject(QuoteProducer.class);
        Quote quote = producer.generate();

        ConvertQuoteToJson converter = context.getObject(ConvertQuoteToJson.class);
        String strQuote = converter.convert(quote).trim();

        String[] out = strQuote.replaceAll("[{}]", "").split(",");
        //general test case, text without "," !!!
        for (String s : out) {
            String[] pair = s.replaceAll("\"", "").trim().split(":");
            if (pair[0].trim().equals("id"))
                Assert.assertEquals(String.valueOf(quote.getId()), pair[1].trim());
            if (pair[0].trim().equals("text"))
                Assert.assertEquals(quote.getText(), pair[1].trim());
            if (pair[0].trim().equals("status"))
                Assert.assertEquals(quote.getStatus().toString(), pair[1].trim());

        }

    }
}