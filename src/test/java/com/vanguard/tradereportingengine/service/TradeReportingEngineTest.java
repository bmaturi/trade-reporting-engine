package com.vanguard.tradereportingengine.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TradeReportingEngineTest {

    @Autowired
    private TradeReportingEngineImpl engine;

    @Test
    void testProcessInputFiles() throws Exception {
        engine.processInputFiles();
    }

    @Test
    void readFileAndFilterNoFile() {
        assertThrows(FileNotFoundException.class, () -> engine.readFileAndFilter("filename"),
                "Expected to throw FileNotFoundException but did not.");

    }

    @Test
    void readFileAndFilterInvalidFile() throws Exception {
        assertNull(engine.readFileAndFilter("input/invalid.xml"));
    }

    @Test
    void readFileAndFilterValidFile() throws Exception {
        assertNotNull(engine.readFileAndFilter("input/invalid.xml"));
    }
}
