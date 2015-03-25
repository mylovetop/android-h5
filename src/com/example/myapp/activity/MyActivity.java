package com.example.myapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import com.example.myapp.R;


public class MyActivity extends Activity{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goSplash();
    }

    private void goSplash(){
        Intent intent = new Intent(MyActivity.this, SplashActivity.class);
        startActivity(intent);
        finish();
    }


}
