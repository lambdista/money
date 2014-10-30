package org.typesafely.money

/**
 * Generic Currency
 *
 * @author Alessandro Lacava 
 * @since 2014-10-27
 */
trait Currency

object Currency {

  /**
   * Builds a [[org.typesafely.money.Currency]] object by decoding its corresponding code. It's case-insensitive so,
   * for example, passing "USD" or "usd" always yields a USD object.
   *
   * @param s the string to decode
   * @return an object of type [[org.typesafely.money.Currency]]
   */
  def apply(s: String): Currency = s.toUpperCase() match {
    case "USD" => USD
    case "EUR" => EUR
    case "GBP" => GBP
  }

  object EUR extends Currency {
    val getCode: String = "EUR"
    override val toString = getCode
  }

  object USD extends Currency {
    val getCode: String = "USD"
    override val toString = getCode
  }

  object GBP extends Currency {
    val getCode: String = "GBP"
    override val toString = getCode
  }
}
