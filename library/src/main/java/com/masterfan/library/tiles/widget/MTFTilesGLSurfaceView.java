package com.masterfan.library.tiles.widget;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.masterfan.library.tiles.render.MTFRenderer;

/**
 * Created by Artem Kholodnyi on 11/3/15.
 */
public class MTFTilesGLSurfaceView extends GLSurfaceView {
    private MTFRenderer mRenderer;

    public MTFTilesGLSurfaceView(Context context) {
        super(context);
    }

    public MTFTilesGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onPause() {
        super.onPause();
        mRenderer.cancelAnimation();
    }

    public void setRenderer(MTFRenderer renderer) {
        super.setRenderer(renderer);
        mRenderer = renderer;
    }

}
