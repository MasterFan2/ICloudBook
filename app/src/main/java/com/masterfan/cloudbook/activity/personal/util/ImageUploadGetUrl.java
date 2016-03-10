package com.masterfan.cloudbook.activity.personal.util;

/**
 * Created by Administrator on 2016/3/1 0001.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.personal.ui.MultiImageSelectorActivity;
import com.masterfan.cloudbook.activity.personal.widget.ChoosePicturePopupWindow;
import com.masterfan.cloudbook.activity.personal.widget.Crop;

import java.io.File;
import java.util.ArrayList;

import javax.security.auth.login.LoginException;

/**
 * Created by zgc on 2015/5/8.
 * 得到 选择图片 或照相的 图片 地址
 */
public class ImageUploadGetUrl implements View.OnClickListener{


    private final static String TAG = "ImageGetUrl";

    private GetPictureBack getPictureBack;

    private boolean isSetONactivityResult = false;// 是否设置了 onActivityResult

    private ChoosePicturePopupWindow menuWindow;

    private static final int REQUEST_IMAGE = 2;//请求 加载图片
    // 请求加载系统照相机
    private static final int REQUEST_CAMERA = 100;

    private Activity activity;

    private boolean isShootme = false;// 是否 截图

    private boolean issquare;//  截图时， 是否为 正方形

    private File mTmpFile;// 照相后 保存的 文件

    private DialogLoadingUtil dialogLoadingUtil;

    private int x;
    private int y;

    private int width;
    private int height;

    public ImageUploadGetUrl(GetPictureBack getPictureBack){

        this.getPictureBack = getPictureBack;
        if(isSetONactivityResult == false){

            Log.e(TAG,"onActivityResult()  ==   null");
        }
    }

    /**
     * 图片弹出框，提示用户 选择图片 还是 拍照
     * @param activity
     * @param showView
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public void showdiaView(Activity activity,View showView){

        this.activity = activity;
        this.isShootme = false;
        menuWindow = new ChoosePicturePopupWindow(activity,this);
        menuWindow.showAtLocation(showView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        menuWindow.update();
    }

    /**
     * 弹出提示框，并截图后处理
     * @param activity
     * @param showView
     * @param x  宽度比
     * @param y 高度比
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public void showdiaViewwithAspect(Activity activity,View showView,int x, int y){

        this.activity = activity;
        this.isShootme = false;
        this.x = x;
        this.y = y;
        menuWindow = new ChoosePicturePopupWindow(activity,this);
        menuWindow.showAtLocation(showView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        menuWindow.update();
    }


    /**
     * 弹出提示框，并截图后处理
     * @param activity
     * @param showView
     * @param width  宽度
     * @param height 高度
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public void showdiaViewwithMaxSize(Activity activity,View showView,int width, int height){

        this.activity = activity;
        this.isShootme = true;
        this.width = width;
        this.height = height;
        menuWindow = new ChoosePicturePopupWindow(activity,this);
        menuWindow.showAtLocation(showView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        menuWindow.update();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("AAAA","ImageUploadGetUrl页面的onActivityResult");
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == -1) {// 选择 相片 返回

                ArrayList<String> mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                if(mSelectPath == null){
                    return;
                }
                if(mSelectPath.size()==0){
                    return;
                }
                if(isShootme == false){// 不需要截图 处理得时候，直接返回

                    sendFileUrl(mSelectPath.get(0));
                    return;
                }else{// 请求 截图
                    crop(mSelectPath.get(0));
                    return;
                }
            }
        }
        // 相机拍照完成后，返回图片路径
        if(requestCode == REQUEST_CAMERA){
            if(resultCode == Activity.RESULT_OK) {
                if (mTmpFile != null) {

                    if(isShootme == false){// 不需要截图 处理得时候，直接返回
                        sendFileUrl(mTmpFile.toString());
                        return;
                    }else{
                        crop(mTmpFile.toString());
                    }

                }
            }else{
                if(mTmpFile != null && mTmpFile.exists()){
                    mTmpFile.delete();
                }
            }
        }

        if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, data);
        }

    }

    // 截图后 返回
    private void handleCrop(int resultCode, Intent result) {
        Log.i("AAAA","截图后 返回");
        if (resultCode == -1) {
            Uri uri = Crop.getOutput(result);

            File file2 = ChoosePictures.getFileFromUri(uri);
            if(file2 == null){
                return;
            }
            if(file2.exists() == false){
                return;
            }
            sendFileUrl(file2.toString());
        } else if (resultCode == Crop.RESULT_ERROR) {

            String mes = Crop.getError(result).getMessage();
            if(!TextUtils.isEmpty(mes)){
                Toast.makeText(activity,mes, Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onClick(View view) {

        if(menuWindow != null) menuWindow.dismiss();
        switch (view.getId()) {
            case R.id.pop_choose_album_btn://选择相片

                showSelectAction(activity);

                break;
            case R.id.pop_take_photo_btn: //拍照

                showCameraAction(activity);
                break;
        }
    }

    /**
     * 选择 相册
     * @param activity
     */
    public void showSelectAction(Activity activity) {
        this.activity = activity;

        if(activity == null){
            return;
        }

        Intent intent = new Intent(activity, MultiImageSelectorActivity.class);
        // 是否显示拍摄图片
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
        // 最大可选择图片数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
        // 选择模式
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);// 设置 为 多选
        // 默认选择
//                if (mSelectPath != null && mSelectPath.size() > 0) {
//                    intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, mSelectPath);
//                }
        activity.startActivityForResult(intent, REQUEST_IMAGE);
    }

    /**
     * 选择相机
     */
    public void showCameraAction(Activity activity) {
        this.activity = activity;


        // 跳转到系统照相机
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(cameraIntent.resolveActivity(activity.getPackageManager()) != null){
            // 设置系统相机拍照后的输出路径
            // 创建临时文件
            mTmpFile = FileUtils.createTmpFile(activity);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
            activity.startActivityForResult(cameraIntent, REQUEST_CAMERA);
        }else{
            Toast.makeText(activity, R.string.msg_no_camera, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 返回 图片 URl
     * @param imgUrl
     */
    private final void sendFileUrl(final String imgUrl){

        dialogLoadingUtil =   new DialogLoadingUtil(activity, R.style.MDialog_load,"正在处理....");
        dialogLoadingUtil.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = ImgBmpUtil.compressByScale(imgUrl);

                Message msg = new Message();
                msg.what =200;
                msg.obj = str;
                handler.sendMessage(msg);
            }
        }).start();

    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            switch (msg.what) {

                case 200:

                    if(dialogLoadingUtil !=null){
                        dialogLoadingUtil .dismiss();
                    }

                    String str = (String) msg.obj;
                    if(str == null){
                        return;
                    }
                    File file2 = new File(str);
                    if (file2 == null) {
                        T.s(activity, "文件上传失败，请重新上传！");
                        return;
                    }
                    getPictureBack.getBack(str);
            }
        }
    };

    // 截图
    private void crop(String fileUrl){
        Log.i("AAAA","进入 crop  截图 "+fileUrl);
        Uri destination = Uri.fromFile(new File(activity.getCacheDir(), "cropped"));
        if(x !=0&&y!=0){
            Log.i("AAAA","进入 crop  截图111 ");
            Crop.of( Uri.fromFile(new File(fileUrl)), destination).withAspect(x,y).start(activity);
        }else{
            if(width !=0&&height !=0){
                Log.i("AAAA","进入 crop  截图222 ");
                Crop.of(Uri.fromFile(new File(fileUrl)), destination).withAspect(x,y).start(activity);
            }else{
                Log.i("AAAA","进入 crop  截图333 ");
                Crop.of(Uri.fromFile(new File(fileUrl)), destination).withAspect(1,1).start(activity);
            }
        }
    }



}
