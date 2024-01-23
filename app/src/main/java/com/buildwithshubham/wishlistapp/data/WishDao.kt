package com.buildwithshubham.wishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.buildwithshubham.wishlistapp.data.Wish
import kotlinx.coroutines.flow.Flow


@Dao
abstract class WishDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun addAWish(wishEntity: Wish)


    @Query("Select * from `wish_list`")
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Update
    abstract suspend fun updateAWish(wishEntity: Wish)

    @Delete
    abstract suspend fun deleteAWish(wishEntity: Wish)

    @Query("Select * from `wish_list` where id=:wishId")
    abstract fun getAWishById(wishId: Long): Flow<Wish>
}