package com.example.pfc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {

    private lateinit var ageEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var sexM: RadioButton
    private lateinit var sexW: RadioButton
    private lateinit var answerText : TextView

    private var ageValue: Double = 0.0
    private var heightValue: Double = 0.0
    private var weightValue: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.design)

        ageEditText = findViewById(R.id.age)
        heightEditText = findViewById(R.id.height)
        weightEditText = findViewById(R.id.weight)
        sexM = findViewById(R.id.sexM)
        sexW = findViewById(R.id.sexW)
        answerText = findViewById(R.id.answerText)

        val buttonClick = findViewById<Button>(R.id.countPFCBtn)

        buttonClick.setOnClickListener {
            calculateBMR()
        }
    }

    private fun calculateBMR() {
        ageValue = ageEditText.text.toString().toDoubleOrNull() ?: 0.0
        heightValue = heightEditText.text.toString().toDoubleOrNull() ?: 0.0
        weightValue = weightEditText.text.toString().toDoubleOrNull() ?: 0.0

        var BMR = 0.0

        if (sexM.isChecked) {
            BMR = 88.362 + (13.397 * weightValue) + (4.799 * heightValue) - (5.677 * ageValue)
        } else if (sexW.isChecked) {
            BMR = 447.593 + (9.247 * weightValue)  + (3.098 * heightValue) - (4.330 * ageValue)
        }

        val protein = (BMR * 0.2 / 4).toInt()
        val fat = (BMR * 0.3/9).toInt()
        val carbohydrates = (BMR * 0.5/4).toInt()

        answerText.text = "Суточная норма калорий: ${BMR.toInt()} калорий, белки $protein гр., жиры $fat " +
                "гр., углеводы $carbohydrates гр."
    }
}