/*
 * Copyright 2015 Alessandro Lacava.
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

/**
  * This class is responsible for performing `Currency` conversions.
  *
  * @author Alessandro Lacava (@lambdista)
  * @since 2015-03-07
  */
case class Converter(conversion: Conversion) {

  /**
    *
    * @param from the `Currency` to convert from
    * @param to the `Currency` to convert to
    * @return the conversion rate
    */
  def conversionRate(from: Currency, to: Currency): BigDecimal = {
    if (from == to) 1
    else conversion((from, to))
  }

  /**
    * 
    * @param sourceMoney the `Money` to convert
    * @param targetCurrency the `Currency` to convert to
    * @return the `Money` obtained by converting `sourceMoney` to `targetCurrency`
    */
  def convert(sourceMoney: Money, targetCurrency: Currency)(implicit converter: Converter): Money = {
    val rate = conversionRate(sourceMoney.currency, targetCurrency)

    Money(sourceMoney.amount * rate, targetCurrency)
  }
}

object Converter {
  // default implementation. Works only among same currency money operations
  implicit val converter = new Converter(Map())
}
