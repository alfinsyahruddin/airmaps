package dev.alfin.airmaps.core.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import dev.alfin.airmaps.modules.domain.entities.Coordinate
import kotlin.math.*

class LocationHelper {
    companion object {
        @SuppressLint("MissingPermission")
        fun getCurrentLocation(context: Context, completionHandler: (Coordinate) -> Unit) {
            Toast.makeText(context, "Getting current location...", Toast.LENGTH_SHORT).show()

            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                object : CancellationToken() {
                    override fun onCanceledRequested(p0: OnTokenCanceledListener) =
                        CancellationTokenSource().token

                    override fun isCancellationRequested() = false
                }
            ).addOnSuccessListener { location ->
                if (location == null)
                    Toast.makeText(context, "Cannot get location.", Toast.LENGTH_SHORT).show()
                else {
                    Log.d("AIRMAPS", location.toString())

                    val coordinate = Coordinate(
                        latitude = location.latitude,
                        longitude = location.longitude,
                    )
                    completionHandler(coordinate)
                }
            }
        }


        fun nearbyCoordinate(
            // The center of coordinate
            coordinate: Coordinate,
            // Radius limit
            r: Double,
            // How many coordinates that we want to generate
            n: Int
        ): List<Coordinate> {
            val earthRadius = 6371000.0 // Earth's radius in kilometers

            // Convert inputs to radians
            val phi1 = Math.toRadians(coordinate.latitude)
            val lambda1 = Math.toRadians(coordinate.longitude)
            val d = r / earthRadius // angular distance

            // Initialize an empty list to store the coordinates
            val coordinates = mutableListOf<Coordinate>()

            // Loop over different bearings
            for (i in 0 until n) {
                // Convert bearing to radians
                val theta = Math.toRadians(i.toDouble() * 360.0 / n.toDouble())

                // Apply the formula for the destination point
                val phi2 = asin(sin(phi1) * cos(d) + cos(phi1) * sin(d) * cos(theta))
                val lambda2 = lambda1 + atan2(sin(theta) * sin(d) * cos(phi1), cos(d) - sin(phi1) * sin(phi2))

                // Convert outputs to degrees
                val lat2 = Math.toDegrees(phi2)
                val lon2 = Math.toDegrees(lambda2)

                // Append the coordinate to the list
                coordinates.add(Coordinate(lat2, lon2))
            }

            // Return the list of coordinates
            return coordinates
        }
    }
}