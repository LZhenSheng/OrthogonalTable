package com.example.myapplication.activity;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.activity.base.BaseActivity;
import com.example.myapplication.bean.UniFormTable;
import com.example.myapplication.util.PreferenceUtil;
import com.github.androidprogresslayout.ProgressLayout;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;

public class GuideActivity extends BaseActivity {

    private ArrayList<UniFormTable> UniFormTableList=new ArrayList<UniFormTable>();

    @BindView(R.id.progress_layout)
    ProgressLayout progressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
    }

    @Override
    public void initData() {
        super.initData();
        lightStatusBar(R.color.white);
        if(PreferenceUtil.getGuide()){
            startActivityAfterFinishThis(LoginActivity.class);
        }else{
            progressLayout.showProgress();

            loadTable();
            PreferenceUtil.setGuide(true);
            progressLayout.showContent();

            startActivityAfterFinishThis(LoginActivity.class);
        }

    }


    private void loadTable(){
        try{
            InputStream inputStream=getResources().openRawResource(R.raw.table);
            InputStreamReader isr=null;
            isr=new InputStreamReader(inputStream,"UTF-8");
            BufferedReader br=new BufferedReader(isr);
            String line="";
            ArrayList<String> readList=new ArrayList<String>();
            while((line=br.readLine())!=null){
                readList.add(line);
            }


            for(int k=0;k<readList.size();k++) {
                line = readList.get(k);
                if (line.contains("^")) {
                    //创建正交矩阵表对象
                    UniFormTable TableFactors = new UniFormTable();
                    int index1 = line.indexOf('=');
                    int length = line.length();

                    //行数
                    TableFactors.setmRuns(Integer.parseInt(line.substring(index1 + 1, length).trim()));

                    //因素
                    String s = line.substring(0, index1 - 1).trim();
                    TableFactors.setmFactorLevelCount(s.split(" ").length);
                    TableFactors.setmFactors(new int[TableFactors.getmFactorLevelCount()]);
                    TableFactors.setmLevels(new int[TableFactors.getmFactorLevelCount()]);


                    for (int j = 0; j < TableFactors.getmFactorLevelCount(); j++) {
                        //因素
                        TableFactors.getmFactors()[j] = Integer.parseInt(s.split(" ")[j].split("\\^")[1].toString());
                        //水平
                        TableFactors.getmLevels()[j] = Integer.parseInt(s.split(" ")[j].split("\\^")[0].toString());
                    }

                    //总因素数
                    int TotalFactorCount = 0;
                    for (int i = 0; i < TableFactors.getmFactorLevelCount(); i++) {
                        TotalFactorCount = TotalFactorCount + TableFactors.getmFactors()[i];
                    }


                    TableFactors.setmTableMatrixString(new String[TableFactors.getmRuns()]);
                    //构造正交矩阵表，行数=实验次数，列数=总因素数也就是变量的个数
                    TableFactors.setmTableMatrix(new String[TableFactors.getmRuns()][TotalFactorCount]);

                    //从"^"的下一行开始
                    String MatrixString = "";
                    int indexs = 0;//正交表矩阵行数
                    for (int ll = k + 1; ll < readList.size(); ll++) {
                        //判断正交矩阵是否结束
                        if (readList.get(ll).length() == 0) {
                            break;
                        }

                        int start = 0;
                        int end = 0;
                        int eee = 0;
                        //有几组因素
                        for (int bb = 0; bb < TableFactors.getmFactorLevelCount(); bb++) {
                            //每组因素数的个数
                            for (int cc = 0; cc < TableFactors.getmFactors()[bb]; cc++) {
                                //该组因素水平数的占位长度
                                int levelwidth = 0;
                                //这一组因素数有多少种取值
                                if (TableFactors.getmLevels()[bb] <= 10) {
                                    levelwidth = 1;
                                } else if (TableFactors.getmLevels()[bb] <= 100) {
                                    levelwidth = 2;
                                }
                                //逐行逐个添加
                                end = end + levelwidth;
                                TableFactors.getmTableMatrix()[indexs][eee] = readList.get(ll).substring(start, end);
                                eee++;
                                start = start + levelwidth;
                            }
                        }

                        //按行存入正交表矩阵中
                        TableFactors.getmTableMatrixString()[indexs] = readList.get(ll);
                        indexs++;
                    }
                    //按每种正交表存入列表
                    UniFormTableList.add(TableFactors);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        ToastUtil.successShortToast(orm.queryUniFormTableORM().size()+" "+UniFormTableList.size());
    }

}
