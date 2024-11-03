package ru.pkozlov.app;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class TestcontainersConfiguration {
    private static final String DATABASE_NAME = "test-app";

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14-alpine")
            .withReuse(true)
            .withDatabaseName(DATABASE_NAME);
}
