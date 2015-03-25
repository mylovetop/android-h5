package com.example.myapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.myapp.R;
import com.example.myapp.common.Constant;
import com.example.myapp.util.ParcelableData;

/**
 * Created by zhangjie on 2015/3/21.
 */
public class SplashActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);

        initContentView();
    }

    private void initContentView(){
        findViewById(R.id.splash_enter_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.splash_enter_button:
                Intent intent = new Intent(SplashActivity.this, MainFragmentActivity.class);
                Bundle extras = new Bundle();
                ParcelableData p = new ParcelableData();
                p.setNavTopTitle("首页");
                extras.putParcelable(Constant.KEY_PARCEL_ABLE_DATA, p);
                startActivity(intent);
                finish();
                break;
        }
    }
}
