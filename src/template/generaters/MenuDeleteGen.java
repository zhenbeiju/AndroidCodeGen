package template.generaters;

import template.KeyList;
import template.util.FileUtil;

/**
 * Created by wy on 2016/11/2.
 */
public class MenuDeleteGen {
    private static String template="" +
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<menu xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:app=\"http://schemas.android.com/apk/res-auto\">\n" +
            "\n" +
            "    <item\n" +
            "        android:id=\"@+id/menu_delete\"\n" +
            "        android:title=\"delete\"\n" +
            "        app:showAsAction=\"always\" />\n" +
            "\n" +
            "</menu>";


    public static void gen(){
        FileUtil.exportString(KeyList.res_path+"menu/delete.xml",template);
    }


}
