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

import com.lambdista.money.syntax._

/**
  * `Numeric` implementation for [[com.lambdista.money.Money]]
  *
  * @author Alessandro Lacava 
  * @since 2015-06-18
  */
class NumericMoney(defaultCurrency: Currency)(implicit converter: Converter)
    extends Numeric[Money] {
  override def plus(x: Money, y: Money): Money = x + y

  override def toDouble(x: Money): Double = x.amount.toDouble

  override def toFloat(x: Money): Float = x.amount.toFloat

  override def toInt(x: Money): Int = x.amount.toInt

  override def negate(x: Money): Money = Money(-x.amount, x.currency)

  override def fromInt(x: Int): Money = x(defaultCurrency)

  override def toLong(x: Money): Long = x.amount.toLong

  override def times(x: Money, y: Money): Money = x * y

  override def minus(x: Money, y: Money): Money = x - y

  override def compare(x: Money, y: Money): Int = x compare y
}
