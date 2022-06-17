package com.example.myapplication.bean;

import java.util.ArrayList;
import java.util.List;

//因素类
public class Factors {
    private String mFactorName;
    private List<String> mLevels=new ArrayList<String>();

    public String getmFactorName() {
        return mFactorName;
    }

    public void setmFactorName(String mFactorName) {
        this.mFactorName = mFactorName;
    }

    public List<String> getmLevels() {
        return mLevels;
    }

    public void addList(String[] data) {
        for(int i=0;i<data.length;i++){
            mLevels.add(data[i]);
        }
    }

    public void setmLevels(List<String> mLevels) {
        this.mLevels = mLevels;
    }




}
