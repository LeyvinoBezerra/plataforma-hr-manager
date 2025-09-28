package br.edu.ufersa.rh.utils;

public abstract class StringUtil {

    /**
     * validar recrusos de input e-mails
     */
    public static final String VALIDAR_EMAIL_REGEX_RFC_5322 = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isAnyEmpty(final CharSequence... css) {
        if (css != null && css.length == 0) {
            return false;
        }
        for (final CharSequence cs : css){
            if (isEmpty(cs)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean isAnyBlank(final CharSequence... css) {
        if (css != null && css.length == 0) {
            return false;
        }
        for (final CharSequence cs : css){
            if (isBlank(cs)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsWhitespace(final CharSequence seq) {
        if (isEmpty(seq)) {
            return false;
        }
        final int strLen = seq.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(seq.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
