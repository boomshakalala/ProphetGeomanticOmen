package com.xianzhifengshui.ui.index.home;

import android.os.Handler;

import com.xianzhifengshui.adapter.ViewSupportModel;
import com.xianzhifengshui.api.BaseListModel;
import com.xianzhifengshui.api.model.Carousel;
import com.xianzhifengshui.api.model.HomeItemModle;
import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.NaviMenu;
import com.xianzhifengshui.api.model.User;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.api.utils.KLog;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

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

    public void checkLogin(final SPUtils sp){
        if (sp.getBoolean(AppConfig.IS_LOGIN)){
            UserInfo userInfo = JMessageClient.getMyInfo();
            if (userInfo == null){
                User user = sp.getObject("user", null);
                if (user != null) {
                    JMessageClient.login(user.getUsername(), user.getPassword(), new BasicCallback() {
                        @Override
                        public void gotResult(int i, String s) {
                            if (i == 0){
                                return;
                            }else {
                                sp.clear();
                            }
                        }
                    });
                }

            }else {
                return;
            }
        }else {
            return;
        }

    }

    @Override
    public void refreshData() {
        view.showWaiting();
        api.indexGetDataList(new ActionCallbackListener<HomeItemModle>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(HomeItemModle data) {
                final List<Object> dataList = new ArrayList<>();
//                dataList.addAll(data.getCarouselList());
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
                dataList.add(carousels);
                dataList.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
                dataList.addAll(data.getNaviMenuList());
                dataList.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
                api.masterList(1, AppConfig.PAGE_SIZE, new ActionCallbackListener<BaseListModel<ArrayList<Master>>>() {
                    @Override
                    public void onProgress(long bytesWritten, long totalSize) {

                    }

                    @Override
                    public void onSuccess(BaseListModel<ArrayList<Master>> data) {
                        dataList.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_LABEL,"推荐大师",true));
                        dataList.addAll(data.getList());
                        dataList.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
                        api.lecturesList(1, AppConfig.PAGE_SIZE, new ActionCallbackListener<BaseListModel<ArrayList<Lecture>>>() {
                            @Override
                            public void onProgress(long bytesWritten, long totalSize) {

                            }

                            @Override
                            public void onSuccess(BaseListModel<ArrayList<Lecture>> data) {
                                dataList.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_LABEL,"推荐讲座",false));
                                dataList.addAll(data.getList());
                                dataList.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
                                log(dataList);
                                view.refreshData(dataList);
                                view.closeWait();
                            }

                            @Override
                            public void onFailure(int errorEvent, String message) {
                                view.showTip(message);
                                view.showFailure();

                            }
                        });
                    }

                    @Override
                    public void onFailure(int errorEvent, String message) {
                            view.showTip(message);
                            view.showFailure();
                    }
                });
                log(dataList);
                view.refreshData(dataList);
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.showTip(message);
                view.showFailure();
            }
        });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                List<Object> data ;
//                ArrayList<Carousel> carousels = new ArrayList<>();
//                String[] imgUrls = {"http://img3.fengniao.com/forum/attachpics/913/114/36502745.jpg",
//                "http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg",
//                "http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg",
//                "http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg"};
//        for (int i = 0; i < 4; i++) {
//            Carousel carousel = new Carousel();
//            carousel.setPicUrl(imgUrls[i]);
//            carousels.add(carousel);
//        }
//        data = new ArrayList<>();
//        data.add(carousels);
//        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
//        for (int i = 0; i < 8; i++) {
//            data.add(new NaviMenu());
//        }
//        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
//        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_LABEL,"推荐大师",true));
//        for (int i = 0; i < 4; i++) {
//            data.add(new Master());
//        }
//        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
//        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_LABEL,"精品讲座",false));
//        for (int i = 0; i < 4; i++) {
//            data.add(new Lecture());
//        }
//                view.closeWait();
//                view.refreshData(data);
//            }
//        },3000);
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

    @Override
    public void praise(String masterCode) {
        String userCode = getUserCode();
        api.masterPointOfPraise(masterCode, userCode, new ActionCallbackListener<Void>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(Void data) {

            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.showTip(message);
            }
        });
    }
}
