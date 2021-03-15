package com.lukegjpotter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SasKeyGeneration {

    public String getSasToken(String storageAccountCanonicalizedResource, String storageAccountKey) {
        final String permissions = "rcwl"; // Read Write List
        final String resource = "c"; // Container c, Object o
        final String startDateString = "2021-03-11T12:00:00Z";
        final String expiryDateString = "2022-03-13T12:00:00Z";
        final String azureApiVersion = "2020-04-08";

        /* Source: Create Account SAS
         * Link: https://docs.microsoft.com/en-us/rest/api/storageservices/create-account-sas
         *       https://docs.microsoft.com/en-us/rest/api/storageservices/service-sas-examples#example-get-a-blob-using-a-containers-shared-access-signature */
        final String stringToSign = permissions + "\n" // signedpermissions
                + startDateString + "\n" // signedstart
                + expiryDateString + "\n" // signedexpiry
                + storageAccountCanonicalizedResource + "\n" // Canonical Resource
                + "\n" // 1
                + "\n" // 2
                + "\n" // 3
                + azureApiVersion + "\n" // signedversion
                + resource + "\n" // signedresourcetype
                + "\n" // 1
                + "\n" // 2
                + "\n" // 3
                + "\n" // 4
                + "\n"; // 5

        final String signature = getHMAC256(storageAccountKey, stringToSign);

        return "?"
                + "sv=" + azureApiVersion
                + "&st=" + URLEncoder.encode(startDateString, StandardCharsets.UTF_8)
                + "&se=" + URLEncoder.encode(expiryDateString, StandardCharsets.UTF_8)
                + "&sr=" + resource
                + "&sp=" + permissions
                + "&sig=" + URLEncoder.encode(signature, StandardCharsets.UTF_8);
    }

    private String getHMAC256(String accountKey, String stringToSign) {
        try {
            final String algorithm = "HmacSHA256";
            SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(accountKey), algorithm);
            Mac sha256HMAC = Mac.getInstance(algorithm);
            sha256HMAC.init(secretKey);
            return Base64.getEncoder().encodeToString(sha256HMAC.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }

        return "";
    }
}
