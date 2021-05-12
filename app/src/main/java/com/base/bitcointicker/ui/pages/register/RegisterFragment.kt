package com.base.bitcointicker.ui.pages.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.base.bitcointicker.R
import com.base.bitcointicker.ui.base.fragment.BaseViewModelFragment
import com.base.bitcointicker.ui.common.enums.IntentBundleKeyEnum
import com.base.bitcointicker.ui.pages.detail.DetailActivity
import com.base.bitcointicker.ui.pages.register.viewmodel.RegisterFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.btn_register
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.*

class RegisterFragment() : BaseViewModelFragment<RegisterFragmentViewModel>() {

    override val viewModelClass = RegisterFragmentViewModel::class.java
    override val layoutViewRes = R.layout.fragment_register

    lateinit var authorization: FirebaseAuth
    private val bundle = Bundle()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authorization = FirebaseAuth.getInstance()
        btn_register.setOnClickListener {
            val email = edt_email.text.toString().toLowerCase(Locale.getDefault())
            val password = edt_password.text.toString().toLowerCase(Locale.getDefault())
            authorization.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    requireActivity()
                ) {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "registration is success",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(activity, DetailActivity::class.java)
                        bundle.putString(
                            IntentBundleKeyEnum.DETAIL_KEY.toString(),
                            IntentBundleKeyEnum.DETAIL_LOGIN.toString()
                        )
                        intent.putExtras(bundle)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "please check your mail",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}