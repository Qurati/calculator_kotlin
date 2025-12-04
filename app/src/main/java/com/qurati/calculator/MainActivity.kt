package com.qurati.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.qurati.calc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var firstNum = ""
    var act = ""

    fun action(_act: String, num: String): String {
        act = _act
        firstNum = num
        return act
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            btn0.setOnClickListener { tvRes.text = tvRes.text.toString() + "0" }
            btn1.setOnClickListener { tvRes.text = tvRes.text.toString() + "1" }
            btn2.setOnClickListener { tvRes.text = tvRes.text.toString() + "2" }
            btn3.setOnClickListener { tvRes.text = tvRes.text.toString() + "3" }
            btn4.setOnClickListener { tvRes.text = tvRes.text.toString() + "4" }
            btn5.setOnClickListener { tvRes.text = tvRes.text.toString() + "5" }
            btn6.setOnClickListener { tvRes.text = tvRes.text.toString() + "6" }
            btn7.setOnClickListener { tvRes.text = tvRes.text.toString() + "7" }
            btn8.setOnClickListener { tvRes.text = tvRes.text.toString() + "8" }
            btn9.setOnClickListener { tvRes.text = tvRes.text.toString() + "9" }
            btnClear.setOnClickListener { tvRes.text = "" }

            btnDiv.setOnClickListener {
                if (!tvRes.text.isBlank()) {
                    tvRes.hint = action(btnDiv.text.toString(), tvRes.text.toString())
                    tvRes.text = ""
                } else {
                    Toast.makeText(this@MainActivity, "Введите число!", Toast.LENGTH_LONG).show()
                }
            }
            btnMul.setOnClickListener {
                if (!tvRes.text.isBlank()) {
                    tvRes.hint = action(btnMul.text.toString(), tvRes.text.toString())
                    tvRes.text = ""
                } else {
                    Toast.makeText(this@MainActivity, "Введите число!", Toast.LENGTH_LONG).show()
                }
            }
            btnAdd.setOnClickListener {
                if (!tvRes.text.isBlank()) {
                    tvRes.hint = action(btnAdd.text.toString(), tvRes.text.toString())
                    tvRes.text = ""
                } else {
                    Toast.makeText(this@MainActivity, "Введите число!", Toast.LENGTH_LONG).show()
                }
            }
            btnSub.setOnClickListener {
                if (!tvRes.text.isBlank()) {
                    tvRes.hint = action(btnSub.text.toString(), tvRes.text.toString())
                    tvRes.text = ""
                } else {
                    Toast.makeText(this@MainActivity, "Введите число!", Toast.LENGTH_LONG).show()
                }
            }

            btnComma.setOnClickListener {
                if (!tvRes.text.toString().contains(".")){
                    tvRes.text = tvRes.text.toString() + "."
                }
            }
            btnEq.setOnClickListener {
                if (!tvRes.text.isBlank()) {
                    val res = when (act) {
                        "+" -> firstNum.toDouble() + tvRes.text.toString().toDouble()
                        "-" -> firstNum.toDouble() - tvRes.text.toString().toDouble()
                        "/" -> {
                            if (tvRes.text.toString() != "0") {
                                firstNum.toDouble() / tvRes.text.toString().toDouble()
                            } else {
                                0.0
                            }
                        }

                        "*" -> firstNum.toDouble() * tvRes.text.toString().toDouble()
                        else -> 0.0
                    }
                    try {
                        val Result = if (res % 1 == 0.0) {
                            res.toInt().toString()
                        } else {
                            res.toString()
                        }
                        tvRes.text = Result
                        tvRes.hint = "0"
                    } catch (e: Exception) {
                        tvRes.hint = "Ошибка!"
                        tvRes.text = ""
                        Toast.makeText(this@MainActivity, e.toString(), Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Введите число!", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}