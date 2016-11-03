package template.generaters;

import template.KeyList;
import template.util.FileUtil;
import template.util.StringCaseUtil;

/**
 * Created by wy on 2016/11/2.
 */
public class ManagerGen {
    static String CLASS_TEMPLATE="package #{packageName}.model;\n\n" +
            "\n" +
            "import java.lang.reflect.Array;\n" +
            "import java.util.ArrayList;\n" +
            "import java.util.List;\n" +
            "\n" +
            "public class #{name.upcase}Manager {\n" +
            "    private static List<#{name.upcase}> #{name.lowercase}s = new ArrayList<>();\n" +
            "\n" +
            "    //TODO please overwrite blow code\n" +
            "    public static List<#{name.upcase}> get#{name.upcase}s() {\n" +
            "        List<#{name.upcase}> result = new ArrayList<>();\n" +
            "        result.addAll(#{name.lowercase}s);\n" +
            "        return result;\n" +
            "    }\n" +
            "\n" +
            "    //TODO please overwrite blow code\n" +
            "    public static #{name.upcase} getById(String id) {\n" +
            "        int index = Integer.valueOf(id);\n" +
            "        return #{name.lowercase}s.get(index);\n" +
            "    }\n" +
            "\n" +
            "    //TODO please overwrite blow code\n" +
            "    public static boolean insertOrUpdate(#{name.upcase} #{name.lowercase}) {\n" +
            "        synchronized (#{name.lowercase}s) {\n" +
            "            if(!#{name.lowercase}s.contains( #{name.lowercase})){\n" +
            "                return  #{name.lowercase}s.add( #{name.lowercase});\n" +
            "            }\n" +
            "            return true;"+
            "        }\n" +
            "    }\n" +
            "\n" +
            "    //TODO please overwrite blow code\n" +
            "    public static boolean delete(#{name.upcase} #{name.lowercase}) {\n" +
            "        synchronized (#{name.lowercase}s) {\n" +
            "            return #{name.lowercase}s.remove(#{name.lowercase});\n" +
            "        }\n" +
            "    }\n" +
            "}\n";
    public static void gen(String fileName){
        String result = CLASS_TEMPLATE.replaceAll("#\\{name.upcase}", StringCaseUtil.UpCase(fileName))
                .replaceAll("#\\{name.lowercase}",StringCaseUtil.LowCase(fileName))
                .replaceAll("#\\{packageName}", KeyList.packageName);
        FileUtil.exportString(
                "./model/#{name.upcase}Manager.java".replace("#{name.upcase}",StringCaseUtil.UpCase(fileName)),result);
    }
}
