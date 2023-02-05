package com.houlis.haris.core.network.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.houlis.haris.core.domain.Picture
import com.houlis.haris.core.domain.PicturesRepositoryContract
import com.houlis.haris.core.network.api.PicturesApi
import com.houlis.haris.core.network.asResult
import com.houlis.haris.core.network.di.ImageBaseUrl
import dev.forkhandles.result4k.Result4k
import dev.forkhandles.result4k.map
import dev.forkhandles.result4k.mapFailure
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

internal class PicturesRepository @Inject constructor(
    private val api: PicturesApi,
    @ImageBaseUrl
    private val imageBaseUrl: String,
    private val dataStore: DataStore<Preferences>,
    private val json: Json,
) : PicturesRepositoryContract {

    private val pictureKey = stringPreferencesKey("picture")

    override suspend fun searchFor(query: String): Result4k<List<Picture>, Nothing?> = asResult {
        api.searchFor(query)
    }
        .map {
            it.toDomain(imageBaseUrl)
        }
        .mapFailure {
            null
        }

    override suspend fun save(picture: Picture) {
        dataStore.edit { prefs ->
            prefs[pictureKey] = json.encodeToString(picture)
        }
    }

    override suspend fun retrieve(picId: String): Picture =
        dataStore.data.map { prefs ->
            val serialized = prefs[pictureKey] ?: throw IllegalArgumentException("Picture with id $picId not found!")
            json.decodeFromString<Picture>(serialized)
        }.first()

}
