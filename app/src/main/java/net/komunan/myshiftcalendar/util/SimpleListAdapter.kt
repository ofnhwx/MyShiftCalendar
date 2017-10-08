package net.komunan.myshiftcalendar.util

import android.databinding.BaseObservable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import net.komunan.myshiftcalendar.ReleaseApplication

abstract class SimpleListAdapter<out T : BaseObservable>(protected val items: List<T>) : BaseAdapter() {
    protected val inflater: LayoutInflater = LayoutInflater.from(ReleaseApplication.context)

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return (convertView ?: newView(position, parent)).apply {
            bindView(this, position)
        }
    }

    abstract fun newView(position: Int, parent: ViewGroup): View
    abstract fun bindView(view: View, position: Int)
}
