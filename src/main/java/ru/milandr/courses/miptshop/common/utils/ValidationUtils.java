package ru.milandr.courses.miptshop.common.utils;

import java.util.Collection;

public class ValidationUtils {

    public static void validateIsNotNull(Object object,
                                         String exceptionMessage) throws ValidationException {
        if (object == null) {
            throw new ValidationException(exceptionMessage);
        }
    }

    public static void validateIsNull(Object object,
                                      String exceptionMessage) throws ValidationException {
        if (object != null) {
            throw new ValidationException(exceptionMessage);
        }
    }

    public static void validateIsNotEmpty(Collection collection,
                                          String exceptionMessage) throws ValidationException {
        if (collection.isEmpty()) {
            throw new ValidationException(exceptionMessage);
        }
    }
}
