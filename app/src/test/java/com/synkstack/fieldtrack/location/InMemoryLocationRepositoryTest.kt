package com.synkstack.fieldtrack.location

import com.synkstack.fieldtrack.location.data.InMemoryLocationRepository
import com.synkstack.fieldtrack.location.model.LocationPoint
import com.synkstack.fieldtrack.location.model.SyncStatus
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class InMemoryLocationRepositoryTest {

    private lateinit var repository: InMemoryLocationRepository

    @Before
    fun setUp() {
        repository = InMemoryLocationRepository()
    }

    private fun samplePoint(
        id: String,
        syncStatus: SyncStatus = SyncStatus.PENDING
    ) = LocationPoint(
        id = id,
        latitude = 28.6139,
        longitude = 77.2090,
        accuracyMeters = 12.5f,
        capturedAtEpochMillis = 1_700_000_000_000L,
        syncStatus = syncStatus
    )

    @Test
    fun `getAll returns empty list when nothing recorded`() = runTest {
        assertTrue(repository.getAll().isEmpty())
    }

    @Test
    fun `record adds a point that getAll then returns`() = runTest {
        val point = samplePoint("point-1")

        repository.record(point)

        assertEquals(listOf(point), repository.getAll())
    }

    @Test
    fun `record preserves capture order across multiple points`() = runTest {
        val first = samplePoint("point-1")
        val second = samplePoint("point-2")

        repository.record(first)
        repository.record(second)

        assertEquals(listOf(first, second), repository.getAll())
    }

    @Test
    fun `getPending excludes already-synced points`() = runTest {
        val pending = samplePoint("pending-1", SyncStatus.PENDING)
        val synced = samplePoint("synced-1", SyncStatus.SYNCED)

        repository.record(pending)
        repository.record(synced)

        assertEquals(listOf(pending), repository.getPending())
    }

    @Test
    fun `markSynced flips status for matching ids only`() = runTest {
        val a = samplePoint("a")
        val b = samplePoint("b")
        repository.record(a)
        repository.record(b)

        repository.markSynced(listOf("a"))

        val all = repository.getAll().associateBy { it.id }
        assertEquals(SyncStatus.SYNCED, all.getValue("a").syncStatus)
        assertEquals(SyncStatus.PENDING, all.getValue("b").syncStatus)
    }

    @Test
    fun `markSynced with unknown id does not throw or change existing points`() = runTest {
        val point = samplePoint("real-id")
        repository.record(point)

        repository.markSynced(listOf("does-not-exist"))

        assertEquals(SyncStatus.PENDING, repository.getAll().single().syncStatus)
    }

    @Test
    fun `observeAll emits current snapshot and updates after record`() = runTest {
        assertTrue(repository.observeAll().first().isEmpty())

        val point = samplePoint("point-1")
        repository.record(point)

        assertEquals(listOf(point), repository.observeAll().first())
    }
}
