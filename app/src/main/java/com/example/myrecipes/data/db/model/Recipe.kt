package com.example.myrecipes.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.myrecipes.utils.DataConverter

@Entity(tableName = "recipes")
data class Recipe (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @TypeConverters(DataConverter::class)
    @ColumnInfo(name = "ingredients")
    val ingredients: List<Ingredient>,
    @ColumnInfo(name = "preparation_description")
    val preparationDescription: String,
    @ColumnInfo(name = "notes")
    val notes: String,
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
    @ColumnInfo(name = "is_favourite")
    val isFavourite: Boolean,
    @ColumnInfo(name = "modification_timestamp")
    val modificationTimestamp: Long
)