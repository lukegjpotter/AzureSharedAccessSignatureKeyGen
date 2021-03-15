package com.lukegjpotter;

/**
 * DO NOT COMMIT UPDATES TO GITHUB.
 * Only commit initial file and stubs.
 * It is added to the .GitIgnore file.
 */
public class PrivateConfiguration {

    // Storage Account 1 Implementation
    public static final String storageAccount1Name = "StorageAccountName"; // TODO populate.
    public static final String storageAccount1Container = "ContainerName"; // TODO populate.
    public static final String storageAccount1Key = "AzurePortal->AccessKeys=="; // TODO populate.
    // Test Expected
    public static final String expectedSas1 = "?sv=2020-04-08&generatefrom=StorageExplorer"; // TODO populate.
    // Generated
    public static final String storageAccount1CanonicalizedResource = "/blob/" + storageAccount1Name + "/" + storageAccount1Container;
    public static final String storageAccount1Uri = "https://" + storageAccount1Name + ".blob.core.windows.net/" + storageAccount1Container;

    // Storage Account 2 Implementation
    public static final String storageAccount2Name = "StorageAccountName"; // TODO populate.
    public static final String storageAccount2Container = "ContainerName"; // TODO populate.
    public static final String storageAccount2Key = "AzurePortal->AccessKeys=="; // TODO populate.
    // Test Expected
    public static final String expectedSas2 = "?sv=2020-04-08&generatefrom=StorageExplorer"; // TODO populate.
    // Generated
    public static final String storageAccount2CanonicalizedResource = "/blob/" + storageAccount2Name + "/" + storageAccount2Container;
    public static final String storageAccount2Uri = "https://" + storageAccount2Name + ".blob.core.windows.net/" + storageAccount2Container;
}
