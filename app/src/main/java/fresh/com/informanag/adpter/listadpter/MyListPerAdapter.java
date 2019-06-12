package fresh.com.informanag.adpter.listadpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhouwei.library.CustomPopWindow;

import java.util.ArrayList;

import fresh.com.informanag.R;
import fresh.com.informanag.ui.act.actbusiness.ActWaitOrderDetails;

/**
 * Created by Administrator
 * on 2019/5/16 0016
 */
public class MyListPerAdapter extends BaseAdapter {


    private ArrayList<String> datas = new ArrayList<>();
    private LayoutInflater inflater;

    private Context context;

    MyViewHolder myViewHolder = null;
    LinearLayout lin_order;

    public MyListPerAdapter(ArrayList<String> datas, Context context, LinearLayout lin_order) {
        this.datas.clear();
        this.datas.addAll(datas);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.lin_order = lin_order;
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            myViewHolder = new MyViewHolder();

            convertView = inflater.inflate(R.layout.layout_shop_wait_order, parent, false);

            myViewHolder.text_quxiao = (TextView) convertView.findViewById(R.id.text_quxiao);
            myViewHolder.lin_order = (LinearLayout) convertView.findViewById(R.id.lin_order);

            convertView.setTag(myViewHolder);


        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }


        myViewHolder.text_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopListView(context, lin_order);
            }
        });

        myViewHolder.lin_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ActWaitOrderDetails.class));
            }
        });


        return convertView;
    }


    public class MyViewHolder {

        private TextView text_quxiao;

        private LinearLayout lin_order;

    }


    private void showPopListView(Context context, LinearLayout textView) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_list, null);


        //创建并显示popWindow
        CustomPopWindow mListPopWindow = new CustomPopWindow.PopupWindowBuilder(context)
                .setView(contentView)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)//显示大小
                .create()
                .showAsDropDown(textView, 0, 20);

        //处理popWindow 显示内容
        handleListView(contentView, context, mListPopWindow);
    }

    private void handleListView(View contentView, Context context, CustomPopWindow customPopWindow) {


        ArrayList<String> datas = new ArrayList<>();
        datas.clear();


        for (int i = 0; i < 4; i++) {
            datas.add("");
        }
        ListView recyclerView = (ListView) contentView.findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(datas, context, customPopWindow);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


}
