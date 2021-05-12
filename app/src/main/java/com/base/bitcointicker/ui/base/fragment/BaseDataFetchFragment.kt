package com.base.bitcointicker.ui.base.fragment

import android.os.Bundle
import android.view.View
import com.base.bitcointicker.ui.base.viewmodel.BaseFragmentViewModel

abstract class BaseDataFetchFragment<VM> :
    BaseViewModelFragment<VM>() where VM : BaseFragmentViewModel {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
