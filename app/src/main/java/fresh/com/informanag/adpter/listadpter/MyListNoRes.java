package fresh.com.informanag.adpter.listadpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.idlestar.ratingstar.RatingStarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fresh.com.informanag.R;
import fresh.com.informanag.view.Nine.TNinePlaceGridView;

/**
 * Created by Administrator
 * on 2019/5/16 0016
 *
 * 未回复
 *
 */
public class MyListNoRes extends BaseAdapter {


    private ArrayList<String> datas = new ArrayList<>();
    private LayoutInflater inflater;


    public MyListNoRes(ArrayList<String> datas, Context context) {
        this.datas.clear();
        this.datas.addAll(datas);

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

            convertView = inflater.inflate(R.layout.layout_unanswered, parent, false);

            convertView.setTag(myViewHolder);

        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }


        myViewHolder.ninePlaceGridView = (TNinePlaceGridView) convertView.findViewById(R.id.ninePlaceGridView);

        ArrayList<ArrayList<Object>> imageNames2D = new ArrayList<ArrayList<Object>>();

        imageNames2D.clear();

        for (int i = 0; i < 30; i++) {
            ArrayList<Object> imageNames = new ArrayList<Object>();
            Random random = new Random();
            for (int j = 0; j <= random.nextInt(8); j++) {
                if (j%2 == 0) {
                    imageNames.add(R.mipmap.animation_img1);
//                    imageNames.add("http://7xi8d6.com1.z0.glb.clouddn.com/20171011084856_0YQ0jN_joanne_722_11_10_2017_8_39_5_505.jpeg");
                } else {
                    imageNames.add(R.mipmap.animation_img3);
//                    imageNames.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-10-10-sakura.gun_10_10_2017_12_33_34_751.jpg");
                }
            }
            imageNames2D.add(imageNames);
        }

        myViewHolder.ninePlaceGridView.setImageNames(imageNames2D.get(position));

        return convertView;
    }


    public class MyViewHolder {


        private TNinePlaceGridView ninePlaceGridView;


    }
}
