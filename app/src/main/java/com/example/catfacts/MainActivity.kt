package com.example.catfacts

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import androidx.navigation.ui.AppBarConfiguration
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.catfacts.data.CatFact
import com.example.catfacts.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val catButton = findViewById<Button>(R.id.catButton)
        val catText = findViewById<TextView>(R.id.catText)

        catButton.setOnClickListener {
            val url = "https://catfact.ninja/fact"
            val queue = Volley.newRequestQueue(this)

            val request = StringRequest(Request.Method.GET, url,
                { response -> catText.text = parseResponse(response) },
                { catText.text = "Uh-oh. See Xavier for help!!"}
            )

            queue.add(request)
        }

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Sending cat fact", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun parseResponse(response: String): String? {

        val facts = Gson().fromJson(response, CatFact::class.java)
        return facts.fact

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}