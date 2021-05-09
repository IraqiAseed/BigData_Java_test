package com.epam.consumer.services;

import com.epam.infra.InjectValue;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SaveJsonStringToFile implements JsonSaver {

    @InjectValue("consumer_output_location")
    private String locationDir;

    @SneakyThrows
    @Override
    public void save(String jsonString,String name) {
        String fileName = locationDir + name + ".json";
        Files.write(Paths.get(fileName),jsonString.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
    }
}
