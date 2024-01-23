package com.buildwithshubham.wishlistapp.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {

    suspend fun addAWish(wish: Wish) {
        wishDao.addAWish(wish)
    }

    fun getAllWish(): Flow<List<Wish>> = wishDao.getAllWishes()

    suspend fun updateAWish(wish: Wish) {
        wishDao.updateAWish(wish)
    }

    suspend fun deleteWish(wish: Wish) {
        wishDao.deleteAWish(wish)
    }

    fun getWishById(id: Long): Flow<Wish> {
        return wishDao.getAWishById(id)
    }

}