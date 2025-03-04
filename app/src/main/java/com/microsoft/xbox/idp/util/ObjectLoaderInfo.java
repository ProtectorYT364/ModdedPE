package com.microsoft.xbox.idp.util;

import android.app.LoaderManager;

import com.microsoft.xbox.idp.toolkit.ObjectLoader;

/**
 * 07.01.2021
 *
 * @author Тимашков Иван
 * @author https://github.com/TimScriptov
 */

public class ObjectLoaderInfo implements ErrorHelper.LoaderInfo {
    private final LoaderManager.LoaderCallbacks<?> callbacks;

    public ObjectLoaderInfo(LoaderManager.LoaderCallbacks<?> loaderCallbacks) {
        this.callbacks = loaderCallbacks;
    }

    public LoaderManager.LoaderCallbacks<?> getLoaderCallbacks() {
        return this.callbacks;
    }

    public void clearCache(Object obj) {
        ObjectLoader.Cache objectLoaderCache = CacheUtil.getObjectLoaderCache();
        synchronized (objectLoaderCache) {
            objectLoaderCache.remove(obj);
        }
    }

    public boolean hasCachedData(Object obj) {
        boolean z;
        ObjectLoader.Cache objectLoaderCache = CacheUtil.getObjectLoaderCache();
        synchronized (objectLoaderCache) {
            z = objectLoaderCache.get(obj) != null;
        }
        return z;
    }
}
