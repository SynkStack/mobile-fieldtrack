package com.synkstack.fieldtrack.location

import com.synkstack.fieldtrack.location.model.LocationPoint
import com.synkstack.fieldtrack.location.model.SyncStatus
import org.junit.Assert.assertEquals
import org.junit.Test

class LocationPointTest {

    @Test
    fun `defaults sync status to PENDING`() {
        val point = LocationPoint(
            id = "id-1",
            latitude = 12.9716,
            longitude = 77.5946,
            capturedAtEpochMillis = 1_700_000_000_000L
        )

        assertEquals(SyncStatus.PENDING, point.syncStatus)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `rejects latitude outside valid range`() {
        LocationPoint(
            id = "id-1",
            latitude = 120.0,
            longitude = 0.0,
            capturedAtEpochMillis = 1_700_000_000_000L
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `rejects longitude outside valid range`() {
        LocationPoint(
            id = "id-1",
            latitude = 0.0,
            longitude = 200.0,
            capturedAtEpochMillis = 1_700_000_000_000L
        )
    }
}
