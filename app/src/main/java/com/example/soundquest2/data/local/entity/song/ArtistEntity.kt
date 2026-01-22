package com.example.soundquest2.data.local.entity.song

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.soundquest2.domain.model.Gender

@Entity(tableName = "artists")
data class ArtistEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "country_code")
    val countryCode: String,
    val gender: Gender,
    @ColumnInfo(name = "picture_uri")
    val pictureUri: String
)
