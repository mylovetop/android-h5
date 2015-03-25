package com.example.myapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.myapp.R;
import com.example.myapp.common.Constant;
import com.example.myapp.util.ParcelableData;

/**
 * Created by zhangjie on 2015/3/21.
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    //网页地址
    private String webViewUrl = "http://www.baidu.com";
    //网页存放跟目录
    private String webPageRoot = "file:///android_assets/www/";
    //页面布局view
    private int layoutView = R.layout.layout_base;
    //顶部导航栏标题
    private  String navTopTitle = "title";

    private Activity nextActivity;

    private ParcelableData parcelableData;

    private Boolean navBottomHide = false;



    public abstract void init();
    //初始化信息，在setContentView 方式之后
    public void initAfterContentView(){
        hideNavBottom();
        navBottomClick();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParcelableData();
        init();
        setContentView(layoutView);
        initAfterContentView();
        setOnListenerClick();
        initContent();
    }

    //初始化页面数据
    private void initParcelableData(){
        Bundle bundle = getIntent().getExtras();
        if (null != bundle){
            parcelableData = (ParcelableData)bundle.getParcelable(Constant.KEY_PARCEL_ABLE_DATA);
            if (null != parcelableData){
                this.webViewUrl = parcelableData.getWebViewUrl();
                this.navTopTitle = parcelableData.getNavTopTitle();
            }
        }
    }

    //设置页面监听事件
    public void setOnListenerClick(){
        findViewById(R.id.id_nav_top_left).setOnClickListener(this);
        setNavBottomOnListenerClick();
    }

    private void setNavBottomOnListenerClick(){
        RadioButton radioButton1 = (RadioButton)findViewById(R.id.id_nav_bottom_item_1);
        if (null != radioButton1){
            radioButton1.setOnClickListener(this);
        }

        RadioButton radioButton2 = (RadioButton)findViewById(R.id.id_nav_bottom_item_2);
        if (null != radioButton2){
            radioButton2.setOnClickListener(this);
        }

        RadioButton radioButton3 = (RadioButton)findViewById(R.id.id_nav_bottom_item_3);
        if (null != radioButton3){
            radioButton3.setOnClickListener(this);
        }
    }

    //初始化页面显示内容
    private void initContent(){
        initTitle();
        initWebView();
    }

    private void initTitle(){
        TextView tv = (TextView)findViewById(R.id.id_nav_top_title);
        tv.setText(this.navTopTitle);
    }

    //隐藏底部导航栏
    private void hideNavBottom(){

    }

    //导航栏事件
    private void navBottomClick(){

    }


    protected void initWebView(){
        WebView webView = (WebView)findViewById(R.id.base_webView);
        //WebView加载web资源
        webView.loadUrl(webViewUrl);
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(getWebViewClient());
    }

    protected WebViewClient getWebViewClient(){
       return new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                if(!url.equals(webViewUrl)){
                    gotoPage(url);
                }else{
                    view.loadUrl(url);
                }
                return true;
            }
        };
    }

    protected  void gotoPage(String url){

        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        ParcelableData p = new ParcelableData();
        p.setWebViewUrl(url);
        p.setNavTopTitle("详情");
        bundle.putParcelable(Constant.KEY_PARCEL_ABLE_DATA, p);
        intent.putExtras(bundle);
        startActivity(intent);
//        this.overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_nav_top_left:
                finish();
                //由左向右滑入的效果
                this.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                break;
            case R.id.id_nav_bottom_item_1:
                if (!this.getClass().equals(HomeActivity.class)){
                    gotoNav("首页", HomeActivity.class);
                }
                break;
            case R.id.id_nav_bottom_item_2:
                if (!this.getClass().equals(NavSecondActivity.class)){
                    gotoNav("第二页", NavSecondActivity.class);
                }
                break;
            case R.id.id_nav_bottom_item_3:
                if (!this.getClass().equals(NavThirdActivity.class)){
                    gotoNav("第3页", NavThirdActivity.class);
                }
                break;
        }
    }

    private void gotoNav(String navTopTitle, Class classz){
        Intent intent = new Intent(this, classz);
        Bundle bundle = new Bundle();
        ParcelableData p = new ParcelableData();
        p.setNavTopTitle(navTopTitle);
        bundle.putParcelable(Constant.KEY_PARCEL_ABLE_DATA, p);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public String getWebViewUrl() {
        return webViewUrl;
    }

    public void setWebViewUrl(String webViewUrl) {
        this.webViewUrl = webViewUrl;
    }

    public String getWebPageRoot() {
        return webPageRoot;
    }

    public void setWebPageRoot(String webPageRoot) {
        this.webPageRoot = webPageRoot;
    }

    public int getLayoutView() {
        return layoutView;
    }

    public void setLayoutView(int layoutView) {
        this.layoutView = layoutView;
    }

    public String getNavTopTitle() {
        return navTopTitle;
    }

    public void setNavTopTitle(String navTopTitle) {
        this.navTopTitle = navTopTitle;
    }

    public Activity getNextActivity() {
        return nextActivity;
    }

    public void setNextActivity(Activity nextActivity) {
        this.nextActivity = nextActivity;
    }

    public ParcelableData getParcelableData() {
        return parcelableData;
    }

    public void setParcelableData(ParcelableData parcelableData) {
        this.parcelableData = parcelableData;
    }

    public Boolean getNavBottomHide() {
        return navBottomHide;
    }

    public void setNavBottomHide(Boolean navBottomHide) {
        this.navBottomHide = navBottomHide;
    }
}
