package com.lukegjpotter;

/**
 * DO NOT COMMIT UPDATES TO GITHUB.
 * Only commit initial file and stubs.
 * It is added to the .GitIgnore file.
 */
public class PrivateConfiguration {

    // Implementation
    public static final String storageAccount1Name = "StorageAccountName"; // TODO populate.
    public static final String storageAccount1Container = "ContainerName"; // TODO populate.
    public static final String storageAccount1Key = "LongStringThatEndsWith=="; // TODO populate.

    // Generated
    public static final String storageAccount1CanonicalizedResource = "/blob/" + storageAccount1Name + "/" + storageAccount1Container;
    public static final String storageAccount1Uri = "https://" + storageAccount1Name + ".blob.core.windows.net/" + storageAccount1Container;

    // Test Expected
    public static final String expectedSas = "?sv=2020-04-08&generatefrom=StorageExplorer"; // TODO populate.

}
