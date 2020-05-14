package com.portfolio.demo;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.Assert.assertTrue;

public abstract class SingletonTestDB {

    static final PostgreSQLContainer POSTGRE_SQL_CONTAINER;

    static {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer();
        POSTGRE_SQL_CONTAINER.start();
        System.setProperty("DB_URL", POSTGRE_SQL_CONTAINER.getJdbcUrl());
        System.setProperty("DB_USERNAME", POSTGRE_SQL_CONTAINER.getUsername());
        System.setProperty("DB_PASSWORD", POSTGRE_SQL_CONTAINER.getPassword());
    }

    @Test
    void test() {
        assertTrue(POSTGRE_SQL_CONTAINER.isRunning());
    }



}
