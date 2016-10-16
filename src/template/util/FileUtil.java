package template.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zhanglin on 16-10-16.
 */
public class FileUtil {
    public static void exportString(String path, String content) {
        ArrayList<String> srcArray = new ArrayList<>();
        srcArray.add(content);
        writeArrayStringToFile(srcArray, path, null, false);

    }

    public static void writeArrayStringToFile(ArrayList<String> srcArray, String destFile, String trim, boolean isAdd) {
        System.out.println("try output");
        if (srcArray != null && srcArray.size() > 0) {
            File file = new File(destFile);
            BufferedWriter output = null;
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                try {
                    file.createNewFile();
                } catch (IOException var17) {
                    var17.printStackTrace();
                }
            }

            try {
                output = new BufferedWriter(new FileWriter(file, isAdd));
                Iterator e = srcArray.iterator();

                while (e.hasNext()) {
                    String src = (String) e.next();
                    if (trim != null) {
                        output.write(src + trim + "\n");
                    } else {
                        output.write(src + "\n");
                    }
                }
            } catch (IOException var18) {
                var18.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.flush();
                        output.close();
                    } catch (IOException var16) {
                        var16.printStackTrace();
                    }
                }

            }
        } else {
            System.out.println("none value");
        }

    }

    public static String getPath() {
        //方式一
        return System.getProperty("user.dir");
    }
}
