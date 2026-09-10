package com.synkstack.fieldtrack.location.model

/**
 * A single captured location sample.
 *
 * This is a plain data holder with no platform (GPS/FusedLocationProvider)
 * or persistence dependencies — those are intentionally out of scope for
 * this foundation and will be introduced when real location capture and
 * sync are implemented.
 *
 * @param id Locally unique identifier for the sample, assigned at capture
 *   time so it can be tracked through a future sync pipeline.
 * @param latitude Latitude in decimal degrees.
 * @param longitude Longitude in decimal degrees.
 * @param accuracyMeters Reported accuracy radius, if known.
 * @param capturedAtEpochMillis Wall-clock time the sample was captured.
 * @param syncStatus Where this sample stands relative to a future
 *   delayed-sync process.
 */
data class LocationPoint(
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val accuracyMeters: Float? = null,
    val capturedAtEpochMillis: Long,
    val syncStatus: SyncStatus = SyncStatus.PENDING
) {
    init {
        require(latitude in -90.0..90.0) { "latitude must be in [-90, 90], was $latitude" }
        require(longitude in -180.0..180.0) { "longitude must be in [-180, 180], was $longitude" }
    }
}
