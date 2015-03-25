package com.example.myapp.activity;


import android.view.View;
import com.example.myapp.R;



/**
 * Created by zhangjie on 2015/3/21.
 */
public class HomeActivity extends BaseActivity{


    @Override
    public void init() {
        this.setLayoutView(R.layout.layout_base_nav_bottom);
//        this.setWebViewUrl("http://tech.sina.com.cn/z/sinawap/");
        this.setNavTopTitle("首页");
    }

    @Override
    public void initAfterContentView(){
        //Parameters:visibility One of VISIBLE, INVISIBLE, or GONE，想对应的三个常量值：0、4、8
        //VISIBLE:0  意思是可见的
        //INVISIBILITY:4 意思是不可见的，但还占着原来的空间
        //GONE:8  意思是不可见的，不占用原来的布局空间
        findViewById(R.id.id_nav_top_left).setVisibility(View.INVISIBLE);
    }


}
