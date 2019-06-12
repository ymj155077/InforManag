package fresh.com.informanag.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by Administrator
 * on 2019/5/24 0024
 */
public class MyGrideView extends GridView {

    public MyGrideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /**
     * 设置上下不滚动
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            return true;//true:禁止滚动
        }
        return super.dispatchTouchEvent(ev);
    }
}
