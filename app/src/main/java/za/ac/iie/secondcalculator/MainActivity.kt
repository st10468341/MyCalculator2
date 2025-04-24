package za.ac.iie.secondcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var inputText: EditText
    private lateinit var resultText: TextView

    private var firstNumber = 0.0
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputText = findViewById(R.id.inputText)
        resultText = findViewById(R.id.resultText)

        val btnAdd: Button = findViewById(R.id.btnAdd)
        val btnSubtract: Button = findViewById(R.id.btnSubtract)
        val btnMultiply: Button = findViewById(R.id.btnMultiply)
        val btnDivide: Button = findViewById(R.id.btnDivide)
        val btnEquals: Button = findViewById(R.id.btnEquals)
        val btnClear: Button = findViewById(R.id.btnClear)
        val btnSqrt: Button = findViewById(R.id.btnSqrt)
        val btnSquare: Button = findViewById(R.id.btnSquare)

        btnAdd.setOnClickListener { handleOperator("+") }
        btnSubtract.setOnClickListener { handleOperator("-") }
        btnMultiply.setOnClickListener { handleOperator("*") }
        btnDivide.setOnClickListener { handleOperator("/") }

        btnEquals.setOnClickListener { calculateResult() }
        btnClear.setOnClickListener { clearInput() }
        btnSqrt.setOnClickListener { calculateSqrt() }
        btnSquare.setOnClickListener { calculateSquare() }
    }

    private fun handleOperator(op: String) {
        val input = inputText.text.toString()
        if (input.isNotEmpty()) {
            firstNumber = input.toDoubleOrNull() ?: run {
                resultText.text = "Invalid input"
                return
            }
            operator = op
            inputText.setText("")
        }
    }

    private fun calculateResult() {
        val input = inputText.text.toString()
        if (input.isNotEmpty()) {
            val secondNumber = input.toDoubleOrNull() ?: run {
                resultText.text = "Invalid input"
                return
            }

            val result = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> {
                    if (secondNumber == 0.0) {
                        resultText.text = "Can't divide by 0"
                        return
                    } else {
                        firstNumber / secondNumber
                    }
                }
                else -> {
                    resultText.text = "Select operation"
                    return
                }
            }

            resultText.text = result.toString()
            inputText.setText("")
        }
    }

    private fun clearInput() {
        inputText.setText("")
        resultText.text = ""
    }

    private fun calculateSqrt() {
        val input = inputText.text.toString()
        if (input.isNotEmpty()) {
            val number = input.toDoubleOrNull() ?: run {
                resultText.text = "Invalid input"
                return
            }
            resultText.text = sqrt(number).toString()
            inputText.setText("")
        }
    }

    private fun calculateSquare() {
        val input = inputText.text.toString()
        if (input.isNotEmpty()) {
            val number = input.toDoubleOrNull() ?: run {
                resultText.text = "Invalid input"
                return
            }
            resultText.text = (number * number).toString()
            inputText.setText("")
        }
    }
}
