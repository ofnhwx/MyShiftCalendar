package net.komunan.myshiftcalendar

import android.app.Application
import android.content.Context
import android.util.Log

import com.beardedhen.androidbootstrap.TypefaceProvider
import com.crashlytics.android.Crashlytics
import com.jakewharton.threetenabp.AndroidThreeTen

import io.fabric.sdk.android.Fabric
import timber.log.Timber

open class ReleaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Fabric.with(this, Crashlytics())
        Timber.plant(timberTree())
        AndroidThreeTen.init(this)
        TypefaceProvider.registerDefaultIconSets()
    }

    protected open fun timberTree(): Timber.Tree {
        return object : Timber.Tree() {
            override fun log(priority: Int, tag: String, message: String, t: Throwable) {
                when (priority) {
                    Log.ERROR -> Log.e(tag, message, t)
                    Log.WARN -> Log.w(tag, message, t)
                    Log.INFO -> Log.i(tag, message, t)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        var instance: ReleaseApplication? = null
            private set

        @JvmStatic
        val context: Context?
            get() = instance
    }
}
