package com.synkstack.fieldtrack.location.model

/**
 * Sync lifecycle state for a [LocationPoint].
 *
 * FieldTrack is expected to capture location data while a device may be
 * offline, so captured points start as [PENDING] and are later flipped to
 * [SYNCED] by a future sync process. No such process exists yet — this enum
 * only exists so the data model has somewhere to record that state.
 */
enum class SyncStatus {
    PENDING,
    SYNCED
}
