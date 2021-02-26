package com.lukegjpotter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SasKeyStringToSign {

    public String getSignedIdentifier() {
        String signedstart = "2015-07-01T08:49Z", signedexpiry = "2015-07-02T08:49Z", signedresource = "c", signedpermissions = "w", signedidentifier = "YWJjZGVmZw==", signedversion = "2015-02-21", containerpath = "/myaccount/pictures";

        String stringToSign = signedpermissions + "\n"
                + signedstart + "\n"
                + signedexpiry + "\n"
                + containerpath + "\n"
                + signedidentifier + "\n"
                + "2013-08-15";

        String signedString = "";
        try {
            signedString = getAuthenticationString(stringToSign, signedidentifier);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(signedString);

        return signedString;

    }

    private static String getAuthenticationString(String stringToSign, String key) throws Exception {
        // String key = "???";
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(Base64.getDecoder().decode(key), "HmacSHA256"));
        String authKey = Base64.getEncoder().encodeToString(mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8)));

        return authKey;
    }
}
