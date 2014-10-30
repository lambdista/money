package org.typesafely.money.example

import grizzled.slf4j.Logging

import org.typesafely.money.Currency._
import org.typesafely.money.{Currency, Money, Conversion}
import org.typesafely.money.Implicits._


/**
 *
 *
 * @author Alessandro Lacava 
 * @since 2014-10-27
 */
object Main extends Logging {

  def main(args: Array[String]): Unit = {
    implicit val conversion: Conversion = Map(
      (GBP, EUR) -> 1.270,
      (EUR, USD) -> 1.268,
      (GBP, USD) -> 1.611
    )

    val a = 100.001(USD) + 200(EUR) to GBP
    info(s"a: $a")

    val b: Money = 100(USD) + 210.4(EUR) to EUR
    info(s"b: $b")

    val c = 100.001(USD) + 200(EUR)
    val d = c(GBP)
    info(s"c: $d")

    val e = 100(USD) + 23.560
    info(s"e: $e")

    val f = 100(USD) * 23
    info(s"f: $f")

    val usd = Currency("USD")

    val g = 100(usd) * 23(EUR)
    info(s"g: $g")

    val h = 100(USD) / 23
    info(s"h: $h")

  }

}
