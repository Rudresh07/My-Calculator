package com.example.calculator

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonAllclr.setOnClickListener {
            binding.TVInput.text = ""
            binding.TVOutput.text=""
        }

        binding.button1.setOnClickListener {
            binding.TVInput.append("1")
        }

        binding.button2.setOnClickListener {
            binding.TVInput.append("2")
        }

        binding.button3.setOnClickListener {
            binding.TVInput.append("3")
        }

        binding.button4.setOnClickListener {
            binding.TVInput.append("4")
        }

        binding.button5.setOnClickListener {
            binding.TVInput.append("5")
        }

        binding.button6.setOnClickListener {
            binding.TVInput.append("6")
        }

        binding.button7.setOnClickListener {
            binding.TVInput.append("7")
        }

        binding.button8.setOnClickListener {
            binding.TVInput.append("8")
        }

        binding.button9.setOnClickListener {
            binding.TVInput.append("9")
        }

        binding.button0.setOnClickListener {
            binding.TVInput.append("0")
        }

        binding.buttonadd.setOnClickListener {
            val currentText = binding.TVInput.text.toString()
            if (currentText.isNotEmpty()) {
                // Get the last character
                val lastChar = currentText.last()
                // Check if the last character is an operator
                if (isSymbol(lastChar)) {
                    // Replace the last character with the new operator
                    binding.TVInput.text = currentText.substring(0, currentText.length - 1) + "+"
                } else {
                    // Append the new operator
                    binding.TVInput.append("+")
                }
            }
        }

        binding.buttonsub.setOnClickListener {
            val currentText = binding.TVInput.text.toString()
            if (currentText.isNotEmpty()) {
                val lastChar = currentText.last()
                if (isSymbol(lastChar)) {
                    binding.TVInput.text = currentText.substring(0, currentText.length - 1) + "-"
                } else {
                    binding.TVInput.append("-")
                }
            }
        }

        binding.buttonmul.setOnClickListener {
            val currentText = binding.TVInput.text.toString()
            if (currentText.isNotEmpty()) {
                val lastChar = currentText.last()
                if (isSymbol(lastChar)) {
                    binding.TVInput.text = currentText.substring(0, currentText.length - 1) + "*"
                } else {
                    binding.TVInput.append("*")
                }
            }
        }

        binding.buttondivide.setOnClickListener {
            val currentText = binding.TVInput.text.toString()
            if (currentText.isNotEmpty()) {
                val lastChar = currentText.last()
                if (isSymbol(lastChar)) {
                    binding.TVInput.text = currentText.substring(0, currentText.length - 1) + "/"
                } else {
                    binding.TVInput.append("/")
                }
            }
        }

        binding.buttonper.setOnClickListener {
            val currentText = binding.TVInput.text.toString()
            if (currentText.isNotEmpty()) {
                val lastChar = currentText.last()
                if (isSymbol(lastChar)) {
                    binding.TVInput.text = currentText.substring(0, currentText.length - 1) + "%"
                } else {
                    binding.TVInput.append("%")
                }
            }
        }



        binding.buttondot.setOnClickListener {
            val currentText = binding.TVInput.text.toString()
            if (currentText.isNotEmpty() && !isSymbol(currentText.last())) {
                binding.TVInput.append(".")
            }
        }


        binding.buttonequal.setOnClickListener {
            val expressionStr = binding.TVInput.text.toString()
            try {
                // Try to evaluate the expression
                val expression = ExpressionBuilder(expressionStr).build()
                val result = expression.evaluate()

                // Check if the result is finite and not NaN
                if (!result.isInfinite() && !result.isNaN()) {
                    val longResult = result.toLong()
                    if (result == longResult.toDouble()) {
                        binding.TVOutput.text = longResult.toString()
                    } else {
                        binding.TVOutput.text = result.toString()
                    }
                } else {
                    // Handle division by zero case
                    binding.TVOutput.text = "Division by zero"
                }
            } catch (e: Exception) {
                // Handle other errors (e.g., invalid expression)
                binding.TVOutput.text = "Invalid expression"
            }
        }


        binding.buttonclr.setOnClickListener {
            val currentText = binding.TVInput.text.toString()
            if (currentText.isNotEmpty()) {
                // Remove the last character from the current text
                val newText = currentText.substring(0, currentText.length - 1)
                binding.TVInput.text = newText
            }
        }


    }
    fun isSymbol(char: Char): Boolean {
        return char == '+' || char == '-' || char == '*' || char == '/' || char == '%'
    }
}