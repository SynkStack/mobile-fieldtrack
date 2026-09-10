package com.synkstack.fieldtrack.location.data

import com.synkstack.fieldtrack.location.model.LocationPoint
import kotlinx.coroutines.flow.Flow

/**
 * Abstraction over storage/retrieval of captured [LocationPoint]s.
 *
 * This is intentionally source-agnostic: the initial implementation
 * ([InMemoryLocationRepository]) just holds points in memory, but the
 * interface is shaped so a persistent (e.g. Room-backed) implementation can
 * be dropped in later without changing callers. Likewise, [getPending] and
 * [markSynced] exist so a future delayed-sync worker has what it needs
 * without this interface changing shape at that point.
 *
 * No network or persistence behavior lives here yet.
 */
interface LocationRepository {

    /** Stores a newly captured point. */
    suspend fun record(point: LocationPoint)

    /** Returns all points currently held, in capture order. */
    suspend fun getAll(): List<LocationPoint>

    /** Returns only points that have not yet been synced. */
    suspend fun getPending(): List<LocationPoint>

    /** Marks the given point ids as synced. Unknown ids are ignored. */
    suspend fun markSynced(ids: Collection<String>)

    /** Emits the current list of points, and again whenever it changes. */
    fun observeAll(): Flow<List<LocationPoint>>
}
