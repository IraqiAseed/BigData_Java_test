package com.epam.consumer.flow;

import com.epam.common.model.Quote;
import com.epam.consumer.services.QuoteReader;
import com.epam.infra.ApplicationContext;
import com.epam.infra.InjectValue;
import com.epam.infra.JavaConfig;
import com.epam.producer.flow.QuoterProducerFlowManager;
import lombok.SneakyThrows;
import org.junit.Test;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class QuoteConsumerFlowManagerTest {
    @InjectValue("consumer_output_location")
    String location;

    @SneakyThrows
    @Test
    public void saveQuotesAsJsonFiles() {

        ApplicationContext context = new ApplicationContext(JavaConfig.builder().packagesToScan("com.epam").build());
        QuoterProducerFlowManager pflowManager = context.getObject(QuoterProducerFlowManager.class);
        QuoteConsumerFlowManager cflowManager = context.getObject(QuoteConsumerFlowManager.class);

        pflowManager.saveQuote();
        URI uri = ClassLoader.getSystemClassLoader().getResource("application.properties").toURI();
        File file = new File(uri);

        Map<String, String> propMap = new BufferedReader(new FileReader(file)).lines()
                .map(line -> line.split("="))
                .collect(Collectors.toMap(arr -> arr[0].trim(), arr -> arr[1].trim()));
        String consumerOutLocDir = propMap.get("consumer_output_location");

        List<File> filesBefore = Files.list(Paths.get(consumerOutLocDir))
                .map(Path::toFile)
                .collect(Collectors.toList());
        int sizeBefore = filesBefore.size();
        cflowManager.saveQuotesAsJsonFiles();

        Thread.sleep(1000);

        List<File> filesAfter = Files.list(Paths.get(consumerOutLocDir))
                .map(Path::toFile)
                .collect(Collectors.toList());
        int sizeAfter = filesAfter.size();
        assertEquals(sizeBefore + 1, sizeAfter);

        filesAfter.removeAll(filesBefore);
        String newFile = filesAfter.get(0).getName();
        int i = newFile.lastIndexOf('.');

        assertEquals("json", newFile.substring(i + 1));




    }
}