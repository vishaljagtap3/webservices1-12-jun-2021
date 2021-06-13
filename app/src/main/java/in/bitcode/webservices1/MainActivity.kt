package `in`.bitcode.webservices1

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WebThread( PlacesHandler() ).execute(null)
    }

    private class PlacesHandler : Handler() {
        override fun handleMessage(msg: Message) {

            if(msg.obj == null) {
                Log.e("tag", "No places found!")
                return
            }

            for(place in msg.obj as List<Place>) {
                Log.e("tag", place.toString())
            }

        }

    }

}