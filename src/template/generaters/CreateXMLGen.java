package template.generaters;

import template.FieldModel;
import template.util.StringCaseUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhanglin on 16-10-12.
 */
public class CreateXMLGen {
    private static String CLASS_TEMPLATE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    android:orientation=\"vertical\">" +
            "#{UIITEM}" +
            "</LinearLayout>";
    private static String UI_ITEM_TEMPLATE = "  <LinearLayout\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:orientation=\"horizontal\">\n" +
            "\n" +
            "        <TextView\n" +
            "            android:id=\"@+id/#{fieldName.XMLCase}_desc\"\n" +
            "            android:layout_width=\"wrap_content\"\n" +
            "            android:layout_height=\"60dp\"\n" +
            "            android:layout_marginLeft=\"14dp\"\n" +
            "            android:gravity=\"center_vertical\"\n" +
            "            android:text=\"#{fieldName.upcase}:\"\n" +
            "            android:textColor=\"@color/dark_8f\"\n" +
            "            android:textSize=\"15sp\" />\n" +
            "\n" +
            "#{fieldEdit}\n" +
            "\n" +
            "    </LinearLayout>\n" +
            "\n" +
            "    <View\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"0.2dp\"\n" +
            "        android:background=\"@color/dark_4f\"></View>\n";


    public static void gen(String fileName, List<FieldModel> models) {
        StringBuilder builder = new StringBuilder();

        for (FieldModel model : models) {

            String ui = UI_ITEM_TEMPLATE.replace("#{fieldEdit}", model.type.xmlEditModel)
                    .replace("#{fieldName.upcase}", StringCaseUtil.UpCase(model.name))
                    .replaceAll("#\\{fieldName.XMLCase\\}", StringCaseUtil.XMLCase(model.name));
            builder.append(ui);
        }

        String result = CLASS_TEMPLATE.replace("#{UIITEM}", builder.toString());
//        System.out.println(result);


    }

}
