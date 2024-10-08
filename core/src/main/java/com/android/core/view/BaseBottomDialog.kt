package com.android.core.view

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.android.core.R
import com.android.core.utility.cast
import com.android.core.viewmodel.BaseViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.reflect.ParameterizedType

@ExperimentalCoroutinesApi
abstract class BaseBottomDialog<T : ViewBinding, ViewModel : BaseViewModel> :
    BottomSheetDialogFragment() {


    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean = false
    ): T

    lateinit var bottomSheetDialog: BottomSheetDialog

    lateinit var viewModel: ViewModel


    lateinit var binding: T


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
        bottomSheetDialog =
            super.onCreateDialog(savedInstanceState) as BottomSheetDialog


        bottomSheetDialog.setOnShowListener { dialog: DialogInterface ->
            val dialogc = dialog as BottomSheetDialog

            val bottomSheet =
                dialogc.findViewById<FrameLayout>(R.id.design_bottom_sheet)
            val bottomSheetBehavior: BottomSheetBehavior<*> =
                BottomSheetBehavior.from<FrameLayout?>(bottomSheet!!)
            bottomSheetBehavior.peekHeight =
                (Resources.getSystem().displayMetrics.heightPixels * 0.80).toInt()


            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED


        }

        return bottomSheetDialog
    }


    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    @ExperimentalCoroutinesApi
    fun showToast(message: String) {
        (activity as? BaseActivity<*, *>)?.showToast(message)

    }

    open fun close() {
        bottomSheetDialog.dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)

        val clazz = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1]
            .cast<Class<ViewModel>>()

        if (!::viewModel.isInitialized) {
            viewModel = ViewModelProvider(this).get(clazz)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = getViewBinding(inflater, container)

        return binding.root
    }

    open fun initUI() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("FRAGMENT PAGE STATE", "${this.javaClass.simpleName} onViewCreated")


        setReceivers()
        setListeners()

        initUI()

    }

    open fun setReceivers() {

    }

    open fun setListeners() {}


}
