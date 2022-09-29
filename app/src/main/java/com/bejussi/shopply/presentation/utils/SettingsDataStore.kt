package com.bejussi.shopply.presentation.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val SETTINGS_PREFERENCES_NAME = "settings_preferences"
val Context.datastore : DataStore< Preferences> by  preferencesDataStore(name = SETTINGS_PREFERENCES_NAME)

class SettingsDataStore(private val context: Context) {

    private val settingsDataStore = context.datastore

    val language: Flow<String> = settingsDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.LANGUAGE]?: "English"
        }

    val notification: Flow<Boolean> = settingsDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.NOTIFICATION]?: true
        }

    val darkTheme: Flow<Boolean> = settingsDataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.DARK_THEME]?: false
        }

    suspend fun updateLanguage(language: String) {
        settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.LANGUAGE] = language
        }
    }

    suspend fun updateNotification(notification: Boolean) {
        settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.NOTIFICATION] = notification
        }
    }

    suspend fun updateDarkTheme(darkTheme: Boolean) {
        settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.DARK_THEME] = darkTheme
        }
    }

    private object PreferencesKeys {
        val LANGUAGE = stringPreferencesKey("LANGUAGE")
        val NOTIFICATION = booleanPreferencesKey("NOTIFICATION")
        val DARK_THEME = booleanPreferencesKey("DARK_THEME")
    }
}