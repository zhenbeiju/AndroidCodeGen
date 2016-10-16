package template.util;


/**
 * Created by zhanglin on 16-10-12.
 */
public class StringCaseUtil {
    public static String LowCase(String name) {
        if (name != null && name.length() > 0) {
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        } else {
            return name;
        }
    }

    public static String UpCase(String name) {
        if (name != null && name.length() > 0) {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        } else {
            return name;
        }
    }

    public static String XMLCase(String name) {
        if (name != null && name.length() > 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < name.length(); i++) {
                char c = name.charAt(i);
                //TODO

                if (c >= 65 && c <= 90) {
                    if (i > 0) {
                        builder.append("_");
                    }
                    builder.append((char) ((int) c + 32));
                } else {
                    builder.append(c);
                }

            }
            System.out.println(builder.toString());
            return builder.toString();
        } else {
            return name;
        }
    }
}
