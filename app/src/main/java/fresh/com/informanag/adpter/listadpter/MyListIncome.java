package fresh.com.informanag.adpter.listadpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;

import fresh.com.informanag.R;
import fresh.com.informanag.ui.act.actbusiness.ActAlreadyDetails;

/**
 * Created by Administrator
 * on 2019/5/16 0016
 * <p>
 * 账单的列表
 */
public class MyListIncome extends BaseAdapter {


    private ArrayList<String> datas = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    MyViewHolder myViewHolder = null;

    public MyListIncome(ArrayList<String> datas, Context context) {
        this.datas.clear();
        this.datas.addAll(datas);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
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

            convertView = inflater.inflate(R.layout.layout_order_list, parent, false);



            convertView.setTag(myViewHolder);


        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }




        return convertView;
    }


    public class MyViewHolder {



    }


}
