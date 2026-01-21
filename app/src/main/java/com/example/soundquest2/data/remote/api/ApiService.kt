package com.example.soundquest2.data.remote.api

import com.example.soundquest2.data.remote.dto.SongDto
import com.example.soundquest2.domain.model.Era
import com.example.soundquest2.domain.model.Genre
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.storage.storage
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

object ApiService {
    private val postgrest = SupabaseClient.client.postgrest
    private val storage = SupabaseClient.client.storage

    suspend fun getAllSongs(language: String): List<SongDto> {
        val columns = Columns.raw(
            """
            id,
            picture_uri,
            genre,
            era,
            song_translations!inner(
                language,
                title,
                info
            ),
            song_media(
                segment,
                duration,
                audio_path,
                video_path
            ),
            artist:artists(
                id,
                picture_uri,
                artist_translations!inner(
                    language,
                    name,
                    info
                )
            )
            """.trimIndent()
        )

        return postgrest.from("songs").select(columns = columns) {
            filter {
                eq("song_translations.language", language)
                eq("artist.artist_translations.language", language)
            }
        }.decodeList<SongDto>()
    }

    suspend fun getRandomSongs(language: String, count: Int = 10): List<SongDto> {
        return postgrest.rpc(
            function = "get_random_songs",
            parameters = buildJsonObject {
                put("p_language", language)
                put("p_count", count)
            }
        ).decodeList()
    }

    suspend fun getSongsByGenre(language: String, genre: Genre): List<SongDto> {
        val columns = Columns.raw(
            """
            id,
            picture_uri,
            genre,
            era,
            song_translations!inner(
                language,
                title,
                info
            ),
            song_media(
                segment,
                duration,
                audio_path,
                video_path
            ),
            artist:artists(
                id,
                picture_uri,
                artist_translations!inner(
                    language,
                    name,
                    info
                )
            )
            """.trimIndent()
        )

        return postgrest.from("songs").select(columns = columns) {
            filter {
                eq("song_translations.language", language)
                eq("artist.artist_translations.language", language)
                eq("genre", genre)
            }
        }.decodeList<SongDto>()
    }

    suspend fun getSongsByEra(language: String, era: Era): List<SongDto> {
        val columns = Columns.raw(
            """
            id,
            picture_uri,
            genre,
            era,
            song_translations!inner(
                language,
                title,
                info
            ),
            song_media(
                segment,
                duration,
                audio_path,
                video_path
            ),
            artist:artists(
                id,
                picture_uri,
                artist_translations!inner(
                    language,
                    name,
                    info
                )
            )
            """.trimIndent()
        )

        return postgrest.from("songs").select(columns = columns) {
            filter {
                eq("song_translations.language", language)
                eq("artist.artist_translations.language", language)
                eq("era", era)
            }
        }.decodeList<SongDto>()
    }

}

