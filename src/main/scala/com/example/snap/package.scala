package com.example

package object snap {

  sealed trait Suit
  case object Hearts extends Suit
  case object Diamonds extends Suit
  case object Clubs extends Suit
  case object Spades extends Suit

  val allSuits: List[Suit] = List(Hearts, Clubs, Diamonds, Spades)

  sealed trait CardValue
  case object Two extends CardValue
  case object Three extends CardValue
  case object Four extends CardValue
  case object Five extends CardValue
  case object Six extends CardValue
  case object Seven extends CardValue
  case object Eight extends CardValue
  case object Nine extends CardValue
  case object Ten extends CardValue
  case object Jack extends CardValue
  case object Queen extends CardValue
  case object King extends CardValue
  case object Ace extends CardValue

  val allValues: List[CardValue] = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)

  case class Card(suit: Suit, value: CardValue) {
    def prettyPrint = s"The $value of $suit"
  }

  sealed trait MatchCriteria

  case object Suit extends MatchCriteria
  case object Value extends MatchCriteria
  case object Both extends MatchCriteria

  object MatchCriteria {

    def apply(char: Char): Option[MatchCriteria] = {
      char match {
        case 'S' | 's' => Some(Suit)
        case 'V' | 'v' => Some(Value)
        case 'B' | 'b' => Some(Both)
        case _ => None
      }
    }
  }
}


