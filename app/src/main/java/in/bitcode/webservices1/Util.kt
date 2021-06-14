package `in`.bitcode.webservices1

import android.util.Log
import org.json.JSONObject
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Util {
    fun demoReqRes() {

        var url = URL("https://bitcode.in")
        //var url = URL("https://bitcode.in/images/logo/bitcode.png")
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

    fun getPlaces() : List<Place>?{

        var url = URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=AIzaSyAYh5QhQeFn2TMH4Ru_o1Zfb6voBs6i9rk")
        var httpUrlConnection : HttpURLConnection = url.openConnection() as HttpURLConnection

        httpUrlConnection.doInput = true

        /*
        httpUrlConnection.headerFields
        httpUrlConnection.requestMethod = "POST"
        httpUrlConnection.doOutput = true
        httpUrlConnection.outputStream.write("data")
        httpUrlConnection.outputStream.close()
         */

        httpUrlConnection.connect() //now the http request is sent to the server

        Log.e("tag", "Res code: ${httpUrlConnection.responseCode}")
        Log.e("tag", "Res message: ${httpUrlConnection.responseMessage}")
        Log.e("tag", "Con type: ${httpUrlConnection.contentType}")
        Log.e("tag", "Con length: ${httpUrlConnection.contentLength}")
        Log.e("tag", "Con Encoding: ${httpUrlConnection.contentEncoding}")

        var inStream : InputStream = httpUrlConnection.inputStream
        var data = ByteArray(1024*2)
        var count= 0

        var responseBuffer = StringBuffer()

        count = inStream.read(data, 0, 1024*2)
        while( count != -1) {
            //Log.e("tag", String(data, 0, count) )
            responseBuffer.append(String(data, 0, count))
            count = inStream.read(data, 0, 1024*2)
        }
        inStream.close()

        Log.e("tag", responseBuffer.toString())

        var jRoot = JSONObject(responseBuffer.toString())
        if( !jRoot.getString("status").equals("OK")) {
            return null
        }

        var places = ArrayList<Place>()
        var jPlaces = jRoot.getJSONArray("results")
        for(i in 0..(jPlaces.length()-1) ) {
            var jPlace = jPlaces.getJSONObject(i)
            var place = Place()

            place.name = jPlace.getString("name")
            place.rating = jPlace.getDouble("rating")
            place.address = jPlace.getString("vicinity")
            place.status = jPlace.getString("business_status").equals("OPERATIONAL")

            var jLoc = jPlace.getJSONObject("geometry").getJSONObject("location")
            place.lat = jLoc.getDouble("lat")
            place.lng = jLoc.getDouble("lng")

            places.add(place)
        }

        return places
    }
}