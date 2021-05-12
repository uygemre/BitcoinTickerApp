package com.base.bitcointicker.ui.pages.login.viewmodel

import com.base.bitcointicker.ui.base.viewmodel.BaseFragmentViewModel
import com.base.bitcointicker.ui.pages.login.repository.LoginFragmentRepository
import javax.inject.Inject

class LoginFragmentViewModel @Inject constructor(
    private val repository: LoginFragmentRepository
) : BaseFragmentViewModel() {
}