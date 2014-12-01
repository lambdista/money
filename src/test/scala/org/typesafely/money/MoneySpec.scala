package org.typesafely.money

import org.specs2.Specification
import org.specs2.specification.Fragments

import org.typesafely.money.Currency._
import org.typesafely.money.Implicits._

/**
 *
 *
 * @author Alessandro Lacava 
 * @since 2014-11-28
 */
class MoneySpec extends Specification {

  implicit val conversion: Conversion = Map(
    (GBP, EUR) -> 1.270,
    (EUR, USD) -> 1.268,
    (GBP, USD) -> 1.611
  )

  override def is: Fragments = s2"""
    Test for the Money class using the following conversions:

      (GBP, EUR) -> 1.270
      (EUR, USD) -> 1.268
      (GBP, USD) -> 1.611
      -------------------------------------------------------------------------------

      100(USD) + 200(USD) === 300(USD) must be true                               $e1

      (100.001(USD) + 200(EUR) to GBP) === 219.49162(GBP) must be true            $e2

      (100(USD) + 210.4(EUR) to EUR) === 289.26435(EUR) must be true              $e3

      exprToGBP === 219.49162(GBP) must be true                                   $e4

      100(USD) + 23.560 === 123.56(USD) must be true                              $e5

      100(USD) * 23 === 2300(USD) must be true                                    $e6

      100(usd) * 23(eur) === 2916.4(USD) must be true                             $e7

      100(USD) / 23 === 4.34783(USD) must be true                                 $e8

      100(USD) > 90(EUR) must be false                                            $e9

  """

  def e1 = 100(USD) + 200(USD) === 300(USD) must beTrue

  def e2 = (100.001(USD) + 200(EUR) to GBP).round(5) === 219.49162(GBP) must beTrue

  def e3 = (100(USD) + 210.4(EUR) to EUR).round(5) === 289.26435(EUR) must beTrue

  val expr = 100.001(USD) + 200(EUR)
  val exprToGBP = expr(GBP).round(5)

  def e4 = exprToGBP === 219.49162(GBP) must beTrue

  def e5 = 100(USD) + 23.560 === 123.56(USD) must beTrue

  def e6 = 100(USD) * 23 === 2300(USD) must beTrue

  val usd = Currency("$")
  val eur = Currency("€")

  def e7 = 100(usd) * 23(eur) === 2916.4(USD) must beTrue

  def e8 = (100(USD) / 23).round(5) === 4.34783(USD) must beTrue

  def e9 = 100(USD) > 90(EUR) must beFalse

}
