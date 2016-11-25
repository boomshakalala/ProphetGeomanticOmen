package com.xianzhifengshui.widget.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableRow;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.ImageDirListAdapter;
import com.xianzhifengshui.adapter.ListPopupAdapter;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.ScreenUtils;
import com.xianzhifengshui.utils.SizeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/22.
 * 描述: 下拉列表弹窗
 */
public class ListSelectDropDownPopupWindow extends PopupWindow implements View.OnTouchListener{
    private List<String> data;
    private LinearLayout container;
    private OnItemSelectedListener onItemSelectedListener;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && v.getId() !=R.id.text_select_pop_item){
            dismiss();
            return true;
        }
        return false;
    }



    public interface OnItemSelectedListener{
        void onSelected(int position);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public ListSelectDropDownPopupWindow(Context context) {
        super(View.inflate(context, R.layout.widget_pop_list, null), LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        setTouchable(true);
        setAnimationStyle(R.anim.slide_in_from_top);
        initViews();
    }

    private void initViews() {
        container = (LinearLayout) findViewById(R.id.layout_pop_list_container);
        getContentView().setOnTouchListener(this);
    }


    private View findViewById(int resId){
        return getContentView().findViewById(resId);
    }

    private Context getContext(){
        return getContentView().getContext();
    }

    public void loadItems(List<String> items){
        for ( int i = 0; i < items.size(); i++) {
            View itemView = View.inflate(getContext(),R.layout.widget_select_pop_item,null);
            TextView textView = (TextView) itemView.findViewById(R.id.text_select_pop_item);
            textView.setText(items.get(i));
            final int pos = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemSelectedListener != null) {
                        KLog.d("onClick");
                        onItemSelectedListener.onSelected(pos);
                    }
                }
            });
            container.addView(itemView);
            if (i<items.size()-1){
                View view = new View(getContext());
                view.setBackgroundColor(getContext().getResources().getColor(R.color.spline_color));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,1);
                params.setMargins(SizeUtils.dp2px(getContext(),20),0,SizeUtils.dp2px(getContext(),20),0);
                view.setLayoutParams(params);
                container.addView(view);
            }

        }
    }

}
