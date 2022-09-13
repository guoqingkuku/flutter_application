package com.clx.flutter_application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.clx.flutter_application.databinding.ActivityMainBinding
import com.clx.flutter_application.flutter.FlutterEngineManager
import com.clx.flutter_application.flutter.SingleFlutterActivity
import io.flutter.embedding.android.FlutterActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    companion object {
        const val channel_name = "com.clx.flutter_application"
        const val engine_id = "engine_id"
        const val method_name = "textMethod"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val list = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

        FlutterEngineManager.createFlutterEngine(engine_id, list)


        binding.fab.setOnClickListener {
            FlutterEngineManager.invokeMethod(engine_id, channel_name, method_name,"+++++++++算求+++++++++")
            startActivity(
                SingleFlutterActivity
                    .withCachedEngine(engine_id)
                    .build(this)
            )
        }
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()

        FlutterEngineManager.destroyEngine(engine_id)

    }
}