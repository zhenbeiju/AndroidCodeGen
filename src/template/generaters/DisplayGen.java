package template.generaters;

import template.FieldModel;
import template.KeyList;
import template.util.FileUtil;
import template.util.StringCaseUtil;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhanglin on 16-10-12.
 */
public class DisplayGen {
    private static String CLASS_TEMPLATE = "package #{packageName}.fragment;\n\n" +
            "import android.os.Bundle;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.Menu;\n" +
            "import android.view.MenuInflater;\n" +
            "import android.view.MenuItem;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.TextView;\n\n" +
            "import #{packageName}.R;\n" +
            "import #{packageName}.model.#{name.upcase};\n" +
            "import commanutil.base.BaseFragment;\n" +
            "public class Show#{name.upcase}Fragment extends BaseFragment {\n" +
            "\tprivate #{name.upcase} #{name.lowercase};\n" +
            "#{UIItems}\n" +
            "\t@Override\n" +
            "\tpublic void setData(Object object) {\n" +
            "\t\tsuper.setData(object);\n" +
            "\t\tthis.#{name.lowercase} = (#{name.upcase})object;\n" +
            "\t}\n\n" +
            "\t@Nullable\n" +
            "\t@Override\n" +
            "\tpublic View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "\t\tView view = inflater.inflate(R.layout.fragment_show_#{name.XMLCase}, null);\n" +
            "#{findUIItem}\n" +
            "\t\treturn view;\n" +
            "\t}\n\n" +
            "\t@Override\n" +
            "\tpublic void onResume() {\n" +
            "\t\tsuper.onResume();\n" +
            "\t\tupdateUI();\n" +
            "\t}\n\n" +
            "\tpublic void updateUI() {\n" +
            "\t\tif (#{name.lowercase} != null) {\n" +
            "\t\t\t//TODO update UI\n" +
            "#{setUIItem}\n" +
            "\t\t}\n" +
            "\t}\n\n" +
            "}";

    public static String FIELD_TEMPLATE = "\tprivate TextView #{fieldName.lowercase}Tv;\n";
    public static String FIND_TEMPLATE = "\t\t#{fieldName.lowercase}Tv = (TextView)view.findViewById(R.id.#{fieldName.XMLCase});\n";
    public static String SET_TEMPLATE = "\t\t\t#{fieldName.lowercase}Tv.setText(#Content{#{name.lowercase}.get#{fieldName.upcase}()});\n";

    public static void gen(String name, List<FieldModel> models) {
        StringBuilder fieldBuilder = new StringBuilder();
        StringBuilder findBuilder = new StringBuilder();
        StringBuilder setBuilder = new StringBuilder();
        for (FieldModel model : models) {
            String field = FIELD_TEMPLATE.replace("#{fieldName.lowercase}", StringCaseUtil.LowCase(model.name));
            fieldBuilder.append(field);
            String find = FIND_TEMPLATE.replace("#{fieldName.lowercase}", StringCaseUtil.LowCase(model.name))
                    .replace("#{fieldName.XMLCase}", StringCaseUtil.XMLCase(model.name));
            findBuilder.append(find);
            String set = SET_TEMPLATE.replace("#{fieldName.lowercase}", StringCaseUtil.LowCase(model.name))
                    .replace("#{name.lowercase}", StringCaseUtil.LowCase(name))
                    .replace("#{fieldName.upcase}", StringCaseUtil.UpCase(model.name));
            Pattern patter = Pattern.compile("(.*)?#Content\\{(.*)\\}(.*)?");
            Matcher matcher = patter.matcher(set);
            if (matcher.find()) {
                String content = matcher.group(2);
                String deFormater = model.type.formater.formater().replace("#{value}", content);
                set = set.replace("#Content{" + content + "}", deFormater);
            }
            setBuilder.append(set);
        }

        String result = CLASS_TEMPLATE.replaceAll("#\\{name.upcase\\}", StringCaseUtil.UpCase(name))
                .replaceAll("#\\{name.lowercase\\}", StringCaseUtil.LowCase(name))
                .replaceAll("#\\{name.XMLCase\\}", StringCaseUtil.XMLCase(name))
                .replaceAll("#\\{packageName\\}", KeyList.packageName)
                .replaceAll("#\\{setUIItem\\}", setBuilder.toString())
                .replaceAll("#\\{findUIItem\\}", findBuilder.toString())
                .replaceAll("#\\{UIItems\\}", fieldBuilder.toString());
//        System.out.println(result);
        FileUtil.exportString(
                "./fragment/Show#{name.upcase}Fragment.java".replace("#{name.upcase}", StringCaseUtil.UpCase(name))
                , result);
    }

}
