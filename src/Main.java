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
    private static List<FieldModel> fieldModels = new ArrayList<>();

    public static void main(String[] args) {
        ParseQuene<String> parseQuene = new ParseQuene<String>();
        for (String s : args) {
            parseQuene.add(s);
        }
        if (!parseArgs(parseQuene)) {
            return;
        }

        if (init()) {
            if (checkParam(packageName)) {
                KeyList.packageName = packageName;
            }
            CodeGen codeGen = new CodeGen(modelName, fieldModels);
            codeGen.doGenerate();
            System.err.print(DEPEND_NOTICE);
        }
    }


    private static boolean init() {

        String path = FileUtil.getPath();
        System.out.println(path);
        KeyList.res_path = path.substring(0, path.lastIndexOf("java")) + "res" + File.separator;

        // replace all not work
        if (packageName == null) {
            String packgeName = path.substring(path.lastIndexOf("java") + 5);
            while (packgeName.contains(File.separator)) {
                packgeName = packgeName.replace(File.separator, ".");
            }
            KeyList.packageName = packgeName;
        } else {
            KeyList.packageName = packageName;
        }

        String codepath = KeyList.packageName;
        while (codepath.contains(".")) {
            codepath = KeyList.packageName.replace(".", File.separator);
        }
        KeyList.class_path = path.substring(0, path.lastIndexOf("java")) + "java" + File.separator + codepath;

        System.out.print(KeyList.class_path);
        System.out.println(KeyList.packageName);
        System.out.println(KeyList.res_path);
        return true;
    }


    private static boolean parseArgs(ParseQuene<String> parseQuene) {
        String arg0 = parseQuene.pop();
        if (arg0 == null) {
            if (!checkParam(modelName)) {
                System.err.println("you need assign a model name , use: -g <modelName> ");
                return false;
            }
            if (fieldModels.size() == 0) {
                System.err.println("you need assign a field , use: -attr <fieldName> <fieldType>");
                return false;
            }

            return true;
        }
        switch (arg0) {
            case "-help":
            case "-h":
            case "usage":
                System.out.println(HELP);
                return false;
            case "-g":
            case "-G":
                modelName = parseQuene.pop();
                if (checkParam(modelName)) {
                    return parseArgs(parseQuene);
                } else {
                    System.err.println("error model name");
                    return false;
                }

            case "-p":
                packageName = parseQuene.pop();
                if (checkParam(packageName)) {
                    return parseArgs(parseQuene);
                } else {
                    System.err.println("error package name");
                    return false;
                }

            case "-db":
                break;
            case "-api":
                break;
            case "-attr":
                List<String> attrs = new ArrayList<>();
                while (parseQuene.touch() != null && !parseQuene.touch().startsWith("-")) {
                    attrs.add(parseQuene.pop());
                }
                try {
                    for (int i = 0; i < attrs.size(); i += 2) {
                        String name = attrs.get(i);
                        String type = attrs.get(i + 1);
                        try {
                            FieldType type1 = FieldType.valueOf(type.toUpperCase());
                            FieldModel model = new FieldModel(name, type1);
                            fieldModels.add(model);
                        } catch (Exception e) {
                            System.err.println(" fieldType not support :\n support type is: int, string, float, double, name, email, password");
                            return false;
                        }
                    }


                    return parseArgs(parseQuene);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("error attr values, format is :  <fieldName>  <fieldType>");
                    return false;
                }

            default:
                System.err.println("unknown arg");
                return false;
        }

        return true;
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

    private static boolean checkParam(String param) {
        if (param != null && param.length() > 0) {
            return true;
        }
        return false;
    }
}
