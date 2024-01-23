package com.buildwithshubham.wishlistapp

import android.app.Application

// Add this in the AppManifest
class WishListApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}