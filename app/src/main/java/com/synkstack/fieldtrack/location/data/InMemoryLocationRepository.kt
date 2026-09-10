package com.synkstack.fieldtrack.location.data

import com.synkstack.fieldtrack.location.model.LocationPoint
import com.synkstack.fieldtrack.location.model.SyncStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * In-memory [LocationRepository] suitable for early development and tests.
 *
 * Points are held only for the lifetime of the process — nothing is
 * persisted to disk or sent anywhere. This exists so upper layers (UI,
 * capture logic, and eventually a sync worker) have a real dependency to
 * build against before a durable storage implementation exists.
 */
class InMemoryLocationRepository : LocationRepository {

    private val mutex = Mutex()
    private val state = MutableStateFlow<List<LocationPoint>>(emptyList())

    override suspend fun record(point: LocationPoint) {
        mutex.withLock {
            state.value = state.value + point
        }
    }

    override suspend fun getAll(): List<LocationPoint> {
        return state.value
    }

    override suspend fun getPending(): List<LocationPoint> {
        return state.value.filter { it.syncStatus == SyncStatus.PENDING }
    }

    override suspend fun markSynced(ids: Collection<String>) {
        if (ids.isEmpty()) return
        mutex.withLock {
            state.value = state.value.map { point ->
                if (point.id in ids) point.copy(syncStatus = SyncStatus.SYNCED) else point
            }
        }
    }

    override fun observeAll(): Flow<List<LocationPoint>> = state.asStateFlow()
}
