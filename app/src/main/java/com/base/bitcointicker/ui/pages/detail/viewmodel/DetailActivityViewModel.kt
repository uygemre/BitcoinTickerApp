package com.base.bitcointicker.ui.pages.detail.viewmodel

import com.base.bitcointicker.ui.base.viewmodel.BaseActivityViewModel
import com.base.bitcointicker.ui.pages.detail.repository.DetailActivityRepository
import javax.inject.Inject

class DetailActivityViewModel @Inject constructor(
    private val repository: DetailActivityRepository
) : BaseActivityViewModel() {
}