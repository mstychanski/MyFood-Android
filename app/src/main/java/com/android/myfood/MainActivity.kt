package com.android.myfood



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.myfood.diets.DietsFragment
import com.android.myfood.settings.SettingsFragment
import com.android.myfood.storage.StorageFragment
import com.android.myfood.todo.ToDoFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = StorageFragment()
        val toDoFragment = ToDoFragment()
        val dietsFragment = DietsFragment()
        val settingsFragment = SettingsFragment()

        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {

                R.id.nav_home      -> makeCurrentFragment(homeFragment)
                R.id.nav_todo_list -> makeCurrentFragment(toDoFragment)
                R.id.nav_diets     -> makeCurrentFragment(dietsFragment)
                R.id.nav_settings  -> makeCurrentFragment(settingsFragment)

            }
            true
        }
    }


    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }

}