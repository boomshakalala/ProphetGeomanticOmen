package com.xianzhifengshui.ui.edituserinfo;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.utils.StringUtils;
import com.xianzhifengshui.utils.TimeUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 编辑用户资料页面
 */
public class EditUserInfoActivity extends BaseActivity implements EditUserInfoContract.View, View.OnClickListener {

    /*======= 控件声明区 =======*/
    private Button saveBtn;
    private RelativeLayout avatarBtn;
    private TextView genderBtn;
    private TextView ageBtn;
    private TextView areaBtn;
    private OptionsPickerView<String> genderPicker;
    private OptionsPickerView<String> areaPicker;
    private TimePickerView agePicker;
    /*========================*/

    private EditUserInfoContract.Presenter presenter;
    private ArrayList<String> genderList;
    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,EditUserInfoActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initViews() {
        saveBtn = (Button) findViewById(R.id.btn_edit_user_info_save);
        avatarBtn = (RelativeLayout) findViewById(R.id.btn_edit_user_info_avatar);
        genderBtn = (TextView) findViewById(R.id.btn_edit_user_info_gender);
        ageBtn = (TextView) findViewById(R.id.btn_edit_user_info_age);
        areaBtn = (TextView) findViewById(R.id.btn_edit_user_info_area);
        genderPicker = new OptionsPickerView<>(this);
        genderPicker.setPicker(genderList);
        genderPicker.setCyclic(false);
        genderPicker.setTitle(getString(R.string.text_gender));
        genderPicker.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                genderBtn.setText(genderList.get(options1));
            }
        });
        agePicker = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH);
        agePicker.setTitle("出生年月");
        agePicker.setCyclic(false);
        agePicker.setRange(1900,2016);
        agePicker.setTime(new Date(System.currentTimeMillis()));
        agePicker.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
               ageBtn.setText((TimeUtils.getCurTimeDate().getYear() - date.getYear())+"");
            }
        });
        areaPicker = new OptionsPickerView<>(this);
        areaPicker.setTitle("所在城市");
        genderBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        avatarBtn.setOnClickListener(this);
        ageBtn.setOnClickListener(this);
        areaBtn.setOnClickListener(this);
        presenter.loadAreaData();
    }

    @Override
    protected void initData() {
        presenter = new EditUserInfoPresenter(this);
        genderList = StringUtils.arry2List(getResources().getStringArray(R.array.gender));

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_edit_user_info;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_edit_user_info);
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void updateUserInfo() {

    }

    @Override
    public void updateUserAvatar() {

    }

    @Override
    public void initAreaPicker(final ArrayList<String> provinceList, final ArrayList<ArrayList<String>> cityList, final ArrayList<ArrayList<ArrayList<String>>> areaList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                areaPicker.setPicker(provinceList,cityList,areaList,true);
                areaPicker.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3) {
                        String area = provinceList.get(options1)+"~"+ cityList.get(options1).get(option2)+
                                "~"+ areaList.get(options1).get(option2).get(options3);
                        areaBtn.setText(area);

                    }
                });
            }
        });
    }


    @Override
    public void setPresenter(EditUserInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void showTip(String text) {
        showToast(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_edit_user_info_gender:
                genderPicker.show();
                break;
            case R.id.btn_edit_user_info_age:
                agePicker.show();
                break;
            case R.id.btn_edit_user_info_area:
                areaPicker.show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {

        }
    }
}
