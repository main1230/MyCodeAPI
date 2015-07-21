package com.zzl.test.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Toast;

import com.zzl.test.application.AppManager;
import com.zzl.test.application.PreferencesManager;
import com.zzl.test.utils.NetworkStatueUtil;

/**
 * Description: 基类
 * Created by zhangzl
 * Date: 15/7/21
 */
abstract class BaseActivity extends FragmentActivity {
    public ProgressDialog mProgressDialog;
    public PreferencesManager mPreferencesManager;
    /**
     * 初始化控件
     */
    public abstract void initUI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        AppManager.getAppManager().addActivity(this);
        mPreferencesManager = PreferencesManager.getInstance();
        initUI();
    }

    /**
     * 跳转到其他界面
     * @param cls 跳转页面
     * @param bundle Bundle参数
     * @param isReturn 是否返回
     * @param requestCode 请求Code
     * @param isFinish 是否销毁当前页面
     */
    public void jumpToPage(Class<?> cls, Bundle bundle, boolean isReturn,
                           int requestCode, boolean isFinish) {
        if (cls == null){
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (isReturn){
            startActivityForResult(intent, requestCode);
        } else {
            startActivity(intent);
        }
        if (isFinish) {
            finish();
        }
    }
    /**
     * 跳转到其他界面
     * @param cls 跳转页面
     * @param bundle Bundle参数
     * @param isFinish 是否销毁当前页面
     */
    public void jumpToPage(Class<?> cls, Bundle bundle, boolean isFinish){
        jumpToPage(cls, bundle, false, 0, isFinish);
    }
    /**
     * 跳转到其他界面，不销毁当前页面，也不传参数
     * @param cls 跳转页面
     */
    public void jumpToPage(Class<?> cls){
        jumpToPage(cls, null, false, 0, false);
    }

    /**
     * 显示提示信息
     * @param rId 提示信息内容所对于的string
     */
    public void showToastMsg(int rId) {
        Toast.makeText(this, rId, Toast.LENGTH_LONG).show();
    }
    public void showToastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示进度条
     * @param msg
     */
    public boolean showProgressDialog(String msg) {
        if (!NetworkStatueUtil.isConnectInternet(this)) {
            showToastMsg("当前网络不可用，请检查网络设置");
            return false;
        }
        msg = TextUtils.isEmpty(msg) ? "正在读取数据，请稍候..." : msg;
        mProgressDialog = ProgressDialog.show(this, null, msg, true, false);
        return true;
    }
    /**
     * 显示进度条
     * @return
     */
    public boolean showProgressDialog() {
        return showProgressDialog(null);
    }

    /**
     * 取消进度条
     */
    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
