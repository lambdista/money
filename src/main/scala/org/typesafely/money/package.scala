package org.typesafely

import scala.math.BigDecimal.RoundingMode

import java.text.DecimalFormat

/**
 * This package object contains utility functions and type aliases
 *
 * @author Alessandro Lacava 
 * @since 2014-10-27
 */
package object money {

  type Conversion = Map[(Currency, Currency), BigDecimal]

  /**
   * Formats the BigDecimal value using a number of decimal digits equals to the decimalDigits param, which defaults to 5.
   *
   * @param value the BigDecimal value to format
   * @param decimalDigits the number of decimal digits to include
   * @return a formatted string representing the value passed as a parameter
   */
  def toFormattedString(value: BigDecimal, decimalDigits: Int = 5): String = {
    val df = new DecimalFormat("0." + ("#" * decimalDigits));
    df.format(value.setScale(decimalDigits, RoundingMode.HALF_DOWN).underlying())
  }

}
