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
package com.lambdista.money

import com.lambdista.util.Logger

/**
 * This class is responsible for performing `Currency` conversions.
 *
 * @author Alessandro Lacava 
 * @since 2015-03-07
 */
case class Converter(conversion: Conversion) extends {

  private val logger = Logger(this.getClass)

  /**
   * Converts from one `Currency` to the other.
   *
   * @param from the `Currency` to convert from
   * @param to the `Currency` to convert to
   * @return a `Currency` conversion rate that means 1 `from` = x `to`, where x is the result of this method
   */
  def convert(from: Currency, to: Currency): BigDecimal = {
    if (from == to) 1
    else {
      val out = conversion.getOrElse((from, to), 1 / conversion((to, from)))
      logger.debug(s"Conversion applied (1 $from = ${toFormattedString(out)} $to)")
      out
    }
  }

}
