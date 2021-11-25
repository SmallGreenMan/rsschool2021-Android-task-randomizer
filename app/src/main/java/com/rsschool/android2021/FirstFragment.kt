package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class  FirstFragment : Fragment() {

    private var listener: GenereteButtonListener? = null

    private var generateButton: Button? = null
    private var previousResult: TextView? = null

    private var minEditText: EditText? = null
    private var maxEditText: EditText? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as GenereteButtonListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        // TODO: val min = ...
        // TODO: val max = ...

        minEditText = view.findViewById(R.id.min_value)
        //val min = minEditText?.getText().toString().toInt()
        maxEditText = view.findViewById(R.id.max_value)
        //val max = maxEditText?.getText().toString().toInt()

        //minEditText.setOnCh

        generateButton?.setEnabled(false)
        generateButton?.setOnClickListener {
            // TODO: send min and max to the SecondFragment
            val min = minEditText?.text.toString().toInt()
            val max = maxEditText?.text.toString().toInt()
            listener?.onGenereteButtonListener(min,max)
        }

        minEditText?.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_UP)
                    chackMinAndMax(minEditText?.text.toString(), maxEditText?.text.toString())
                return false
            }
        })
        maxEditText?.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_UP)
                    chackMinAndMax(minEditText?.text.toString(), maxEditText?.text.toString())
                return false
            }
        })
    }


    private fun chackMinAndMax(min: String, max: String) {
        var rez = false
        try {
            val min = minEditText?.getText().toString().toInt()
            val max = maxEditText?.getText().toString().toInt()
            if (min <= max) rez = true
        } catch (e: Exception){
            Log.i("FirsFragment","FirsFragmentExaption = " + e.toString())
        }
        generateButton?.setEnabled(rez)
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }

    interface GenereteButtonListener {
        fun onGenereteButtonListener(min: Int, max: Int)
    }
}