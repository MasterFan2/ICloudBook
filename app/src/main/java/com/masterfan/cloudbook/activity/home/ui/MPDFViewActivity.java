package com.masterfan.cloudbook.activity.home.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.masterfan.cloudbook.R;
import com.masterfan.pdflibrary.pdfview.PDFView;
import com.masterfan.pdflibrary.pdfview.listener.OnLoadCompleteListener;
import com.masterfan.pdflibrary.pdfview.listener.OnPageChangeListener;

import java.io.File;

/**
 * Created by 13510 on 2016/2/2.
 */
public class MPDFViewActivity extends AppCompatActivity implements OnPageChangeListener {

    public static final String SAMPLE_FILE = "java.pdf";

    public static final String ABOUT_FILE = "about.pdf";

    PDFView pdfView;

    String pdfName = SAMPLE_FILE;

    Integer pageNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_read_pdf);

        File file = new File(SAMPLE_FILE);
        Log.i("AAAA","文件地址："+file.getPath());
        Log.i("AAAA","存在？="+file.exists());
        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        System.out.println(":::加载完成:::" + nbPages);
                    }
                })
                .load();

        pdfView.enableDoubletap(true);
        pdfView.enableSwipe(true);


    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        System.out.println(page + "");
    }
}
