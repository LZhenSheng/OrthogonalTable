package com.example.myapplication.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/***
 * 存储相关工具栏
 * @author 胜利镇
 * @time 2020/8/9 21:25
 */
public class StorageUtil {
    /**
     * jpg图片
     */
    public static final String JPG = "jpg";

    /**
     * mp3
     */
    public static final String MP3 = "mp3";

    /**
     * MediaStore，ContentProviders内容提供者data列
     */
    private static final String COLUMN_DATA = "_data";

    /**
     * 保存图片到系统相册
     *
     * @param context
     * @param file
     * @return
     */
    public static Uri savePicture(Context context, File file) {
        //创建媒体路径
        Uri uri = insertPictureMediaStore(context, file);

        if (uri == null) {
            //获取路径失败
            return null;
        }

        //将原来的图片保存到目标uri
        //也就是保存到系统图片媒体库
        return saveFile(context, file, uri);
    }

    /**
     * 保存文件到uri对应的路径
     *
     * @param context
     * @param file
     * @param uri
     * @return
     */
    private static Uri saveFile(Context context, File file, Uri uri) {
        FileOutputStream fileOutputStream = null;
        try {
            //获取内容提供者
            ContentResolver contentResolver = context.getContentResolver();

            //获取uri对应的文件描述器
            ParcelFileDescriptor parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "w");

            //创建文件输出流
            fileOutputStream = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());

            //将file拷贝到输出流
            FileUtils.copyFile(file, fileOutputStream);

            return uri;
        } catch (IOException e) {
            //发送了异常
            e.printStackTrace();
        } finally {
            //判断是否需要关闭流
            if (fileOutputStream != null) {
                try {
                    //关闭流
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * 创建相册图片路径
     *
     * @param context
     * @param file
     * @return
     */
    private static Uri insertPictureMediaStore(Context context, File file) {
        //创建内容集合
        ContentValues contentValues = new ContentValues();

        //媒体显示的名称
        //设置为文件的名称
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, file.getName());

        //媒体类型
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //拍摄媒体的时间
            contentValues.put(MediaStore.Images.Media.DATE_TAKEN, file.lastModified());
        }

        return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    /**
     * 获取应用扩展sdcard中的路径
     *
     * @param context
     * @param userId  用户id
     * @param title   标题
     * @param suffix  后缀
     * @return
     */
    public static File getExternalPath(Context context, String userId, String title, String suffix) {
        //获取下载文件类型目录
        //该路径下的文件卸载应用后会清空
        //在Android 10路径为：
        // /storage/emulated/0/Android/data/com.ixuea.courses.mymusic/files/Download/
        File dir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);

        //格式化路径
        String path = String.format("MyCloudMusic/%s/%s/%s.%s", userId, suffix, title, suffix);

        //创建文件对象
        File file = new File(dir, path);

        if (!file.getParentFile().exists()) {
            //如果上层目录不存在

            //就创建
            file.getParentFile().mkdirs();
        }

        //返回文件的绝对路径
        return file;
    }

    /**
     * 获取MediaStore uri的路径
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getMediaStorePath(Context context, Uri uri) {
        return getDataColumn(context, uri);
    }

    /**
     * 获取uri对应的data列值
     * 其实就是文件路径
     * 这种写法支持MediaStore，ContentProviders
     */
    private static String getDataColumn(Context context, Uri uri) {
        //Cursor cursor = null;
        //try {
        //    cursor = context
        //            //获取内容提供者
        //            .getContentResolver()
        //
        //            //查询数据
        //            /**
        //             * @param uri 以content://开通的地址
        //             * @param projection 返回哪些列
        //             * @param selection 查询条件，类似sql中where条件
        //             * @param selectionArgs 查询条件参数
        //             * @param sortOrder 排序参数
        //             */
        //            .query(uri,
        //                    new String[]{COLUMN_DATA},
        //                    null,
        //                    null,
        //                    null);
        //
        //    if (cursor != null && cursor.moveToFirst()) {
        //        //有数据
        //
        //        //获取这一列的索引
        //        int index = cursor.getColumnIndexOrThrow(COLUMN_DATA);
        //
        //
        //        //获取这一列字符类型数据
        //        return cursor.getString(index);
        //    }
        //} catch (IllegalArgumentException e) {
        //    e.printStackTrace();
        //} finally {
        //    //关闭游标
        //    if (cursor != null) {
        //        cursor.close();
        //    }
        //}

        try (Cursor cursor = context
                //获取内容提供者
                .getContentResolver()

                //查询数据
                /**
                 * @param uri 以content://开通的地址
                 * @param projection 返回哪些列
                 * @param selection 查询条件，类似sql中where条件
                 * @param selectionArgs 查询条件参数
                 * @param sortOrder 排序参数
                 */
                .query(uri,
                        new String[]{COLUMN_DATA},
                        null,
                        null,
                        null)) {
            if (cursor != null && cursor.moveToFirst()) {
                //有数据

                //获取这一列的索引
                int index = cursor.getColumnIndexOrThrow(COLUMN_DATA);


                //获取这一列字符类型数据
                return cursor.getString(index);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return null;
    }

}
