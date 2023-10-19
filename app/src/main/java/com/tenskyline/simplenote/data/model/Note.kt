package com.tenskyline.simplenote.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int=0,
    @ColumnInfo(name = "title")
    val title: String="",
    @ColumnInfo(name = "description")
    val description: String="",
    @ColumnInfo(name = "createdAt")
    val createdAt: String="",
)
