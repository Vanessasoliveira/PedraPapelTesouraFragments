package br.edu.ifsp.scl.sdm.pedrapapeltesourafragments


import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.commit
import br.edu.ifsp.scl.sdm.pedrapapeltesourafragments.databinding.ActivityMainBinding
import java.util.*
import kotlin.random.Random.Default.nextInt


class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by lazy {
    ActivityMainBinding.inflate(layoutInflater)
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)


        with(supportFragmentManager.beginTransaction()) {

            //deslocamento da pilha qunado algum fragmento for removido
            setReorderingAllowed(true)
            addToBackStack("principal")
            add(R.id.principalFcv, MainFragment(), "MainFragment")
            commit()
        }



    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == R.id.settingsFragmentMi) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack("configurações")
                replace(R.id.principalFcv, SettingsFragment(), "SettingsFragment")
            }
            true
        } else
            false
}
