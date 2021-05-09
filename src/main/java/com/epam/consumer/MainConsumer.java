package com.epam.consumer;

import com.epam.consumer.flow.QuoteConsumerFlowManager;
import com.epam.infra.ApplicationContext;
import com.epam.infra.JavaConfig;
import lombok.SneakyThrows;

public class MainConsumer {
    @SneakyThrows
    public static void main(String[] args) {

        ApplicationContext context = new ApplicationContext(JavaConfig.builder().packagesToScan("com.epam").build());
        QuoteConsumerFlowManager flowManager = context.getObject(QuoteConsumerFlowManager.class);
        while(true)
        {
            flowManager.saveQuotesAsJsonFiles();
            Thread.sleep(10000);
        }

    }
}
