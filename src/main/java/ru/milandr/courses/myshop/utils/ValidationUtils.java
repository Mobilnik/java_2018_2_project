package ru.milandr.courses.myshop.utils;

public class ValidationUtils {

    public static void validateIsNotNullWithException(Object object,
                                                      String exceptionMessage) throws BadRequestException {
        if (object == null) {
            throw new BadRequestException(exceptionMessage);
        }
    }

    public static void validateIsNullWithException(Object object,
                                                      String exceptionMessage) throws BadRequestException {
        if (object != null) {
            throw new BadRequestException(exceptionMessage);
        }
    }
}
