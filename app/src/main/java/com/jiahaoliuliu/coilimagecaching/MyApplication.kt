package com.jiahaoliuliu.coilimagecaching

import android.app.Application
import android.graphics.Bitmap
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.bitmapConfig
import coil3.util.DebugLogger

/**
 * Custom application class that configures Coil ImageLoader with memory-optimized settings.
 *
 * Memory optimization strategies:
 * 1. Memory cache set to 5% of available memory (reduced from 10%)
 * 2. Disk cache set to 5% to prioritize disk storage over memory
 * 3. RGB_565 bitmap config to reduce per-pixel memory by 50% vs ARGB_8888
 * 4. Disable strong references to allow garbage collection of bitmaps
 * 5. Network cache strategy optimized for frequently accessed images
 */
class MyApplication : Application(), SingletonImageLoader.Factory {
    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader(this).newBuilder()
            // Memory cache: Keep minimal in-memory cache
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(this, 0.05) // Reduced from 0.1 (10%) to 0.05 (5%)
                    .strongReferencesEnabled(false) // Allow GC to collect bitmaps
                    .build()
            }
            // Disk cache: Use disk storage to reduce memory pressure
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(0.05)
                    .directory(cacheDir)
                    .build()
            }
            // Bitmap config: Use RGB_565 instead of ARGB_8888 to save 50% memory per pixel
            .bitmapConfig(Bitmap.Config.RGB_565)
            // Network cache policy: Read from cache first, write to cache always
            .networkCachePolicy(CachePolicy.ENABLED)
            .logger(DebugLogger())
            .build()
    }
}