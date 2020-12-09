package com.lukegjpotter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        // Values to be Set from args.
        String httpMethod = "PUT"; // should be args[0]
        String storageAccountName = ""; // should be args [1]
        String key = ""; // should be args [2]

        // Values to be set programmatically.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z"); // Sat, 21 Feb 2015 00:48:38
        String formattedDate = simpleDateFormat.format(new Date());
        System.out.println("Valid for 15 minutes from: " + formattedDate);

        String stringToSign = httpMethod + "\n"
                + "\n" // Content-Encoding
                + "\n" // Content-Language
                + "\n" // Content-Length (empty string when zero)
                + "\n" // Content-MD5
                + "\n" // Content-Type
                + "\n" // Date
                + "\n" // If-Modified-Since
                + "\n" // If-Match
                + "\n" // If-None-Match
                + "\n" // If-Unmodified-Since
                + "\n" // Range
                + "x-ms-date:" + formattedDate + "\nx-ms-version:2020-04-08\n" // CanonicalizedHeaders
                + "/" + storageAccountName + "/" + "\ncomp:list"; // CanonicalizedResource

        try {
            System.out.println(getAuthenticationString(stringToSign, storageAccountName, key));
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static String getAuthenticationString(String stringToSign, String storageAccountName, String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(Base64.getDecoder().decode(key), "HmacSHA256"));
        String authKey = Base64.getEncoder().encodeToString(mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8)));

        return "SharedKey " + storageAccountName + ":" + authKey;
    }
}
