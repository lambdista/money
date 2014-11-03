package org.typesafely.money

/**
 * Implicit conversions for this lib.
 *
 * @author Alessandro Lacava 
 * @since 2014-10-27
 */
object Implicits {

  implicit class BigDecimalOps(value: BigDecimal) {
    def apply(currency: Currency): Money = Money(value, currency)
  }

  implicit class IntOps(value: Int) {
    def apply(currency: Currency): Money = (value: BigDecimal).apply(currency)
  }

  implicit class DoubleOps(value: Double) {
    def apply(currency: Currency): Money = (value: BigDecimal).apply(currency)
  }

}
