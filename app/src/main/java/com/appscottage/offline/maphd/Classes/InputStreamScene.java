package com.appscottage.offline.maphd.Classes;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamScene extends Scene {
    private static final boolean DEBUG = false;
    private static final int DOWN_SAMPLE_SHIFT = 2;
    private static final String TAG;
    private static final Options options;
    private static Paint red;
    private final int BYTES_PER_PIXEL;
    private Rect calculatedCacheWindowRect;
    private BitmapRegionDecoder decoder;
    private int percent;
    private Bitmap sampleBitmap;

    static {
        TAG = InputStreamScene.class.getSimpleName();
        options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        red = new Paint();
        red.setColor(SupportMenu.CATEGORY_MASK);
        red.setStrokeWidth(5.0f);
    }

    public InputStreamScene(InputStream inputStream) throws IOException {
        this.BYTES_PER_PIXEL = 4;
        this.percent = 5;
        this.calculatedCacheWindowRect = new Rect();
        Options tmpOptions = new Options();
        this.decoder = BitmapRegionDecoder.newInstance(inputStream, DEBUG);
        tmpOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, tmpOptions);
        setSceneSize(tmpOptions.outWidth, tmpOptions.outHeight);
        tmpOptions.inJustDecodeBounds = DEBUG;
        tmpOptions.inSampleSize = 4;
        this.sampleBitmap = BitmapFactory.decodeStream(inputStream, null, tmpOptions);
        initialize();
    }

    protected Bitmap fillCache(Rect origin) {
        if (this.decoder != null) {
            return this.decoder.decodeRegion(origin, options);
        }
        return null;
    }

    protected void drawSampleRectIntoBitmap(Bitmap bitmap, Rect rectOfSample) {
        if (bitmap != null) {
            Canvas c = new Canvas(bitmap);
            int left = rectOfSample.left >> DOWN_SAMPLE_SHIFT;
            int top = rectOfSample.top >> DOWN_SAMPLE_SHIFT;
            c.drawBitmap(this.sampleBitmap, new Rect(left, top, left + (rectOfSample.width() >> DOWN_SAMPLE_SHIFT), top + (rectOfSample.height() >> DOWN_SAMPLE_SHIFT)), new Rect(0, 0, c.getWidth(), c.getHeight()), null);
        }
    }

    protected Rect calculateCacheWindow(Rect viewportRect) {
        long bytesToUse = (Runtime.getRuntime().maxMemory() * ((long) this.percent)) / 100;
        Point size = getSceneSize();
        int vw = viewportRect.width();
        int vh = viewportRect.height();
        int mw = 0;
        int mh = 0;
        int th = 0;
        int tw = 0;
        while (true) {
            if (((long) (((vw + tw) * (vh + th)) * 4)) >= bytesToUse) {
                break;
            }
            mw = tw;
            mh = th;
            th++;
            tw++;
        }
        if (vw + mw > size.x) {
            mw = Math.max(0, size.x - vw);
        }
        if (vh + mh > size.y) {
            mh = Math.max(0, size.y - vh);
        }
        int left = viewportRect.left - (mw >> 1);
        int right = viewportRect.right + (mw >> 1);
        if (left < 0) {
            right -= left;
            left = 0;
        }
        int i = size.x;
        if (right > size.x) {
            left -= right - size.x;
            right = size.x;
        }
        int top = viewportRect.top - (mh >> 1);
        int bottom = viewportRect.bottom + (mh >> 1);
        if (top < 0) {
            bottom -= top;
            top = 0;
        }
        i = size.y;
        if (bottom > size.y) {
            top -= bottom - size.y;
            bottom = size.y;
        }
        this.calculatedCacheWindowRect.set(left, top, right, bottom);
        return this.calculatedCacheWindowRect;
    }

    protected void fillCacheOutOfMemoryError(OutOfMemoryError error) {
        if (this.percent > 0) {
            this.percent--;
        }
        Log.e(TAG, String.format("caught oom -- cache now at %d percent.", new Object[]{Integer.valueOf(this.percent)}));
    }

    protected void drawComplete(Canvas canvas) {
    }
}
