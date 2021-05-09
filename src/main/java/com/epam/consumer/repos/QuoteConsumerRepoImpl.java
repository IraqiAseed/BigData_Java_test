package com.epam.consumer.repos;

import com.epam.infra.InjectValue;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class QuoteConsumerRepoImpl implements QuoteConsumerRepo {

    @InjectValue("producer_output_location")
    private String locationDir;

    @SneakyThrows
    @Override
    public List<File> getQuoteFiles() {
        return Files.list(Paths.get(locationDir))
                .map(Path::toFile)
                .collect(Collectors.toList());
    }
}
