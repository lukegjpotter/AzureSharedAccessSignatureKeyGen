package com.lukegjpotter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SasKeyGeneration {

    public String getSasToken() {
        final String permissions = "rwl"; // Read Write List
        final String resource = "c"; // Container
        final String service = "b"; // Blob
        final String startDateString = "2021-03-03T15:03:00Z";
        final String expiryDateString = "2021-03-04T15:03:00Z";
        final String azureApiVersion = "2020-04-08";

        /* Source: Create Account SAS
         * Link: https://docs.microsoft.com/en-us/rest/api/storageservices/create-account-sas */
        final String storageSasStringToSign = PrivateConfiguration.storageAccount1Name + "\n" // accountname
                + permissions + "\n" // signedpermissions
                + service + "\n" // signedservice
                + resource + "\n" // signedresourcetype
                + startDateString + "\n" // signedstart
                + expiryDateString + "\n" // signedexpiry
                + "\n" // signedIP
                + "\n" // signedProtocol
                + azureApiVersion + "\n"; // signedversion

        final String signature = getHMAC256(PrivateConfiguration.storageAccount1Key, storageSasStringToSign);

        return "?"
                + "sv=" + azureApiVersion
                + "&st=" + URLEncoder.encode(startDateString, StandardCharsets.UTF_8)
                + "&se=" + URLEncoder.encode(expiryDateString, StandardCharsets.UTF_8)
                + "&sr=" + resource
                + "&sp=" + permissions
                + "&sig=" + URLEncoder.encode(signature, StandardCharsets.UTF_8);
    }

    private String getHMAC256(String accountKey, String stringToSign) {
        final String algorithm = "HmacSHA256";
        String signature = null;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(accountKey), algorithm);
            Mac sha256HMAC = Mac.getInstance(algorithm);
            sha256HMAC.init(secretKey);
            signature = Base64.getEncoder().encodeToString(sha256HMAC.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }
}
