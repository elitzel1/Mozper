package com.example.android.mozper.presentation.base

import android.os.Bundle
import android.view.View
import com.example.android.mozper.R
import com.example.android.mozper.databinding.ActivityContainerBinding
import com.example.android.mozper.presentation.utils.SceneTransition

open class MozperActivity : DaggerBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getBindingRootView())
    }

    protected open fun getBindingRootView(): View {
        val binding = ActivityContainerBinding.inflate(layoutInflater)
        return binding.root
    }

    protected fun showFragmentView(
        fragmentView: DaggerBaseFragment,
        tag: String? = null,
        replace: Boolean = false,
        addToBackStack: Boolean = false,
        sceneTransition: SceneTransition = SceneTransition.NOTHING,
        shareElements: List<Pair<View, String>>? = null
    ) {
        val toReplace = replace || shareElements != null
        val transaction = supportFragmentManager.beginTransaction()

        when (sceneTransition) {
            SceneTransition.PUSH -> {
                transaction.setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
            }
            else -> {
                // Do nothing
            }
        }
        if (toReplace) {
            transaction.replace(R.id.fragmentContainer, fragmentView, tag)
        } else {
            transaction.add(R.id.fragmentContainer, fragmentView, tag)
        }
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }
}