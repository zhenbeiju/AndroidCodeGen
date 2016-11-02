package template.generaters;

import template.FieldModel;
import template.KeyList;
import template.util.FileUtil;
import template.util.StringCaseUtil;

import java.util.List;

/**
 * Created by zhanglin on 16-10-12.
 */
public class ListXMLGen {
    private static String CLASS_TEMPLATE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    android:orientation=\"vertical\">\n" +
            "\n" +
            "    <android.support.v4.widget.SwipeRefreshLayout\n" +
            "        android:id=\"@+id/swipe\"\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"match_parent\">\n" +
            "\n" +
            "        <android.support.v7.widget.RecyclerView\n" +
            "            android:id=\"@+id/recycler\"\n" +
            "            android:layout_width=\"match_parent\"\n" +
            "            android:layout_height=\"match_parent\">\n" +
            "        </android.support.v7.widget.RecyclerView>\n" +
            "\n" +
            "    </android.support.v4.widget.SwipeRefreshLayout>\n" +
            "\n" +
            "\n" +
            "</LinearLayout>";

    public static void gen(String fileName, List<FieldModel> models) {

        FileUtil.exportString(KeyList.res_path+"layout/fragment_list_"+ StringCaseUtil.XMLCase(fileName)+".xml",CLASS_TEMPLATE);
//        System.out.println(CLASS_TEMPLATE);
        //TODO
    }

}
