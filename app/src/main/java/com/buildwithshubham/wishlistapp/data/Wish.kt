package com.buildwithshubham.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish_list")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish_title")
    val title: String = "",
    @ColumnInfo(name = "wish_description")
    val description: String = ""
)

object DummyWish {
    val wishList = listOf(
        Wish(id = 1L, title = "Apple Watch", description = "Some dummy text here"),
        Wish(id = 2L, title = "iPhone something", description = "Some dummy text here"),
        Wish(id = 3L, title = "Macbook Air", description = "Some dummy text here"),
        Wish(id = 4L, title = "AR Headset", description = "Some dummy text here"),
        Wish(id = 5L, title = "Google Watch", description = "Some dummy text here"),
    )
}
