import template.CodeGen;
import template.FieldModel;
import template.FieldType;
import template.KeyList;
import template.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println(args);
        }

        String path = FileUtil.getPath();
        String packgeName = path.substring(path.lastIndexOf("java") + 5).replaceAll("/", ".");
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
