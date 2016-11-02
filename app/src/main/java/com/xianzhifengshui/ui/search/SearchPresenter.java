package com.xianzhifengshui.ui.search;

import android.os.Handler;

import com.xianzhifengshui.api.BaseListModel;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.StringUtils;

import org.w3c.dom.ProcessingInstruction;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/28.
 * 描述:
 */
public class SearchPresenter extends BasePresenter implements SearchContract.Presenter{

    private SearchContract.View view;
    private String keyword;
    private int currentPage;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadData(String keyword) {
        this.keyword = keyword;
        view.setKeyword(keyword);
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Master> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(new Master());
                }
                view.loadData(list);
                view.closeWait();
            }
        },1500);
    }

    @Override
    public void loadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Master> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(new Master());
                }
                view.loadMore(list);
                view.closeWait();
            }
        },1500);
    }
}
