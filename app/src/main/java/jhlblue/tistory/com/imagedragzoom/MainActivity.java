package jhlblue.tistory.com.imagedragzoom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonWithoutRect, buttonWithRect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonWithoutRect = findViewById(R.id.button_without_rect);
        buttonWithRect = findViewById(R.id.button_with_rect);

        buttonWithoutRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ZoomWithoutRectActivity.class));
            }
        });

        buttonWithRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ZoomWithRectActivity.class));
            }
        });
    }
}