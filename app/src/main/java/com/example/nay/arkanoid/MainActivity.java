package com.example.nay.arkanoid;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    UpdateThread updateThread;
    Handler updateHandler;
    private GameCanvas gameCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getSupportActionBar().hide();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        gameCanvas = new GameCanvas(this);
        setContentView(gameCanvas);
        createHandler();
        updateThread = new UpdateThread(updateHandler);
        updateThread.start();
    }

    private void createHandler() {
        updateHandler = new Handler(){
            public void handleMessage(Message msg) {
                gameCanvas.update();
                gameCanvas.invalidate();
                super.handleMessage(msg);
            }
        };
    }
}
