package org.typesafely.money

import grizzled.slf4j.Logging

import org.typesafely.money.{toFormattedString => bigDecimalToFormattedString}

/**
 * This is the main class of the lib. A Money is represented by its `amount` and `currency`.
 *
 * @param amount the amount of this money
 * @param currency the currency for this money
 * @author Alessandro Lacava
 * @since 2014-10-27
 */
case class Money(amount: BigDecimal, currency: Currency) extends Logging {

  /**
   * Converts this money to another money represented using otherCurrency
   *
   * @param thatCurrency the currency to convert this money to
   * @param conversion the conversion to use
   * @return a new object where its currency is expressed in terms of otherCurrency
   */
  def apply(thatCurrency: Currency)(implicit conversion: Conversion): Money = {
    val rate = convert(currency, thatCurrency)
    Money(amount * rate, thatCurrency)
  }

  /**
   * Just an alias for [[org.typesafely.money.Money#apply]]
   */
  def to(thatCurrency: Currency)(implicit conversion: Conversion): Money = apply(thatCurrency)

  /**
   * Adds this money to thatMoney. The result is expressed in terms of this money's currency.
   *
   * @param thatMoney the money to sum to this money
   * @param conversion the conversion to use
   * @return a new object which is the result of summing this money to thatMoney after converting thatMoney to this
   *         money's currency
   */
  def +(thatMoney: Money)(implicit conversion: Conversion): Money = {
    debug(s"Adding $this to $thatMoney")
    performOperation(thatMoney, _ + _)
  }

  /**
   * Adds amount to this money.
   *
   * @param thatAmount the amount to sum to this money
   * @param conversion the conversion to use
   * @return a new object which is the result of summing amount to this money
   */
  def +(thatAmount: BigDecimal)(implicit conversion: Conversion): Money = this + Money(thatAmount, this.currency)

  /**
   * Subtracts thatMoney from this money. The result is expressed in terms of this money's currency.
   *
   * @param thatMoney the money to subtract from this money
   * @param conversion the conversion to use
   * @return a new object which is the result of subtracting thatMoney from this money after converting thatMoney to this
   *         money's currency
   */
  def -(thatMoney: Money)(implicit conversion: Conversion): Money = {
    debug(s"Subtracting $this from $thatMoney")
    performOperation(thatMoney, _ - _)
  }

  /**
   * Subtracts amount from this money.
   *
   * @param thatAmount the amount to sum to this money
   * @param conversion the conversion to use
   * @return a new object which is the result of summing amount to this money
   */
  def -(thatAmount: BigDecimal)(implicit conversion: Conversion): Money = this - Money(thatAmount, this.currency)

  /**
   * Multiplies thatMoney by this money. The result is expressed in terms of this money's currency.
   *
   * @param thatMoney the money to multiply by this money
   * @param conversion the conversion to use
   * @return a new object which is the result of multiplying thatMoney by this money after converting thatMoney to this
   *         money's currency
   */
  def *(thatMoney: Money)(implicit conversion: Conversion): Money = {
    debug(s"Multiplying $this by $thatMoney")
    performOperation(thatMoney, _ * _)
  }

  /**
   * Multiplies amount by this money.
   *
   * @param thatAmount the amount to multiply by this money
   * @param conversion the conversion to use
   * @return a new object which is the result of multiplying amount to this money
   */
  def *(thatAmount: BigDecimal)(implicit conversion: Conversion): Money = this * Money(thatAmount, this.currency)

  /**
   * Divides this money by thatMoney. The result is expressed in terms of this money's currency.
   *
   * @param thatMoney the money to use ad divisor
   * @param conversion the conversion to use
   * @return a new object which is the result of dividing this money (dividend) by thatMoney (divisor) after converting
   *         thatMoney to this money's currency
   */
  def /(thatMoney: Money)(implicit conversion: Conversion): Money = {
    debug(s"Dividing $this by $thatMoney")
    performOperation(thatMoney, _ / _)
  }

  /**
   * Divides amount by this money.
   *
   * @param thatAmount the amount to multiply by this money
   * @param conversion the conversion to use
   * @return a new object which is the result of multiplying amount to this money
   */
  def /(thatAmount: BigDecimal)(implicit conversion: Conversion): Money = this / Money(thatAmount, this.currency)

  def >(thatMoney: Money)(implicit conversion: Conversion): Boolean = compare(thatMoney, _ > _)

  def >(thatAmount: BigDecimal): Boolean = this.amount > thatAmount

  def >=(thatMoney: Money)(implicit conversion: Conversion): Boolean = compare(thatMoney, _ >= _)

  def >=(thatAmount: BigDecimal): Boolean = this.amount >= thatAmount

  def <(thatMoney: Money)(implicit conversion: Conversion): Boolean = !(this >= thatMoney)

  def <(thatAmount: BigDecimal): Boolean = this.amount < thatAmount

  def <=(thatMoney: Money)(implicit conversion: Conversion): Boolean = !(this > thatMoney)

  def <=(thatAmount: BigDecimal): Boolean = this.amount <= thatAmount

  def ==(thatMoney: Money)(implicit conversion: Conversion): Boolean = compare(thatMoney, _ == _)

  def !=(thatMoney: Money)(implicit conversion: Conversion): Boolean = !(this == thatMoney)

  private def compare(thatMoney: Money, comparisonFunc: (BigDecimal, BigDecimal) => Boolean)(implicit conversion: Conversion): Boolean = {
    val thisAmount = this.amount
    val thatAmount = thatMoney.convert(thatMoney.currency, this.currency) * thatMoney.amount
    debug(s"thisAmount: ${bigDecimalToFormattedString(thisAmount)}, thatAmount: ${bigDecimalToFormattedString(thatAmount)}")

    comparisonFunc(thisAmount, thatAmount)
  }

  private def performOperation(thatMoney: Money, operation: (BigDecimal, BigDecimal) => BigDecimal)(implicit conversion: Conversion): Money = {
    thatMoney match {
      case Money(v, c) if (c == currency) => Money(operation(amount, v), currency)
      case Money(v, c) => performOperation(thatMoney.to(currency), operation)
    }
  }

  private def convert(from: Currency, to: Currency)(implicit conversion: Conversion): BigDecimal = {
    if (from == to) {
      1
    } else {
      val out = conversion.getOrElse((from, to), 1 / conversion((to, from)))
      debug(s"Conversion applied (1 $from = ${bigDecimalToFormattedString(out)} $to)")
      out
    }
  }

  /**
   * @return the string representation of this money which has, at most, 5 decimal digits. If you need to customize the
   *         number of decimal digits use [[org.typesafely.money.Money# t o F o r m a t t e d S t r i n g]] instead
   */
  override def toString(): String = toFormattedString()

  /**
   * Formats this money object using a number of decimal digits equals to the decimalDigits param, which defaults to 5.
   *
   * @param decimalDigits the number of decimal digits to include
   * @return a formatted string representing this money object
   */
  def toFormattedString(decimalDigits: Int = 5): String = {
    bigDecimalToFormattedString(amount, decimalDigits) + " " + currency.toString()
  }


}
