package com.epam.consumer.repos;

import com.epam.common.model.Quote;

import java.io.File;
import java.util.List;

public interface QuoteConsumerRepo {
    List<File> getQuoteFiles();
}
