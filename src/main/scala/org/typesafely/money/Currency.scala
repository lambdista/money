package org.typesafely.money

/**
 * Generic Currency
 *
 * @author Alessandro Lacava 
 * @since 2014-10-27
 */
trait Currency

object Currency {

  val $ = USD

  /**
   * Builds a [[org.typesafely.money.Currency]] object by decoding its corresponding code. It's case-insensitive so,
   * for example, passing "USD" or "usd" always yields a USD object.
   *
   * @param s the string to decode
   * @return an object of type [[org.typesafely.money.Currency]]
   */
  def apply(s: String): Currency = s.toUpperCase match {
    case "AED" => AED
    case "AFN" => AFN
    case "ALL" => ALL
    case "AMD" => AMD
    case "ANG" => ANG
    case "AOA" => AOA
    case "ARS" => ARS
    case "AUD" => AUD
    case "AWG" => AWG
    case "AZN" => AZN
    case "BAM" => BAM
    case "BBD" => BBD
    case "BDT" => BDT
    case "BGN" => BGN
    case "BHD" => BHD
    case "BIF" => BIF
    case "BMD" => BMD
    case "BND" => BND
    case "BOB" => BOB
    case "BRL" => BRL
    case "BSD" => BSD
    case "BTN" => BTN
    case "BWP" => BWP
    case "BYR" => BYR
    case "BZD" => BZD
    case "CAD" => CAD
    case "CDF" => CDF
    case "CHF" => CHF
    case "CLP" => CLP
    case "CNY" => CNY
    case "COP" => COP
    case "CRC" => CRC
    case "CUC" => CUC
    case "CUP" => CUP
    case "CVE" => CVE
    case "CZK" => CZK
    case "DJF" => DJF
    case "DKK" => DKK
    case "DOP" => DOP
    case "DZD" => DZD
    case "EGP" => EGP
    case "ERN" => ERN
    case "ETB" => ETB
    case "EUR" => EUR
    case "€" => EUR
    case "FJD" => FJD
    case "FKP" => FKP
    case "GBP" => GBP
    case "£" => GBP
    case "GEL" => GEL
    case "GGP" => GGP
    case "GHS" => GHS
    case "GIP" => GIP
    case "GMD" => GMD
    case "GNF" => GNF
    case "GTQ" => GTQ
    case "GYD" => GYD
    case "HKD" => HKD
    case "HNL" => HNL
    case "HRK" => HRK
    case "HTG" => HTG
    case "HUF" => HUF
    case "IDR" => IDR
    case "ILS" => ILS
    case "IMP" => IMP
    case "INR" => INR
    case "IQD" => IQD
    case "IRR" => IRR
    case "ISK" => ISK
    case "JEP" => JEP
    case "JMD" => JMD
    case "JOD" => JOD
    case "JPY" => JPY
    case "KES" => KES
    case "KGS" => KGS
    case "KHR" => KHR
    case "KMF" => KMF
    case "KPW" => KPW
    case "KRW" => KRW
    case "KWD" => KWD
    case "KYD" => KYD
    case "KZT" => KZT
    case "LAK" => LAK
    case "LBP" => LBP
    case "LKR" => LKR
    case "LRD" => LRD
    case "LSL" => LSL
    case "LTL" => LTL
    case "LYD" => LYD
    case "MAD" => MAD
    case "MDL" => MDL
    case "MGA" => MGA
    case "MKD" => MKD
    case "MMK" => MMK
    case "MNT" => MNT
    case "MOP" => MOP
    case "MRO" => MRO
    case "MUR" => MUR
    case "MVR" => MVR
    case "MWK" => MWK
    case "MXN" => MXN
    case "MYR" => MYR
    case "MZN" => MZN
    case "NAD" => NAD
    case "NGN" => NGN
    case "NIO" => NIO
    case "NOK" => NOK
    case "NPR" => NPR
    case "NZD" => NZD
    case "OMR" => OMR
    case "PAB" => PAB
    case "PEN" => PEN
    case "PGK" => PGK
    case "PHP" => PHP
    case "PKR" => PKR
    case "PLN" => PLN
    case "PYG" => PYG
    case "QAR" => QAR
    case "RON" => RON
    case "RSD" => RSD
    case "RUB" => RUB
    case "RWF" => RWF
    case "SAR" => SAR
    case "SBD" => SBD
    case "SCR" => SCR
    case "SDG" => SDG
    case "SEK" => SEK
    case "SGD" => SGD
    case "SHP" => SHP
    case "SLL" => SLL
    case "SOS" => SOS
    case "SPL" => SPL
    case "SRD" => SRD
    case "STD" => STD
    case "SVC" => SVC
    case "SYP" => SYP
    case "SZL" => SZL
    case "THB" => THB
    case "TJS" => TJS
    case "TMT" => TMT
    case "TND" => TND
    case "TOP" => TOP
    case "TRY" => TRY
    case "TTD" => TTD
    case "TVD" => TVD
    case "TWD" => TWD
    case "TZS" => TZS
    case "UAH" => UAH
    case "UGX" => UGX
    case "USD" => USD
    case "$" => USD
    case "UYU" => UYU
    case "UZS" => UZS
    case "VEF" => VEF
    case "VND" => VND
    case "VUV" => VUV
    case "WST" => WST
    case "XCD" => XCD
    case "XDR" => XDR
    case "XOF" => XOF
    case "XPF" => XPF
    case "YER" => YER
    case "ZAR" => ZAR
    case "ZMW" => ZMW
    case "ZWD" => ZWD
  }

  /**
   * United Arab Emirates Dirham
   */
  object AED extends Currency {
    val getCode: String = "AED"

    override val toString = getCode
  }

  /**
   * Afghanistan Afghani
   */
  object AFN extends Currency {
    val getCode: String = "AFN"

    override val toString = getCode
  }

  /**
   * Albania Lek
   */
  object ALL extends Currency {
    val getCode: String = "ALL"

    override val toString = getCode
  }

  /**
   * Armenia Dram
   */
  object AMD extends Currency {
    val getCode: String = "AMD"

    override val toString = getCode
  }

  /**
   * Netherlands Antilles Guilder
   */
  object ANG extends Currency {
    val getCode: String = "ANG"

    override val toString = getCode
  }

  /**
   * Angola Kwanza
   */
  object AOA extends Currency {
    val getCode: String = "AOA"

    override val toString = getCode
  }

  /**
   * Argentina Peso
   */
  object ARS extends Currency {
    val getCode: String = "ARS"

    override val toString = getCode
  }

  /**
   * Australia Dollar
   */
  object AUD extends Currency {
    val getCode: String = "AUD"

    override val toString = getCode
  }

  /**
   * Aruba Guilder
   */
  object AWG extends Currency {
    val getCode: String = "AWG"

    override val toString = getCode
  }

  /**
   * Azerbaijan New Manat
   */
  object AZN extends Currency {
    val getCode: String = "AZN"

    override val toString = getCode
  }

  /**
   * Bosnia and Herzegovina Convertible Marka
   */
  object BAM extends Currency {
    val getCode: String = "BAM"

    override val toString = getCode
  }

  /**
   * Barbados Dollar
   */
  object BBD extends Currency {
    val getCode: String = "BBD"

    override val toString = getCode
  }

  /**
   * Bangladesh Taka
   */
  object BDT extends Currency {
    val getCode: String = "BDT"

    override val toString = getCode
  }

  /**
   * Bulgaria Lev
   */
  object BGN extends Currency {
    val getCode: String = "BGN"

    override val toString = getCode
  }

  /**
   * Bahrain Dinar
   */
  object BHD extends Currency {
    val getCode: String = "BHD"

    override val toString = getCode
  }

  /**
   * Burundi Franc
   */
  object BIF extends Currency {
    val getCode: String = "BIF"

    override val toString = getCode
  }

  /**
   * Bermuda Dollar
   */
  object BMD extends Currency {
    val getCode: String = "BMD"

    override val toString = getCode
  }

  /**
   * Brunei Darussalam Dollar
   */
  object BND extends Currency {
    val getCode: String = "BND"

    override val toString = getCode
  }

  /**
   * Bolivia Boliviano
   */
  object BOB extends Currency {
    val getCode: String = "BOB"

    override val toString = getCode
  }

  /**
   * Brazil Real
   */
  object BRL extends Currency {
    val getCode: String = "BRL"

    override val toString = getCode
  }

  /**
   * Bahamas Dollar
   */
  object BSD extends Currency {
    val getCode: String = "BSD"

    override val toString = getCode
  }

  /**
   * Bhutan Ngultrum
   */
  object BTN extends Currency {
    val getCode: String = "BTN"

    override val toString = getCode
  }

  /**
   * Botswana Pula
   */
  object BWP extends Currency {
    val getCode: String = "BWP"

    override val toString = getCode
  }

  /**
   * Belarus Ruble
   */
  object BYR extends Currency {
    val getCode: String = "BYR"

    override val toString = getCode
  }

  /**
   * Belize Dollar
   */
  object BZD extends Currency {
    val getCode: String = "BZD"

    override val toString = getCode
  }

  /**
   * Canada Dollar
   */
  object CAD extends Currency {
    val getCode: String = "CAD"

    override val toString = getCode
  }

  /**
   * Congo/Kinshasa Franc
   */
  object CDF extends Currency {
    val getCode: String = "CDF"

    override val toString = getCode
  }

  /**
   * Switzerland Franc
   */
  object CHF extends Currency {
    val getCode: String = "CHF"

    override val toString = getCode
  }

  /**
   * Chile Peso
   */
  object CLP extends Currency {
    val getCode: String = "CLP"

    override val toString = getCode
  }

  /**
   * China Yuan Renminbi
   */
  object CNY extends Currency {
    val getCode: String = "CNY"

    override val toString = getCode
  }

  /**
   * Colombia Peso
   */
  object COP extends Currency {
    val getCode: String = "COP"

    override val toString = getCode
  }

  /**
   * Costa Rica Colon
   */
  object CRC extends Currency {
    val getCode: String = "CRC"

    override val toString = getCode
  }

  /**
   * Cuba Convertible Peso
   */
  object CUC extends Currency {
    val getCode: String = "CUC"

    override val toString = getCode
  }

  /**
   * Cuba Peso
   */
  object CUP extends Currency {
    val getCode: String = "CUP"

    override val toString = getCode
  }

  /**
   * Cape Verde Escudo
   */
  object CVE extends Currency {
    val getCode: String = "CVE"

    override val toString = getCode
  }

  /**
   * Czech Republic Koruna
   */
  object CZK extends Currency {
    val getCode: String = "CZK"

    override val toString = getCode
  }

  /**
   * Djibouti Franc
   */
  object DJF extends Currency {
    val getCode: String = "DJF"

    override val toString = getCode
  }

  /**
   * Denmark Krone
   */
  object DKK extends Currency {
    val getCode: String = "DKK"

    override val toString = getCode
  }

  /**
   * Dominican Republic Peso
   */
  object DOP extends Currency {
    val getCode: String = "DOP"

    override val toString = getCode
  }

  /**
   * Algeria Dinar
   */
  object DZD extends Currency {
    val getCode: String = "DZD"

    override val toString = getCode
  }

  /**
   * Egypt Pound
   */
  object EGP extends Currency {
    val getCode: String = "EGP"

    override val toString = getCode
  }

  /**
   * Eritrea Nakfa
   */
  object ERN extends Currency {
    val getCode: String = "ERN"

    override val toString = getCode
  }

  /**
   * Ethiopia Birr
   */
  object ETB extends Currency {
    val getCode: String = "ETB"

    override val toString = getCode
  }

  /**
   * Euro Member Countries
   */
  object EUR extends Currency {
    val getCode: String = "EUR"

    override val toString = getCode
  }

  /**
   * Fiji Dollar
   */
  object FJD extends Currency {
    val getCode: String = "FJD"

    override val toString = getCode
  }

  /**
   * Falkland Islands (Malvinas) Pound
   */
  object FKP extends Currency {
    val getCode: String = "FKP"

    override val toString = getCode
  }

  /**
   * United Kingdom Pound
   */
  object GBP extends Currency {
    val getCode: String = "GBP"

    override val toString = getCode
  }

  /**
   * Georgia Lari
   */
  object GEL extends Currency {
    val getCode: String = "GEL"

    override val toString = getCode
  }

  /**
   * Guernsey Pound
   */
  object GGP extends Currency {
    val getCode: String = "GGP"

    override val toString = getCode
  }

  /**
   * Ghana Cedi
   */
  object GHS extends Currency {
    val getCode: String = "GHS"

    override val toString = getCode
  }

  /**
   * Gibraltar Pound
   */
  object GIP extends Currency {
    val getCode: String = "GIP"

    override val toString = getCode
  }

  /**
   * Gambia Dalasi
   */
  object GMD extends Currency {
    val getCode: String = "GMD"

    override val toString = getCode
  }

  /**
   * Guinea Franc
   */
  object GNF extends Currency {
    val getCode: String = "GNF"

    override val toString = getCode
  }

  /**
   * Guatemala Quetzal
   */
  object GTQ extends Currency {
    val getCode: String = "GTQ"

    override val toString = getCode
  }

  /**
   * Guyana Dollar
   */
  object GYD extends Currency {
    val getCode: String = "GYD"

    override val toString = getCode
  }

  /**
   * Hong Kong Dollar
   */
  object HKD extends Currency {
    val getCode: String = "HKD"

    override val toString = getCode
  }

  /**
   * Honduras Lempira
   */
  object HNL extends Currency {
    val getCode: String = "HNL"

    override val toString = getCode
  }

  /**
   * Croatia Kuna
   */
  object HRK extends Currency {
    val getCode: String = "HRK"

    override val toString = getCode
  }

  /**
   * Haiti Gourde
   */
  object HTG extends Currency {
    val getCode: String = "HTG"

    override val toString = getCode
  }

  /**
   * Hungary Forint
   */
  object HUF extends Currency {
    val getCode: String = "HUF"

    override val toString = getCode
  }

  /**
   * Indonesia Rupiah
   */
  object IDR extends Currency {
    val getCode: String = "IDR"

    override val toString = getCode
  }

  /**
   * Israel Shekel
   */
  object ILS extends Currency {
    val getCode: String = "ILS"

    override val toString = getCode
  }

  /**
   * Isle of Man Pound
   */
  object IMP extends Currency {
    val getCode: String = "IMP"

    override val toString = getCode
  }

  /**
   * India Rupee
   */
  object INR extends Currency {
    val getCode: String = "INR"

    override val toString = getCode
  }

  /**
   * Iraq Dinar
   */
  object IQD extends Currency {
    val getCode: String = "IQD"

    override val toString = getCode
  }

  /**
   * Iran Rial
   */
  object IRR extends Currency {
    val getCode: String = "IRR"

    override val toString = getCode
  }

  /**
   * Iceland Krona
   */
  object ISK extends Currency {
    val getCode: String = "ISK"

    override val toString = getCode
  }

  /**
   * Jersey Pound
   */
  object JEP extends Currency {
    val getCode: String = "JEP"

    override val toString = getCode
  }

  /**
   * Jamaica Dollar
   */
  object JMD extends Currency {
    val getCode: String = "JMD"

    override val toString = getCode
  }

  /**
   * Jordan Dinar
   */
  object JOD extends Currency {
    val getCode: String = "JOD"

    override val toString = getCode
  }

  /**
   * Japan Yen
   */
  object JPY extends Currency {
    val getCode: String = "JPY"

    override val toString = getCode
  }

  /**
   * Kenya Shilling
   */
  object KES extends Currency {
    val getCode: String = "KES"

    override val toString = getCode
  }

  /**
   * Kyrgyzstan Som
   */
  object KGS extends Currency {
    val getCode: String = "KGS"

    override val toString = getCode
  }

  /**
   * Cambodia Riel
   */
  object KHR extends Currency {
    val getCode: String = "KHR"

    override val toString = getCode
  }

  /**
   * Comoros Franc
   */
  object KMF extends Currency {
    val getCode: String = "KMF"

    override val toString = getCode
  }

  /**
   * Korea (North) Won
   */
  object KPW extends Currency {
    val getCode: String = "KPW"

    override val toString = getCode
  }

  /**
   * Korea (South) Won
   */
  object KRW extends Currency {
    val getCode: String = "KRW"

    override val toString = getCode
  }

  /**
   * Kuwait Dinar
   */
  object KWD extends Currency {
    val getCode: String = "KWD"

    override val toString = getCode
  }

  /**
   * Cayman Islands Dollar
   */
  object KYD extends Currency {
    val getCode: String = "KYD"

    override val toString = getCode
  }

  /**
   * Kazakhstan Tenge
   */
  object KZT extends Currency {
    val getCode: String = "KZT"

    override val toString = getCode
  }

  /**
   * Laos Kip
   */
  object LAK extends Currency {
    val getCode: String = "LAK"

    override val toString = getCode
  }

  /**
   * Lebanon Pound
   */
  object LBP extends Currency {
    val getCode: String = "LBP"

    override val toString = getCode
  }

  /**
   * Sri Lanka Rupee
   */
  object LKR extends Currency {
    val getCode: String = "LKR"

    override val toString = getCode
  }

  /**
   * Liberia Dollar
   */
  object LRD extends Currency {
    val getCode: String = "LRD"

    override val toString = getCode
  }

  /**
   * Lesotho Loti
   */
  object LSL extends Currency {
    val getCode: String = "LSL"

    override val toString = getCode
  }

  /**
   * Lithuania Litas
   */
  object LTL extends Currency {
    val getCode: String = "LTL"

    override val toString = getCode
  }

  /**
   * Libya Dinar
   */
  object LYD extends Currency {
    val getCode: String = "LYD"

    override val toString = getCode
  }

  /**
   * Morocco Dirham
   */
  object MAD extends Currency {
    val getCode: String = "MAD"

    override val toString = getCode
  }

  /**
   * Moldova Leu
   */
  object MDL extends Currency {
    val getCode: String = "MDL"

    override val toString = getCode
  }

  /**
   * Madagascar Ariary
   */
  object MGA extends Currency {
    val getCode: String = "MGA"

    override val toString = getCode
  }

  /**
   * Macedonia Denar
   */
  object MKD extends Currency {
    val getCode: String = "MKD"

    override val toString = getCode
  }

  /**
   * Myanmar (Burma) Kyat
   */
  object MMK extends Currency {
    val getCode: String = "MMK"

    override val toString = getCode
  }

  /**
   * Mongolia Tughrik
   */
  object MNT extends Currency {
    val getCode: String = "MNT"

    override val toString = getCode
  }

  /**
   * Macau Pataca
   */
  object MOP extends Currency {
    val getCode: String = "MOP"

    override val toString = getCode
  }

  /**
   * Mauritania Ouguiya
   */
  object MRO extends Currency {
    val getCode: String = "MRO"

    override val toString = getCode
  }

  /**
   * Mauritius Rupee
   */
  object MUR extends Currency {
    val getCode: String = "MUR"

    override val toString = getCode
  }

  /**
   * Maldives (Maldive Islands) Rufiyaa
   */
  object MVR extends Currency {
    val getCode: String = "MVR"

    override val toString = getCode
  }

  /**
   * Malawi Kwacha
   */
  object MWK extends Currency {
    val getCode: String = "MWK"

    override val toString = getCode
  }

  /**
   * Mexico Peso
   */
  object MXN extends Currency {
    val getCode: String = "MXN"

    override val toString = getCode
  }

  /**
   * Malaysia Ringgit
   */
  object MYR extends Currency {
    val getCode: String = "MYR"

    override val toString = getCode
  }

  /**
   * Mozambique Metical
   */
  object MZN extends Currency {
    val getCode: String = "MZN"

    override val toString = getCode
  }

  /**
   * Namibia Dollar
   */
  object NAD extends Currency {
    val getCode: String = "NAD"

    override val toString = getCode
  }

  /**
   * Nigeria Naira
   */
  object NGN extends Currency {
    val getCode: String = "NGN"

    override val toString = getCode
  }

  /**
   * Nicaragua Cordoba
   */
  object NIO extends Currency {
    val getCode: String = "NIO"

    override val toString = getCode
  }

  /**
   * Norway Krone
   */
  object NOK extends Currency {
    val getCode: String = "NOK"

    override val toString = getCode
  }

  /**
   * Nepal Rupee
   */
  object NPR extends Currency {
    val getCode: String = "NPR"

    override val toString = getCode
  }

  /**
   * New Zealand Dollar
   */
  object NZD extends Currency {
    val getCode: String = "NZD"

    override val toString = getCode
  }

  /**
   * Oman Rial
   */
  object OMR extends Currency {
    val getCode: String = "OMR"

    override val toString = getCode
  }

  /**
   * Panama Balboa
   */
  object PAB extends Currency {
    val getCode: String = "PAB"

    override val toString = getCode
  }

  /**
   * Peru Nuevo Sol
   */
  object PEN extends Currency {
    val getCode: String = "PEN"

    override val toString = getCode
  }

  /**
   * Papua New Guinea Kina
   */
  object PGK extends Currency {
    val getCode: String = "PGK"

    override val toString = getCode
  }

  /**
   * Philippines Peso
   */
  object PHP extends Currency {
    val getCode: String = "PHP"

    override val toString = getCode
  }

  /**
   * Pakistan Rupee
   */
  object PKR extends Currency {
    val getCode: String = "PKR"

    override val toString = getCode
  }

  /**
   * Poland Zloty
   */
  object PLN extends Currency {
    val getCode: String = "PLN"

    override val toString = getCode
  }

  /**
   * Paraguay Guarani
   */
  object PYG extends Currency {
    val getCode: String = "PYG"

    override val toString = getCode
  }

  /**
   * Qatar Riyal
   */
  object QAR extends Currency {
    val getCode: String = "QAR"

    override val toString = getCode
  }

  /**
   * Romania New Leu
   */
  object RON extends Currency {
    val getCode: String = "RON"

    override val toString = getCode
  }

  /**
   * Serbia Dinar
   */
  object RSD extends Currency {
    val getCode: String = "RSD"

    override val toString = getCode
  }

  /**
   * Russia Ruble
   */
  object RUB extends Currency {
    val getCode: String = "RUB"

    override val toString = getCode
  }

  /**
   * Rwanda Franc
   */
  object RWF extends Currency {
    val getCode: String = "RWF"

    override val toString = getCode
  }

  /**
   * Saudi Arabia Riyal
   */
  object SAR extends Currency {
    val getCode: String = "SAR"

    override val toString = getCode
  }

  /**
   * Solomon Islands Dollar
   */
  object SBD extends Currency {
    val getCode: String = "SBD"

    override val toString = getCode
  }

  /**
   * Seychelles Rupee
   */
  object SCR extends Currency {
    val getCode: String = "SCR"

    override val toString = getCode
  }

  /**
   * Sudan Pound
   */
  object SDG extends Currency {
    val getCode: String = "SDG"

    override val toString = getCode
  }

  /**
   * Sweden Krona
   */
  object SEK extends Currency {
    val getCode: String = "SEK"

    override val toString = getCode
  }

  /**
   * Singapore Dollar
   */
  object SGD extends Currency {
    val getCode: String = "SGD"

    override val toString = getCode
  }

  /**
   * Saint Helena Pound
   */
  object SHP extends Currency {
    val getCode: String = "SHP"

    override val toString = getCode
  }

  /**
   * Sierra Leone Leone
   */
  object SLL extends Currency {
    val getCode: String = "SLL"

    override val toString = getCode
  }

  /**
   * Somalia Shilling
   */
  object SOS extends Currency {
    val getCode: String = "SOS"

    override val toString = getCode
  }

  /**
   * Seborga Luigino
   */
  object SPL extends Currency {
    val getCode: String = "SPL"

    override val toString = getCode
  }

  /**
   * Suriname Dollar
   */
  object SRD extends Currency {
    val getCode: String = "SRD"

    override val toString = getCode
  }

  /**
   * São Tomé and Príncipe Dobra
   */
  object STD extends Currency {
    val getCode: String = "STD"

    override val toString = getCode
  }

  /**
   * El Salvador Colon
   */
  object SVC extends Currency {
    val getCode: String = "SVC"

    override val toString = getCode
  }

  /**
   * Syria Pound
   */
  object SYP extends Currency {
    val getCode: String = "SYP"

    override val toString = getCode
  }

  /**
   * Swaziland Lilangeni
   */
  object SZL extends Currency {
    val getCode: String = "SZL"

    override val toString = getCode
  }

  /**
   * Thailand Baht
   */
  object THB extends Currency {
    val getCode: String = "THB"

    override val toString = getCode
  }

  /**
   * Tajikistan Somoni
   */
  object TJS extends Currency {
    val getCode: String = "TJS"

    override val toString = getCode
  }

  /**
   * Turkmenistan Manat
   */
  object TMT extends Currency {
    val getCode: String = "TMT"

    override val toString = getCode
  }

  /**
   * Tunisia Dinar
   */
  object TND extends Currency {
    val getCode: String = "TND"

    override val toString = getCode
  }

  /**
   * Tonga Pa'anga
   */
  object TOP extends Currency {
    val getCode: String = "TOP"

    override val toString = getCode
  }

  /**
   * Turkey Lira
   */
  object TRY extends Currency {
    val getCode: String = "TRY"

    override val toString = getCode
  }

  /**
   * Trinidad and Tobago Dollar
   */
  object TTD extends Currency {
    val getCode: String = "TTD"

    override val toString = getCode
  }

  /**
   * Tuvalu Dollar
   */
  object TVD extends Currency {
    val getCode: String = "TVD"

    override val toString = getCode
  }

  /**
   * Taiwan New Dollar
   */
  object TWD extends Currency {
    val getCode: String = "TWD"

    override val toString = getCode
  }

  /**
   * Tanzania Shilling
   */
  object TZS extends Currency {
    val getCode: String = "TZS"

    override val toString = getCode
  }

  /**
   * Ukraine Hryvnia
   */
  object UAH extends Currency {
    val getCode: String = "UAH"

    override val toString = getCode
  }

  /**
   * Uganda Shilling
   */
  object UGX extends Currency {
    val getCode: String = "UGX"

    override val toString = getCode
  }

  /**
   * United States Dollar
   */
  object USD extends Currency {
    val getCode: String = "USD"

    override val toString = getCode
  }

  /**
   * Uruguay Peso
   */
  object UYU extends Currency {
    val getCode: String = "UYU"

    override val toString = getCode
  }

  /**
   * Uzbekistan Som
   */
  object UZS extends Currency {
    val getCode: String = "UZS"

    override val toString = getCode
  }

  /**
   * Venezuela Bolivar
   */
  object VEF extends Currency {
    val getCode: String = "VEF"

    override val toString = getCode
  }

  /**
   * Viet Nam Dong
   */
  object VND extends Currency {
    val getCode: String = "VND"

    override val toString = getCode
  }

  /**
   * Vanuatu Vatu
   */
  object VUV extends Currency {
    val getCode: String = "VUV"

    override val toString = getCode
  }

  /**
   * Samoa Tala
   */
  object WST extends Currency {
    val getCode: String = "WST"

    override val toString = getCode
  }

  /**
   * East Caribbean Dollar
   */
  object XCD extends Currency {
    val getCode: String = "XCD"

    override val toString = getCode
  }

  /**
   * International Monetary Fund (IMF) Special Drawing Rights
   */
  object XDR extends Currency {
    val getCode: String = "XDR"

    override val toString = getCode
  }

  /**
   * Communauté Financière Africaine (BCEAO) Franc
   */
  object XOF extends Currency {
    val getCode: String = "XOF"

    override val toString = getCode
  }

  /**
   * Comptoirs Français du Pacifique (CFP) Franc
   */
  object XPF extends Currency {
    val getCode: String = "XPF"

    override val toString = getCode
  }

  /**
   * Yemen Rial
   */
  object YER extends Currency {
    val getCode: String = "YER"

    override val toString = getCode
  }

  /**
   * South Africa Rand
   */
  object ZAR extends Currency {
    val getCode: String = "ZAR"

    override val toString = getCode
  }

  /**
   * Zambia Kwacha
   */
  object ZMW extends Currency {
    val getCode: String = "ZMW"

    override val toString = getCode
  }

  /**
   * Zimbabwe Dollar
   */
  object ZWD extends Currency {
    val getCode: String = "ZWD"

    override val toString = getCode
  }

}
