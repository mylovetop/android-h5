package com.example.myapp.activity;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

//import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.myapp.R;

/**
 * Created by zhangjie on 2015/3/24.
 */
public class MainFragmentActivity extends BaseActivity {

    private Fragment[] mFragments;
    private RadioGroup navBottom;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RadioButton navBottomItem1, navBottomItem2, navBottomItem3;


    @Override
    public void init() {

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_main_fragment);

        mFragments = new Fragment[3];
        fragmentManager = getFragmentManager();//getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.id_fragment_1);
        mFragments[1] = fragmentManager.findFragmentById(R.id.id_fragment_2);
        mFragments[2] = fragmentManager.findFragmentById(R.id.id_fragment_3);
        if (null != fragmentManager){
            fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
            if (null != fragmentTransaction){}
            fragmentTransaction.show(mFragments[0]).commit();
        }

        setFragmentIndicator();
    }


    private void setFragmentIndicator() {

        navBottom = (RadioGroup) findViewById(R.id.id_nav_bottom);
        navBottomItem1 = (RadioButton) findViewById(R.id.id_nav_bottom_item_1);
        navBottomItem2 = (RadioButton) findViewById(R.id.id_nav_bottom_item_2);
        navBottomItem3 = (RadioButton) findViewById(R.id.id_nav_bottom_item_3);

        navBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fragmentTransaction = fragmentManager.beginTransaction()
                        .hide(mFragments[0]).hide(mFragments[1])
                        .hide(mFragments[2]);
                switch (checkedId) {
                    case R.id.id_nav_bottom_item_1:
                        fragmentTransaction.show(mFragments[0]).commit();
                        break;

                    case R.id.id_nav_bottom_item_2:
                        fragmentTransaction.show(mFragments[1]).commit();
                        break;

                    case R.id.id_nav_bottom_item_3:
                        fragmentTransaction.show(mFragments[2]).commit();
                        break;

                    default:
                        break;
                }
            }
        });
    }
}
