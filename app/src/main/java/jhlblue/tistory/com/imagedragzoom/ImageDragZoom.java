package jhlblue.tistory.com.imagedragzoom;

import android.widget.ImageView;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by JHLBLUE on 2017-04-20.
 */

public class ImageDragZoom {
    private static final int DEFAULT_IMAGE_TIMES = 2;

    private int cropImageWidth = 0, cropImageHeight = 0;
    private int xPosition = 0, yPosition = 0;
    private int times = 0;
    private ImageView originalImage, cropImage;
    private Context context;
    private Handler handler;
    private View touchView = null;

    private int newX, newY, newHeight, newWidth;
    private Bitmap bitmap;

    public ImageDragZoom(Context context, ImageView originalImage, ImageView cropImage, Handler handler) {
        this.originalImage = originalImage;
        this.cropImage = cropImage;
        this.handler = handler;
        this.context = context;
        this.times = DEFAULT_IMAGE_TIMES;
    }

    public void setCropImageSize(int width, int height) {
        this.cropImageWidth = width / 2;
        this.cropImageHeight = height / 2;
    }

    public void setCropImageTimes(int times) {
        this.times = times;
    }

    public void setTouchEvent(MotionEvent event) {
        xPosition = (int) event.getX();
        yPosition = (int) event.getY();
        handler.post(cropRunnable);
    }

    private Runnable cropRunnable = new Runnable() {
        @Override
        public synchronized void run() {
            if (cropImageWidth > 0 && cropImageHeight > 0) {
                if (touchView == null) {
                    touchView = LayoutInflater.from(context).inflate(R.layout.view_touch, null);
                    ((ViewGroup) originalImage.getParent()).addView(touchView);
                    touchView.bringToFront();
                }

                touchView.setTranslationX(xPosition - cropImageWidth / times);
                touchView.setTranslationY(yPosition - cropImageHeight / times);
                touchView.invalidate();
                touchView.bringToFront();

                originalImage.setDrawingCacheEnabled(true);
                bitmap = originalImage.getDrawingCache();

                int imageWidth = bitmap.getWidth();
                int imageHeight = bitmap.getHeight();

                newX = xPosition - (cropImageWidth / (times));
                newY = yPosition - (cropImageHeight / (times));

                if (newX <= 1)
                    newX = 1;
                if (newY <= 1)
                    newY = 1;

                if (newX >= imageWidth - (cropImageWidth * 2 / times)) {
                    newX = imageWidth - (cropImageWidth * 2 / times);
                }
                if (newY >= imageHeight - (cropImageHeight * 2 / times)) {
                    newY = imageHeight - (cropImageHeight * 2 / times);
                }

                newWidth = cropImageWidth * 2 / times;
                newHeight = cropImageHeight * 2 / times;

                cropImage.setImageBitmap(Bitmap.createBitmap(bitmap, newX, newY, newWidth, newHeight));
            }
        }
    };
}