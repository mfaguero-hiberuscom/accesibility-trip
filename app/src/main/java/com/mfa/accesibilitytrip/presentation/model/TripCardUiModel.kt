package com.mfa.accesibilitytrip.presentation.model

import androidx.annotation.StringRes
import com.mfa.accesibilitytrip.R

data class TripCardUiModel(
    val id: String,
    val origin: String,
    val destination: String,
    val nextDeparture: String,
    val price: String,
    val travelType: TravelTypeUiModel,
    val duration: String,
    val vehicleName: String,
    val isFavorite: Boolean = false,
) {
    companion object {
        private val MONTH_ABBR = listOf(
            "ene.", "feb.", "mar.", "abr.", "may.", "jun.",
            "jul.", "ago.", "sep.", "oct.", "nov.", "dic.",
        )

        private fun formatDate(day: Int, month: Int, year: Int): String {
            return "$day ${MONTH_ABBR[month - 1]} $year"
        }

        fun mockCatalog(): List<TripCardUiModel> {
            val featuredTrips = listOf(
                TripCardUiModel(
                    id = "tierra-marte",
                    origin = "Tierra",
                    destination = "Marte",
                    nextDeparture = formatDate(20, 3, 2140),
                    price = "3.200 cr",
                    travelType = TravelTypeUiModel.HYPERLUMINOUS,
                    duration = "2 h 15 min",
                    vehicleName = "Atlas Nova",
                    isFavorite = true,
                ),
                TripCardUiModel(
                    id = "europa-titan",
                    origin = "Europa",
                    destination = "Titán",
                    nextDeparture = formatDate(22, 3, 2140),
                    price = "1.860 cr",
                    travelType = TravelTypeUiModel.NORMAL,
                    duration = "18 h 00 min",
                    vehicleName = "Senda Boreal",
                    isFavorite = true,
                ),
                TripCardUiModel(
                    id = "venus-encelado",
                    origin = "Venus",
                    destination = "Encélado",
                    nextDeparture = formatDate(1, 4, 2140),
                    price = "2.480 cr",
                    travelType = TravelTypeUiModel.HYPERLUMINOUS,
                    duration = "4 h 10 min",
                    vehicleName = "Aurora Delta",
                    isFavorite = true,
                ),
            )

            val origins = listOf(
                "Mercurio",
                "Luna",
                "Ío",
                "Calisto",
                "Ganímedes",
                "Neptuno",
                "Plutón",
                "Titania",
                "Ceres",
                "Andrómeda Hub",
            )
            val destinations = listOf(
                "Marte",
                "Titán",
                "Europa",
                "Encélado",
                "Kepler-442b",
                "Próxima b",
                "Tritón",
                "Oberón",
                "Fobos",
                "Deimos",
            )
            val ships = listOf(
                "Horizonte 7",
                "Vector Comet",
                "Pioneer Silk",
                "Atlas Eclipse",
                "Nebula Pulse",
                "Zenit Coral",
                "Vía Celeste",
                "Ion Runner",
            )

            val generatedTrips = List(size = 97) { index ->
                val origin = origins[index % origins.size]
                val destination = destinations[(index + 3) % destinations.size]
                val departureDay = (index % 27) + 1
                val departureMonth = ((index / 9) % 9) + 4
                val credits = 980 + (index * 47)
                TripCardUiModel(
                    id = "trip-$index-${origin.lowercase()}-${destination.lowercase()}",
                    origin = origin,
                    destination = destination,
                    nextDeparture = formatDate(departureDay, departureMonth, 2140),
                    price = "${credits / 1000}.${(credits % 1000).toString().padStart(3, '0')} cr",
                    travelType = if (index % 3 == 0) {
                        TravelTypeUiModel.HYPERLUMINOUS
                    } else {
                        TravelTypeUiModel.NORMAL
                    },
                    duration = "${2 + (index % 20)} h ${(10 + ((index * 7) % 50)).toString().padStart(2, '0')} min",
                    vehicleName = ships[index % ships.size],
                )
            }

            return featuredTrips + generatedTrips
        }
    }
}

enum class TravelTypeUiModel(@get:StringRes val labelRes: Int) {
    NORMAL(R.string.travel_type_normal),
    HYPERLUMINOUS(R.string.travel_type_hyperluminous),
}
