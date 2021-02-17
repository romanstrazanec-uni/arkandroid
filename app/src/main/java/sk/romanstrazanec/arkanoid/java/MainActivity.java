package sk.romanstrazanec.arkanoid.java;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
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

        gameCanvas = new GameCanvas(this);
        setContentView(gameCanvas);
        createHandler();
        updateThread = new UpdateThread(updateHandler);
        updateThread.start();
    }

    private void createHandler() {
        updateHandler = new Handler() {
            public void handleMessage(Message msg) {
                gameCanvas.update();
                gameCanvas.invalidate();
                super.handleMessage(msg);
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameCanvas.stopSensor();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameCanvas.startSensor();
    }
}
