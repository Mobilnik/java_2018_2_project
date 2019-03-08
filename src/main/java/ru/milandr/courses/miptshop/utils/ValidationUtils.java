package ru.milandr.courses.miptshop.utils;

public class ValidationUtils {

    public static void validateIsNotNull(Object object,
                                         String exceptionMessage) throws BadRequestException {
        if (object == null) {
            throw new BadRequestException(exceptionMessage);
        }
    }

    public static void validateIsNull(Object object,
                                      String exceptionMessage) throws BadRequestException {
        if (object != null) {
            throw new BadRequestException(exceptionMessage);
        }
    }
}
