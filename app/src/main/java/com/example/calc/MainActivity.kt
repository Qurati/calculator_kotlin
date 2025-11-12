package com.example.calc

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var firstNumber: String = "0"
    private var currentOperation: String = ""
    private var isNewOperation: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeViews()
    }

    private fun initializeViews() {
        val edit: EditText = findViewById(R.id.edit)
        val result: TextView = findViewById(R.id.tv1)
        val operation: TextView = findViewById(R.id.tv2)

        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)

        val btnDiv: Button = findViewById(R.id.btnDiv)
        val btnMul: Button = findViewById(R.id.btnMul)
        val btnSub: Button = findViewById(R.id.btnSub)
        val btnEq: Button = findViewById(R.id.btnEq)
        val btnAdd: Button = findViewById(R.id.btnAdd)
        val btnComma: Button = findViewById(R.id.btnComma)
        val btnClear: Button? = findViewById(R.id.btnClear)

        val numberClickListener = android.view.View.OnClickListener { view ->
            val button = view as Button
            val number = button.text.toString()

            if (isNewOperation) {
                edit.text = Editable.Factory.getInstance().newEditable(number)
                isNewOperation = false
            } else {
                val currentText = edit.text.toString()
                if (currentText == "0") {
                    edit.text = Editable.Factory.getInstance().newEditable(number)
                } else {
                    edit.text = Editable.Factory.getInstance().newEditable(currentText + number)
                }
            }
        }

        btn0.setOnClickListener(numberClickListener)
        btn1.setOnClickListener(numberClickListener)
        btn2.setOnClickListener(numberClickListener)
        btn3.setOnClickListener(numberClickListener)
        btn4.setOnClickListener(numberClickListener)
        btn5.setOnClickListener(numberClickListener)
        btn6.setOnClickListener(numberClickListener)
        btn7.setOnClickListener(numberClickListener)
        btn8.setOnClickListener(numberClickListener)
        btn9.setOnClickListener(numberClickListener)

        btnComma.setOnClickListener {
            val currentText = edit.text.toString()
            if (!currentText.contains('.')) {
                edit.text = Editable.Factory.getInstance().newEditable(currentText + '.')
            }
        }

        val operationClickListener = android.view.View.OnClickListener { view ->
            val button = view as Button
            firstNumber = edit.text.toString()
            currentOperation = button.text.toString()
            operation.text = currentOperation
            isNewOperation = true
        }


        btnClear?.setOnClickListener {
            edit.text = Editable.Factory.getInstance().newEditable("0")
        }

        btnAdd.setOnClickListener(operationClickListener)
        btnSub.setOnClickListener(operationClickListener)
        btnMul.setOnClickListener(operationClickListener)
        btnDiv.setOnClickListener(operationClickListener)

        btnEq.setOnClickListener {
            if (currentOperation.isNotEmpty() && firstNumber.isNotEmpty()) {
                val secondNumber = edit.text.toString()

                val resultValue = when (currentOperation) {
                    "+" -> firstNumber.toDouble() + secondNumber.toDouble()
                    "-" -> firstNumber.toDouble() - secondNumber.toDouble()
                    "*" -> firstNumber.toDouble() * secondNumber.toDouble()
                    "/" -> {
                        if (secondNumber.toDouble() != 0.0)
                            firstNumber.toDouble() / secondNumber.toDouble() else TODO()
                    }

                    else -> 0.0
                }

                val Result = if (resultValue % 1 == 0.0) {
                    resultValue.toInt().toString()
                } else {
                    resultValue.toString()
                }

                result.text = Result
                edit.text = Editable.Factory.getInstance().newEditable(Result)
                operation.text = ""
                currentOperation = ""
                isNewOperation = true

            }
        }

    }

}