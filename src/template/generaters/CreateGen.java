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
public class CreateGen {
    private static String CLASS_TEMPLATE = "package #{packageName}.fragment;\n\n" +

            "import android.os.Bundle;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.Menu;\n" +
            "import android.view.MenuInflater;\n" +
            "import android.view.MenuItem;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.EditText;\n\n" +
            "import #{packageName}.R;\n" +
            "import #{packageName}.model.#{name.upcase};\n" +
            "import commanutil.base.BaseFragment;\n" +
            "import #{packageName}.model.#{name.upcase}Manager;\n\n" +
            "public class Create#{name.upcase}Fragment extends BaseFragment {\n" +
            "    private #{name.upcase} #{name.lowercase};\n" +
            "#{UIItems}\n" +
            "    @Override\n" +
            "    public void setData(Object... object) {\n" +
            "        if (object != null && object[0] != null) {\n" +
            "            this.#{name.lowercase} = (#{name.upcase})object[0];\n" +
            "        } else {\n" +
            "            this.#{name.lowercase} = new #{name.upcase}();\n" +
            "        }\n" +
            "    }\n\n" +
            "    @Nullable\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "        View view = inflater.inflate(R.layout.fragment_create_#{name.XMLCase}, null);\n\n" +
            "#{findUIItem}\n" +
            "        setHasOptionsMenu(true);\n" +
            "        return view;\n" +
            "    }\n\n" +
            "    @Override\n" +
            "    public void onResume() {\n" +
            "        super.onResume();\n" +
            "        updateUI();\n" +
            "    }\n\n" +
            "    public void updateUI() {\n" +
            "        if (#{name.lowercase} != null) {\n" +
            "            //TODO update UI\n" +
            "#{setUIItem}\n" +
            "        }\n" +
            "    }\n\n" +
            "    @Override\n" +
            "    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {\n" +
            "        super.onCreateOptionsMenu(menu, inflater);\n" +
            "        inflater.inflate(R.menu.done, menu);\n" +
            "    }\n\n" +
            "    @Override\n" +
            "    public boolean onOptionsItemSelected(MenuItem item) {\n" +
            "        if (item.getItemId() == R.id.menu_done) {\n" +
            "            //TODO READ VALUE\n" +
            "            if (#{name.lowercase} == null) {\n" +
            "                #{name.lowercase} = new #{name.upcase}();\n" +
            "            }\n" +
            "#{getUIItem}\n" +
            "\n" +
            "            //TODO please overwrite blow code\n" +
            "            if (#{name.upcase}Manager.insertOrUpdate(#{name.lowercase})) {\n" +
            "                getActivity().onBackPressed();\n" +
            "            }" +
            "\n" +
            "            return true;\n" +
            "        }\n" +
            "        return super.onOptionsItemSelected(item);\n" +
            "    }" +
            "}";

    public static String FIELD_TEMPLATE = "    private EditText #{fieldName.lowercase}Et;\n";
    public static String FIND_TEMPLATE = "        #{fieldName.lowercase}Et = (EditText)view.findViewById(R.id.#{fieldName.XMLCase});\n";
    public static String SET_TEMPLATE = "            #{fieldName.lowercase}Et.setText(#Content{#{name.lowercase}.get#{fieldName.upcase}()});\n";
    public static String GET_TEMPLATE = "            #{name.lowercase}.set#{fieldName.upcase}(#Content{#{fieldName.lowercase}Et.getText().toString()});\n";


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
            Pattern patter = Pattern.compile("(.*)?#Content\\{(.*)\\}(.*)?");
            Matcher matcher = patter.matcher(set);
            if (matcher.find()) {
                String content = matcher.group(2);
                String deFormater = model.type.formater.formater().replace("#{value}", content);
                set = set.replace("#Content{" + content + "}", deFormater);
            }
            setBuilder.append(set);

            String get = GET_TEMPLATE.replace("#{name.lowercase}", StringCaseUtil.LowCase(name))
                    .replace("#{fieldName.upcase}", StringCaseUtil.UpCase(model.name))
                    .replace("#{fieldName.lowercase}", StringCaseUtil.LowCase(model.name));

            matcher = patter.matcher(get);
            if (matcher.find()) {
                String content = matcher.group(2);
                String deFormater = model.type.formater.deFormate().replace("#{value}", content);
                get = get.replace("#Content{" + content + "}", deFormater);
            }
            getBuilder.append(get);
        }

        String result = CLASS_TEMPLATE.replaceAll("#\\{name.upcase\\}", StringCaseUtil.UpCase(name))
                .replaceAll("#\\{name.lowercase\\}", StringCaseUtil.LowCase(name))
                .replaceAll("#\\{name.XMLCase\\}", StringCaseUtil.XMLCase(name))
                .replaceAll("#\\{packageName\\}", KeyList.packageName)
                .replaceAll("#\\{getUIItem\\}", getBuilder.toString())
                .replaceAll("#\\{setUIItem\\}", setBuilder.toString())
                .replaceAll("#\\{findUIItem\\}", findBuilder.toString())
                .replaceAll("#\\{UIItems\\}", fieldBuilder.toString());
//        System.out.println(result);
        FileUtil.exportString(
                KeyList.class_path + "/fragment/Create#{name.upcase}Fragment.java".replace("#{name.upcase}", StringCaseUtil.UpCase(name))
                , result);

    }

}
