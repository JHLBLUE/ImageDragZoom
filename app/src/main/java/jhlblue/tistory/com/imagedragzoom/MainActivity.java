package jhlblue.tistory.com.imagedragzoom;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageDragZoom imageDragZoom;
    private ImageView originalImage, zoomedImage;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        originalImage = (ImageView)findViewById(R.id.origin_image);
        zoomedImage = (ImageView) findViewById(R.id.zoomed_image);
        imageDragZoom = new ImageDragZoom(this, originalImage, zoomedImage, handler);
        imageDragZoom.setCropImageTimes(2);

        originalImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN){
                    imageDragZoom.setTouchEvent(event);
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return true;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        imageDragZoom.setCropImageSize(zoomedImage.getWidth(), zoomedImage.getHeight());
    }
}