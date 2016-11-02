import template.CodeGen;
import template.FieldModel;
import template.FieldType;
import template.KeyList;
import template.util.FileUtil;

import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println(args);
        }

        String path = FileUtil.getPath();
        System.out.println(path);
        KeyList.res_path =path.substring(0,path.lastIndexOf("java"))+"res"+File.separator;
        System.out.println(KeyList.res_path);
        // replace all not work
        while (path.contains(File.separator)){
            path =path.replace(File.separator, ".");
        }
        String packgeName = path.substring(path.lastIndexOf("java") + 5);
        System.out.println(packgeName);
        KeyList.class_path = path;
        KeyList.packageName = packgeName;

        List<FieldModel> testModel = new ArrayList<>();
        testModel.add(new FieldModel("Name", FieldType.STRING));
        testModel.add(new FieldModel("Age", FieldType.INT));
        testModel.add(new FieldModel("Price", FieldType.DOUBLE));
        CodeGen codeGen = new CodeGen("Test", testModel);
        codeGen.doGenerate();



//        getPath();
    }


}
