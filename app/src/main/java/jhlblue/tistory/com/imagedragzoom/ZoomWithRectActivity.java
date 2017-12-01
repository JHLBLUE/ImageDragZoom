package jhlblue.tistory.com.imagedragzoom;
/**
 * Created by jhlblue on 2017. 11. 20..
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ZoomWithRectActivity extends AppCompatActivity {
    private ImageDragZoom imageDragZoom;
    private ImageView originalImage, zoomedImage;
    private Handler handler = new Handler();
    private View touchView;
    private ViewGroup viewGroup;
    private int times = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_with_rect);

        originalImage = (ImageView) findViewById(R.id.origin_image);
        zoomedImage = (ImageView) findViewById(R.id.zoomed_image);

        viewGroup = (ViewGroup) findViewById(R.id.linear_layout_parent);
        touchView = LayoutInflater.from(this).inflate(R.layout.view_touch, null);
        viewGroup.addView(touchView);

        imageDragZoom = new ImageDragZoom(this, originalImage, zoomedImage, touchView, handler);
        imageDragZoom.setCropImageTimes(times);

        originalImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
                    imageDragZoom.setTouchEvent(event);
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return true;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        imageDragZoom.setCropImageSize(zoomedImage.getWidth(), zoomedImage.getHeight(), times);
    }
}