package utils;

import java.util.Arrays;

public class Utils {

    public static boolean contains(String str, String[] strArray) {
        return Arrays.asList(strArray).contains(str);
    }
}
