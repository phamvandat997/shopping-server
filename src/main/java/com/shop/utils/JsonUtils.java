package com.shop.utils;

import com.google.gson.Gson;
import com.shop.dto.exceptions.ValidationCustomException;

import java.util.Objects;

public class JsonUtils {

    private static final Gson gson = new Gson();

    public static <T> T toObject(String data, Class<T> clazz) {
        T targetObject;
        try {
            targetObject = gson.fromJson(data, clazz);
        } catch (Exception ex) {
            throw new ValidationCustomException(ErrorCodes.ERROR_SOMETHING_WENT_WRONG);
        }
        if (Objects.isNull(targetObject)) {
            throw new ValidationCustomException(ErrorCodes.ERROR_SOMETHING_WENT_WRONG);
        }
        return targetObject;
    }

    public static String toJson(Object object) {
        if (Objects.isNull(object)) {
            throw new ValidationCustomException(ErrorCodes.ERROR_SOMETHING_WENT_WRONG);
        }
        return gson.toJson(object);
    }
}
