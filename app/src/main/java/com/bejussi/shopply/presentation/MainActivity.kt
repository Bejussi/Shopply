package com.bejussi.shopply.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.work.*
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.bejussi.shopply.R
import com.bejussi.shopply.presentation.utils.NotificationWorker
import com.bejussi.shopply.presentation.utils.SettingsDataStore
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : LocalizationActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    private lateinit var settingsDataStore: SettingsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
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
                setLanguage(language)
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
}