package com.xianzhifengshui.ui.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.ChatAdapter;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.KeyboardUtils;
import com.xianzhifengshui.widget.RecordVoiceButton;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.sax.TemplatesHandler;

import cn.jpush.im.android.api.model.Message;

/**
 * 作者: chengx
 * 日期: 2016/11/29.
 * 描述:
 */
public class ChatActivity extends BaseActivity implements ChatContract.View, View.OnClickListener, View.OnFocusChangeListener, TextWatcher {


    /*====== 控件声明区 =======*/
    private RecordVoiceButton rvBtn;
    private ImageView switchBtn;
    private ImageView addFileBtn;
    private EditText chatEt;
    private TableLayout tableLayout;
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    private ImageButton pickImageBtn;
    private ImageButton takePhotoBtn;
    private TextView sendBtn;
    /*=======================*/

    private ChatContract.Presenter presenter;
    private boolean softInputShowing = false;
    private boolean isInputBykeyBoard = true;
    private ChatAdapter adapter;
    private List<Message> data;


    public static void launcher(Context context){
        Intent intent = new Intent(context,ChatActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setTitle("管理员");
    }

    @Override
    protected void initViews() {
        rvBtn = (RecordVoiceButton) findViewById(R.id.jmui_voice_btn);
        rvBtn.setOnRecordListener((ChatPresenter)presenter);
        switchBtn = (ImageView) findViewById(R.id.jmui_switch_voice_ib);
        addFileBtn = (ImageView) findViewById(R.id.jmui_add_file_btn);
        chatEt = (EditText) findViewById(R.id.jmui_chat_input_et);
        tableLayout = (TableLayout) findViewById(R.id.jmui_more_menu_tl);
        pickImageBtn = (ImageButton) findViewById(R.id.jmui_pick_from_local_btn);
        takePhotoBtn = (ImageButton) findViewById(R.id.jmui_pick_from_camera_btn);
        pullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.recyclerView);
        sendBtn = (TextView) findViewById(R.id.jmui_send_btn);
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        switchBtn.setOnClickListener(this);
        addFileBtn.setOnClickListener(this);
        chatEt.setOnFocusChangeListener(this);
        chatEt.addTextChangedListener(this);
        sendBtn.setOnClickListener(this);
        presenter.init();
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        adapter = new ChatAdapter(this,data);
        presenter = new ChatPresenter(this,"admin");
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void loadHistory(List<Message> data) {
        adapter.setData(data);
        recyclerView.smoothScrollToPosition(adapter.getItemCount()-1);
    }

    @Override
    public void loadMessage(Message data) {
        log(data);
        adapter.addMsgToList(data);
        recyclerView.smoothScrollToPosition(adapter.getItemCount()-1);
    }

    @Override
    public void updateVoiceLevel(int level) {
        log(level);
        rvBtn.updateVoiceLevel(level);
    }


    @Override
    public void setPresenter(ChatContract.Presenter presenter) {
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
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    public void setMoreMenuHeight() {
        int softKeyboardHeight = sp.getInt("keyboardHeight", 0);
        if(softKeyboardHeight > 0){
            tableLayout.setLayoutParams(new LinearLayout
                    .LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, softKeyboardHeight));
        }

    }

    private void dismissMoreMenu(){
        tableLayout.setVisibility(View.GONE);
    }

    private void isKeyboard(){
        switchBtn.setImageResource(R.drawable.chat_voice_icon);
        chatEt.setVisibility(View.VISIBLE);
        rvBtn.setVisibility(View.GONE);
    }

    private void notKeyboard(){
        switchBtn.setImageResource(R.drawable.chat_keybord_icon);
        chatEt.setVisibility(View.GONE);
        rvBtn.setVisibility(View.VISIBLE);
    }

    private void showSoftInputAndDismissMenu(){
        KeyboardUtils.showSoftInput(this,chatEt);
        tableLayout.setVisibility(View.GONE);
        softInputShowing = true;
        setMoreMenuHeight();
    }

    private void showSendBtn(){
        if (isInputBykeyBoard){
            addFileBtn.setVisibility(View.GONE);
            sendBtn.setVisibility(View.VISIBLE);
        }
    }

    private void hideSendBtn(){
        sendBtn.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jmui_switch_voice_ib:
                dismissMoreMenu();
                isInputBykeyBoard = !isInputBykeyBoard;
                if (isInputBykeyBoard){
                    isKeyboard();
                    showSoftInputAndDismissMenu();
                }else {
                    notKeyboard();
                    KeyboardUtils.hideSoftInput(this);
                    softInputShowing = false;
                    if (tableLayout.getVisibility() == View.VISIBLE){
                        tableLayout.setVisibility(View.GONE);
                    }

                }
                break;
            case R.id.jmui_add_file_btn:
                if (!isInputBykeyBoard){
                    isKeyboard();
                    isInputBykeyBoard = true;
                    tableLayout.setVisibility(View.VISIBLE);
                }else {
                    if (tableLayout.getVisibility() == View.VISIBLE){
                        showSoftInputAndDismissMenu();
                    }else {
                        KeyboardUtils.hideSoftInput(this);
                        softInputShowing = false;
                        chatEt.clearFocus();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tableLayout.setVisibility(View.VISIBLE);
                            }
                        }, 200);

                    }
                }
                break;
            case R.id.jmui_send_btn:
                String content = chatEt.getText().toString();
                presenter.sendTextMessage("admin",content);
                chatEt.setText("");
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            dismissMoreMenu();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length()>0){
            showSendBtn();
        }else {
            hideSendBtn();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
