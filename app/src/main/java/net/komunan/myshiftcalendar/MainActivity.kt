package net.komunan.myshiftcalendar

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import hugo.weaving.DebugLog
import net.komunan.myshiftcalendar.databinding.ActivityMainBinding
import net.komunan.myshiftcalendar.event.MoveToTemplateEditEvent
import net.komunan.myshiftcalendar.event.MoveToTemplateListEvent
import net.komunan.myshiftcalendar.ui.calendar.CalendarFragment
import net.komunan.myshiftcalendar.ui.template.TemplateEditFragment
import net.komunan.myshiftcalendar.ui.template.TemplateListFragment
import net.komunan.myshiftcalendar.util.BaseActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

@DebugLog
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).also {
            // 広告表示
            (it.root.findViewById<View>(R.id.ads_container) as? ViewGroup)?.let {
                MobileAds.initialize(this, BuildConfig.ADMOB_APP_ID)
                adView = AdView(this).apply {
                    visibility = View.VISIBLE
                    adSize = AdSize.BANNER
                    adUnitId = BuildConfig.AD_UNIT_ID
                    loadAd(AdRequest.Builder().build())
                    it.addView(this)
                }
            }
        }

        if (savedInstanceState == null) {
            setContent(CalendarFragment.create(), false)
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()
        adView?.resume()
    }

    override fun onPause() {
        super.onPause()
        adView?.pause()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        adView?.destroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_preferences -> {
                MoveToTemplateListEvent.execute()
                //setContent(PreferenceFragment.create(), true);
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @Subscribe
    fun moveToTemplateList(event: MoveToTemplateListEvent) {
        setContent(TemplateListFragment.create())
    }

    @Subscribe
    fun moveToTemplateEdit(event: MoveToTemplateEditEvent) {
        setContent(TemplateEditFragment.create(event.id))
    }

    private fun setContent(fragment: Fragment, addToBackStack: Boolean = true) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content, fragment)
            if (addToBackStack) {
                addToBackStack(null)
            }
        }.commit()
    }
}
