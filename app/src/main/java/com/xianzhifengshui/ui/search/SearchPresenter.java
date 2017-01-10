package com.xianzhifengshui.ui.search;


import com.xianzhifengshui.api.BaseListModel;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.api.utils.KLog;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BasePresenter;

import java.util.ArrayList;

/**
 * 作者: chengx
 * 日期: 2016/10/28.
 * 描述:
 */
public class SearchPresenter extends BasePresenter implements SearchContract.Presenter{

    private SearchContract.View view;
    private String keyword;
    private int currentPage = 1;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    private void requestData(){
        view.closeWait();
        api.masterList(currentPage, AppConfig.PAGE_SIZE, 1, "", keyword, new ActionCallbackListener<BaseListModel<ArrayList<Master>>>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(BaseListModel<ArrayList<Master>> data) {
                if (data.getPageNum()==currentPage){
                    //关闭记载更多
                    view.closeLoadMore();
                }
                ArrayList<Master> dataList = data.getList();
                if (dataList != null && dataList.size()>0) {
                    if (currentPage == 1){
                        view.loadData(dataList);
                    }else {
                        view.loadMore(dataList);
                    }
                }else {
                    if (currentPage == 1){
                        log("showEmpty");
                        view.showEmpty();
                    }else {
                        log("closeloadmore");
                        view.showTip("没有更多了");
                    }
                }
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.showFailure(message);
            }
        });
    }

    @Override
    public void loadData(String keyword) {
        if (keyword != null) {
            this.keyword = keyword;
        }
        view.setKeyword(keyword);
        currentPage = 1;
        requestData();

    }

    @Override
    public void loadMore() {
        currentPage++;
        requestData();
    }
}
