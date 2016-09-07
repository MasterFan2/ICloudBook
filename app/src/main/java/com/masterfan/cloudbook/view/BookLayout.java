package com.masterfan.cloudbook.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Shader;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.Date;

public class BookLayout extends FrameLayout
{

    public static final String LOG_TAG = "liaowenxin";
    private int totalPageNum;
    private Context mContext;
    private boolean hasInit = false;
    private final int defaultWidth = 600, defaultHeight = 400;
    private int contentWidth = 0;
    private int contentHeight = 0;
    private View currentPage, middlePage, nextPage, prevPage;
    private LinearLayout invisibleLayout;
    private LinearLayout mainLayout;
    private BookView mBookView;
    private Handler aniEndHandle;
    private static boolean closeBook = false;

    private Corner mSelectCorner;
    private final int clickCornerLen = 250 * 250; // 50dip
    private float scrollX = 0, scrollY = 0;
    private int indexPage = 0;

    private BookState mState;
    private Point aniStartPos;
    private Point aniStopPos;
    private Date aniStartTime;
    private long aniTime = 800;
    private long timeOffset = 10;

    // private Listener mListener;
    private BookAdapter mPageAdapter;

    private GestureDetector mGestureDetector;
    private BookOnGestureListener mGestureListener;

    public BookLayout(Context context)
    {
        super(context);
        Init(context);
    }

    public BookLayout(Context context, AttributeSet attrs)
    {
        super(context,attrs);
        Init(context);
    }

    public BookLayout(Context context, AttributeSet attrs, int defStyle)
    {
        super(context,attrs,defStyle);
        Init(context);
    }

    public void setPageAdapter(BookAdapter pa)
    {
        Log.d(LOG_TAG, "setPageAdapter");
        mPageAdapter = pa;
    }

    public void Init(Context context)
    {
        Log.d(LOG_TAG, "Init");
        totalPageNum = 0;
        mContext = context;
        mSelectCorner = Corner.None;

        mGestureListener = new BookOnGestureListener();
        mGestureDetector = new GestureDetector(mGestureListener);
        mGestureDetector.setIsLongpressEnabled(false);
        aniEndHandle = new Handler();

        this.setOnTouchListener(touchListener);
        this.setLongClickable(true);
    }

    protected void dispatchDraw(Canvas canvas)
    {
        Log.d(LOG_TAG, "dispatchDraw");
        super.dispatchDraw(canvas);
        if (!hasInit)
        {
            hasInit = true;
            indexPage = 0;
            if (mPageAdapter == null)
            {
                throw new RuntimeException("please set the PageAdapter on init");
            }
            totalPageNum = mPageAdapter.getCount();
            mainLayout = new LinearLayout(mContext);
            mainLayout.setLayoutParams(new LayoutParams(contentWidth, contentHeight));
            mainLayout.setBackgroundColor(0xffffffff);
            mState = BookState.READY;

            invisibleLayout = new LinearLayout(mContext);
            invisibleLayout.setLayoutParams(new LayoutParams(contentWidth, contentHeight));

            this.addView(invisibleLayout);
            this.addView(mainLayout);

            mBookView = new BookView(mContext);
            mBookView.setLayoutParams(new LayoutParams(contentWidth, contentHeight));
            this.addView(mBookView);

            updatePageView();
            invalidate();
        }
        else if (mState == BookState.READY)
        {
            mBookView.update();
        }
    }

    /**
     * 此方法做过修改，改成一页显示
     */
    public void updatePageView()
    {
        Log.d(LOG_TAG, "updatePageView");
        if (indexPage < 0 || indexPage > totalPageNum - 1)
        {
            return;
        }
        invisibleLayout.removeAllViews();
        mainLayout.removeAllViews();

        /* 当前页 */
        currentPage = mPageAdapter.getView(indexPage);
        if (currentPage == null)
        {
            currentPage = new WhiteView(mContext);
        }
        currentPage.setLayoutParams(new LayoutParams(contentWidth, contentHeight));
        mainLayout.addView(currentPage);

        /* 背景页 */
        middlePage = new WhiteView(mContext);
        middlePage.setLayoutParams(new LayoutParams(contentWidth, contentHeight));
        invisibleLayout.addView(middlePage);

        /* 前一页 */
        prevPage = null;
        if (indexPage > 0)
        {
            prevPage = mPageAdapter.getView(indexPage - 1);
        }
        if (prevPage == null)
        {
            prevPage = new WhiteView(mContext);
        }
        prevPage.setLayoutParams(new LayoutParams(contentWidth, contentHeight));
        invisibleLayout.addView(prevPage);

        /* 后一页 */
        nextPage = null;
        if (indexPage < totalPageNum - 1)
        {
            nextPage = mPageAdapter.getView(indexPage + 1);
        }
        if (nextPage == null)
        {
            nextPage = new WhiteView(mContext);
        }
        nextPage.setLayoutParams(new LayoutParams(contentWidth, contentHeight));
        invisibleLayout.addView(nextPage);

        Log.d(LOG_TAG, "updatePageView finish");
    }

    OnTouchListener touchListener = new OnTouchListener()
    {
        public boolean onTouch(View v, MotionEvent event)
        {
            Log.d(LOG_TAG, "onTouch " + " x: " + event.getX() + " y: " + event.getY() + " mState:" + mState);
            mGestureDetector.onTouchEvent(event);
            int action = event.getAction();
            if (action == MotionEvent.ACTION_UP && mSelectCorner != Corner.None
                    && mState == BookState.TRACKING)
            {
                if (mState == BookState.ANIMATING)
                    return false;
                if (mSelectCorner == Corner.LeftTop)
                {
                    if (scrollX < contentWidth / 2)
                    {
                        aniStopPos = new Point(0, 0);
                    }
                    else
                    {
                        aniStopPos = new Point(2 * contentWidth, 0);
                    }
                }
                else if (mSelectCorner == Corner.RightTop)
                {
                    if (scrollX < contentWidth / 2)
                    {
                        aniStopPos = new Point(-contentWidth, 0);
                    }
                    else
                    {
                        aniStopPos = new Point(contentWidth, 0);
                    }
                }
                else if (mSelectCorner == Corner.LeftBottom)
                {
                    if (scrollX < contentWidth / 2)
                    {
                        aniStopPos = new Point(0, contentHeight);
                    }
                    else
                    {
                        aniStopPos = new Point(2 * contentWidth, contentHeight);
                    }
                }
                else if (mSelectCorner == Corner.RightBottom)
                {
                    if (scrollX < contentWidth / 2)
                    {
                        aniStopPos = new Point(-contentWidth, contentHeight);
                    }
                    else
                    {
                        aniStopPos = new Point(contentWidth, contentHeight);
                    }
                }
                aniStartPos = new Point((int) scrollX, (int) scrollY);
                aniTime = 800;
                mState = BookState.ABOUT_TO_ANIMATE;
                closeBook = true;
                aniStartTime = new Date();
                mBookView.startAnimation();
            }
            return true;
        }
    };

    class BookOnGestureListener implements OnGestureListener
    {
        public boolean onDown(MotionEvent event)
        {
            Log.d(LOG_TAG, "onDown");
            if (mState == BookState.ANIMATING)
                return false;
            float x = event.getX(), y = event.getY();
            int w = contentWidth, h = contentHeight;
            if (x * x + y * y < clickCornerLen)
            {
                if (indexPage > 0)
                {
                    mSelectCorner = Corner.LeftTop;
                    aniStartPos = new Point(0, 0);
                }
            }
            else if ((x - w) * (x - w) + y * y < clickCornerLen)
            {
                if (indexPage < totalPageNum - 1)
                {
                    mSelectCorner = Corner.RightTop;
                    aniStartPos = new Point(contentWidth, 0);
                }
            }
            else if (x * x + (y - h) * (y - h) < clickCornerLen)
            {
                if (indexPage > 0)
                {
                    mSelectCorner = Corner.LeftBottom;
                    aniStartPos = new Point(0, contentHeight);
                }
            }
            else if ((x - w) * (x - w) + (y - h) * (y - h) < clickCornerLen)
            {
                if (indexPage < totalPageNum - 1)
                {
                    mSelectCorner = Corner.RightBottom;
                    aniStartPos = new Point(contentWidth, contentHeight);
                }
            }
            if (mSelectCorner != Corner.None)
            {
                aniStopPos = new Point((int) x, (int) y);
                aniTime = 800;
                mState = BookState.ABOUT_TO_ANIMATE;
                closeBook = false;
                aniStartTime = new Date();
                mBookView.startAnimation();
            }
            return false;
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {
            Log.d(LOG_TAG, "onFling velocityX:" + velocityX + " velocityY:" + velocityY);
            if (mSelectCorner != Corner.None)
            {
                if (mSelectCorner == Corner.LeftTop)
                {
                    if (velocityX < 0)
                    {
                        aniStopPos = new Point(0, 0);
                    }
                    else
                    {
                        aniStopPos = new Point(2 * contentWidth, 0);
                    }
                }
                else if (mSelectCorner == Corner.RightTop)
                {
                    if (velocityX < 0)
                    {
                        aniStopPos = new Point(-contentWidth, 0);
                    }
                    else
                    {
                        aniStopPos = new Point(contentWidth, 0);
                    }
                }
                else if (mSelectCorner == Corner.LeftBottom)
                {
                    if (velocityX < 0)
                    {
                        aniStopPos = new Point(0, contentHeight);
                    }
                    else
                    {
                        aniStopPos = new Point(2 * contentWidth, contentHeight);
                    }
                }
                else if (mSelectCorner == Corner.RightBottom)
                {
                    if (velocityX < 0)
                    {
                        aniStopPos = new Point(-contentWidth, contentHeight);
                    }
                    else
                    {
                        aniStopPos = new Point(contentWidth, contentHeight);
                    }
                }
                Log.d(LOG_TAG, "onFling animate");
                aniStartPos = new Point((int) scrollX, (int) scrollY);
                aniTime = 1000;
                mState = BookState.ABOUT_TO_ANIMATE;
                closeBook = true;
                aniStartTime = new Date();
                mBookView.startAnimation();
            }
            return false;
        }

        public void onLongPress(MotionEvent e)
        {
            Log.d(LOG_TAG, "onLongPress");
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
        {
            mState = BookState.TRACKING;
            if (mSelectCorner != Corner.None)
            {
                scrollX = e2.getX();
                scrollY = e2.getY();
                mBookView.startAnimation();
            }
            return false;
        }

        public void onShowPress(MotionEvent e)
        {
            Log.d(LOG_TAG, "onShowPress");
        }

        public boolean onSingleTapUp(MotionEvent e)
        {
            Log.d(LOG_TAG, "onSingleTapUp");

            if (mSelectCorner != Corner.None)
            {
                if (mSelectCorner == Corner.LeftTop)
                {
                    if (scrollX < contentWidth / 2)
                    {
                        aniStopPos = new Point(0, 0);
                    }
                    else
                    {
                        aniStopPos = new Point(2 * contentWidth, 0);
                    }
                }
                else if (mSelectCorner == Corner.RightTop)
                {
                    if (scrollX < contentWidth / 2)
                    {
                        aniStopPos = new Point(-contentWidth, 0);
                    }
                    else
                    {
                        aniStopPos = new Point(contentWidth, 0);
                    }
                }
                else if (mSelectCorner == Corner.LeftBottom)
                {
                    if (scrollX < contentWidth / 2)
                    {
                        aniStopPos = new Point(0, contentHeight);
                    }
                    else
                    {
                        aniStopPos = new Point(2 * contentWidth, contentHeight);
                    }
                }
                else if (mSelectCorner == Corner.RightBottom)
                {
                    if (scrollX < contentWidth / 2)
                    {
                        aniStopPos = new Point(-contentWidth, contentHeight);
                    }
                    else
                    {
                        aniStopPos = new Point(contentWidth, contentHeight);
                    }
                }
                aniStartPos = new Point((int) scrollX, (int) scrollY);
                aniTime = 800;
                mState = BookState.ABOUT_TO_ANIMATE;
                closeBook = true;
                aniStartTime = new Date();
                mBookView.startAnimation();
            }
            return false;
        }
    }

    protected void onFinishInflate()
    {
        Log.d(LOG_TAG, "onFinishInflate");
        super.onFinishInflate();
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);

        if(changed)
        {
            contentWidth = this.getWidth();
            contentHeight = this.getHeight();
            if (contentWidth == 0)
                contentWidth = defaultWidth;
            if (contentHeight == 0)
                contentHeight = defaultHeight;
            Log.d(LOG_TAG, "onLayout, width:" + contentWidth + " height:" + contentHeight);
        }
    }

    class BookView extends SurfaceView implements SurfaceHolder.Callback
    {
        DrawThread dt;
        SurfaceHolder surfaceHolder;
        Path angelPath = new Path();
        Matrix m = new Matrix();
        Paint ps = new Paint();

        public BookView(Context context)
        {
            super(context);
            surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
        }

        public void startAnimation()
        {
            if (dt == null)
            {
                Log.d(LOG_TAG, "startAnimation");
                dt = new DrawThread(this, getHolder());
                dt.start();
            }
        }

        public void stopAnimation()
        {
            Log.d(LOG_TAG, "stopAnimation");
            if (dt != null)
            {
                dt.flag = false;
                Thread t = dt;
                dt = null;
                t.interrupt();
            }
        }

        public void drawLT(Canvas canvas)
        {
            double dx = contentWidth - scrollX, dy = scrollY;
            double len = Math.sqrt(dx * dx + dy * dy);
            if (len > contentWidth)
            {
                scrollX = (float) (contentWidth - contentWidth * dx / len);
                scrollY = (float) (contentWidth * dy / len);
            }

            double px = scrollX;
            double py = scrollY;
            double arc = 2 * Math.atan(py / px) * 180 / Math.PI;

            // 三角
            ps.reset();
            ps.setColor(Color.WHITE);
            ps.setAntiAlias(true);
            canvas.save();
            m.reset();
            m.postTranslate(scrollX - contentWidth, scrollY);
            m.postRotate((float) (arc), scrollX, scrollY);
            canvas.setMatrix(m);
            canvas.drawRect(0, 0, contentWidth, contentHeight, ps);
            canvas.restore();

            arc = arc * Math.PI / 360;

            angelPath.reset();

            double r = Math.sqrt(px * px + py * py);
            double p1 = r / (2 * Math.cos(arc));
            double p2 = r / (2 * Math.sin(arc));
            if (arc == 0)
            {
                angelPath.moveTo((float) p1, 0);
                angelPath.lineTo((float) p1, contentHeight);
                angelPath.lineTo(0, contentHeight);
                angelPath.lineTo(0, 0);
                angelPath.close();
            }
            else if (p2 > contentHeight || p2 < 0)
            {
                double p3 = (p2 - contentHeight) * Math.tan(arc);
                angelPath.moveTo((float) p1, 0);
                angelPath.lineTo(0, 0);
                angelPath.lineTo(0, contentHeight);
                angelPath.lineTo((float) p3, contentHeight);
                angelPath.close();
            }
            else
            {
                angelPath.moveTo((float) p1, 0);
                angelPath.lineTo(0, 0);
                angelPath.lineTo(0, (float) p2);
                angelPath.close();
            }

            // 绘制上一页
            canvas.save();
            canvas.clipPath(angelPath);
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
            prevPage.draw(canvas);
            canvas.restore();

            // 绘制阴影
            ps.reset();
            Shader lg2 =
                    new LinearGradient(scrollX,
                            scrollY,
                            0,
                            0,
                            new int[] {0x00000000, 0x33000000, 0x00000000 },
                            new float[] {0.35f, 0.5f, 0.65f },
                            Shader.TileMode.CLAMP);
            ps.setShader(lg2);
            ps.setAntiAlias(true);
            canvas.drawRect(0, 0, contentWidth, contentHeight, ps);

        }

        public void drawLB(Canvas canvas)
        {
            double dx = contentWidth - scrollX, dy = contentHeight - scrollY;
            double len = Math.sqrt(dx * dx + dy * dy);
            if (len > contentWidth)
            {
                scrollX = (float) (contentWidth - contentWidth * dx / len);
                scrollY = (float) (contentHeight - contentWidth * dy / len);
            }
            double px = scrollX;
            double py = contentHeight - scrollY;
            double arc = 2 * Math.atan(py / px) * 180 / Math.PI;

            // 三角
            ps.reset();
            ps.setColor(Color.WHITE);
            ps.setAntiAlias(true);
            canvas.save();
            m.reset();
            m.postTranslate(scrollX - contentWidth, scrollY - contentHeight);
            m.postRotate((float) (-arc), scrollX, scrollY);
            canvas.setMatrix(m);
            canvas.drawRect(0, 0, contentWidth, contentHeight, ps);
            canvas.restore();

            // 后一页
            arc = arc * Math.PI / 360;
            angelPath.reset();
            double r = Math.sqrt(px * px + py * py);
            double p1 = r / (2 * Math.cos(arc));
            double p2 = r / (2 * Math.sin(arc));
            if (arc == 0)
            {
                angelPath.moveTo((float) p1, 0);
                angelPath.lineTo(0, 0);
                angelPath.lineTo(0, contentHeight);
                angelPath.lineTo((float) p1, contentHeight);
                angelPath.close();
            }
            else if (p2 > contentHeight || p2 < 0)
            {
                double p3 = (p2 - contentHeight) * Math.tan(arc);
                angelPath.moveTo((float) p3, 0);
                angelPath.lineTo(0, 0);
                angelPath.lineTo(0, contentHeight);
                angelPath.lineTo((float) p1, contentHeight);
                angelPath.close();
            }
            else
            {
                angelPath.moveTo(0, contentHeight);
                angelPath.lineTo((float) p1, contentHeight);
                angelPath.lineTo(0, contentHeight - (float) p2);
                angelPath.close();
            }

            canvas.save();
            // canvas.
            canvas.clipPath(angelPath);
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
            prevPage.draw(canvas);
            canvas.restore();

            ps.reset();
            ps.setAntiAlias(true);
            Shader lg2 =
                    new LinearGradient(scrollX, scrollY, 0, contentHeight, new int[] {0x00000000, 0x33000000,
                            0x00000000 }, new float[] {0.35f, 0.5f, 0.65f }, Shader.TileMode.CLAMP);
            ps.setShader(lg2);
            canvas.drawRect(0, 0, contentWidth, contentHeight, ps);
        }

        public void drawRT(Canvas canvas)
        {
            double dx = scrollX, dy = scrollY;
            double len = Math.sqrt(dx * dx + dy * dy);
            if (len > contentWidth)
            {
                scrollX = (float) (contentWidth * dx / len);
                scrollY = (float) (contentWidth * dy / len);
            }

            double px = contentWidth - scrollX;
            double py = scrollY;
            double arc = 2 * Math.atan(py / px) * 180 / Math.PI;

            // 三角
            ps.reset();
            ps.setColor(Color.WHITE);
            ps.setAntiAlias(true);
            canvas.save();
            m.reset();
            m.postTranslate(scrollX, scrollY);
            m.postRotate((float) (-arc), scrollX, scrollY);
            canvas.setMatrix(m);
            canvas.drawRect(0, 0, contentWidth, contentHeight, ps);
            canvas.restore();

            // 后一页
            arc = arc * Math.PI / 360;
            angelPath.reset();
            double r = Math.sqrt(px * px + py * py);
            double p1 = contentWidth - r / (2 * Math.cos(arc));
            double p2 = r / (2 * Math.sin(arc));


            if (arc == 0)
            {
                angelPath.moveTo(contentWidth, 0);
                angelPath.lineTo((float) p1, 0);
                angelPath.lineTo((float) p1, contentHeight);
                angelPath.lineTo(contentWidth, contentHeight);
                angelPath.close();
            }
            else if (p2 > contentHeight || p2 < 0)
            {
                double p3 = contentWidth - (p2 - contentHeight) * Math.tan(arc);
                angelPath.moveTo(contentWidth, 0);
                angelPath.lineTo((float) p1, 0);
                angelPath.lineTo((float) p3, contentHeight);
                angelPath.lineTo(contentWidth, contentHeight);
                angelPath.close();
            }
            else
            {
                angelPath.moveTo((float) p1, 0);
                angelPath.lineTo(contentWidth, (float) p2);
                angelPath.lineTo(contentWidth, 0);
                angelPath.close();
            }

            canvas.save();
            // canvas.
            canvas.clipPath(angelPath);
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
            nextPage.draw(canvas);
            canvas.restore();
            // 阴影

            ps.reset();
            ps.setAntiAlias(true);
            Shader lg2 =
                    new LinearGradient(scrollX, scrollY, contentWidth, 0, new int[] {0x00000000,
                            0x33000000, 0x00000000 }, new float[] {0.35f, 0.5f, 0.65f }, Shader.TileMode.CLAMP);
            ps.setShader(lg2);
            canvas.drawRect(0, 0, contentWidth, contentHeight, ps);
        }

        public void drawRB(Canvas canvas)
        {
            double dx = scrollX, dy = contentHeight - scrollY;
            double len = Math.sqrt(dx * dx + dy * dy);
            if (len > contentWidth)
            {
                scrollX = (float) (contentWidth * dx / len);
                scrollY = (float) (contentHeight - contentWidth * dy / len);
            }

            double px = contentWidth - scrollX;
            double py = contentHeight - scrollY;
            double arc = 2 * Math.atan(py / px) * 180 / Math.PI;

            // 三角
            ps.reset();
            ps.setColor(Color.WHITE);
            ps.setAntiAlias(true);
            canvas.save();
            m.reset();
            m.postTranslate(scrollX, scrollY - contentHeight);
            m.postRotate((float) (arc), scrollX, scrollY);
            canvas.setMatrix(m);
            canvas.drawRect(0, 0, contentWidth, contentHeight, ps);
            canvas.restore();

            //下一页
            arc = arc * Math.PI / 360;
            angelPath.reset();
            double r = Math.sqrt(px * px + py * py);
            double p1 = contentWidth - r / (2 * Math.cos(arc));
            double p2 = r / (2 * Math.sin(arc));
            if (arc == 0)
            {
                angelPath.moveTo(contentWidth, 0);
                angelPath.lineTo((float) p1, 0);
                angelPath.lineTo((float) p1, contentHeight);
                angelPath.lineTo(contentWidth, contentHeight);
                angelPath.close();
            }
            else if (p2 > contentHeight || p2 < 0)
            {
                double p3 = contentWidth - (p2 - contentHeight) * Math.tan(arc);
                angelPath.moveTo(contentWidth, 0);
                angelPath.lineTo((float) p3, 0);
                angelPath.lineTo((float) p1, contentHeight);
                angelPath.lineTo(contentWidth, contentHeight);
                angelPath.close();
            }
            else
            {
                angelPath.moveTo(contentWidth, contentHeight);
                angelPath.lineTo(contentWidth, contentHeight - (float) p2);
                angelPath.lineTo((float) p1, contentHeight);
                angelPath.close();
            }
            canvas.save();
            // canvas.
            canvas.clipPath(angelPath);
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
            nextPage.draw(canvas);
            canvas.restore();


            ps.reset();
            Shader lg2 =
                    new LinearGradient(scrollX,
                            scrollY,
                            contentWidth,
                            contentHeight,
                            new int[] {0x00000000, 0x33000000, 0x00000000 },
                            new float[] {0.35f, 0.5f, 0.65f },
                            Shader.TileMode.CLAMP);
            ps.setShader(lg2);
            canvas.drawRect(0, 0, contentWidth, contentHeight, ps);
        }

        public void drawPrevPageEnd(Canvas canvas)
        {
            prevPage.draw(canvas);
        }

        public void drawNextPageEnd(Canvas canvas)
        {
            nextPage.draw(canvas);
        }

        public void drawPage(Canvas canvas)
        {
            if (mSelectCorner == Corner.LeftTop)
            {
                drawLT(canvas);
            }
            else if (mSelectCorner == Corner.LeftBottom)
            {
                drawLB(canvas);
            }
            else if (mSelectCorner == Corner.RightTop)
            {
                drawRT(canvas);
            }
            else if (mSelectCorner == Corner.RightBottom)
            {
                drawRB(canvas);
            }
        }

        public void update()
        {
            Canvas canvas = surfaceHolder.lockCanvas(null);
            try
            {
                synchronized (surfaceHolder)
                {
                    doDraw(canvas);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (canvas != null)
                {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }

        protected void doDraw(Canvas canvas)
        {
            Log.d(LOG_TAG, "bookView doDraw");
            mainLayout.draw(canvas);

        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
        {

        }

        public void surfaceCreated(SurfaceHolder holder)
        {
            update();
        }

        public void surfaceDestroyed(SurfaceHolder holder)
        {
            if (dt != null)
            {
                dt.flag = false;
                dt = null;
            }
        }
    }

    public boolean getAnimateData()
    {
        Log.d(LOG_TAG, "getAnimateData");
        long time = aniTime;
        Date date = new Date();
        long t = date.getTime() - aniStartTime.getTime();
        t += timeOffset;
        if (t < 0 || t > time)
        {
            mState = BookState.ANIMATE_END;
            return false;
        }
        else
        {
            mState = BookState.ANIMATING;
            double sx = aniStopPos.x - aniStartPos.x;
            scrollX = (float) (sx * t / time + aniStartPos.x);
            double sy = aniStopPos.y - aniStartPos.y;
            scrollY = (float) (sy * t / time + aniStartPos.y);
            return true;
        }
    }

    public void handleAniEnd(Canvas canvas)
    {
        Log.d(LOG_TAG, "handleAniEnd");
        if (closeBook)
        {
            closeBook = false;
            if (mSelectCorner == Corner.LeftTop || mSelectCorner == Corner.LeftBottom)
            {
                if (scrollX > contentWidth / 2)
                {
                    indexPage -= 1;
                    mBookView.drawPrevPageEnd(canvas);
                    aniEndHandle.post(new Runnable()
                    {
                        public void run()
                        {
                            updatePageView();
                        }
                    });
                }
                else
                {
                    mBookView.doDraw(canvas);
                }
            }
            else if (mSelectCorner == Corner.RightTop || mSelectCorner == Corner.RightBottom)
            {
                if (scrollX < contentWidth / 2)
                {
                    indexPage += 1;
                    mBookView.drawNextPageEnd(canvas);
                    aniEndHandle.post(new Runnable()
                    {
                        public void run()
                        {
                            updatePageView();
                        }
                    });
                }
                else
                {
                    mBookView.doDraw(canvas);
                }
            }
            mSelectCorner = Corner.None;
            mState = BookState.READY;
        }
        else
        {
            mState = BookState.TRACKING;
        }
        mBookView.stopAnimation();
        // aniEndHandle.post(new Runnable() {
        // public void run() {
        // // BookLayout.this.invalidate();
        // }
        // });
    }

    class WhiteView extends View
    {
        public WhiteView(Context context)
        {
            super(context);
        }

        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);
        }
    }

    public class DrawThread extends Thread
    {
        BookView bv;
        SurfaceHolder surfaceHolder;
        boolean flag = false;
        int sleepSpan = 30;

        public DrawThread(BookView bv, SurfaceHolder surfaceHolder)
        {
            this.bv = bv;
            this.surfaceHolder = surfaceHolder;
            this.flag = true;
        }

        public void run()
        {
            Canvas canvas = null;
            while (flag)
            {
                try
                {
                    canvas = surfaceHolder.lockCanvas(null);
                    if (canvas == null)
                        continue;
                    synchronized (surfaceHolder)
                    {
                        if (mState == BookState.ABOUT_TO_ANIMATE || mState == BookState.ANIMATING)
                        {
                            bv.doDraw(canvas);
                            getAnimateData();
                            bv.drawPage(canvas);
                        }
                        else if (mState == BookState.TRACKING)
                        {
                            bv.doDraw(canvas);
                            bv.drawPage(canvas);
                        }
                        else if (mState == BookState.ANIMATE_END)
                        {
                            handleAniEnd(canvas);
                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    if (canvas != null)
                    {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
//                try
//                {
//                    Thread.sleep(sleepSpan);
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
            }
        }
    }

}
