package edu.temple.coroutineconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    //TODO (Refactor to replace Thread code with coroutines)
    lateinit var cakeImageView: ImageView

    val handler = Handler(Looper.getMainLooper(), Handler.Callback {
        cakeImageView.alpha = it.what / 100f
        true
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scope = CoroutineScope(Job() + Dispatchers.Default)



        cakeImageView = findViewById(R.id.imageView)


        findViewById<Button>(R.id.revealButton).setOnClickListener{
            scope.launch{
                fade()
            }
        }
    }
    suspend fun fade(){
        repeat(100) {
            withContext(Dispatchers.Main){
                handler.sendEmptyMessage(it)
            }
            handler.sendEmptyMessage(it)
            delay(40)
        }
    }
}