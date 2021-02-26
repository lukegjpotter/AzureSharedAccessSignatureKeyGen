package com.lukegjpotter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SasKeyStringToSignTest {

    @Test
    public void testAzureDocumentationCode() {
        String expected = "Rcp6gQRfV7WDlURdVTqCa+qEArnfJxDgE+KH3TCChIs=";
        String actual = new SasKeyStringToSign().getSignedIdentifier();

        assertEquals(actual, expected);
    }
}