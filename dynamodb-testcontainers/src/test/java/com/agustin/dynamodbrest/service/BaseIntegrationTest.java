package com.agustin.dynamodbrest.service;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.localstack.LocalStackContainer;

@ContextConfiguration(initializers = BaseIntegrationTest.Initializer.class)
public abstract class BaseIntegrationTest {
    private static LocalStackContainer localStackContainer = new LocalStackContainer()
            .withServices(LocalStackContainer.Service.DYNAMODB);

    static{
        localStackContainer.start();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                   "dynamodb.uri=" +
                           localStackContainer
                                   .getEndpointConfiguration(LocalStackContainer.Service.DYNAMODB)
                                   .getServiceEndpoint()
            );

            values.applyTo(configurableApplicationContext);
        }
    }
}
