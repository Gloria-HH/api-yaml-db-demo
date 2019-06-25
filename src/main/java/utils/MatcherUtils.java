package utils;

import common.Constants;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.lang.reflect.InvocationTargetException;

public class MatcherUtils {
    public static Matcher getMatcher(String condition, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Matchers matches = new Matchers();
        Matcher matcher;
        if (Utils.contains(condition, Constants.MATCHER_STRING)) {
            matcher = (Matcher) Matchers.class.getDeclaredMethod(condition, String.class).invoke(matches, value);
        } else {
            matcher = (Matcher) Matchers.class.getDeclaredMethod(condition, Object.class).invoke(matches, value);
        }
        return matcher;
    }
}
