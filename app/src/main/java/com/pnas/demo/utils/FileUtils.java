package com.pnas.demo.utils;

import android.os.Environment;


import com.pnas.demo.base.MyApplication;
import com.pnas.demo.constacts.IConstant;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ljb on 2015/11/26.
 */
public class FileUtils {

    private static final String ROOT_DIR = "wmi";

    /**
     * 根据时间戳，返回一个空图片文件
     */
    public static File getImageFile() {
        String imagePath = getPicDir();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        File imageFile = new File(imagePath + File.separator + "WMI_IMG_"
                + timeStamp + ".jpg");
        LogUtil.d("IMG_URI", imageFile.getAbsolutePath());
        return imageFile;
    }


    /**
     * 获取图片目录
     *
     * @return String
     */
    public static String getPicDir() {
        return getDir(IConstant.FILE_IMAGE_PATH + File.separator + "pic");
    }

    /**
     * 获取截图存放目录
     */
    public static String getPicClipDir() {
        return getDir(IConstant.FILE_IMAGE_PATH + File.separator + "pic" + File.separator + "clip");
    }

    /**
     * 获取存储根目录
     *
     * @param string
     * @return String
     */
    private static String getDir(String string) {
        if (isSDAvailable()) {
            return getSDDir(string);
        } else {
            return getDataDir(string);
        }
    }

    /**
     * 判断sd卡是否可以用
     *
     * @return boolean
     */
    private static boolean isSDAvailable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED) ? true : false;
    }


    /**
     * 获取到手机内存的目录
     *
     * @param string
     * @return
     */
    private static String getDataDir(String string) {
        String path = MyApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + string;
        File file = new File(path);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getAbsolutePath();
            } else {
                return "";
            }
        }
        return file.getAbsolutePath();
    }

    /**
     * 获取到sd卡的目录
     *
     * @param key_dir
     * @return
     */
    private static String getSDDir(String key_dir) {
        StringBuilder sb = new StringBuilder();
        String absolutePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath();
        sb.append(absolutePath);
        sb.append(File.separator).append(ROOT_DIR).append(File.separator)
                .append(key_dir);

        String filePath = sb.toString();
        LogUtil.d("IMG_URI", "filePath-->" + filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getAbsolutePath();
            } else {
                return "";
            }
        }
        return file.getAbsolutePath();
    }

    public static String formetFileSize(long fileS) {// 转换文件大小

        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

}
