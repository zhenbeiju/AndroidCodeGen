package template.generaters;

import template.KeyList;
import template.util.FileUtil;

/**
 * Created by wy on 2016/11/2.
 */
public class MenuAddGen {
    private static String template="" +
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<menu xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:app=\"http://schemas.android.com/apk/res-auto\">\n" +
            "\n" +
            "    <item\n" +
            "        android:id=\"@+id/menu_add\"\n" +
            "        android:title=\"add\"\n" +
            "        app:showAsAction=\"always\" />\n" +
            "\n" +
            "</menu>";


    public static void gen(){
        FileUtil.exportString(KeyList.res_path+"menu/add.xml",template);
    }


}
