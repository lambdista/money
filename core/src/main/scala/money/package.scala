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
import scala.math.BigDecimal.RoundingMode
import scala.math.BigDecimal.RoundingMode.RoundingMode

import java.text.DecimalFormat

/**
  * This package object contains utility functions and type aliases
  *
  * @author Alessandro Lacava (@lambdista)
  * @since 2014-10-27
  */
package object money {
  implicit val DEFAULT_CURRENCY = USD

  type Conversion = Map[(Currency, Currency), BigDecimal]

  /**
    * Formats the BigDecimal value using a number of decimal digits equals to the decimalDigits param, which defaults to 5.
    *
    * @param value the BigDecimal value to format
    * @param decimalDigits the number of decimal digits to include. The valid range is [0, 100], both inclusive
    * @return a formatted string representing the value passed as a parameter
    */
  def toFormattedString(
    value: BigDecimal,
    decimalDigits: Int = 5,
    roundingMode: RoundingMode = RoundingMode.HALF_DOWN
  ): String = {
    val lowerBound = 0
    val upperBound = 100
    require(
      decimalDigits >= 0 && decimalDigits <= 100,
      s"decimalDigits valid range is [$lowerBound, $upperBound], both inclusive"
    )

    val df =
      if (decimalDigits == 0)
        new DecimalFormat("0")
      else
        new DecimalFormat("0." + ("#" * decimalDigits))

    df.format(value.setScale(decimalDigits, roundingMode).underlying())
  }

  /**
    *  Extensions for this DSL.
    */
  implicit class BigDecimalOps(val value: BigDecimal) extends AnyVal {
    def apply(currency: Currency)(implicit converter: Converter): Money = Money(value, currency)
  }

  implicit class IntOps(val value: Int) extends AnyVal {
    def apply(currency: Currency)(implicit converter: Converter): Money = BigDecimal(value).apply(currency)
  }

  implicit class DoubleOps(val value: Double) extends AnyVal {
    def apply(currency: Currency)(implicit converter: Converter): Money = BigDecimal(value).apply(currency)
  }

  implicit def numericMoney(implicit converter: Converter) = new NumericMoney(DEFAULT_CURRENCY)
}
