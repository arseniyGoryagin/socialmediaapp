package com.arseniy.blogapp.data.local

import android.content.Context
import androidx.compose.ui.res.stringResource
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TokenManager(private val context: Context) {


    val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "tokenStore")


    companion object {

        private val TOKEN_KEY = stringPreferencesKey("TOKEN")

    }

    fun getToken(): Flow<String> {
        return context.dataStore.data.map{ store ->
            store[TOKEN_KEY] ?: ""

        }
    }

    suspend fun saveToken(token : String){
        context.dataStore.edit { store ->
            store[TOKEN_KEY] = token

        }
    }

    suspend fun deleteToken(){
        context.dataStore.edit { store ->
            store[TOKEN_KEY] = ""

        }
    }



}