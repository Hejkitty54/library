package com.example.library

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest

class MainActivityRepositoryImpl: MainActivityRepository {

    override fun volley() {

        val url = "https://fakerapi.it/api/v1/texts?_quantity=1&_characters=300&_seed=1&_locale"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                Log.d("Debug", "Response is: ${response.toString()}")

            },
            { error ->
                // TODO: Handle error
            }
        )

        // Access the RequestQueue through your singleton class.
        // MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}