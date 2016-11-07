package template.generaters;

import template.FieldModel;
import template.KeyList;
import template.util.FileUtil;
import template.util.StringCaseUtil;

import java.util.List;

/**
 * Created by zhanglin on 16-10-12.
 */
public class ModelGen {

    public static String ModelTemplate = "package #{packageName}.model;\n" +
            "public class #{name.upcase} {\n" +
            "\n" +
            "#{filed}" +
            "\n" +
            "#{method}" +
            "\n" +
            "}";
    public static String FeildTemplate = "\tprivate #{type} #{fieldName.lowercase};\n";
    public static String MethodTemplate = "\tpublic void set#{fieldName.upcase}(#{type} #{fieldName.lowercase}){\n" +
            "\t\t" + "this.#{fieldName.lowercase} = #{fieldName.lowercase};\n" +
            "\t}\n\n" +
            "\tpublic #{type} get#{fieldName.upcase}(){\n" +
            "\t\treturn this.#{fieldName.lowercase};\n" +
            "\t}\n\n";


    /**
     * @param name
     * @param models
     */
    public static void gen(String name, List<FieldModel> models) {

        StringBuilder fieldsBuild = new StringBuilder();
        StringBuilder methodBuilder = new StringBuilder();
        for (FieldModel model : models) {

            String fieldLowerCase = StringCaseUtil.LowCase(model.name);
            String fieldUpperCase = StringCaseUtil.UpCase(model.name);
            String content = FeildTemplate.replace("#{type}", model.type.className)
                    .replace("#{fieldName.lowercase}", fieldLowerCase);
            fieldsBuild.append(content);

            String methodContent = MethodTemplate.replaceAll("#\\{fieldName\\.upcase\\}", fieldUpperCase)
                    .replaceAll("#\\{type\\}", model.type.className)
                    .replaceAll("#\\{fieldName\\.lowercase\\}", fieldLowerCase);

            methodBuilder.append(methodContent);
        }

        String result = ModelTemplate.replace("#{name}", name)
                .replace("#{name.upcase}", StringCaseUtil.UpCase(name))
                .replaceAll("#\\{packageName\\}", KeyList.packageName)
                .replace("#{filed}", fieldsBuild.toString())
                .replace("#{method}", methodBuilder.toString());
//        System.out.println("try output result");
        //TODO output to file
        FileUtil.exportString(
                KeyList.class_path + "/model/#{name.upcase}.java".replace("#{name.upcase}", StringCaseUtil.UpCase(name))
                , result);

    }
}
