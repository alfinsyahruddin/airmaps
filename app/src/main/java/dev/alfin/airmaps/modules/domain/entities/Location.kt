package dev.alfin.airmaps.modules.domain.entities

data class Location(
    val id: Int,
    val name: String,
    val description: String = "",
    val coordinate: Coordinate
) {
    companion object
}

val Location.Companion.dummy: Location
    get() = Location(
        id = 0,
        name = "Jakarta Pusat",
        description = "Karet Tengsin, Kecamatan Tanah Abang, Kota Jakarta Pusat, Indonesia.",
        coordinate = Coordinate.dummy
    )

val Location.Companion.placeholder: Location
    get() = Location(
        id = 0,
        name = "Loading...",
        description = "N/A",
        coordinate = Coordinate.dummy
    )