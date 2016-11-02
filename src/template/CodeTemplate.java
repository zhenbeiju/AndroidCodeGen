package template;

/**
 * Created by zhanglin on 16-10-12.
 */
public class CodeTemplate {

    public static String editTextXmlName = "" +
            "  <EditText\n" +
            "            android:id=\"@+id/#{fieldName.XMLCase}\"\n" +
            "            android:layout_width=\"match_parent\"\n" +
            "            android:layout_height=\"60dp\"\n" +
            "            android:layout_marginLeft=\"30dp\"\n" +
            "            android:layout_weight=\"1\"\n" +
            "            android:inputType=\"text\"\n" +
            "            android:gravity=\"center_vertical\"\n" +
            "            android:text=\"60\"\n" +
            "            android:textColor=\"@color/dark_af\"\n" +
            "            android:textSize=\"15sp\" />";
    public static String editNumberXmlName = "" +
            "  <EditText\n" +
            "            android:id=\"@+id/#{fieldName.XMLCase}\"\n" +
            "            android:layout_width=\"match_parent\"\n" +
            "            android:layout_height=\"60dp\"\n" +
            "            android:layout_marginLeft=\"30dp\"\n" +
            "            android:layout_weight=\"1\"\n" +
            "            android:inputType=\"number\"\n" +
            "            android:gravity=\"center_vertical\"\n" +
            "            android:text=\"60\"\n" +
            "            android:textColor=\"@color/dark_af\"\n" +
            "            android:textSize=\"15sp\" />";
    public static String editPasswordXmlName = "" +
            "  <EditText\n" +
            "            android:id=\"@+id/#{fieldName.XMLCase}\"\n" +
            "            android:layout_width=\"match_parent\"\n" +
            "            android:layout_height=\"60dp\"\n" +
            "            android:layout_marginLeft=\"30dp\"\n" +
            "            android:layout_weight=\"1\"\n" +
            "            android:inputType=\"textPassword\"\n" +
            "            android:gravity=\"center_vertical\"\n" +
            "            android:text=\"60\"\n" +
            "            android:textColor=\"@color/dark_af\"\n" +
            "            android:textSize=\"15sp\" />";
    public static String editEmailXmlName = "" +
            "  <EditText\n" +
            "            android:id=\"@+id/#{fieldName.XMLCase}\"\n" +
            "            android:layout_width=\"match_parent\"\n" +
            "            android:layout_height=\"60dp\"\n" +
            "            android:layout_marginLeft=\"30dp\"\n" +
            "            android:layout_weight=\"1\"\n" +
            "            android:inputType=\"textEmailAddress\"\n" +
            "            android:gravity=\"center_vertical\"\n" +
            "            android:text=\"60\"\n" +
            "            android:textColor=\"@color/dark_af\"\n" +
            "            android:textSize=\"15sp\" />";


}
