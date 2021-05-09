package com.epam.consumer.flow;

import com.epam.consumer.repos.QuoteConsumerRepo;
import com.epam.consumer.services.QuoteConsumer;
import lombok.AllArgsConstructor;

import java.io.File;
import java.util.List;

@AllArgsConstructor
public class QuoteConsumerFlowManager {

    private final QuoteConsumerRepo quoteConsumerRepo;
    private final QuoteConsumer quoteConsumer;

    public void saveQuotesAsJsonFiles() {
        List<File> quoteFiles = quoteConsumerRepo.getQuoteFiles();
        for (File quoteFile : quoteFiles) {
            Thread t = new Thread(() -> quoteConsumer.consume(quoteFile));
            t.start();
        }
    }


}
