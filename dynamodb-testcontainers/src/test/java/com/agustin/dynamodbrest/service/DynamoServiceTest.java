package com.agustin.dynamodbrest.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class DynamoServiceTest extends BaseIntegrationTest{
    @Autowired
    private DynamoService dynamoService;

    @Autowired
    private DynamoDbClient dynamoDbClient;

    @Test
    void listTables() {
        dynamoService.createTable("users","id");

        assertThat(dynamoDbClient.listTables().tableNames()).contains("users");
    }
}