package com.avrzll.recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import com.avrzll.recipe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    PencarianBahan.OnFragmentInteractionListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomePage())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> replaceFragment(HomePage())
                R.id.nav_search -> replaceFragment(PencarianBahan())
                R.id.nav_box -> replaceFragment(BoxPage())
                R.id.nav_author -> replaceFragment(AuthorPage())
                else -> {
                }
            }
            true
        }
    }

    override fun onReplaceFragment(fragment: Fragment) {
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
