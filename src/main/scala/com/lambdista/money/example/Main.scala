package com.lambdista.money.example

import com.lambdista.money.Currency._
import com.lambdista.money._

/**
 * Main example
 *
 * @author Alessandro Lacava 
 * @since 2014-10-27
 */
object Main {

  def main(args: Array[String]): Unit = {

    implicit val conversion: Conversion = Map(
      (GBP, EUR) -> 1.270,
      (EUR, USD) -> 1.268,
      (GBP, USD) -> 1.611
    )

    val sumAndConversion1 = 100.001(USD) + 200(EUR) to GBP
    println(s"sumAndConversion1: $sumAndConversion1")

    val sumAndConversion2: Money = 100(USD) + 210.4(EUR) to EUR
    println(s"sumAndConversion2: $sumAndConversion2")

    val sum = 100.001(USD) + 200(EUR)
    val simpleConversion = sum(GBP)
    println(s"simpleConversion: $simpleConversion")

    val sumWithSimpleNumber = 100(USD) + 23.560
    println(s"sumWithSimpleNumber: $sumWithSimpleNumber")

    val multiplicationWithSimpleNumber = 100(USD) * 23
    println(s"multiplicationWithSimpleNumber: $multiplicationWithSimpleNumber")

    val usd = Currency("USD")

    val multiplication = 100(usd) * 23(EUR)
    println(s"multiplication: $multiplication")

    val divisionWithSimpleNumber = 100(USD) / 23
    println(s"divisionWithSimpleNumber: $divisionWithSimpleNumber")

    val comparison = 100(USD) > 90(EUR)
    println(s"100 USD > 90 EUR? $comparison")

  }

}
