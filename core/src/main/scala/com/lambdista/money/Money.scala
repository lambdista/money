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

import scala.math.BigDecimal.RoundingMode
import scala.math.BigDecimal.RoundingMode.RoundingMode

import com.lambdista.money.{toFormattedString => bigDecimalToFormattedString}

/**
  * This is the main class of the lib. A Money is represented by its `amount` and `currency`.
  *
  * @param amount the amount of this money
  * @param currency the currency for this money
  * @param converter the `Converter` to use
  *
  * @author Alessandro Lacava
  * @since 2014-10-27
  */
case class Money(amount: BigDecimal, currency: Currency)(implicit converter: Converter) extends Ordered[Money] {
  /**
    * Converts this money to another money represented using otherCurrency
    *
    * @param thatCurrency the currency to convert this money to
    * @return a new object where its currency is expressed in terms of otherCurrency
    */
  def apply(thatCurrency: Currency): Money = {
    val rate = converter.convert(currency, thatCurrency)
    Money(amount * rate, thatCurrency)
  }

  /**
    * Just an alias for `apply`
    */
  val to: Currency => Money = apply(_)

  /**
    * Adds this money to that. The result is expressed in terms of this money's currency.
    *
    * @param that the money to sum to this money
    * @return a new object which is the result of summing this money to that after converting that to this
    *         money's currency
    */
  def +(that: Money): Money = performOperation(that, _ + _)

  /**
    * Adds amount to this money.
    *
    * @param that the amount to sum to this money
    * @return a new object which is the result of summing amount to this money
    */
  def +(that: BigDecimal): Money = this + Money(that, this.currency)

  /**
    * Subtracts that from this money. The result is expressed in terms of this money's currency.
    *
    * @param that the money to subtract from this money
    * @return a new object which is the result of subtracting that from this money after converting that to this
    *         money's currency
    */
  def -(that: Money): Money = performOperation(that, _ - _)

  /**
    * Subtracts amount from this money.
    *
    * @param that the amount to sum to this money
    * @return a new object which is the result of summing amount to this money
    */
  def -(that: BigDecimal): Money = this - Money(that, this.currency)

  /**
    * Multiplies that by this money. The result is expressed in terms of this money's currency.
    *
    * @param that the money to multiply by this money
    * @return a new object which is the result of multiplying that by this money after converting that to this
    *         money's currency
    */
  def *(that: Money): Money = performOperation(that, _ * _)

  /**
    * Multiplies amount by this money.
    *
    * @param that the amount to multiply by this money
    * @return a new object which is the result of multiplying amount to this money
    */
  def *(that: BigDecimal): Money = this * Money(that, this.currency)

  /**
    * Divides this money by that. The result is expressed in terms of this money's currency.
    *
    * @param that the money to use ad divisor
    * @return a new object which is the result of dividing this money (dividend) by that (divisor) after converting
    *         that to this money's currency
    */
  def /(that: Money): Money = performOperation(that, _ / _)

  /**
    * Divides amount by this money.
    *
    * @param that the amount to multiply by this money
    * @return a new object which is the result of multiplying amount to this money
    */
  def /(that: BigDecimal): Money = this / Money(that, this.currency)

  /**
    * Compares this `Money` with `that`. The comparison is made between this amount and `that`
    *
    * @param that the amount to compare this object with.
    * @return true if this amount is not equal to `that`, false otherwise.
    */
  def !==(that: BigDecimal): Boolean = !(this === that)

  /**
    * Compares this `Money` with `that`. The comparison is made between this amount and `that`
    *
    * @param that the amount to compare this object with.
    * @return true if this amount is equal to `that`, false otherwise.
    */
  def ===(that: BigDecimal): Boolean = this.amount == that

  /**
    * Rounds this `Money` to the given number of `decimalDigits` using the provided `roundingMode`
    *
    * @param decimalDigits the number of decimal digits to keep
    * @return a new `Money` object whose number of decimal digits is `decimalDigits`
    */
  def round(decimalDigits: Int, roundingMode: RoundingMode = RoundingMode.HALF_DOWN): Money =
    Money(amount.setScale(decimalDigits, roundingMode), currency)

  /**
    * @return the string representation of this money which has, at most, 5 decimal digits. If you need to customize the
    *         number of decimal digits use `toFormattedString` instead
    */
  override def toString: String = toFormattedString()

  /**
    * Formats this money object using a number of decimal digits equals to the decimalDigits param, which defaults to 5.
    *
    * @param decimalDigits the number of decimal digits to include
    * @return a formatted string representing this money object
    */
  def toFormattedString(decimalDigits: Int = 5): String =
    s"${bigDecimalToFormattedString(amount, decimalDigits)} ${currency.toString}"

  private def performOperation(that: Money, operation: (BigDecimal, BigDecimal) => BigDecimal): Money =
    that match {
      case Money(v, c) if c == currency => Money(operation(amount, v), currency)
      case Money(v, c)                  => performOperation(that.to(currency), operation)
    }

  /**
    * Compares this `Money` object with `that`.
    *
    * @param that the other `Money` object
    * @return a number < 1 if this < that, a number > 1 if this > that, 0 if they are equal.
    */
  override def compare(that: Money): Int = {
    val thatAmount = converter.convert(that.currency, this.currency) * that.amount

    this.amount compare thatAmount
  }
}
