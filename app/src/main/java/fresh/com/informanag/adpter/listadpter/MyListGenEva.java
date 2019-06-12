package fresh.com.informanag.adpter.listadpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import fresh.com.informanag.R;
import fresh.com.informanag.ui.act.actOrderpersonal.ActsubEvaDestails;

/**
 * Created by Administrator
 * on 2019/5/16 0016
 * <p>
 * 待使用
 */
public class MyListGenEva extends BaseAdapter {


    private ArrayList<String> datas = new ArrayList<>();
    private LayoutInflater inflater;

    private Context context;


    public MyListGenEva(ArrayList<String> datas, Context context) {
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

        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            myViewHolder = new MyViewHolder();

            convertView = inflater.inflate(R.layout.layout_gen, parent, false);

            myViewHolder.card_view_collbe = (CardView) convertView.findViewById(R.id.card_view_collbe);

            convertView.setTag(myViewHolder);

        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }


        myViewHolder.card_view_collbe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                订单收货详情页

                context.startActivity(new Intent(context, ActsubEvaDestails.class));

            }
        });


        return convertView;
    }


    public class MyViewHolder {

        private CardView card_view_collbe;


    }


}
