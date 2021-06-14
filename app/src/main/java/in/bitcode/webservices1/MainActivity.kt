package `in`.bitcode.webservices1

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //WebThread( PlacesHandler() ).execute(null)
        var reqQueue = Volley.newRequestQueue(this)
        var jsonObjectReq = JsonObjectRequest(
            Request.Method.GET,
            "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=AIzaSyAYh5QhQeFn2TMH4Ru_o1Zfb6voBs6i9rk",
            null,
            JSONPlacesListener(),
            null
        )
    }

    inner class JSONPlacesListener : Response.Listener<JSONObject> {
        override fun onResponse(response: JSONObject?) {
            if(response != null) {
                var places : List<Place>? = parsePlaces(response)
                if (places != null) {
                    for(place in places) {
                        Log.e("tag", place.toString())
                    }
                }
            }
        }
    }

    private fun parsePlaces(jRoot : JSONObject) : List<Place>?{
        if( !jRoot?.getString("status").equals("OK")) {
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