package com.appscottage.offline.maphd.Classes;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class RandomAccessFileInputStream extends InputStream {
    public static int DEFAULT_BUFFER_SIZE;
    String TAG;
    long fileLength;
    RandomAccessFile fp;
    long markPos;

    static {
        DEFAULT_BUFFER_SIZE = AccessibilityNodeInfoCompat.ACTION_COPY;
    }

    public RandomAccessFileInputStream(File file, int bufferSize) throws FileNotFoundException {
        this.markPos = -1;
        this.fileLength = -1;
        this.TAG = "WorldMapActivityRAIFS";
        this.fp = new RandomAccessFile(file, "r");
        try {
            this.fileLength = this.fp.length();
        } catch (IOException e) {
            Log.e(this.TAG, e.getMessage());
        }
        Log.d(this.TAG, "opened, len = " + this.fileLength);
    }

    public int available() {
        long pos = 0;
        try {
            pos = this.fp.getFilePointer();
        } catch (IOException e) {
            Log.e(this.TAG, "available " + e.getMessage());
        }
        int res = (int) (this.fileLength - pos);
        Log.d(this.TAG, "available " + res);
        return res;
    }

    public RandomAccessFileInputStream(File file) throws FileNotFoundException {
        this(file, DEFAULT_BUFFER_SIZE);
    }

    public RandomAccessFileInputStream(String filename) throws FileNotFoundException {
        this(new File(filename), DEFAULT_BUFFER_SIZE);
    }

    public int read() throws IOException {
        return this.fp.read();
    }

    public int read(byte[] b, int off, int len) throws IOException {
        return this.fp.read(b, off, len);
    }

    public int read(byte[] b) throws IOException {
        return this.fp.read(b);
    }

    public void close() throws IOException {
        this.fp.close();
    }

    public int skip(int n) throws IOException {
        int res = this.fp.skipBytes(n);
        Log.d(this.TAG, "skip " + n + " res " + res + " pos now " + this.fp.getFilePointer());
        return res;
    }

    public void mark(int readLimit) {
        try {
            this.markPos = this.fp.getFilePointer();
        } catch (IOException e) {
            Log.e(this.TAG, e.getMessage());
        }
        Log.d(this.TAG, "mark at " + this.markPos + " readLimit " + readLimit);
    }

    public void reset() throws IOException {
        long oldpos = this.fp.getFilePointer();
        this.fp.seek(0);
        Log.d(this.TAG, "reset oldPos" + oldpos + " to " + this.markPos + " resulting pos " + this.fp.getFilePointer());
    }

    public boolean markSupported() {
        return true;
    }
}
