package com.xianzhifengshui.ui.evaluate;

import android.os.Handler;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Evaluate;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.ui.evaluate.EvaluateContract;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/2.
 * 描述:
 */
public class EvaluatePresenter extends BasePresenter implements EvaluateContract.Presenter {
    EvaluateContract.View view;

    public EvaluatePresenter(EvaluateContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void refreshData() {
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Evaluate> evaluates = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Evaluate evaluate = new Evaluate();
                    evaluate.setContent("你瞅啥 瞅你咋的");
                    evaluate.setDate("2016-11-14");
                    evaluate.setIcon("http://g.hiphotos.baidu.com/image/pic/item/9e3df8dcd100baa1cb7acbdb4210b912c8fc2e7f.jpg");
                    evaluate.setNickname("OnTheWay");
                    evaluate.setTitle("瞅你咋的");
                    evaluates.add(evaluate);
                }
                view.closeWait();
                view.refreshData(evaluates);
            }
        },1500);
    }

    @Override
    public void loadMore() {
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.closeWait();
            }
        },1500);
    }
}
