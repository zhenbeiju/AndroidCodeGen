package template;

import template.generaters.*;

import java.util.List;

/**
 * Created by zhanglin on 16-10-12.
 */
public class CodeGen {
    List<FieldModel> fieldModels;
    String modelName;


    public CodeGen(String modelName, List<FieldModel> fieldModels) {
        this.fieldModels = fieldModels;
        this.modelName = modelName;
    }


    public void doGenerate() {
        ModelGen.gen(modelName, fieldModels);
        CreateGen.gen(modelName, fieldModels);
        DisplayGen.gen(modelName, fieldModels);
        ListGen.gen(modelName, fieldModels);
        CreateXMLGen.gen(modelName, fieldModels);
        DisplayXMLGen.gen(modelName, fieldModels);
        ListXMLGen.gen(modelName, fieldModels);
    }


}
