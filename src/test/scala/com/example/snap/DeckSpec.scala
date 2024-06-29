package com.example.snap

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.Random

class DeckSpec extends AnyFlatSpec with Matchers {

  val random: Random = new Random
  random.setSeed(100L)
  val deck: Deck = Deck()

  it should "create a new deck, with 13 cards of each suit, and 4 copies of each value" in {
    deck.cards.size shouldBe 52
    List(Hearts, Clubs, Diamonds, Spades).map(suit =>
      deck.cards.count(_.suit.equals(suit)) shouldBe 13
    )
    List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace).map(value =>
      deck.cards.count(_.value.equals(value)) shouldBe 4
    )
  }

  it should "shuffle deck to create a newly ordered deck" in {
    val shuffled: Deck = deck.shuffle(random)
    shuffled.cards.size shouldBe 52
    shuffled.cards.head shouldBe(Card(Spades, Ten))
    shuffled.cards.last shouldBe(Card(Spades, Ace))
  }

}
