package com.tam.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

       var lastNumeric : Boolean = false
       var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view:View){
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View){
        tvInput.text=""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view : View){
        if(lastNumeric && !lastDot) {
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View){
        if(lastNumeric) {
            var tvValue = tvInput.text.toString()
            var prefix = ""
            try {

                   if(tvValue.startsWith("-")){
                       prefix = "-"
                       tvValue = tvValue.substring(1)
                   }
                if (tvValue.contains("-")) {
                    var splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeDotAfterZero((one.toDouble() - two.toDouble()).toString())
                }else   if (tvValue.contains("+")) {
                    var splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeDotAfterZero((one.toDouble() + two.toDouble()).toString())
                } else if (tvValue.contains("/")) {
                    var splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeDotAfterZero((one.toDouble() / two.toDouble()).toString())
                }else  if (tvValue.contains("*")) {
                    var splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeDotAfterZero((one.toDouble() * two.toDouble()).toString())
                }
            }catch (e:ArithmeticException){
            e.printStackTrace()
            }
        }

    }

    private fun removeDotAfterZero(value : String): String {
        var result = value
        if(value.contains(".0"))
            result = value.substring(0,result.length - 2)
            return result
    }

    fun operator(view: View){
        if(lastNumeric && !addOperator(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun addOperator(value : String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("+") || value.contains("*") || value.contains("-")
        }
    }

}


