import template.CodeGen;
import template.FieldModel;
import template.FieldType;
import template.KeyList;
import template.util.FileUtil;

import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    private static String DEPEND_NOTICE = "\n\n please add blow depend to your gradle file:\n\n" +
            "    compile 'com.android.support:appcompat-v7:25.0.0'\n" +
            "    compile 'com.android.support:recyclerview-v7:25.0.0'\n" +
            "    compile 'com.android.support:cardview-v7:25.0.0'\n\n";

    private static String HELP = "usage:\n" +
            " -g <ModelName>  assign Model name\n" +
            " -attr <FieldName> <FieldType> ...   assign Attribute name and type \n" +
            " -db  assign local database (unable)\n" +
            " -api assign net api(unable) \n" +
            " -p <packageName>  assign package name ";

    private static String packageName = null;
    private static String modelName = null;

    public static void main(String[] args) {
        ParseQuene<String> parseQuene = new ParseQuene<String>();
        for (String s : args) {
            parseQuene.add(s);
        }
        if (parseArgs(parseQuene)) {

            return;
        }

        if (init()) {
            List<FieldModel> testModel = new ArrayList<>();
            testModel.add(new FieldModel("Name", FieldType.STRING));
            testModel.add(new FieldModel("Age", FieldType.INT));
            testModel.add(new FieldModel("Price", FieldType.DOUBLE));
            CodeGen codeGen = new CodeGen("Test", testModel);
            codeGen.doGenerate();
            System.err.print(DEPEND_NOTICE);
        }
    }


    private static boolean init() {
        String path = FileUtil.getPath();
        System.out.println(path);
        KeyList.res_path = path.substring(0, path.lastIndexOf("java")) + "res" + File.separator;
        System.out.println(KeyList.res_path);
        // replace all not work
        while (path.contains(File.separator)) {
            path = path.replace(File.separator, ".");
        }
        String packgeName = path.substring(path.lastIndexOf("java") + 5);
        System.out.println(packgeName);
        KeyList.class_path = path;
        KeyList.packageName = packgeName;
        return true;
    }


    private static boolean parseArgs(ParseQuene<String> parseQuene) {
        String arg0 = parseQuene.pop();
        if (arg0 == null) {
            return false;
        }
        switch (arg0) {
            case "-help":
            case "-h":
            case "usage":
                System.out.println(HELP);
                return true;
            case "-g":
            case "-G":
                modelName = parseQuene.pop();

                break;
            case "-p":
                packageName = parseQuene.pop();
                break;
            case "-db":

                break;
            case "-api":
                break;
            case "-attr":
                break;
            default:
                System.err.println("unkown argment");
                return true;
        }

        return false;
    }


    private static class ParseQuene<T> {
        private List<T> list = new ArrayList<T>();


        public void add(T t) {
            synchronized (list) {
                list.add(t);
            }
        }

        public T pop() {
            if (list.size() > 0) {
                synchronized (list) {
                    T t = list.get(0);
                    list.remove(0);
                    return t;
                }
            } else {
                return null;
            }
        }

        public T touch() {
            if (list.size() > 0) {
                synchronized (list) {
                    T t = list.get(0);
                    return t;
                }
            } else {
                return null;
            }
        }

    }


}
