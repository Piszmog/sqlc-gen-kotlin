package com.example.ondeck.postgresql

import com.example.dbtest.PostgresDbTestExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class QueriesImplTest {
    companion object {
        @JvmField @RegisterExtension val dbtest = PostgresDbTestExtension("src/main/resources/ondeck/postgresql/schema")
    }

    @Test
    fun testQueries() {
        val q = QueriesImpl(dbtest.getConnection())
        val city = q.createCity(
                slug = "san-francisco",
                name = "San Francisco"
        )!!
        val venueId = q.createVenue(
                slug = "the-fillmore",
                name = "The Fillmore",
                city = city.slug,
                spotifyPlaylist = "spotify=uri",
                status = Status.OPEN,
                statuses = listOf(Status.OPEN, Status.CLOSED),
                tags = listOf("rock", "punk")
        )
        val venue = q.getVenue(
                slug = "the-fillmore",
                city = city.slug
        )!!
        assertEquals(venueId, venue.id)

        assertEquals(city, q.getCity(city.slug))
        assertEquals(listOf(VenueCountByCityRow(city.slug, 1)), q.venueCountByCity())
        assertEquals(listOf(city), q.listCities())
        assertEquals(listOf(venue), q.listVenues(city.slug))

        q.updateCityName(slug = city.slug, name = "SF")
        val id = q.updateVenueName(slug = venue.slug, name = "Fillmore")
        assertEquals(venue.id, id)

        q.deleteVenue(venue.slug)

        val person1Id = q.createPerson("Jane", null)
        val person2Id = q.createPerson("John", Mood.HAPPY)
        val people = q.listPeople()
        assertEquals(2, people.size)
        val person1 = people.first { it.id == person1Id }
        val person2 = people.first { it.id == person2Id }
        assertNull(person1.mood)
        assertEquals(Mood.HAPPY, person2.mood)
    }
}
