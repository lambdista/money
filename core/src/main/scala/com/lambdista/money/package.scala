package com.lambdista

import scala.math.BigDecimal.RoundingMode
import scala.math.BigDecimal.RoundingMode.RoundingMode

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
   * @param decimalDigits the number of decimal digits to include. The valid range is [0, 100], both inclusive
   * @return a formatted string representing the value passed as a parameter
   */
  def toFormattedString(value: BigDecimal, decimalDigits: Int = 5, roundingMode: RoundingMode = RoundingMode.HALF_DOWN): String = {
    val lowerBound = 0
    val upperBound = 100
    require(decimalDigits >= 0 && decimalDigits <= 100, s"decimalDigits valid range is [$lowerBound, $upperBound], both inclusive")

    val df = if (decimalDigits == 0) {
      new DecimalFormat("0")
    }
    else {
      new DecimalFormat("0." + ("#" * decimalDigits))
    }

    df.format(value.setScale(decimalDigits, roundingMode).underlying())
  }

}
