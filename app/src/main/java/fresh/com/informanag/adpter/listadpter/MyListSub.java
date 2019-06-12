package fresh.com.informanag.adpter.listadpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import fresh.com.informanag.R;
import fresh.com.informanag.bean.Bean_wait;
import fresh.com.informanag.ui.act.actOrderpersonal.ActSubPayDestails;
import fresh.com.informanag.util.MdFTion;

/**
 * Created by Administrator
 * on 2019/5/16 0016
 */
public class MyListSub extends BaseAdapter {


    private ArrayList<Bean_wait.DataBean> datas = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;



    public MyListSub(ArrayList<Bean_wait.DataBean> datas, Context context) {
        this.datas.clear();
        this.datas.addAll(datas);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }



    public void clear_data(ArrayList<Bean_wait.DataBean> datas){
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }


    public void add_data(ArrayList<Bean_wait.DataBean> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            myViewHolder = new MyViewHolder();

            convertView = inflater.inflate(R.layout.layout_sub_pay, parent, false);

            myViewHolder.card_view = (CardView) convertView.findViewById(R.id.card_view);

            myViewHolder.lin_sub_cancel = (LinearLayout) convertView.findViewById(R.id.lin_sub_cancel);


            convertView.setTag(myViewHolder);


        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }


        myViewHolder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ActSubPayDestails.class));
            }
        });




        myViewHolder.lin_sub_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View view_pop = MdFTion.setRebuildPop(context, R.layout.layout_pop_sub, R.layout.frag_sub_pay);

                TextView lin_pop_cencle = (TextView) view_pop.findViewById(R.id.lin_pop_cencle);

                lin_pop_cencle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        MdFTion.mBottomSheetPop.dismiss();

                    }
                });

                TextView lin_pop_yes = (TextView) view_pop.findViewById(R.id.lin_pop_yes);

                lin_pop_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MdFTion.mBottomSheetPop.dismiss();
                    }
                });


            }
        });





        return convertView;
    }


    public class MyViewHolder {


        private CardView card_view;
        private LinearLayout lin_sub_cancel;


    }








}
