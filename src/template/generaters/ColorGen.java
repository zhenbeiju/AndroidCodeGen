package template.generaters;

import template.KeyList;
import template.util.FileUtil;

/**
 * Created by wy on 2016/11/2.
 */
public class ColorGen {
    private static String COLOR_TEMPLATE ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<resources>\n" +
            "    <color name=\"dark_4f\">#4f000000</color>\n" +
            "    <color name=\"dark_8f\">#8f000000</color>\n" +
            "    <color name=\"dark_af\">#af000000</color>\n" +
            "</resources>\n";

    public static void gen(){
        FileUtil.exportString(KeyList.res_path+"values_gen/colors.xml",COLOR_TEMPLATE);
    }
}
