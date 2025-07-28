package com.example.inchestocentimeters

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val inchValue = 2.54

    private var isInchesToCm = true

    private lateinit var convertButton: Button
    private lateinit var switchUnitButton: Button
    private lateinit var enterInput: EditText
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        convertButton = findViewById(R.id.buttonConvert)
        switchUnitButton = findViewById(R.id.switchUnit)
        enterInput = findViewById(R.id.editTextInput)
        resultText = findViewById(R.id.resultText)

        switchUnitButton.setOnClickListener {
            isInchesToCm = !isInchesToCm
            if(isInchesToCm) enterInput.hint = getString(R.string.inches_hint)
            else enterInput.hint = getString(R.string.cm_hint)
        }

        convertButton.setOnClickListener {
            val input = enterInput.text.toString()
            if(input.isEmpty()) {
                resultText.text = getString(R.string.result_text_message)
                resultText.setTextColor(Color.RED)
            } else {
                var result = 0.0
                if(isInchesToCm) {
                    result = inchesToCm(input.toDouble())
                    resultText.text = "${result.toString()} cm"
                } else {
                    result = cmToInches(input.toDouble())
                    resultText.text = "${result.toString()} inches"
                }
                resultText.setTextColor(Color.GREEN)
            }
            resultText.visibility = View.VISIBLE
        }

    }

    fun inchesToCm(inches: Double): Double {
        return inches * inchValue
    }

    fun cmToInches(cm: Double): Double {
        return cm / inchValue
    }

}