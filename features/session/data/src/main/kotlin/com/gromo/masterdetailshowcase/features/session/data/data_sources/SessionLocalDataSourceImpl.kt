package com.gromo.masterdetailshowcase.features.session.data.data_sources

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.gromo.masterdetailshowcase.libraries.common.sharedPreferences
import com.gromo.masterdetailshowcase.features.session.domain.data_sources.SessionLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import org.koin.core.annotation.Single

@Single(binds = [SessionLocalDataSource::class])
class SessionLocalDataSourceImpl(
    context: Context
): SessionLocalDataSource, SharedPreferences.OnSharedPreferenceChangeListener {

    companion object {
        private const val SHARED_PREFERENCES_NAME = "164f1b75-f1f6-4045-bdae-13ed0175600e"

        private const val KEY_HAS_SEEN_ONBOARDING = "1192487f-c02a-4bc2-9d9e-1faa6ec8e96f"

        private const val DEFAULT_VALUE_HAS_SEEN_ONBOARDING = false
    }

    private val preferenceKeyChangedFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)
    private val sharedPref by context.sharedPreferences(SHARED_PREFERENCES_NAME)

    init {
        sharedPref.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        key?.let {
            preferenceKeyChangedFlow.tryEmit(it)
        }
    }

    override var hasSeenOnboarding: Boolean
        get() = sharedPref.getBoolean(
            KEY_HAS_SEEN_ONBOARDING,
            DEFAULT_VALUE_HAS_SEEN_ONBOARDING
        )
        set(value) = sharedPref.edit {
            putBoolean(KEY_HAS_SEEN_ONBOARDING, value)
        }

    override fun observeHasSeenOnboarding(): Flow<Boolean> =
        createPreferenceFlow(KEY_HAS_SEEN_ONBOARDING) { hasSeenOnboarding }

    private inline fun <T> createPreferenceFlow(
        key: String,
        crossinline getValue: () -> T,
    ): Flow<T> = preferenceKeyChangedFlow
        // Emit on start so that we always send the initial value
        .onStart { emit(key) }
        .filter { it == key }
        .map { getValue() }
        .distinctUntilChanged()
}