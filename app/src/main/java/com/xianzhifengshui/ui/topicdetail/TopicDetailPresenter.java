package com.xianzhifengshui.ui.topicdetail;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutCompat;

import com.xianzhifengshui.adapter.ViewSupportModel;
import com.xianzhifengshui.api.model.Carousel;
import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.NaviMenu;
import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.api.model.TopicEvaluate;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.ui.index.home.HomeContract;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/7.
 * 描述:
 */
public class TopicDetailPresenter extends BasePresenter implements TopicDetailContract.Presenter {

    private TopicDetailContract.View view;

    public TopicDetailPresenter(TopicDetailContract.View view) {
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

                Topic topic = new Topic();
                data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
                data.add(topic);
                data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
                data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_LABEL,"热门评论(2)",false));

                for (int i = 0; i < 2; i++) {
                    data.add(new TopicEvaluate());
                }
                data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
                data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_LABEL,"新鲜评论(122)",true));
                for (int i = 0; i < 10; i++) {
                    data.add(new TopicEvaluate());
                }
                data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
                view.closeWait();
                view.refreshData(data);
            }
        },3000);
    }

    @Override
    public void loadMore() {
        final List<Object> data = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            data.add("");
        }
        if (view.isActive())
            view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.loadMore(data);
                view.closeWait();
            }
        },3000);
    }

}
