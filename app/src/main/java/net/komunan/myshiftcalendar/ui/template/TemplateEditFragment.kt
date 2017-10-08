package net.komunan.myshiftcalendar.ui.template

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import hugo.weaving.DebugLog
import net.komunan.myshiftcalendar.databinding.FragmentTemplateEditBinding
import net.komunan.myshiftcalendar.util.BaseFragment
import timber.log.Timber

@DebugLog
class TemplateEditFragment : BaseFragment() {
    companion object {
        private val PARAM_ID = "id"

        @JvmStatic
        fun create(id: Long): TemplateEditFragment {
            return TemplateEditFragment().apply {
                arguments = Bundle().apply {
                    putLong(PARAM_ID, id)
                }
            }
        }
    }

    private lateinit var binding: FragmentTemplateEditBinding
    private lateinit var viewModel: FragmentTemplateEditViewModel
    private lateinit var validator: Validator

    @NotEmpty
    private lateinit var title: EditText
    private lateinit var place: EditText
    private lateinit var description: EditText

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, FragmentTemplateEditViewModel.Factory(arguments.getLong(PARAM_ID)))
                .get(FragmentTemplateEditViewModel::class.java)
        validator = Validator(this)
        return FragmentTemplateEditBinding.inflate(inflater!!, container, false).also {
            binding = it
            title = it.title
            place = it.place
            description = it.description
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.template.observe(this, Observer { template ->
            binding.let {
                it.template = template
                it.buttonAllDay.setOnCheckedChangedListener { _, isChecked ->
                    it.template!!.isAllDay = isChecked
                    it.begin.isEnabled = !isChecked
                    it.timeSeparator.isEnabled = !isChecked
                    it.end.isEnabled = !isChecked
                }
                it.begin.setOnClickListener { v -> Timber.e("%s", v) }
                it.end.setOnClickListener { v -> Timber.e("%s", v) }
                it.buttonCancel.setOnClickListener { moveToPrevious() }
                it.buttonSave.setOnClickListener { validator.validate() }
                it.buttonAllDay.isSelected = template!!.isAllDay
            }
        })

        validator.setValidationListener(object : Validator.ValidationListener {
            override fun onValidationSucceeded() {
                binding.template.save()
                moveToPrevious()
            }

            override fun onValidationFailed(errors: List<ValidationError>) {
                errors.forEach {
                    val view = it.view
                    val message = it.getCollatedErrorMessage(context)
                    if (view is EditText) {
                        view.error = message
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}
