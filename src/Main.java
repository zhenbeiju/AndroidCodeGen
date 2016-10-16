import template.CodeGen;
import template.FieldModel;
import template.FieldType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println(args);
        }

//        List<FieldModel> testModel = new ArrayList<>();
//        testModel.add(new FieldModel("Name", FieldType.STRING));
//        testModel.add(new FieldModel("Age", FieldType.INT));
//        testModel.add(new FieldModel("Price", FieldType.DOUBLE));
//        CodeGen codeGen = new CodeGen("Test", testModel);
//        codeGen.doGenerate();
        getPath();
    }

    public static void getPath() {
        //方式一
        System.out.println(System.getProperty("user.dir"));
        //方式二
        File directory = new File("");//设定为当前文件夹
        try {
            System.out.println(directory.getCanonicalPath());//获取标准的路径
            System.out.println(directory.getAbsolutePath());//获取绝对路径
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Main.class.getResource("/"));
    }
}
