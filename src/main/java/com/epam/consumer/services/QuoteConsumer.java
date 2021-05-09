package com.epam.consumer.services;

import java.io.File;

public interface QuoteConsumer {
    void consume(File quoteFile);
}
