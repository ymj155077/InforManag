package fresh.com.informanag.adpter.listadpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.example.zhouwei.library.CustomPopWindow;

import java.util.ArrayList;
import fresh.com.informanag.R;

/**
 * Created by Administrator
 * on 2019/5/22 0022
 */
public class MyAdapter extends BaseAdapter {
    private ArrayList <String> arrayList = new ArrayList<>();

    private LayoutInflater inflater;

    private CustomPopWindow customPopWindow;


    public MyAdapter(ArrayList <String> arrayList, Context context, CustomPopWindow customPopWindow){
        this.inflater = LayoutInflater.from(context);
        this.arrayList.clear();
        this.arrayList.addAll(arrayList);
this.customPopWindow = customPopWindow;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHodler myViewHodler = null;
        if (convertView==null){
            myViewHodler = new MyViewHodler();
            convertView = inflater.inflate(R.layout.item_pop,parent,false);


            myViewHodler.lin_click = (LinearLayout) convertView.findViewById(R.id.lin_click);

            convertView.setTag(myViewHodler);

        }else {
            myViewHodler = (MyViewHodler) convertView.getTag();
        }

        myViewHodler.lin_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopWindow.dissmiss();
            }
        });

        return convertView;
    }

    public class MyViewHodler{
private LinearLayout lin_click;
    }
}
