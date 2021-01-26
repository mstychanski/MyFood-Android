package com.android.myfood.home



import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.myfood.R
import com.android.myfood.home.diets.DietsFragment
import com.android.myfood.home.settings.SettingsFragment
import com.android.myfood.home.storage.StorageFragment
import com.android.myfood.home.tobuy.ToBuyFragment
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeFragment = StorageFragment()
        val toDoFragment = ToBuyFragment()
        val dietsFragment = DietsFragment()
        val settingsFragment = SettingsFragment()

        makeCurrentFragment(homeFragment, "My Food")

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {

                R.id.nav_home -> makeCurrentFragment(homeFragment, "My Food")
                R.id.nav_todo_list -> makeCurrentFragment(toDoFragment, "To Buy")
                R.id.nav_diets -> makeCurrentFragment(dietsFragment, "Diets")
                R.id.nav_settings -> makeCurrentFragment(settingsFragment, "Settings")

            }
            true
        }
    }



    private fun makeCurrentFragment(fragment: Fragment, title: String) {
        val fragmentTitle = findViewById<TextView>(R.id.title)
        supportFragmentManager.beginTransaction().apply {
            fragmentTitle.text = title
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }

}