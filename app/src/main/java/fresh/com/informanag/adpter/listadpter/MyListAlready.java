package fresh.com.informanag.adpter.listadpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import fresh.com.informanag.R;
import fresh.com.informanag.ui.act.actbusiness.ActAlreadyDetails;
import fresh.com.informanag.ui.act.actbusiness.ActToShippedDetails;

/**
 * Created by Administrator
 * on 2019/5/16 0016
 * <p>
 * 商家已发货的列表
 */
public class MyListAlready extends BaseAdapter {


    private ArrayList<String> datas = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    MyViewHolder myViewHolder = null;

    public MyListAlready(ArrayList<String> datas, Context context) {
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

            convertView = inflater.inflate(R.layout.layout_already, parent, false);

            myViewHolder.lin_already = (LinearLayout) convertView.findViewById(R.id.lin_already);

            convertView.setTag(myViewHolder);


        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        myViewHolder.lin_already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ActAlreadyDetails.class));
            }
        });


        return convertView;
    }


    public class MyViewHolder {

        private LinearLayout lin_already;

    }


}
