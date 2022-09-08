package com.bejussi.shopply.presentation

import android.app.Activity
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bejussi.shopply.R
import com.bejussi.shopply.presentation.utils.SettingsDataStore
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    private lateinit var settingsDataStore: SettingsDataStore

    private lateinit var locale: Locale

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingsDataStore = SettingsDataStore(this)

        settingsDataStore.darkTheme.asLiveData().observe(this) { theme ->
            theme.let {
                if (theme) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    setTheme(R.style.Theme_Shopply_Dark)
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    setTheme(R.style.Theme_Shopply_Light)
                }
            }
        }

        settingsDataStore.language.asLiveData().observe(this) { language ->
            language.let {
                getLanguageCode(language)
            }

        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun getLanguageCode(language: String?) {
        val array = resources.getStringArray(R.array.language)
        when(language) {
            array[0] -> setLocal(this, "en")
            array[1] -> setLocal(this, "uk")
            array[2] -> setLocal(this, "ru")
        }
    }

    private fun setLocal(activity: Activity, language: String) {
        locale = Locale(language)
        Locale.setDefault(locale)

        val resources = activity.resources

        val configuration = resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)

        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}