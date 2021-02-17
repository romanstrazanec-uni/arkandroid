package sk.romanstrazanec.arkanoid

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager

class MainActivity : AppCompatActivity() {
    private var updateThread: UpdateThread? = null
    private var updateHandler: Handler? = null
    private var gameCanvas: GameCanvas? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar!!.hide()
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        gameCanvas = GameCanvas(this)
        setContentView(gameCanvas)
        createHandler()
        updateThread = UpdateThread(updateHandler!!)
        updateThread!!.start()
    }

    private fun createHandler() {
        updateHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                gameCanvas!!.update()
                gameCanvas!!.invalidate()
                super.handleMessage(msg)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        gameCanvas!!.stopSensor()
    }

    override fun onResume() {
        super.onResume()
        gameCanvas!!.startSensor()
    }
}