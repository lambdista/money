package com.lambdista.money

import org.slf4j.LoggerFactory

import scala.math.BigDecimal.RoundingMode
import scala.math.BigDecimal.RoundingMode.RoundingMode

import com.typesafe.scalalogging.{Logger, LazyLogging}

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
case class Money(amount: BigDecimal, currency: Currency)(implicit converter: Converter) {

  private val logger = Logger(LoggerFactory.getLogger(this.getClass))
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
  def to(thatCurrency: Currency): Money = apply(thatCurrency)

  /**
   * Adds this money to thatMoney. The result is expressed in terms of this money's currency.
   *
   * @param thatMoney the money to sum to this money
   * @return a new object which is the result of summing this money to thatMoney after converting thatMoney to this
   *         money's currency
   */
  def +(thatMoney: Money): Money = {
    logger.debug(s"Adding $this to $thatMoney")
    performOperation(thatMoney, _ + _)
  }

  /**
   * Adds amount to this money.
   *
   * @param thatAmount the amount to sum to this money
   * @return a new object which is the result of summing amount to this money
   */
  def +(thatAmount: BigDecimal): Money = this + Money(thatAmount, this.currency)

  /**
   * Subtracts thatMoney from this money. The result is expressed in terms of this money's currency.
   *
   * @param thatMoney the money to subtract from this money
   * @return a new object which is the result of subtracting thatMoney from this money after converting thatMoney to this
   *         money's currency
   */
  def -(thatMoney: Money): Money = {
    logger.debug(s"Subtracting $this from $thatMoney")
    performOperation(thatMoney, _ - _)
  }

  /**
   * Subtracts amount from this money.
   *
   * @param thatAmount the amount to sum to this money
   * @return a new object which is the result of summing amount to this money
   */
  def -(thatAmount: BigDecimal): Money = this - Money(thatAmount, this.currency)

  /**
   * Multiplies thatMoney by this money. The result is expressed in terms of this money's currency.
   *
   * @param thatMoney the money to multiply by this money
   * @return a new object which is the result of multiplying thatMoney by this money after converting thatMoney to this
   *         money's currency
   */
  def *(thatMoney: Money): Money = {
    logger.debug(s"Multiplying $this by $thatMoney")
    performOperation(thatMoney, _ * _)
  }

  /**
   * Multiplies amount by this money.
   *
   * @param thatAmount the amount to multiply by this money
   * @return a new object which is the result of multiplying amount to this money
   */
  def *(thatAmount: BigDecimal): Money = this * Money(thatAmount, this.currency)

  /**
   * Divides this money by thatMoney. The result is expressed in terms of this money's currency.
   *
   * @param thatMoney the money to use ad divisor
   * @return a new object which is the result of dividing this money (dividend) by thatMoney (divisor) after converting
   *         thatMoney to this money's currency
   */
  def /(thatMoney: Money): Money = {
    logger.debug(s"Dividing $this by $thatMoney")
    performOperation(thatMoney, _ / _)
  }

  /**
   * Divides amount by this money.
   *
   * @param thatAmount the amount to multiply by this money
   * @return a new object which is the result of multiplying amount to this money
   */
  def /(thatAmount: BigDecimal): Money = this / Money(thatAmount, this.currency)

  /**
   * Compares this `Money` with `thatAmount`. The comparison is made between this amount and `thatAmount`
   *
   * @param thatAmount the amount to compare this object with.
   * @return true if this amount is greater than `thatAmount`, false otherwise.
   */
  def >(thatAmount: BigDecimal): Boolean = this.amount > thatAmount

  /**
   * Compares this `Money` with `thatAmount`. The comparison is made between this amount and `thatAmount`
   *
   * @param thatAmount the amount to compare this object with.
   * @return true if this amount is greater than or equal to `thatAmount`, false otherwise.
   */
  def >=(thatAmount: BigDecimal): Boolean = this.amount >= thatAmount

  /**
   * Compares this `Money` with `thatMoney`. The comparison is made between the amounts after normalizing both `Money`
   * objects to the same currency.
   *
   * @param thatMoney the `Money` object to compare this object with.
   * @return true if this `Money` is lesser than `thatMoney`, false otherwise.
   */
  def <(thatMoney: Money): Boolean = !(this >= thatMoney)

  /**
   * Compares this `Money` with `thatMoney`. The comparison is made between the amounts after normalizing both `Money`
   * objects to the same currency.
   *
   * @param thatMoney the `Money` object to compare this object with.
   * @return true if this `Money` is greater than or equal to `thatMoney`, false otherwise.
   */
  def >=(thatMoney: Money): Boolean = compare(thatMoney, _ >= _)

  /**
   * Compares this `Money` with `thatAmount`. The comparison is made between this amount and `thatAmount`
   *
   * @param thatAmount the amount to compare this object with.
   * @return true if this amount is lesser than `thatAmount`, false otherwise.
   */
  def <(thatAmount: BigDecimal): Boolean = this.amount < thatAmount

  /**
   * Compares this `Money` with `thatMoney`. The comparison is made between the amounts after normalizing both `Money`
   * objects to the same currency.
   *
   * @param thatMoney the `Money` object to compare this object with.
   * @return true if this `Money` is lesser than or equal to `thatMoney`, false otherwise.
   */
  def <=(thatMoney: Money): Boolean = !(this > thatMoney)

  /**
   * Compares this `Money` with `thatMoney`. The comparison is made between the amounts after normalizing both `Money`
   * objects to the same currency.
   *
   * @param thatMoney the `Money` object to compare this object with.
   * @return true if this `Money` is greater than `thatMoney`, false otherwise.
   */
  def >(thatMoney: Money): Boolean = compare(thatMoney, _ > _)

  private def compare(thatMoney: Money, comparisonFunc: (BigDecimal, BigDecimal) => Boolean): Boolean = {
    val thisAmount = this.amount
    val thatAmount = converter.convert(thatMoney.currency, this.currency) * thatMoney.amount
    logger.debug(s"thisAmount: ${bigDecimalToFormattedString(thisAmount)}, thatAmount: ${bigDecimalToFormattedString(thatAmount)}")

    comparisonFunc(thisAmount, thatAmount)
  }

  /**
   * Compares this `Money` with `thatAmount`. The comparison is made between this amount and `thatAmount`
   *
   * @param thatAmount the amount to compare this object with.
   * @return true if this amount is lesser than or equal to `thatAmount`, false otherwise.
   */
  def <=(thatAmount: BigDecimal): Boolean = this.amount <= thatAmount

  /**
   * Compares this `Money` with `thatMoney`. The comparison is made between the amounts after normalizing both `Money`
   * objects to the same currency.
   *
   * @param thatMoney the `Money` object to compare this object with.
   * @return true if this `Money` is not equal to `thatMoney`, false otherwise.
   */
  def !==(thatMoney: Money): Boolean = !(this === thatMoney)

  /**
   * Compares this `Money` with `thatMoney`. The comparison is made between the amounts after normalizing both `Money`
   * objects to the same currency.
   *
   * @param thatMoney the `Money` object to compare this object with.
   * @return true if this `Money` is equal to `thatMoney`, false otherwise.
   */
  def ===(thatMoney: Money): Boolean = compare(thatMoney, _ == _)

  /**
   * Compares this `Money` with `thatAmount`. The comparison is made between this amount and `thatAmount`
   *
   * @param thatAmount the amount to compare this object with.
   * @return true if this amount is not equal to `thatAmount`, false otherwise.
   */
  def !==(thatAmount: BigDecimal): Boolean = !(this === thatAmount)

  /**
   * Compares this `Money` with `thatAmount`. The comparison is made between this amount and `thatAmount`
   *
   * @param thatAmount the amount to compare this object with.
   * @return true if this amount is equal to `thatAmount`, false otherwise.
   */
  def ===(thatAmount: BigDecimal): Boolean = this.amount == thatAmount

  /**
   * Rounds this `Money` to the given number of `decimalDigits` using the provided `roundingMode`
   *
   * @param decimalDigits the number of decimal digits to keep
   * @return a new `Money` object whose number of decimal digits is `decimalDigits`
   */
  def round(decimalDigits: Int, roundingMode: RoundingMode = RoundingMode.HALF_DOWN): Money =
    Money(amount.setScale(decimalDigits, roundingMode), currency)

  override def hashCode(): Int = super.hashCode()

  override def equals(obj: scala.Any): Boolean = super.equals(obj)

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
  def toFormattedString(decimalDigits: Int = 5): String = {
    s"${bigDecimalToFormattedString(amount, decimalDigits)} ${currency.toString}"
  }

  private def performOperation(thatMoney: Money, operation: (BigDecimal, BigDecimal) => BigDecimal): Money = {
    thatMoney match {
      case Money(v, c) if c == currency => Money(operation(amount, v), currency)
      case Money(v, c) => performOperation(thatMoney.to(currency), operation)
    }
  }
}
