package com.base.bitcointicker.ui.pages.register.viewmodel

import com.base.bitcointicker.ui.base.viewmodel.BaseFragmentViewModel
import com.base.bitcointicker.ui.pages.register.repository.RegisterFragmentRepository
import javax.inject.Inject

class RegisterFragmentViewModel @Inject constructor(
    private val repository: RegisterFragmentRepository
) : BaseFragmentViewModel() {
}