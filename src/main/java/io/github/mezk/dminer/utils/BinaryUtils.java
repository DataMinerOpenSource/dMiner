package io.github.mezk.dminer.utils;

public class BinaryUtils {
    public static int convertToIntegerFromBinary(String binaryRepresentation) {
        final String number = binaryRepresentation.substring(1);
        return (binaryRepresentation.charAt(0) == '0' ? 1 : -1) * Integer.parseInt(number, 2);
    }
}