package net.komunan.myshiftcalendar.ui.template

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mikepenz.fontawesome_typeface_library.FontAwesome
import com.mikepenz.iconics.IconicsDrawable
import hugo.weaving.DebugLog
import net.komunan.myshiftcalendar.database.entity.Template
import net.komunan.myshiftcalendar.databinding.FragmentTemplateListBinding
import net.komunan.myshiftcalendar.databinding.ListTemplateItemBinding
import net.komunan.myshiftcalendar.event.MoveToTemplateEditEvent
import net.komunan.myshiftcalendar.util.BaseFragment
import net.komunan.myshiftcalendar.util.SimpleListAdapter

@DebugLog
class TemplateListFragment : BaseFragment() {
    companion object {
        @JvmStatic
        fun create(): TemplateListFragment {
            return TemplateListFragment()
        }
    }

    private lateinit var binding: FragmentTemplateListBinding
    private lateinit var viewModel: FragmentTemplateListViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(FragmentTemplateListViewModel::class.java)
        return FragmentTemplateListBinding.inflate(inflater!!, container, false).also {
            binding = it
            it.fabAdd.setImageDrawable(IconicsDrawable(context).icon(FontAwesome.Icon.faw_plus).color(Color.WHITE))
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.let {
            it.list.setOnItemClickListener { _, _, _, id -> MoveToTemplateEditEvent.execute(id) }
            it.fabAdd.setOnClickListener { MoveToTemplateEditEvent.execute(0) }
            viewModel.templates.observe(this, Observer { templates ->
                val adapter = TemplateListAdapter(templates!!)
                it.list.adapter = adapter
            })
        }
    }

    private class TemplateListAdapter internal constructor(templates: List<Template>) : SimpleListAdapter<Template>(templates) {
        override fun getItemId(position: Int): Long {
            return items[position].id
        }

        override fun newView(position: Int, parent: ViewGroup): View {
            return ListTemplateItemBinding.inflate(inflater, parent, false).root
        }

        override fun bindView(view: View, position: Int) {
            DataBindingUtil.bind<ListTemplateItemBinding>(view).let {
                it.template = items[position]
            }
        }
    }
}
