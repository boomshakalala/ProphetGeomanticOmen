package com.xianzhifengshui.ui.edituserinfo;

import android.content.Context;
import android.os.Handler;

import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.db.Area;
import com.xianzhifengshui.db.City;
import com.xianzhifengshui.db.DBHelper;
import com.xianzhifengshui.db.Province;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 编辑用户信息控制器
 */
public class EditUserInfoPresenter extends BasePresenter implements EditUserInfoContract.Presenter{
    EditUserInfoContract.View view;
    DBHelper dbHelper;
    public EditUserInfoPresenter(EditUserInfoContract.View view) {
        this.view = view;
        this.dbHelper = DBHelper.getInstance((Context)view);
        view.setPresenter(this);
    }

    @Override
    public void submitUserInfo() {
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.updateUserInfo();
                view.closeWait();
            }
        },3000);
    }

    @Override
    public void submitUserAvatar() {
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.updateUserAvatar();
                view.closeWait();
            }
        },3000);
    }

    @Override
    public void loadAreaData() {
        threadPool.submit(new loadAreaRunnable());
    }

    class loadAreaRunnable implements Runnable{

        @Override
        public void run() {
            List<Province> provinceList = dbHelper.queryForAll(Province.class);
            ArrayList<String> provinceArray = new ArrayList<>();
            ArrayList<ArrayList<String>> cityArray = new ArrayList<>();
            ArrayList<ArrayList<ArrayList<String>>> areaArray = new ArrayList<>();
            if (provinceList != null) {
                for (Province p:provinceList) {
                    provinceArray.add(p.getName());
                    ArrayList<String> cityNameArray = new ArrayList<>();
                    Map<String,Object> provinceKeyMap = new LinkedHashMap<>();
                    provinceKeyMap.put("prov_id",p.getId());
                    List<City> cityList = dbHelper.query(City.class,provinceKeyMap);
                    if (cityList == null)
                        continue;
                    ArrayList<ArrayList<String>> cityAreaArray = new ArrayList<>();
                    for (City city : cityList) {
                        cityNameArray.add(city.getName());
                        Map<String,Object> cityKeymap = new LinkedHashMap<>();
                        cityKeymap.put("city_id",city.getId());
                        List<Area> areaList = dbHelper.query(Area.class,cityKeymap);
                        if (areaList == null){
                            areaList = new ArrayList<>();
                            Area area = new Area();
//                            area.set
                        }
                        ArrayList<String> areaNameArray = new ArrayList<>();
                        for (Area area : areaList) {
                            areaNameArray.add(area.getName());
                        }
                        cityAreaArray.add(areaNameArray);
                    }
                    areaArray.add(cityAreaArray);
                    cityArray.add(cityNameArray);
                }
                view.initAreaPicker(provinceArray,cityArray,areaArray);
            }
        }
    }

}
