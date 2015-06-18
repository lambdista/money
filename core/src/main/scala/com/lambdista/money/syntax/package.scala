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

  implicit def numericMoney(implicit converter: Converter) = new NumericMoney(DEFAULT_CURRENCY)
}
