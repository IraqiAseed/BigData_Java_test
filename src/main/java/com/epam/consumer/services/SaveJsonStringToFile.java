package com.epam.consumer.services;

import com.epam.infra.InjectValue;
import lombok.SneakyThrows;

import java.io.FileWriter;

public class SaveJsonStringToFile implements JsonSaver {

    @InjectValue("consumer_output_location")
    private String locationDir;

    @SneakyThrows
    @Override
    public void save(String jsonString,String name) {
        String fileName = locationDir + name + ".json";
        FileWriter file = new FileWriter(fileName);
        file.write(jsonString);
        file.flush();
        file.close();
    }
}
