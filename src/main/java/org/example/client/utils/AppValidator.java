package org.example.client.utils;

public class AppValidator {

    public final static String EMAIL_RGX = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static boolean isEmailValid(String email) {
        if (email != null)
            return email.isEmpty() || !email.matches(EMAIL_RGX);
        return false;
    }
}
