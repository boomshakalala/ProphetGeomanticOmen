package com.xianzhifengshui.base;

/**
 * 作者: chengx
 * 日期: 2016/9/29.
 * 描述: view 公共接口
 */
public interface IView<T> {

    /**
     * 绑定presenter
     * @param presenter presenter实体
     */
    void setPresenter(T presenter);

    /**
     * 是否显示在前台
     * @return true or false
     */
    boolean isActive();

    /**
     * 显示WaittingDialog
     */
    void showWaiting();

    /**
     * 隐藏WaitingDialog
     */
    void closeWait();

    /**
     * 显示提示信息
     * @param text 提示信息内容
     */
    void showTip(String text);
}
