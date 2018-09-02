package com.appscottage.offline.maphd.Classes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.transition.Fade;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Scroller;
import com.google.android.gms.common.ConnectionResult;
import java.io.IOException;
import java.io.InputStream;

public class ImageSurfaceView extends SurfaceView implements Callback, OnGestureListener {
    private static final String TAG;
    private long SCALE_MOVE_GUARD;
    private DrawThread drawThread;
    private GestureDetector gestureDectector;
    private long lastScaleTime;
    private ScaleGestureDetector scaleGestureDetector;
    private InputStreamScene scene;
    private final Touch touch;

    class DrawThread extends Thread {
        private boolean running;
        private SurfaceHolder surfaceHolder;

        public DrawThread(SurfaceHolder surfaceHolder) {
            this.running = false;
            this.surfaceHolder = surfaceHolder;
        }

        public void setRunning(boolean value) {
            this.running = value;
        }

        public void run() {
            while (this.running) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                }
                Canvas c = null;
                try {
                    c = this.surfaceHolder.lockCanvas();
                    if (c != null) {
                        synchronized (this.surfaceHolder) {
                            ImageSurfaceView.this.scene.draw(c);
                        }
                    }
                    if (c != null) {
                        this.surfaceHolder.unlockCanvasAndPost(c);
                    }
                } catch (Throwable th) {
                    if (c != null) {
                        this.surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }

    private class ScaleListener extends SimpleOnScaleGestureListener {
        private PointF screenFocus;

        private ScaleListener() {
            this.screenFocus = new PointF();
        }

        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            if (!(scaleFactor == 0.0f || scaleFactor == 1.0f)) {
                scaleFactor = 1.0f / scaleFactor;
                this.screenFocus.set(detector.getFocusX(), detector.getFocusY());
                ImageSurfaceView.this.scene.getViewport().zoom(scaleFactor, this.screenFocus);
                ImageSurfaceView.this.invalidate();
            }
            ImageSurfaceView.this.lastScaleTime = System.currentTimeMillis();
            return true;
        }
    }

    class Touch {
        Point fling_sceneSize;
        Point fling_viewOrigin;
        Point fling_viewSize;
        final Scroller scroller;
        TouchState state;
        TouchThread touchThread;
        final Point viewDown;
        final Point viewportOriginAtDown;

        class TouchThread extends Thread {
            boolean running;
            final Touch touch;

            TouchThread(Touch touch) {
                this.running = false;
                this.touch = touch;
            }

            void setRunning(boolean value) {
                this.running = value;
            }

            public void run() {
                this.running = true;
                while (this.running) {
                    while (this.touch.state != TouchState.START_FLING && this.touch.state != TouchState.IN_FLING) {
                        try {
                            Thread.sleep(2147483647L);
                        } catch (InterruptedException e) {
                        }
                        if (!this.running) {
                            return;
                        }
                    }
                    synchronized (this.touch) {
                        if (this.touch.state == TouchState.START_FLING) {
                            this.touch.state = TouchState.IN_FLING;
                        }
                    }
                    if (this.touch.state == TouchState.IN_FLING) {
                        Touch.this.scroller.computeScrollOffset();
                        ImageSurfaceView.this.scene.getViewport().setOrigin(Touch.this.scroller.getCurrX(), Touch.this.scroller.getCurrY());
                        if (Touch.this.scroller.isFinished()) {
                            ImageSurfaceView.this.scene.setSuspend(false);
                            synchronized (this.touch) {
                                this.touch.state = TouchState.UNTOUCHED;
                                try {
                                    Thread.sleep(5);
                                } catch (InterruptedException e2) {
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }

        Touch(Context context) {
            this.viewDown = new Point(0, 0);
            this.viewportOriginAtDown = new Point(0, 0);
            this.state = TouchState.UNTOUCHED;
            this.fling_viewOrigin = new Point();
            this.fling_viewSize = new Point();
            this.fling_sceneSize = new Point();
            this.scroller = new Scroller(context);
        }

        void start() {
            this.touchThread = new TouchThread(this);
            this.touchThread.setName("touchThread");
            this.touchThread.start();
        }

        void stop() {
            this.touchThread.running = false;
            this.touchThread.interrupt();
            boolean retry = true;
            while (retry) {
                try {
                    this.touchThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
            this.touchThread = null;
        }

        boolean fling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            ImageSurfaceView.this.scene.getViewport().getOrigin(this.fling_viewOrigin);
            ImageSurfaceView.this.scene.getViewport().getSize(this.fling_viewSize);
            ImageSurfaceView.this.scene.getSceneSize(this.fling_sceneSize);
            synchronized (this) {
                this.state = TouchState.START_FLING;
                ImageSurfaceView.this.scene.setSuspend(true);
                this.scroller.fling(this.fling_viewOrigin.x, this.fling_viewOrigin.y, (int) (-velocityX), (int) (-velocityY), 0, this.fling_sceneSize.x - this.fling_viewSize.x, 0, this.fling_sceneSize.y - this.fling_viewSize.y);
                this.touchThread.interrupt();
            }
            return true;
        }

        boolean down(MotionEvent event) {
            ImageSurfaceView.this.scene.setSuspend(false);
            synchronized (this) {
                this.state = TouchState.IN_TOUCH;
                this.viewDown.x = (int) event.getX();
                this.viewDown.y = (int) event.getY();
                Point p = new Point();
                ImageSurfaceView.this.scene.getViewport().getOrigin(p);
                this.viewportOriginAtDown.set(p.x, p.y);
            }
            return true;
        }

        boolean move(MotionEvent event) {
            if (this.state == TouchState.IN_TOUCH) {
                float zoom = ImageSurfaceView.this.scene.getViewport().getZoom();
                ImageSurfaceView.this.scene.getViewport().setOrigin((int) (((float) this.viewportOriginAtDown.x) - (zoom * (event.getX() - ((float) this.viewDown.x)))), (int) (((float) this.viewportOriginAtDown.y) - (zoom * (event.getY() - ((float) this.viewDown.y)))));
                ImageSurfaceView.this.invalidate();
            }
            return true;
        }

        boolean up(MotionEvent event) {
            if (this.state == TouchState.IN_TOUCH) {
                this.state = TouchState.UNTOUCHED;
            }
            return true;
        }

        boolean cancel(MotionEvent event) {
            if (this.state == TouchState.IN_TOUCH) {
                this.state = TouchState.UNTOUCHED;
            }
            return true;
        }
    }

    enum TouchState {
        UNTOUCHED,
        IN_TOUCH,
        START_FLING,
        IN_FLING
    }

    static {
        TAG = ImageSurfaceView.class.getSimpleName();
    }

    public ImageSurfaceView(Context context) {
        super(context);
        this.lastScaleTime = 0;
        this.SCALE_MOVE_GUARD = 500;
        this.touch = new Touch(context);
        init(context);
    }

    public ImageSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.lastScaleTime = 0;
        this.SCALE_MOVE_GUARD = 500;
        this.touch = new Touch(context);
        init(context);
    }

    public ImageSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.lastScaleTime = 0;
        this.SCALE_MOVE_GUARD = 500;
        this.touch = new Touch(context);
        init(context);
    }

    public void getViewport(Point p) {
        this.scene.getViewport().getOrigin(p);
    }

    public void setViewport(Point viewport) {
        this.scene.getViewport().setOrigin(viewport.x, viewport.y);
    }

    public void setViewportCenter() {
        Point viewportSize = new Point();
        Point sceneSize = this.scene.getSceneSize();
        this.scene.getViewport().getSize(viewportSize);
        this.scene.getViewport().setOrigin((sceneSize.x - viewportSize.x) / 2, (sceneSize.y - viewportSize.y) / 2);
    }

    public void setInputStream(InputStream inputStream) throws IOException {
        this.scene = new InputStreamScene(inputStream);
    }

    public boolean onTouchEvent(MotionEvent me) {
        if (this.gestureDectector.onTouchEvent(me)) {
            return true;
        }
        this.scaleGestureDetector.onTouchEvent(me);
        switch (me.getAction() & MotionEventCompat.ACTION_MASK) {
            case ConnectionResult.SUCCESS /*0*/:
                return this.touch.down(me);
            case Fade.IN /*1*/:
                return this.touch.up(me);
            case Fade.OUT /*2*/:
                if (!this.scaleGestureDetector.isInProgress() && System.currentTimeMillis() - this.lastScaleTime >= this.SCALE_MOVE_GUARD) {
                    return this.touch.move(me);
                }
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                return this.touch.cancel(me);
        }
        return super.onTouchEvent(me);
    }

    private void init(Context context) {
        this.gestureDectector = new GestureDetector(context, this);
        getHolder().addCallback(this);
        this.scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.scene.getViewport().setSize(width, height);
        Log.d(TAG, String.format("onSizeChanged(w=%d,h=%d)", new Object[]{Integer.valueOf(width), Integer.valueOf(height)}));
    }

    public void surfaceCreated(SurfaceHolder holder) {
        this.drawThread = new DrawThread(holder);
        this.drawThread.setName("drawThread");
        this.drawThread.setRunning(true);
        this.drawThread.start();
        this.scene.start();
        this.touch.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        this.touch.stop();
        this.scene.stop();
        this.drawThread.setRunning(false);
        boolean retry = true;
        while (retry) {
            try {
                this.drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return this.touch.fling(e1, e2, velocityX, velocityY);
    }

    public boolean onDown(MotionEvent e) {
        return false;
    }

    public void onLongPress(MotionEvent e) {
    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    public void onShowPress(MotionEvent e) {
    }

    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }
}
