package com.masterfan.cloudbook.activity.test;

import android.os.Bundle;
import android.os.Environment;
import android.widget.ProgressBar;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.utils.S;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.concurrent.Executor;

import butterknife.Bind;

@MTFActivityFeature(layout = R.layout.activity_test_download, status_bar_color = R.color.colorPrimary, toolbar = R.id.toolbar)
public class TestDownloadActivity extends MTFBaseActivity implements Callback.CommonCallback<File>, Callback.ProgressCallback<File>, Callback.Cancelable {

    @Bind(R.id.progress_bar)
    ProgressBar bar;

    private final String download_url = "https://codeload.github.com/cundong/HeaderAndFooterRecyclerView/zip/master";
    private final String save_file_path= Environment.getExternalStorageDirectory().getAbsolutePath() + "/bbc.aar";
    private final Executor executor = new PriorityExecutor(2, true);

    @Override
    public void initialize(Bundle savedInstanceState) {
        RequestParams params = new RequestParams(download_url);
        params.setAutoRename(true);
        params.setSaveFilePath(save_file_path);
        params.setExecutor(executor);
        x.http().get(params, this);
//        cancelable.cancel();取消下载
    }

        @Override
        public void cancel() {
            S.o("cancel");
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public void onWaiting() {
            S.o("onWaiting");
        }

        @Override
        public void onStarted() {
            S.o("onStarted");
        }

        @Override
        public void onLoading(long total, long current, boolean isDownloading) {
            S.o("total=" + total + "        current=" + current);
            float curr = current/(float)total;
            bar.setProgress((int)(curr * 100));
        }

        @Override
        public void onSuccess(File result) {
            S.o("success");
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            S.o("onError:" + ex.getCause().getMessage());
        }

        @Override
        public void onCancelled(CancelledException cex) {
            S.o("onCancelled");
        }

        @Override
        public void onFinished() {
            S.o("onFinished");
        }
}
