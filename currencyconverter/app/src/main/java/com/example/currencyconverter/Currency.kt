package com.example.currencyconverter

//classe contenant une fonction permettant de réaliser la conversion de devise
//Les devises sont basés sur l'euro ce qui nous fait une valeur "pivot", sur laquelle nous basé pour effectuer toutes les conversions possible
//à partir du moment ou les deux devises ont une valeur en euros
class Currency(var currencyISO: CurrencyISO) {
    fun convertTo(currency: CurrencyISO, amount: Double) : Double = amount * (currency.oneEuroAmount/this.currencyISO.oneEuroAmount)


}