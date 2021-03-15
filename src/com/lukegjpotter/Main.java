package com.lukegjpotter;

public class Main {

    public static void main(String[] args) {
        System.out.println("SAS Generation: getSasToken");
        System.out.println(new SasKeyGeneration().getSasToken(PrivateConfiguration.storageAccount1CanonicalizedResource, PrivateConfiguration.storageAccount1Key));
    }
}
