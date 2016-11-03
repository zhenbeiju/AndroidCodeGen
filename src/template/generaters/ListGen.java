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
public class ListGen {
    private static String CLASS_TEMPLATE = "package #{packageName}.fragment;\n" +
            "\n" +
            "import android.content.DialogInterface;\n" +
            "import android.os.Bundle;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.support.v4.widget.SwipeRefreshLayout;\n" +
            "import android.support.v7.widget.LinearLayoutManager;\n" +
            "import android.support.v7.widget.RecyclerView;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.Menu;\n" +
            "import android.view.MenuInflater;\n" +
            "import android.view.MenuItem;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.TextView;\n" +
            "\n" +
            "\n" +
            "import #{packageName}.R;\n" +
            "import #{packageName}.model.#{name.upcase};\n" +
            "import commanutil.base.BaseFragment;\n" +
            "import #{packageName}.fragment.Create#{name.upcase}Fragment;\n" +
            "import #{packageName}.fragment.Show#{name.upcase}Fragment;\n" +
            "import #{packageName}.model.#{name.upcase}Manager;\n\n"+
            "import java.util.List;\n" +
            "import java.util.ArrayList;\n" +


            "\n" +
            "public class List#{name.upcase}Fragment extends BaseFragment {\n" +
            "\n" +
            "\n" +
            "    RecyclerView recycler;\n" +
            "    SwipeRefreshLayout swipe;\n" +
            "    private MyAdapter myAdapter;\n" +
            "    private List<#{name.upcase}> #{name.lowercase}s = new ArrayList();\n" +
            "\n" +
            "    @Override\n" +
            "    public void setData(Object... object) {\n" +
            "        if (object != null && object[0] != null) {\n"+
            "            this.#{name.lowercase}s = (List<#{name.upcase}>)object[0];\n" +
            "        }\n"+
            "    }\n" +
            "\n" +
            "    @Nullable\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "        View view = inflater.inflate(R.layout.fragment_list_#{name.XMLCase}, null);\n" +
            "        recycler = (RecyclerView) view.findViewById(R.id.recycler);\n" +
            "        swipe= (SwipeRefreshLayout) view.findViewById(R.id.swipe);\n" +
            "        myAdapter = new MyAdapter(inflater);\n" +
            "        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));\n" +
            "        recycler.setAdapter(myAdapter);\n" +
            "\n" +
            "        setHasOptionsMenu(true);\n" +
            "        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {\n" +
            "            @Override\n" +
            "            public void onRefresh() {\n" +
            "                getData();\n" +
            "            }\n" +
            "        });\n" +
            "        return view;\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onResume() {\n" +
            "        super.onResume();\n" +
            "        getData();\n" +
            "        swipe.setEnabled(true);\n" +
            "    }\n" +
            "\n" +
            "    public void getData() {\n" +
            "       //TODO refreshData\n" +
            "        swipe.setRefreshing(true);\n" +
            "        tests =TestManager.getTests();\n" +
            "        myAdapter.notifyDataSetChanged();\n"+
            "        swipe.setRefreshing(false);\n"+
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onPause() {\n" +
            "        super.onPause();\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    @Override\n" +
            "    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {\n" +
            "        super.onCreateOptionsMenu(menu, inflater);\n" +
            "        inflater.inflate(R.menu.add, menu);\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public boolean onOptionsItemSelected(MenuItem item) {\n" +
            "        if (item.getItemId() == R.id.menu_add) {\n" +
            "            //TODO jump to create page\n" +
            "            jumpToFragment(CreateTestFragment.class);\n"+
            "            return true;\n" +
            "        }\n" +
            "        return super.onOptionsItemSelected(item);\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onDestroyView() {\n" +
            "        super.onDestroyView();\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    private class MyAdapter extends RecyclerView.Adapter<ViewHolder> {\n" +
            "        private LayoutInflater inflater;\n" +
            "\n" +
            "        public MyAdapter(LayoutInflater inflater) {\n" +
            "            this.inflater = inflater;\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {\n" +
            "            View view = inflater.inflate(R.layout.item_#{name.XMLCase}, null);\n" +
            "            ViewHolder holder = new ViewHolder(view);\n" +
            "            return holder;\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        public void onBindViewHolder(ViewHolder holder, int position) {\n" +
            "            holder.bindData(#{name.lowercase}s.get(position));\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        public int getItemCount() {\n" +
            "            return #{name.lowercase}s.size();\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    private class ViewHolder extends RecyclerView.ViewHolder {\n" +
            "#{UIItem}\n" +
            "        View itemView;\n" +

            "\n" +
            "        public ViewHolder(View itemView) {\n" +
            "            super(itemView);\n" +
            "#{findUIItem}" +
            "            this.itemView = itemView;\n" +
            "        }\n" +
            "\n" +
            "        public void bindData(final #{name.upcase}  #{name.lowercase}) {\n" +
            "#{setUIItem}" +
            "\n" +
            "            itemView.setOnClickListener(new View.OnClickListener() {\n" +
            "                @Override\n" +
            "                public void onClick(View v) {\n" +
            "                    //TODO please overwrite blow code\n" +
            "                    jumpToFragment(ShowTestFragment.class, #{name.lowercase});"+
            "                }\n" +
            "            });\n" +
            "        }\n" +
            "    }\n" +
            "}\n";
    public static String FIELD_TEMPLATE = "        private TextView #{fieldName.lowercase}Tv;\n";
    public static String FIND_TEMPLATE = "        #{fieldName.lowercase}Tv = (TextView)itemView.findViewById(R.id.#{fieldName.XMLCase});\n";
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
                .replaceAll("#\\{setUIItem\\}", setBuilder.toString())
                .replaceAll("#\\{packageName\\}", KeyList.packageName)
                .replaceAll("#\\{findUIItem\\}", findBuilder.toString())
                .replaceAll("#\\{UIItem\\}", fieldBuilder.toString());
//        System.out.println(result);
        FileUtil.exportString("./fragment/List#{name.upcase}Fragment.java".replace("#{name.upcase}", StringCaseUtil.UpCase(name))
                , result);
    }


}
