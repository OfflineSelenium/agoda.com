package com.web.coreframework;

public class Common {

    /**
     * Sleep for certain milliseconds
     */
    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (final InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Convert a Color variable as RGB format to Hex format
     */
    public static String convertToHex(String color) {
        String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");

        int hexValue1 = Integer.parseInt(hexValue[0]);
        hexValue[1] = hexValue[1].trim();
        int hexValue2 = Integer.parseInt(hexValue[1]);
        hexValue[2] = hexValue[2].trim();
        int hexValue3 = Integer.parseInt(hexValue[2]);

        return String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
    }
}