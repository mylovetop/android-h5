package com.example.myapp.util;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangjie on 2015/3/23.
 */
public class ParcelableData implements Parcelable {

    //activity 标题
    private String navTopTitle;
    //页面内容的url
    private String webViewUrl;



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(navTopTitle);
        parcel.writeString(webViewUrl);
    }


    // 1.必须实现Parcelable.Creator接口,否则在获取Person数据的时候，会报错，如下：
    // android.os.BadParcelableException:
    // Parcelable protocol requires a Parcelable.Creator object called  CREATOR on class com.um.demo.Person
    // 2.这个接口实现了从Percel容器读取Person数据，并返回Person对象给逻辑层使用
    // 3.实现Parcelable.Creator接口对象名必须为CREATOR，不如同样会报错上面所提到的错；
    // 4.在读取Parcel容器里的数据事，必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
    // 5.反序列化对象
    public static final Parcelable.Creator<ParcelableData> CREATOR = new Parcelable.Creator<ParcelableData>() {
        public ParcelableData createFromParcel(Parcel in) {
            // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
            ParcelableData p = new ParcelableData();
            p.setNavTopTitle(in.readString());
            p.setWebViewUrl(in.readString());
            return p;
        }

        public ParcelableData[] newArray(int size) {
            return new ParcelableData[size];
        }
    };

    public String getNavTopTitle() {
        return navTopTitle;
    }

    public void setNavTopTitle(String navTopTitle) {
        this.navTopTitle = navTopTitle;
    }

    public String getWebViewUrl() {
        return webViewUrl;
    }

    public void setWebViewUrl(String webViewUrl) {
        this.webViewUrl = webViewUrl;
    }
}
