package com.bejussi.shopply.presentation

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.work.*
import com.bejussi.shopply.R
import com.bejussi.shopply.presentation.utils.NotificationWorker
import com.bejussi.shopply.presentation.utils.SettingsDataStore
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    private lateinit var settingsDataStore: SettingsDataStore

    private var locale: Locale? = null

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
                setLocal(this, language)
            }

        }

        settingsDataStore.notification.asLiveData().observe(this) { notification ->
            notification.let {
                periodicNotificationWork(notification)
            }

        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
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

    private fun periodicNotificationWork(showNotification: Boolean) {
        if (showNotification) {
            setNotification()
        } else {
            return
        }
    }

    private fun setNotification() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()

        val workRequest = PeriodicWorkRequest.Builder(
            NotificationWorker::class.java,
            1,
            TimeUnit.DAYS
        ).setConstraints(constraints)
            .addTag("periodic_notification")
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork("periodic_notification", ExistingPeriodicWorkPolicy.KEEP, workRequest)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}