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
package money

import org.specs2.Specification
import org.specs2.specification.Fragments

/**
 * Specification test for the [[money.Money]] class
 *
 * @author Alessandro Lacava (@lambdista)
 * @since 2014-11-28
 */
class MoneySpec extends Specification {
  val conversion: Conversion = Map(
    (EUR, USD) -> 1.13,
    (EUR, GBP) -> 0.71,
    (USD, EUR) -> 0.88,
    (USD, GBP) -> 0.63,
    (GBP, EUR) -> 1.40,
    (GBP, USD) -> 1.59
  )

  implicit val converter = Converter(conversion)

  val expr = 100.001(USD) + 200(EUR)
  val exprToGBP = (expr to GBP).round(5)
  val usd = Currency("$")
  val eur = Currency("€")

  override def is: Fragments = s2"""
    Test for the Money class using the following conversions:
   |    (EUR, USD) -> 1.13,
   |    (EUR, GBP) -> 0.71,
   |    (USD, EUR) -> 0.88,
   |    (USD, GBP) -> 0.63,
   |    (GBP, EUR) -> 1.40,
   |    (GBP, USD) -> 1.59
      -------------------------------------------------------------------------------

      100(USD) + 200(USD) == 300(USD) must be true                               $e1

      (100.001(USD) + 200(EUR) to GBP) == 210.66733(GBP) must be true            $e2

      (100(USD) + 210.4(EUR) to EUR) == 302.99259(EUR) must be true              $e3

      exprToGBP == 210.66733(GBP) must be true                                   $e4

      100(USD) + 23.560 == 123.56(USD) must be true                              $e5

      100(USD) * 23 == 2300(USD) must be true                                    $e6

      100(usd) * 23(eur) == 2484(USD) must be true                               $e7

      testNumericSumWithMoney(100(USD), 200(EUR)) == 316(USD) must be true       $e8

      100(USD) > 99(EUR) must be false                                           $e9

  """

  def e1 = 100(USD) + 200(USD) == 300(USD) must beTrue

  def e2 = (100.001(USD) + 200(EUR) to GBP).round(5) == 205.38063(GBP) must beTrue

  def e3 = (100(USD) + 210.4(EUR) to EUR).round(5) == 297.22176(EUR) must beTrue

  def e4 = exprToGBP == 205.38063(GBP) must beTrue

  def e5 = 100(USD) + 23.560 == 123.56(USD) must beTrue

  def e6 = 100(USD) * 23 == 2300(USD) must beTrue

  def e7 = (100(USD) / 23).round(5) == 4.34783(USD) must beTrue

  def testNumericSumWithMoney[T : Numeric](a: T, b: T): T = implicitly[Numeric[T]].plus(a, b)

  def e8 = testNumericSumWithMoney(100(USD), 200(EUR)) == 326(USD) must beTrue

  def e9 = 100(USD) > 99(EUR) must beFalse
}
