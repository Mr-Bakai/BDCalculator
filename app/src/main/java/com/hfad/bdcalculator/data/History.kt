package com.hfad.bdcalculator.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class History(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "result") var result: String?,
    @ColumnInfo(name = "typedOnes") var typedOnes: String?
)