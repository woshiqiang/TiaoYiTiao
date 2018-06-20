package com.hbck.tiaoyitiao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static {
        System.loadLibrary("opencv_java3");
        System.loadLibrary("native-lib");
    }

    private ImageView imageView;
    private Button button;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    public static native int[] grayProc(int[] pixels, int w, int h);

    private void initView() {
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        imageView.setImageBitmap(bmp);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                int w = bmp.getWidth();
                int h = bmp.getHeight();
                int[] pixels = new int[w*h];
                bmp.getPixels(pixels, 0, w, 0, 0, w, h);
                int[] resultInt = grayProc(pixels, w, h);
                Bitmap resultImg = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                resultImg.setPixels(resultInt, 0, w, 0, 0, w, h);
                imageView.setImageBitmap(resultImg);
                break;
        }
    }
}
