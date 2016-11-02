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
            "    private #{name.upcase} #{name.lowercase};\n" +
            "#{UIItems}\n" +
            "    @Override\n" +
            "    public void setData(Object... object) {\n" +
            "        if (object != null && object[0] != null) {"+
            "            this.#{name.lowercase} = (#{name.upcase})object[0];\n" +
            "        }"+
            "    }\n\n" +
            "    @Nullable\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "        View view = inflater.inflate(R.layout.fragment_show_#{name.XMLCase}, null);\n" +
            "#{findUIItem}\n" +
            "        setHasOptionsMenu(true);"+
            "        return view;\n" +
            "    }\n\n" +
            "    @Override\n" +
            "    public void onResume() {\n" +
            "        super.onResume();\n" +
            "        updateUI();\n" +
            "    }\n\n" +
            "    @Override\n" +
            "    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {\n" +
            "        super.onCreateOptionsMenu(menu, inflater);\n" +
            "        inflater.inflate(R.menu.edit,menu);\n" +
            "        inflater.inflate(R.menu.delete,menu);\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public boolean onOptionsItemSelected(MenuItem item) {\n" +
            "        if(item.getItemId() ==R.menu.edit){\n" +
            "            jumpToFragment(Create#{name.upcase}Fragment.class, #{name.lowercase});\n" +
            "            return true;\n" +
            "        }else if(item.getItemId() ==R.menu.delete){\n" +
            "            if(TestManager.delete(#{name.lowercase})){\n" +
            "                getActivity().onBackPressed();\n" +
            "            }else{\n" +
            "                DialogInfo.showToast(\"delete fail!\");\n" +
            "            }\n" +
            "            return true;\n" +
            "        }\n" +
            "        return super.onOptionsItemSelected(item);\n" +
            "    }"+
            "    public void updateUI() {\n" +
            "        if (#{name.lowercase} != null) {\n" +
            "            //TODO update UI\n" +
            "#{setUIItem}\n" +
            "        }\n" +
            "    }\n\n" +
            "}";

    public static String FIELD_TEMPLATE = "    private TextView #{fieldName.lowercase}Tv;\n";
    public static String FIND_TEMPLATE = "        #{fieldName.lowercase}Tv = (TextView)view.findViewById(R.id.#{fieldName.XMLCase});\n";
    public static String SET_TEMPLATE = "            #{fieldName.lowercase}Tv.setText(#Content{#{name.lowercase}.get#{fieldName.upcase}()});\n";

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
