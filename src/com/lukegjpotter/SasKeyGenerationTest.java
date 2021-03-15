package com.lukegjpotter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SasKeyGenerationTest {

    @Test
    public void testAzureStorageAccount1() {
        String expected = PrivateConfiguration.expectedSas1;
        String actual = new SasKeyGeneration().getSasToken(PrivateConfiguration.storageAccount1CanonicalizedResource, PrivateConfiguration.storageAccount1Key);

        assertEquals(expected, actual);
    }

    @Test
    public void testAzureStorageAccount2() {
        String expected = PrivateConfiguration.expectedSas2;
        String actual = new SasKeyGeneration().getSasToken(PrivateConfiguration.storageAccount2CanonicalizedResource, PrivateConfiguration.storageAccount2Key);

        assertEquals(expected, actual);
    }
}