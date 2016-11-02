package template.generaters;

import template.FieldModel;
import template.KeyList;
import template.util.FileUtil;
import template.util.StringCaseUtil;

import java.util.List;

/**
 * Created by wy on 2016/11/2.
 */
public class ListItemXMLGen {
    private static String CLASS_TEMPLATE = "" +
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<android.support.v7.widget.CardView xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"wrap_content\"\n" +
            "    android:layout_margin=\"8dp\">\n" +
            "\n" +
            "    <LinearLayout\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:orientation=\"vertical\">\n\n"+
            "    #{UIITEM}" +
            "    </LinearLayout>\n" +
            "</android.support.v7.widget.CardView>";

    public static String UITEMPLATE = " <LinearLayout\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:orientation=\"horizontal\">\n" +
            "\n" +
            "        <TextView\n" +
            "            android:id=\"@+id/#{fieldName.XMLCase}_desc\"\n" +
            "            android:layout_width=\"wrap_content\"\n" +
            "            android:layout_height=\"40dp\"\n" +
            "            android:layout_marginLeft=\"14dp\"\n" +
            "            android:gravity=\"center_vertical\"\n" +
            "            android:text=\"#{fieldName.upcase}:\"\n" +
            "            android:textColor=\"@color/dark_8f\"\n" +
            "            android:textSize=\"15sp\" />\n" +
            "\n" +
            "        <TextView\n" +
            "            android:id=\"@+id/#{fieldName.XMLCase}\"\n" +
            "            android:layout_width=\"match_parent\"\n" +
            "            android:layout_height=\"40dp\"\n" +
            "            android:layout_marginLeft=\"30dp\"\n" +
            "            android:layout_weight=\"1\"\n" +
            "            android:gravity=\"center_vertical\"\n" +
            "            android:text=\"60\"\n" +
            "            android:textColor=\"@color/dark_af\"\n" +
            "            android:textSize=\"15sp\" />\n" +
            "\n" +
            "    </LinearLayout>\n" +
            "\n" +
            "    <View\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"0.2dp\"\n" +
            "        android:background=\"@color/dark_4f\"></View>\n";

    public static void gen(String modelName, List<FieldModel> models){
        StringBuilder builder = new StringBuilder();
        for (FieldModel model : models) {
            String uiItem = UITEMPLATE.replaceAll("#\\{fieldName.XMLCase\\}", StringCaseUtil.XMLCase(model.name))
                    .replace("#{fieldName.upcase}", StringCaseUtil.UpCase(model.name));
            builder.append(uiItem);
        }
        String result = CLASS_TEMPLATE.replace("#{UIITEM}", builder.toString());
        FileUtil.exportString(KeyList.res_path+"layout/item_"+StringCaseUtil.XMLCase(modelName)+".xml",result);
    }
}
