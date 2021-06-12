package `in`.bitcode.webservices1

import android.os.AsyncTask

class WebThread : AsyncTask<Any, Any, Any>() {

    override fun doInBackground(vararg params: Any?): Any? {
        Util().getPlaces()
        return null
    }
}