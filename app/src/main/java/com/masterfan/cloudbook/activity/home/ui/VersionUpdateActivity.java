package com.masterfan.cloudbook.activity.home.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.library.dialog.version.MTFVersionDialog;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 版本更新
 * Created by Administrator on 2016/1/27 0027.
 */
@MTFActivityFeature(layout = R.layout.activity_version_update, status_bar_color = R.color.colorPrimary)
public class VersionUpdateActivity extends MTFBaseActivity {

    @Bind(R.id.version_update_check_textview)
    TextView checkUpdateTxt;

    private MTFVersionDialog versionDialog;//版本dialog

    @Override
    public void initialize(Bundle savedInstanceState) {
        versionDialog = new MTFVersionDialog();
        versionDialog.setOnMTFDialogClickListener(new MTFVersionDialog.OnMTFDialogClickListener() {
            @Override
            public void onClose() {
                versionDialog.tilesClose();
            }

            @Override
            public void onPositive() {
                versionDialog.change2download(null);
            }

            @Override
            public void onTilesAnimFinish() {
                versionDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.version_update_check_textview)
    public void onclickCheckUpdate(View view){
        versionDialog.show(getSupportFragmentManager(),"TAG");
    }
}
