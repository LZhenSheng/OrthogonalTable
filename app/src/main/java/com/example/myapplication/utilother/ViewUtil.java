package com.example.myapplication.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewUtil {
    /**
     * 初始化垂直方向LinearLayoutManager RecyclerView
     * 有小的分割线
     *
     * @param rv
     */
    public static void initVerticalLinearRecyclerView(Context context, RecyclerView rv) {
        //尺寸固定
        rv.setHasFixedSize(true);

        //布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rv.setLayoutManager(layoutManager);

        //分割线
        DividerItemDecoration decoration = new DividerItemDecoration(context, RecyclerView.VERTICAL);
        rv.addItemDecoration(decoration);
    }


    /**
     * 从view创建bitmap(截图)
     *
     * @param view
     * @return
     */
    public static Bitmap captureBitmap(View view) {
        //创建一个和view一样大小的bitmap
        if(view==null){
//            ToastUtil.errorShortToast("错了");
            return null;

        }
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);

        //创建一个画板
        //只是这个画板最终画的内容
        //在Bitmap上
        Canvas canvas = new Canvas(bitmap);

        //获取view的背景
        Drawable background = view.getBackground();
        if (background != null) {
            //如果有背景

            //就显示绘制背景
            background.draw(canvas);
        } else {
            //没有背景

            //绘制白色
            canvas.drawColor(Color.WHITE);
        }

        //绘制view内容
        view.draw(canvas);

        //返回bitmap
        return bitmap;
    }

}
