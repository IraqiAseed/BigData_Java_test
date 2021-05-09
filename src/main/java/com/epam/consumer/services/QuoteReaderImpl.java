package com.epam.consumer.services;

import com.epam.common.model.Quote;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class QuoteReaderImpl implements QuoteReader {
    @SneakyThrows
    @Override
    public Quote read(String name) {
        FileInputStream fis = new FileInputStream(name);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Quote quote = (Quote) ois.readObject();
        fis.close();
        ois.close();
        return quote;
    }
}
