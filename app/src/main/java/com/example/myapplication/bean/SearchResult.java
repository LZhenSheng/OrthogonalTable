package com.example.myapplication.bean;

public class SearchResult {

    private String [] title;

    private String [][] data;

    public String[] getTitle() {
        return title;
    }

    public String getTitleT() {
        String result=title[0];
        for(int i=1;i<title.length;i++){
            result+=" "+title[i];
        }
        return result;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }
}
