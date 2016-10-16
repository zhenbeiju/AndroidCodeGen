package template.generaters;

import template.FieldModel;
import template.util.FileUtil;
import template.util.StringCaseUtil;

import java.util.List;

/**
 * Created by zhanglin on 16-10-12.
 */
public class CreateGen {
    private static String CLASS_TEMPLATE = "\n" +
            "import android.os.Bundle;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.Menu;\n" +
            "import android.view.MenuInflater;\n" +
            "import android.view.MenuItem;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.EditText;\n\n" +

            "import commanutil.base.BaseFragment;\n" +
            "public class Create#{name.upcase}Fragment extends BaseFragment {\n" +
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
            "\t\tView view = inflater.inflate(R.layout.fragment_edit_#{name.xmlcase}, null);\n" +
            "#{findUIItem}\n" +
            "\t\tsetHasOptionsMenu(true);\n" +
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
            "\t@Override\n" +
            "\tpublic void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {\n" +
            "\t\tsuper.onCreateOptionsMenu(menu, inflater);\n" +
            "\t\tinflater.inflate(R.menu.done, menu);\n" +
            "\t}\n\n" +
            "\t@Override\n" +
            "\tpublic boolean onOptionsItemSelected(MenuItem item) {\n" +
            "\t\tif (item.getItemId() == R.menu.done) {\n" +
            "\t\t\t//TODO READ VALUE\n" +
            "#{getUIItem}\n" +
            "\n" +
            "\t\t\t//TODO SAVE\n" +
            "\n" +
            "\t\t\treturn true;\n" +
            "\t\t}\n" +
            "\t\treturn super.onOptionsItemSelected(item);\n" +
            "\t}" +
            "}";

    public static String FIELD_TEMPLATE = "\tprivate EditText #{fieldName.lowercase}ET;\n";
    public static String FIND_TEMPLATE = "\t\t#{fieldName.lowercase}Et = (EditText)view.findViewById(R.id.#{fieldName.XMLCase})\n";
    public static String SET_TEMPLATE = "\t\t\t#{fieldName.lowercase}Et.setText(#{name.lowercase}.get#{fieldName.upcase});\n";
    public static String GET_TEMPLATE = "\t\t\t#{name.lowercase}.set#{fieldName.upcase}(#{fieldName.lowercase}Et.getText())\n";


    public static void gen(String name, List<FieldModel> models) {
        StringBuilder fieldBuilder = new StringBuilder();
        StringBuilder findBuilder = new StringBuilder();
        StringBuilder setBuilder = new StringBuilder();
        StringBuilder getBuilder = new StringBuilder();
        for (FieldModel model : models) {
            String field = FIELD_TEMPLATE.replace("#{fieldName.lowercase}", StringCaseUtil.LowCase(model.name));
            fieldBuilder.append(field);
            String find = FIND_TEMPLATE.replace("#{fieldName.lowercase}", StringCaseUtil.LowCase(model.name))
                    .replace("#{fieldName.XMLCase}", StringCaseUtil.XMLCase(model.name));
            findBuilder.append(find);
            String set = SET_TEMPLATE.replace("#{fieldName.lowercase}", StringCaseUtil.LowCase(model.name))
                    .replace("#{name.lowercase}", StringCaseUtil.LowCase(name))
                    .replace("#{fieldName.upcase}", StringCaseUtil.UpCase(model.name));
            setBuilder.append(set);
            String get = GET_TEMPLATE.replace("#{name.lowercase}", StringCaseUtil.LowCase(name))
                    .replace("#{fieldName.upcase}", StringCaseUtil.UpCase(model.name))
                    .replace("#{fieldName.lowercase}", StringCaseUtil.LowCase(model.name));
            getBuilder.append(get);
        }

        String result = CLASS_TEMPLATE.replaceAll("#\\{name.upcase\\}", StringCaseUtil.UpCase(name))
                .replaceAll("#\\{name.lowercase\\}", StringCaseUtil.LowCase(name))
                .replaceAll("#\\{getUIItem\\}", getBuilder.toString())
                .replaceAll("#\\{setUIItem\\}", setBuilder.toString())
                .replaceAll("#\\{findUIItem\\}", findBuilder.toString())
                .replaceAll("#\\{UIItems\\}", fieldBuilder.toString());
        System.out.println(result);
        FileUtil.exportString(
                "./fragment/Create#{name.upcase}Fragment.java".replace("#{name.upcase}", StringCaseUtil.UpCase(name))
                , result);

    }

}
