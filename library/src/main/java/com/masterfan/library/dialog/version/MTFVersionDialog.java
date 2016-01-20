package com.masterfan.library.dialog.version;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.masterfan.library.R;
import com.masterfan.library.progress.MTFNumberProgressBar;
import com.masterfan.library.tiles.TilesFrameLayout;
import com.masterfan.library.tiles.interfaces.TilesFrameLayoutListener;
import com.masterfan.library.utils.S;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.concurrent.Executor;

/**
 * Created by 13510 on 2016/1/6.
 */
public class MTFVersionDialog extends DialogFragment implements Callback.CommonCallback<File>, Callback.ProgressCallback<File>, Callback.Cancelable {

    private String title = "有新版本啦";
    private String msg   = "1.添加支付功能,更方便购买啦@2.界面调整，更适合阅读@3.更多功能请升级体验.";
    private String btnTxt= "确定";

    //download info
    private final String download_url = "https://codeload.github.com/cundong/HeaderAndFooterRecyclerView/zip/master";
    private final String save_file_path= Environment.getExternalStorageDirectory().getAbsolutePath() + "/bbc.apk";
    private final Executor executor = new PriorityExecutor(2, true);

    //保存下载文件路径
    private String saveFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/venus.apk";

    private OnMTFDialogClickListener onMTFDialogClickListener;//事件监听
    private TilesFrameLayout tilesFrameLayout;//tiles

    private TextView titleTxt;
    private TextView msgTxt;

    private ImageView iconImg;
    private MTFNumberProgressBar progressBar;
    private Button positiveBtn;
    private Button closeBtn;

    private float mProgress      = 0; //下载的进度
//    private HttpHandler handler = null;//下载handler， 可取消下载
    private boolean downloadOK  = false;//是否下载成功

    public void setOnMTFDialogClickListener(OnMTFDialogClickListener l) {
        this.onMTFDialogClickListener = l;
    }

    public MTFVersionDialog() {
    }

    /**
     * 分散效果
     */
    public void tilesClose() {
        tilesFrameLayout.startAnimation();
    }

    /**
     * 切换到下载样式
     */
    public void change2download(String serverUrl) {
        titleTxt.setText("");
        msgTxt.setVisibility(View.GONE);
        iconImg.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        positiveBtn.setVisibility(View.INVISIBLE);
        closeBtn.setVisibility(View.INVISIBLE);

        RequestParams params = new RequestParams(download_url);
        params.setAutoRename(true);
        params.setSaveFilePath(save_file_path);
        params.setExecutor(executor);
        x.http().get(params, this);
//        download(requestCallBack, serverUrl, saveFile);
    }

//    /**
//     * 下载回调
//     */
//    private RequestCallBack<File> requestCallBack = new RequestCallBack<File>() {
//
//        @Override
//        public void onStart() {
//            progressBar.setPercent(0);
//        }
//
//        @Override
//        public void onLoading(long total, long current, boolean isUploading) {
//            mProgress = (float)current / total;
//            S.o(":::" + mProgress + "current=" + current + "        total=" + total);
//            progressBar.setPercent(mProgress);
//        }
//
//        @Override
//        public void onSuccess(ResponseInfo<File> fileResponseInfo) {
//            downloadOK = true;
//            tilesFrameLayout.startAnimation();
//        }
//
//        @Override
//        public void onFailure(HttpException e, String s) {
//            S.o(":::downloadError:::" + e.getExceptionCode() + "::" + s);
//            tilesFrameLayout.startAnimation();
//            downloadOK = false;
//        }
//    };

//    /**
//     * 开始下载
//     *
//     * @param callBack
//     * @param url
//     * @param saveFile
//     */
//    public void download(RequestCallBack<File> callBack, String url, String saveFile) {
//        File file = new File(saveFile);
//        if (file.exists())
//            file.delete();
//
//        HttpUtils http = new HttpUtils();
//        handler = http.download(url, saveFile,
//                true, // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
//                true, // 如果从请求返回信息中获取到文件名，下载完成后自动重命名。
//                callBack);
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args != null) {
            title  = args.getString("title");
            msg    = args.getString("msg");
            btnTxt = args.getString("btnTxt", "确定");
        }
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().getAttributes().windowAnimations = R.style.MTF_Animation_CustomDialog;
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.mtf_dialog_version);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);

        titleTxt = (TextView) dialog.findViewById(R.id.mtf_title_txt);
        titleTxt.setText(title);
        msgTxt = (TextView) dialog.findViewById(R.id.mtf_message_txt);
        msgTxt.setText(msg.contains("@") ? msg.replace("@", "\n") : msg);

        iconImg = (ImageView) dialog.findViewById(R.id.mtf_icon_img);
        progressBar = (MTFNumberProgressBar) dialog.findViewById(R.id.mtf_number_bar);

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dm.widthPixels - 30, dm.heightPixels / 3 + 150);
        tilesFrameLayout = (TilesFrameLayout) dialog.findViewById(R.id.mtf_tilesFrameLayout);
        tilesFrameLayout.setLayoutParams(params);

        tilesFrameLayout.setOnAnimationFinishedListener(new TilesFrameLayoutListener() {
            @Override
            public void onAnimationFinished() {
                if (onMTFDialogClickListener != null) {
                    onMTFDialogClickListener.onTilesAnimFinish();
                }

                if (downloadOK) {
                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(new File(saveFile)), "application/vnd.android.package-archive");
                    startActivityForResult(intent, 205);
                    downloadOK = false;
                }
            }
        });

        positiveBtn = (Button) dialog.findViewById(R.id.mtf_positive_button);
        positiveBtn.setText(btnTxt);
        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMTFDialogClickListener != null) {
                    onMTFDialogClickListener.onPositive();
                }
            }
        });
        closeBtn = (Button) dialog.findViewById(R.id.mtf_close_button);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMTFDialogClickListener != null) {
                    onMTFDialogClickListener.onClose();
                }
            }
        });

        return dialog;
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void onWaiting() {

    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onLoading(long total, long current, boolean isDownloading) {
        S.o("current=" + current + "    total=" + total);
        mProgress = (float)current / total * 100;
        progressBar.setProgress((int) mProgress);
    }

    @Override
    public void onSuccess(File result) {
        downloadOK = true;
        tilesFrameLayout.startAnimation();
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        downloadOK = false;
    }

    @Override
    public void onCancelled(CancelledException cex) {
        downloadOK = false;
    }

    @Override
    public void onFinished() {

    }

    public interface OnMTFDialogClickListener {
        void onClose();
        void onPositive();
        void onTilesAnimFinish();
    }
}
