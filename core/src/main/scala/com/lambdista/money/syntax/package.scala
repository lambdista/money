package com.lambdista.money

/**
 * Implicits for this DSL.
 *
 * @author Alessandro Lacava 
 * @since 2015-04-01
 */
package object syntax {

  implicit class BigDecimalOps(val value: BigDecimal) extends AnyVal {
    def apply(currency: Currency)(implicit converter: Converter): Money = Money(value, currency)
  }

  implicit class IntOps(val value: Int) extends AnyVal {
    def apply(currency: Currency)(implicit converter: Converter): Money = (value: BigDecimal).apply(currency)
  }

  implicit class DoubleOps(val value: Double) extends AnyVal {
    def apply(currency: Currency)(implicit converter: Converter): Money = (value: BigDecimal).apply(currency)
  }

}
