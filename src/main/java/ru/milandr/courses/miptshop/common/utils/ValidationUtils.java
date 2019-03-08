package ru.milandr.courses.miptshop.common.utils;

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
}
