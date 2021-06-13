package `in`.bitcode.webservices1

import android.os.AsyncTask
import android.os.Handler
import android.os.Message

class WebThread(var handler : Handler) : AsyncTask<Any, Any, List<Place>>() {

    override fun doInBackground(vararg params: Any?): List<Place>? {
        return  Util().getPlaces()
    }

    override fun onPostExecute(result: List<Place>?) {
        super.onPostExecute(result)
        var message = Message()
        message.obj = result
        handler.sendMessage(message)
    }
}