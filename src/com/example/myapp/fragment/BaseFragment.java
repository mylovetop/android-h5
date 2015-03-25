package com.example.myapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.example.myapp.MainApplication;
import com.example.myapp.R;
import com.example.myapp.activity.DetailActivity;
import com.example.myapp.common.Constant;
import com.example.myapp.util.ParcelableData;

/**
 * Created by zhangjie on 2015/3/24.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {

    public MainApplication mainApplication;

    //网页地址
    private String webViewUrl = "http://www.baidu.com";
    //网页存放跟目录
    private String webPageRoot = "file:///android_assets/www/";
    //页面布局view
    private int layoutView = R.layout.layout_base;

    private String navTopTitle;



    private Activity nextActivity;

    private ParcelableData parcelableData;

    private Boolean navBottomHide = false;

    private View contentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contentView = inflater.inflate(layoutView, container, false);
        return contentView;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mainApplication == null)
            mainApplication = (MainApplication) getActivity().getApplicationContext();
        initTitle();
        initWebView();
    }

    private void initTitle(){
        TextView tv = (TextView)contentView.findViewById(R.id.id_nav_top_title);
        tv.setText(this.navTopTitle);
    }

    protected void initWebView(){
        WebView webView = (WebView)contentView.findViewById(R.id.base_webView);
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

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        Bundle bundle = new Bundle();
        ParcelableData p = new ParcelableData();
        p.setWebViewUrl(url);
        p.setNavTopTitle("详情");
        bundle.putParcelable(Constant.KEY_PARCEL_ABLE_DATA, p);
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void onClick(View view) {

    }

    public String getNavTopTitle() {
        return navTopTitle;
    }

    public void setNavTopTitle(String navTopTitle) {
        this.navTopTitle = navTopTitle;
    }
}
