package com.example.m5individual13

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("cards_data")

object DataStoreManager {
    private val CARDS_KEY = stringSetPreferencesKey("cards_list")

    suspend fun saveCards(context: Context, cards: Set<String>) {
        context.dataStore.edit { preferences ->
            preferences[CARDS_KEY] = cards
        }
    }

    fun getCards(context: Context): Flow<Set<String>> {
        return context.dataStore.data.map { preferences ->
            preferences[CARDS_KEY] ?: emptySet()
        }
    }
}
