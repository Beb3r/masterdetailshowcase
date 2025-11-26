package com.gromo.masterdetailshowcase.libraries.design.scenes

import androidx.collection.mutableIntListOf
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldDefaults
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.PaneScaffoldDirective
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldAdaptStrategies
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldDestinationItem
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldScope
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldValue
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun <T : Any> rememberListDetailSceneStrategyCustom(
    backNavigationBehavior: BackNavigationBehavior =
        BackNavigationBehavior.PopUntilScaffoldValueChange,
    directive: PaneScaffoldDirective = calculatePaneScaffoldDirective(currentWindowAdaptiveInfo()),
    adaptStrategies: ThreePaneScaffoldAdaptStrategies =
        ListDetailPaneScaffoldDefaults.adaptStrategies(),
): ListDetailSceneStrategyCustom<T> {
    return remember(backNavigationBehavior, directive, adaptStrategies) {
        ListDetailSceneStrategyCustom(
            backNavigationBehavior = backNavigationBehavior,
            directive = directive,
            adaptStrategies = adaptStrategies,
        )
    }
}

@ExperimentalMaterial3AdaptiveApi
class ListDetailSceneStrategyCustom<T : Any>(
    val backNavigationBehavior: BackNavigationBehavior,
    val directive: PaneScaffoldDirective,
    val adaptStrategies: ThreePaneScaffoldAdaptStrategies,
) : SceneStrategy<T> {

    override fun SceneStrategyScope<T>.calculateScene(entries: List<NavEntry<T>>): Scene<T>? {
        val lastPaneMetadata = getPaneMetadata(entries.last()) ?: return null
        val sceneKey = lastPaneMetadata.sceneKey

        val scaffoldEntries = mutableListOf<NavEntry<T>>()
        val scaffoldEntryIndices = mutableIntListOf()
        val entriesAsNavItems = mutableListOf<ThreePaneScaffoldDestinationItem<Any>>()

        var detailPlaceholder: (@Composable ThreePaneScaffoldScope.() -> Unit)? = null

        var idx = entries.lastIndex
        while (idx >= 0) {
            val entry = entries[idx]
            val paneMetadata = getPaneMetadata(entry) ?: break

            if (paneMetadata.sceneKey == sceneKey) {
                scaffoldEntryIndices.add(0, idx)
                scaffoldEntries.add(0, entry)
                entriesAsNavItems.add(
                    0,
                    ThreePaneScaffoldDestinationItem(
                        pane = paneMetadata.role,
                        contentKey = entry.contentKey,
                    ),
                )
                if (paneMetadata is ListMetadata) {
                    detailPlaceholder = paneMetadata.detailPlaceholder
                }
            }
            idx--
        }

        if (scaffoldEntries.isEmpty()) return null

        val scene =
            ThreePaneScaffoldSceneCustom(
                key = sceneKey,
                onBack = onBack,
                backNavBehavior = backNavigationBehavior,
                directive = directive,
                adaptStrategies = adaptStrategies,
                allEntries = entries,
                scaffoldEntries = scaffoldEntries,
                scaffoldEntryIndices = scaffoldEntryIndices,
                entriesAsNavItems = entriesAsNavItems,
                getPaneRole = { getPaneMetadata(it)?.role },
                scaffoldType = ThreePaneScaffoldType.ListDetail(detailPlaceholder ?: {}),
            )

        if (scene.currentScaffoldValue.paneCount <= 1) {
            return null
        }

        return scene
    }

    internal sealed interface PaneMetadata {
        val sceneKey: Any
        val role: ThreePaneScaffoldRole
    }

    internal class ListMetadata(
        override val sceneKey: Any,
        val detailPlaceholder: @Composable ThreePaneScaffoldScope.() -> Unit,
    ) : PaneMetadata {
        override val role: ThreePaneScaffoldRole
            get() = ListDetailPaneScaffoldRole.List
    }

    internal class DetailMetadata(override val sceneKey: Any) :
        PaneMetadata {
        override val role: ThreePaneScaffoldRole
            get() = ListDetailPaneScaffoldRole.Detail
    }

    internal class ExtraMetadata(override val sceneKey: Any) :
        PaneMetadata {
        override val role: ThreePaneScaffoldRole
            get() = ListDetailPaneScaffoldRole.Extra
    }

    companion object Companion {
        internal const val ListDetailRoleKey: String =
            "androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole"

        fun listPane(
            sceneKey: Any = Unit,
            detailPlaceholder: @Composable ThreePaneScaffoldScope.() -> Unit = {},
        ): Map<String, Any> = mapOf(ListDetailRoleKey to ListMetadata(sceneKey, detailPlaceholder))

        fun detailPane(sceneKey: Any = Unit): Map<String, Any> =
            mapOf(ListDetailRoleKey to DetailMetadata(sceneKey))

        fun extraPane(sceneKey: Any = Unit): Map<String, Any> =
            mapOf(ListDetailRoleKey to ExtraMetadata(sceneKey))

        private fun <T : Any> getPaneMetadata(entry: NavEntry<T>): PaneMetadata? =
            entry.metadata[ListDetailRoleKey] as? PaneMetadata
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
internal val ThreePaneScaffoldValue.paneCount: Int
    get() {
        var count = 0
        if (this.primary != PaneAdaptedValue.Hidden) count++
        if (this.secondary != PaneAdaptedValue.Hidden) count++
        if (this.tertiary != PaneAdaptedValue.Hidden) count++
        return count
    }

// Used to signal NavEntries whether they are being composed within a scene
// Useful to adapt UI
val LocalWithinScene = compositionLocalOf { false }