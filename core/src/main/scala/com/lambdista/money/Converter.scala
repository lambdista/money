package com.lambdista.money

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
 * This class is responsible for performing `Currency` conversions.
 *
 * @author Alessandro Lacava 
 * @since 2015-03-07
 */
case class Converter(conversion: Conversion) extends {

  private val logger = Logger(LoggerFactory.getLogger(this.getClass))

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
