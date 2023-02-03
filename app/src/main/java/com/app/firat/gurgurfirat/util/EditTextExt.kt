package com.app.firat.gurgurfirat.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


fun EditText.onTextChanged(textChanged: (text: String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            textChanged.invoke(s.toString())
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    })
}

