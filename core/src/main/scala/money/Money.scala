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

import scala.math.BigDecimal.RoundingMode
import scala.math.BigDecimal.RoundingMode.RoundingMode

import money.{toFormattedString => bigDecimalToFormattedString}

/**
  * This is the main class of the lib. A Money is represented by its `amount` and `currency`.
  *
  * @param amount the amount of this money
  * @param currency the currency for this money
  * @author Alessandro Lacava (@lambdista)
  * @since 2014-10-27
  */
case class Money(amount: BigDecimal, currency: Currency)(implicit converter: Converter) extends Ordered[Money] {
  /**
    * Converts this money to another money represented using otherCurrency
    *
    * @param thatCurrency the currency to convert this money to
    * @return a new object where its currency is expressed in terms of otherCurrency
    */
  def to(thatCurrency: Currency): Money = {
    val rate = converter.conversionRate(currency, thatCurrency)
    Money(amount * rate, thatCurrency)
  }

  /**
    * Adds this money to `that`. The result is expressed in terms of this money's currency.
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
    * Subtracts `that` from this money. The result is expressed in terms of this money's currency.
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
    * Multiplies amount by this money.
    *
    * @param thatAmount the amount to multiply by this money
    * @return a new object which is the result of multiplying amount to this money
    */
  def *(thatAmount: BigDecimal): Money =
    performOperation(Money(thatAmount, this.currency), _ * _)

  /**
    * Divides amount by this money.
    *
    * @param thatAmount the amount to multiply by this money
    * @return a new object which is the result of multiplying amount to this money
    */
  def /(thatAmount: BigDecimal): Money =
    performOperation(Money(thatAmount, this.currency), _ / _)

  /**
    * Converts `that` money to `this` currency and then compares the resulting amounts.
    * <br /><br />
    * <b>IMPORTANT NOTE</b>: It's consistency is the one explained in the `compare` method.
    *
    * @param that the other `Money` object
    * @return true if `this` === `that`, false otherwise
    */
  def ===(that: Money): Boolean = this.compare(that) == 0

  /**
    * Converts `that` money to `this` currency and then compares the resulting amounts.
    * <br /><br />
    * <b>IMPORTANT NOTE</b>: It's consistency is the one explained in the `compare` method.
    *
    * @param that the other `Money` object
    * @return true if `this` !== `that`, false otherwise
    */
  def !==(that: Money): Boolean = this.compare(that) != 0

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
    * Converts `that` money to `this` currency and then compares the amounts.
    * <br /><br />
    * <b>IMPORTANT NOTE</b>: Be careful when using `compare` since it might seem inconsistent because
    * if the conversion rate from, say, EUR to USD is 1.13 it's not guaranteed that the one from USD to EUR is exactly
    * 1 / 1.13 (i.e. the inverse). For this reason, for example, you could have:
    * ```
    * 100(EUR).compare(113(USD)) > 0 == true // (1)
    * // but
    * 113(USD).compare(100(EUR)) < 0 == false // (2)
    * ```
    *
    * This is because `that` is first converted to `this` currency and then the resulting
    * amounts are compared. The rule is as simple as the outer left currency is the base one into which all other
    * currencies are converted within an expression.
    * In the previous example (1) 113 USD is converted to EUR and then compared to 100 EUR.
    * In example (2) it's 100 EUR that is converted to USD and then compared. Since the conversion rates are not one
    * the inverse of the other you get apparently inconsistent results even if, for the provided conversions, they
    * are consistent.
    *
    * @param that the other `Money` object
    * @return an `Int` that is:
    *         <ul>
    *         <li>< 0 if `this` < `that`</li>
    *         <li>> 0 if `this` > `that`</li>
    *         <li>0 if they are equal (after currency conversion, of course)</li>
    *         </ul>
    */
  override def compare(that: Money): Int = {
    val thatAmount = converter.conversionRate(that.currency, this.currency) * that.amount

    this.amount compare thatAmount
  }

  /**
    * Formats this money object using a number of decimal digits equals to the decimalDigits param, which defaults to 5.
    *
    * @param decimalDigits the number of decimal digits to include
    * @return a formatted string representing this money object
    */
  def toFormattedString(decimalDigits: Int = 5): String =
    s"${bigDecimalToFormattedString(amount, decimalDigits)} ${currency.toString}"

  private def performOperation(that: Money, operation: (BigDecimal, BigDecimal) => BigDecimal): Money = {
    val convertedMoney = converter.convert(that, this.currency)

    operation(this.amount, convertedMoney.amount)(this.currency)
  }
}
