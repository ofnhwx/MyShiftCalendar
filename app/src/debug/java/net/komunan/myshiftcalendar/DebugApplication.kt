package net.komunan.myshiftcalendar

import com.facebook.stetho.Stetho

import timber.log.Timber

class DebugApplication : ReleaseApplication() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }

    override fun timberTree(): Timber.Tree {
        return Timber.DebugTree()
    }
}
