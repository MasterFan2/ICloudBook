package com.masterfan.cloudbook.activity.test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import butterknife.Bind;
import butterknife.OnClick;

@MTFActivityFeature(layout = R.layout.activity_main, status_bar_color = R.color.colorPrimary, toolbar = R.id.toolbar)
public class TestMainActivity extends MTFBaseActivity {

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    public void initialize(Bundle savedInstanceState) {

        toolbar.setTitle("测试Main");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @OnClick(R.id.material_icon_btn)
    public void gotoMaterialIcon(View view){
        animStart(TestMaterialIconActivity.class);
    }

    @OnClick(R.id.recycler_linearlayout_btn)
    public void gotoRecyclerLinearlayout(View view){
        animStart(TestRecyclerViewLinearLayoutActivity.class);
    }


    @OnClick(R.id.download)
    public void gotoDownload(View view){
        animStart(TestDownloadActivity.class);
    }

    ///Menu .
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
