package `in`.bitcode.webservices1

import android.util.Log
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Util {
    fun demoReqRes() {

        var url = URL("https://bitcode.in")
        var httpUrlConnection : HttpURLConnection = url.openConnection() as HttpURLConnection
        httpUrlConnection.connect() //now the http request is sent to the server

        Log.e("tag", "Res code: ${httpUrlConnection.responseCode}")
        Log.e("tag", "Res message: ${httpUrlConnection.responseMessage}")
        Log.e("tag", "Con type: ${httpUrlConnection.contentType}")
        Log.e("tag", "Con length: ${httpUrlConnection.contentLength}")
        Log.e("tag", "Con Encoding: ${httpUrlConnection.contentEncoding}")

        var inStream : InputStream = httpUrlConnection.inputStream
        var data = ByteArray(1024*2)
        var count= 0

        count = inStream.read(data, 0, 1024*2)
        while( count != -1) {
            Log.e("tag", String(data, 0, count) )
            count = inStream.read(data, 0, 1024*2)
        }

        inStream.close()
    }

    fun getPlaces() {

        var url = URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=AIzaSyD89lISwG55bNErBuptExfXkrCDaVvnsGs")
        var httpUrlConnection : HttpURLConnection = url.openConnection() as HttpURLConnection
        httpUrlConnection.connect() //now the http request is sent to the server

        Log.e("tag", "Res code: ${httpUrlConnection.responseCode}")
        Log.e("tag", "Res message: ${httpUrlConnection.responseMessage}")
        Log.e("tag", "Con type: ${httpUrlConnection.contentType}")
        Log.e("tag", "Con length: ${httpUrlConnection.contentLength}")
        Log.e("tag", "Con Encoding: ${httpUrlConnection.contentEncoding}")

        var inStream : InputStream = httpUrlConnection.inputStream
        var data = ByteArray(1024*2)
        var count= 0

        count = inStream.read(data, 0, 1024*2)
        while( count != -1) {
            Log.e("tag", String(data, 0, count) )
            count = inStream.read(data, 0, 1024*2)
        }

        inStream.close()
    }
}