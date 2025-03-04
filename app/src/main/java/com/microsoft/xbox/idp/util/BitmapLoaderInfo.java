package com.microsoft.xbox.idp.util;

import android.app.LoaderManager;

import com.microsoft.xbox.idp.toolkit.BitmapLoader;

/**
 * 07.01.2021
 *
 * @author Тимашков Иван
 * @author https://github.com/TimScriptov
 */

public class BitmapLoaderInfo implements ErrorHelper.LoaderInfo {
    private final LoaderManager.LoaderCallbacks<?> callbacks;

    public BitmapLoaderInfo(LoaderManager.LoaderCallbacks<?> loaderCallbacks) {
        this.callbacks = loaderCallbacks;
    }

    public LoaderManager.LoaderCallbacks<?> getLoaderCallbacks() {
        return this.callbacks;
    }

    public void clearCache(Object obj) {
        BitmapLoader.Cache bitmapCache = CacheUtil.getBitmapCache();
        synchronized (bitmapCache) {
            bitmapCache.remove(obj);
        }
    }

    public boolean hasCachedData(Object obj) {
        boolean z;
        BitmapLoader.Cache bitmapCache = CacheUtil.getBitmapCache();
        synchronized (bitmapCache) {
            z = bitmapCache.get(obj) != null;
        }
        return z;
    }
}
