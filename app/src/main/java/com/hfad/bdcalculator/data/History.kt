package com.hfad.bdcalculator.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class History(
    @ColumnInfo(name = "title") val title: String?,
    @PrimaryKey(autoGenerate = true) val uid: Int? = null
)