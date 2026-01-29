package com.example.soundquest2.data.local.dao.song

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.soundquest2.data.local.entity.song.SongEntity
import com.example.soundquest2.data.local.entity.song.relation.SongWithDetails
import com.example.soundquest2.domain.model.enums.Era
import com.example.soundquest2.domain.model.enums.Genre

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSongs(songs: List<SongEntity>)

    @Transaction
    @Query("SELECT * FROM songs")
    suspend fun getAllSongsWithDetails(): List<SongWithDetails>

    suspend fun getRandomSongs(count: Int = 20): List<SongWithDetails> {
        return getAllSongsWithDetails().shuffled().take(count)
    }

    @Transaction
    @Query("SELECT * FROM songs WHERE era = :era")
    suspend fun getSongsByEra(era: Era): List<SongWithDetails>

    @Transaction
    @Query("SELECT * FROM songs WHERE genre = :genre")
    suspend fun getSongsByGenre(genre: Genre): List<SongWithDetails>

}
