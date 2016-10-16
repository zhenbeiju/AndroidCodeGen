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

        List<FieldModel> testModel = new ArrayList<>();
        testModel.add(new FieldModel("Name", FieldType.STRING));
        testModel.add(new FieldModel("Age", FieldType.INT));
        testModel.add(new FieldModel("Price", FieldType.DOUBLE));
        CodeGen codeGen = new CodeGen("Test", testModel);
        codeGen.doGenerate();
//        getPath();
    }


}
