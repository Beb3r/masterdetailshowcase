package com.gromo.masterdetailshowcase.features.session.data.data_sources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.gromo.masterdetailshowcase.features.session.domain.data_sources.SessionLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath
import org.koin.core.annotation.Single

@Single(binds = [SessionLocalDataSource::class])
class SessionLocalDataSourceImpl(
    context: Context
) : SessionLocalDataSource {

    companion object {
        private const val SHARED_PREFERENCES_NAME = "164f1b75-f1f6-4045-bdae-13ed0175600e"

        private val KEY_HAS_SEEN_ONBOARDING =
            booleanPreferencesKey("1192487f-c02a-4bc2-9d9e-1faa6ec8e96f")

        private const val DEFAULT_VALUE_HAS_SEEN_ONBOARDING = false
    }

    private fun sharedPreferencesMigration(context: Context) =
        listOf(SharedPreferencesMigration(context, SHARED_PREFERENCES_NAME))

    private val dataStore = PreferenceDataStoreFactory.createWithPath(
        migrations = sharedPreferencesMigration(context),
        produceFile = { context.filesDir.resolve("${SHARED_PREFERENCES_NAME}.preferences_pb").absolutePath.toPath() },
    )

    override suspend fun getHasSeenOnboarding(): Boolean =
        dataStore.observeKey(
            key = KEY_HAS_SEEN_ONBOARDING,
            defaultValue = DEFAULT_VALUE_HAS_SEEN_ONBOARDING
        ).first()

    override suspend fun setHasSeenOnboarding(hasSeen: Boolean) {
        dataStore.edit { preferences ->
            preferences[KEY_HAS_SEEN_ONBOARDING] = hasSeen
        }
    }

    override fun observeHasSeenOnboarding(): Flow<Boolean> =
        dataStore.observeKey(
            key = KEY_HAS_SEEN_ONBOARDING,
            defaultValue = DEFAULT_VALUE_HAS_SEEN_ONBOARDING
        )
}

fun <T> DataStore<Preferences>.observeKey(
    key: Preferences.Key<T>,
    defaultValue: T,
): Flow<T> {
    return this.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[key] ?: defaultValue
        }
}
