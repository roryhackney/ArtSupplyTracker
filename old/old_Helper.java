/** Helper methods */

public class old_Helper {
    public static void rejectNullOrBlank(String s, String context) {
        rejectNull(s, context);
        rejectBlank(s, context);
    }

    public static void rejectNull(Object o, String context) {
        if (o == null) {
            throw new IllegalArgumentException(context + " cannot be null!");
        }
    }

    public static void rejectBlank(String s, String context) {
        if (s.isBlank()) {
            throw new IllegalArgumentException(context + " cannot be blank!");
        }
    }
}
