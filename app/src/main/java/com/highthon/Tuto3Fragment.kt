package com.highthon

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.CallbackManager
import kotlinx.android.synthetic.main.fragment_tuto3.view.*
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.facebook.login.LoginManager
import java.util.*


class Tuto3Fragment : Fragment() {
    private val callbackManager = CallbackManager.Factory.create()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tuto3, container, false)
        view.nextBtn.setOnClickListener {
//            arrayListOf("publish_actions", "user_posts", "pages_show_list", "manage_pages", "publish_pages")
            LoginManager
                    .getInstance()
                    .logInWithReadPermissions(this@Tuto3Fragment, Arrays.asList("user_status",  "user_posts", "pages_show_list"))
            LoginManager
                    .getInstance()
                    .logInWithPublishPermissions(this@Tuto3Fragment, Arrays.asList("publish_actions",  "manage_pages", "publish_pages"))
            LoginManager.getInstance().registerCallback(callbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(loginResult: LoginResult) {
                            Log.d("dudco", loginResult.accessToken.token.toString())
                            val intent = Intent(context, MainActivity::class.java)
                            intent.putExtra("token", loginResult.accessToken.token.toString())
                            startActivity(intent)
                            activity?.finish()
                        }

                        override fun onCancel() {
                            // App code
                        }

                        override fun onError(exception: FacebookException) {
                            // App code
                            exception.printStackTrace()
                        }
                    })

        }
        return view
    }

    companion object {
        fun create(): Tuto3Fragment {
            return Tuto3Fragment()
        }
    }
}