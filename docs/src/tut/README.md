# Scala DSL for money-related operations

This Domain-Specific Language (DSL) lets you perform operations among different currencies,
by transparently doing all internal conversions. The conversion map is injected implicitly by the client code.

## Using Money
Binary release artifacts are published to the [Sonatype OSS Repository Hosting service](https://oss.sonatype.org). 
Add the following dependency into your SBT build to use `money`:

```scala
libraryDependencies += "com.lambdista" %% "money" % "0.6.1"
```

You can find all the released versions [here](https://github.com/lambdista/money/releases).

Builds are available for Scala 2.12.x, 2.11.x and 2.10.x.

## Usage Example
Here's a simple usage example:

```tut:silent
import money._

object Usage {
  def main(args: Array[String]): Unit = {
    val conversion: Conversion = Map(
      (EUR, USD) -> 1.13,
      (EUR, GBP) -> 0.71,
      (USD, EUR) -> 0.88,
      (USD, GBP) -> 0.63,
      (GBP, EUR) -> 1.40,
      (GBP, USD) -> 1.59
    )

    implicit val converter = Converter(conversion)

    val sumAndConversion1 = 100.001 (USD) + 200 (EUR) to GBP
    println(s"sumAndConversion1: $sumAndConversion1")

    val sumAndConversion2: Money = 100 (USD) + 210.4 (EUR) to EUR
    println(s"sumAndConversion2: $sumAndConversion2")

    val sum              = 100.001 (USD) + 200 (EUR)
    val simpleConversion = sum to GBP
    println(s"simpleConversion: $simpleConversion")

    val sumWithSimpleNumber = 100 (USD) + 23.560
    println(s"sumWithSimpleNumber: $sumWithSimpleNumber")

    val multiplicationWithSimpleNumber = 100 (USD) * 23
    println(s"multiplicationWithSimpleNumber: $multiplicationWithSimpleNumber")

    val usd = Currency("USD")

    val multiplication = 100 (usd) * 23
    println(s"multiplication: $multiplication")

    val divisionWithSimpleNumber = 100 (USD) / 23
    println(s"divisionWithSimpleNumber: $divisionWithSimpleNumber")

    val comparison = 100 (USD) > 99 (EUR)
    println(s"100 USD > 99 EUR? $comparison")
  }
}
```

As you can see the client code just needs a simple import and an implicit value of type `Converter`
in order to use the DSL. The operations shown in the previous code are only a few among the available ones.
Have a look at the `Money` class for a complete coverage.

## Run the example
To run the previous example launch:

```
$ sbt run
```

## Play with the REPL
To play with Scala's REPL launch:

```
$ sbt console
```

This will automatically fire the Scala's REPL and run the following commands for you:

```tut:silent
import money._

val conversion: Conversion = Map(
  (EUR, USD) -> 1.13,
  (EUR, GBP) -> 0.71,
  (USD, EUR) -> 0.88,
  (USD, GBP) -> 0.63,
  (GBP, EUR) -> 1.40,
  (GBP, USD) -> 1.59
)

implicit val converter = Converter(conversion)
```

This way you can start playing with the DSL expressions (e.g.: `100(USD) + 90(EUR)`) without worrying about imports
and the conversion map. Of course if you need to use your own conversion you can redefine it.

## Scala's Numeric type class implementation ##
When using the following import:

```scala
import money._
```

you get the default implicit for `Numeric[Money]` whose implementation is as follows:

```scala
implicit def numericMoney(implicit converter: Converter) = new NumericMoney(DEFAULT_CURRENCY)
```

It uses `DEFAULT_CURRENCY`, which is `USD`. That's necessary for the `fromInt` method of `Numeric`:

```scala
def fromInt(x: Int): Money
```

That is, in order to create a `Money` object you need a `Currency`. 

If you intend to use a different currency you can define your own implicit `Numeric[Money]`, e.g.:

```scala
implicit def numericMoney(implicit converter: Converter) = new NumericMoney(EUR)
```

## Bugs and Feedback
For bugs, questions and discussions please use the [Github Issues](https://github.com/lambdista/money/issues).

## License
Copyright 2014 Alessandro Lacava.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
with the License. You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and limitations under the License.

