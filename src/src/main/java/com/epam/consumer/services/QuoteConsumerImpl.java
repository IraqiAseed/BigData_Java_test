package com.epam.consumer.services;

import com.epam.common.model.Quote;
import lombok.AllArgsConstructor;

import java.io.File;

@AllArgsConstructor
public class QuoteConsumerImpl implements QuoteConsumer {
    private final QuoteReader quoteReader;
    private final ConvertToJson jsonConverter;
    private final JsonSaver jsonSaver;

    @Override
    public void consume(File quoteFile) {
        Quote quote = quoteReader.read(quoteFile.toString());
        String convertedString = jsonConverter.convert(quote);
        jsonSaver.save(convertedString, String.valueOf(quote.getId()));
    }
}

