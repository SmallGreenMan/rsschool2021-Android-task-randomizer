package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import kotlin.random.Random

class SecondFragment : Fragment(com.rsschool.android2021.R.layout.fragment_second), OnBackPressedListener {

    private var listener: SecondFragment.BackButtonListener? = null

    private var backButton: Button? = null
    private var result: TextView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as SecondFragment.BackButtonListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        result?.text = generate(min, max).toString()

        backButton?.setOnClickListener {
            // TODO: implement back
            backButtonAction ()
        }
    }
    override fun onBackPressed() {
        backButtonAction ()
    }
    fun backButtonAction () {
        listener?.onBackButtonListener(result?.text.toString().toInt())
    }

    private fun generate(min: Int, max: Int): Int {
        // TODO: generate random number
        return Random.nextInt(min, max)
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()

            // TODO: implement adding arguments

            return SecondFragment().apply {
                arguments = bundleOf(
                    MIN_VALUE_KEY to min,
                    MAX_VALUE_KEY to max
                )
            }
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }

    interface BackButtonListener {
        fun onBackButtonListener(result: Int)
    }
}