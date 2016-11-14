package com.xianzhifengshui.ui.index.home;

import android.os.Handler;

import com.xianzhifengshui.adapter.ViewSupportModel;
import com.xianzhifengshui.api.model.Carousel;
import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.NaviMenu;
import com.xianzhifengshui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 作者: chengx
 * 日期: 2016/10/26.
 * 描述:
 */
public class HomePresenter extends BasePresenter implements HomeContract.Presenter{

    private HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void refreshData() {
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Object> data = new ArrayList<>();
                ArrayList<Carousel> carousels = new ArrayList<>();
                String[] imgUrls = {"http://img3.fengniao.com/forum/attachpics/913/114/36502745.jpg",
                "http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg",
                "http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg",
                "http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg"};
        for (int i = 0; i < 4; i++) {
            Carousel carousel = new Carousel();
            carousel.setPicUrl(imgUrls[i]);
            carousels.add(carousel);
        }
        data = new ArrayList<>();
        data.add(carousels);
        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
        for (int i = 0; i < 8; i++) {
            data.add(new NaviMenu());
        }
        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_LABEL,"推荐大师",true));
        for (int i = 0; i < 4; i++) {
            data.add(new Master());
        }
        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_LABEL,"精品讲座",false));
        for (int i = 0; i < 4; i++) {
            data.add(new Lecture());
        }
                view.closeWait();
                view.refreshData(data);
            }
        },3000);
    }

    @Override
    public void loadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.closeWait();
            }
        },3000);
    }
}
