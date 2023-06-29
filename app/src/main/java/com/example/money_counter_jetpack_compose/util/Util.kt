package com.example.money_counter_jetpack_compose.util

fun calculateTotalTip(totalBill: Double, tipPercentage: Int): Double {
    return if(totalBill>1 && totalBill.toString().isNotEmpty()) {
        (totalBill * tipPercentage) / 100
    }
    else {
        0.0
    }
}