package com.anything.odoc.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenUtils {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String generateRememberMeToken() {
        byte[] bytes = new byte[32];
        SECURE_RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
