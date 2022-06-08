package com.shop.utils;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class BusinessUtils {

    private static final String REGEX_PHONE_NUMBER = "^[0-9\\s\\-]{6,20}$";
    private static final String REGEX_PHONE_NUMBER_EMPTY = "^$";
    private static final String VALIDATE_PATTERN_EMAIL = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?";
    private static final String VALIDATE_PATTERN_PASSWORD = "^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,16}$";

    public static boolean checkedEmail(String email) {
        boolean checked = true;
        Pattern pattern = Pattern.compile(VALIDATE_PATTERN_EMAIL);
        if (!pattern.matcher(email).matches()) {
            checked = false;
        }
        return checked;
    }

    public static boolean checkedPassword(String password) {
        boolean checked = true;
        Pattern pattern = Pattern.compile(VALIDATE_PATTERN_PASSWORD);
        if (!pattern.matcher(password).matches()) {
            checked = false;
        }
        return checked;
    }

    public static boolean checkedPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(REGEX_PHONE_NUMBER) || phoneNumber.matches(REGEX_PHONE_NUMBER_EMPTY);
    }
}
