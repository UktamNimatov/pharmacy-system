package uz.epam.webproject.test;

import java.util.regex.Pattern;

public class RegexTests {
    private static final String LOGIN_REGEX = "[\\p{Alpha}\\d]{5,29}";
    private static final String FIRST_NAME_REGEX = "[A-Z\\p{Upper}][a-z\\p{Lower}]{1,20}";
    private static final String LAST_NAME_REGEX = "[A-Z\\p{Upper}][a-z\\p{Lower}]{1,20}";
    private static final String EMAIL_REGEX = "(([\\p{Alpha}\\d._]+){4,25}@([\\p{Lower}]+){3,7}\\.([\\p{Lower}]+){2,3})";

    public static void main(String[] args) {
        System.out.println(Pattern.matches(FIRST_NAME_REGEX, "Rayona"));
        System.out.println(Pattern.matches(LAST_NAME_REGEX, "Rayona"));
        System.out.println(Pattern.matches(EMAIL_REGEX, "rayona@gmail.com"));
    }
}
