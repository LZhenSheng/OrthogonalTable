package com.example.myapplication.activity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.activity.base.BaseTitleActivity;
import com.example.myapplication.util.BitmapUtil;
import com.example.myapplication.util.ClickUtil;
import com.example.myapplication.util.ExcelUtils;
import com.example.myapplication.util.StorageUtil;
import com.example.myapplication.util.ToastUtil;
import com.example.myapplication.util.ViewUtil;
import com.example.myapplication.view.TableView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DisplayActivity extends BaseTitleActivity {

    @BindView(R.id.num)
    TextView num;

    @BindView(R.id.share_container)
    LinearLayout share_container;

    @BindView(R.id.table)
    TableView tableView;
    @BindView(R.id.agree)
    Button agree;
    @BindView(R.id.refuse)
    Button refuse;

    private File file;
    private String fileName;
    private List<String[]> data1;
    private String[] title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
    }

    @Override
    public void initData() {
        super.initData();
        Bundle bundle=getIntent().getExtras();
        //数据表头
        title=(String[])bundle.getSerializable("header");
        String [][]data=(String[][])bundle.getSerializable("content");

        //数据内容
        data1=new ArrayList<>();
        int k=0;
        for(int i=0;i<data.length;i++){
            data1.add(data[i]);
        }
        num.setText("n="+title.length);
        int row=data.length;
        tableView.clearTableContents()
                .setHeader(title)
                .addContents(data1)
                .refreshTable();
    }

    @OnClick({R.id.agree, R.id.refuse})
    public void onViewClicked(View view) {
        if(ClickUtil.isFastClick()){
            return ;
        }
        switch (view.getId()) {
            case R.id.agree:
                onSave();
                break;
            case R.id.refuse:
                exportExcel();
                break;
        }
    }

    /**
     * 导出excel
     */
    public void exportExcel() {
        file = new File(getSDPath() + "/测试");
        makeDir(file);
        ExcelUtils.initExcel(file.toString() + "/正交表测试.xls", title);
        fileName = getSDPath() + "/测试/正交表测试.xls";
        ExcelUtils.writeObjListToExcel(data1, fileName, this);
        ToastUtil.successShortToast("导出成功,位置:"+fileName);
//        ExcelUtils.writeObjListToExcel(getRecordData(), fileName, this);
    }

    private  String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        }
        String dir = sdDir.toString();
        return dir;
    }

    public  void makeDir(File dir) {
        if (!dir.getParentFile().exists()) {
            makeDir(dir.getParentFile());
        }
        dir.mkdir();
    }

    private void onSave(){
        //从View生成一张图片
        //Bitmap就是常说的位图
        Bitmap bitmap = ViewUtil.captureBitmap(share_container);
        if (share_container == null) {
            Log.i("ldksjfalkdfj", "onShareClick: ");
        }
        //先保存到自己应用的私有目录
        //例如：/storage/emulated/0/Android/data/com.ixuea.courses.mymusicold/files/Download/MyCloudMusic/1/jpg/share.jpg
        //File targetFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath().concat("/MyCloudMusic"),"share.jpg");
        File file = StorageUtil.getExternalPath(DisplayActivity.this, "1", "share", StorageUtil.JPG);

        //保存图片到文件
        BitmapUtil.saveToFile(bitmap, file);


        //将私有路径的图片保存到相册
        //这样其他应用才能访问
        Uri uri = StorageUtil.savePicture(DisplayActivity.this, file);
        if (uri != null) {
            //获取uri文件路径
            String path = StorageUtil.getMediaStorePath(DisplayActivity.this, uri);
            ToastUtil.successShortToast("保存成功");
        } else {
            ToastUtil.errorShortToast("保存失败");
        }


    }
}
