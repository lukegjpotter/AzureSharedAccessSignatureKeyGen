package com.lukegjpotter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SasKeyGenerationTest {

    @Test
    public void testAzureExampleCode() {
        String expected = PrivateConfiguration.expectedSas;
        String actual = new SasKeyGeneration().getSasToken();

        assertEquals(expected, actual);
    }
}