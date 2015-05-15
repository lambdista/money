/*
 * Copyright 2014 Alessandro Lacava.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambdista.money

import org.specs2.Specification
import org.specs2.specification.Fragments

import com.lambdista.money.Currency._
import com.lambdista.money.syntax._

/**
 * Specification test for the [[com.lambdista.money.Money]] class
 *
 * @author Alessandro Lacava 
 * @since 2014-11-28
 */
class MoneySpec extends Specification {

  val conversion: Conversion = Map(
    (GBP, EUR) -> 1.39,
    (EUR, USD) -> 1.08,
    (GBP, USD) -> 1.50
  )

  implicit val converter = Converter(conversion)

  val expr = 100.001(USD) + 200(EUR)
  val exprToGBP = expr(GBP).round(5)
  val usd = Currency("$")
  val eur = Currency("€")

  override def is: Fragments = s2"""
    Test for the Money class using the following conversions:

      (GBP, EUR) -> 1.270
      (EUR, USD) -> 1.268
      (GBP, USD) -> 1.611
      -------------------------------------------------------------------------------

      100(USD) + 200(USD) === 300(USD) must be true                               $e1

      (100.001(USD) + 200(EUR) to GBP) === 210.66733(GBP) must be true            $e2

      (100(USD) + 210.4(EUR) to EUR) === 302.99259(EUR) must be true              $e3

      exprToGBP === 210.66733(GBP) must be true                                   $e4

      100(USD) + 23.560 === 123.56(USD) must be true                              $e5

      100(USD) * 23 === 2300(USD) must be true                                    $e6

      100(usd) * 23(eur) === 2484(USD) must be true                               $e7

      100(USD) / 23 === 4.34783(USD) must be true                                 $e8

      100(USD) > 99(EUR) must be false                                            $e9

  """

  def e1 = 100(USD) + 200(USD) === 300(USD) must beTrue

  def e2 = (100.001(USD) + 200(EUR) to GBP).round(5) === 210.66733(GBP) must beTrue

  def e3 = (100(USD) + 210.4(EUR) to EUR).round(5) === 302.99259(EUR) must beTrue

  def e4 = exprToGBP === 210.66733(GBP) must beTrue

  def e5 = 100(USD) + 23.560 === 123.56(USD) must beTrue

  def e6 = 100(USD) * 23 === 2300(USD) must beTrue

  def e7 = 100(usd) * 23(eur) === 2484(USD) must beTrue

  def e8 = (100(USD) / 23).round(5) === 4.34783(USD) must beTrue

  def e9 = 100(USD) > 99(EUR) must beFalse

}
