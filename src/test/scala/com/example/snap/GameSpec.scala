package com.example.snap

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GameSpec extends AnyFlatSpec with Matchers {

  val aceOfSpades: Card = Card(Spades, Ace)
  val aceOfHearts: Card = Card(Hearts, Ace)
  val kingOfHearts: Card = Card(Hearts, King)
  val fourOfDiamonds: Card = Card(Diamonds, Four)

  it should "return the winner when no cards are matched" in {
    val initialHands = List(Player("Alice", List(aceOfSpades)), Player("Bob", List(kingOfHearts)))
    val game = new Game(initialHands, Both)
    val winner = game.play()
    winner.name shouldBe "Bob"
    winner.remainingCards shouldBe List(kingOfHearts)
    winner.faceUpCards shouldBe Nil
  }

  it should "play game with three cards and two players matching by suit" in {
    val initialHands = List(Player("Alice", List(aceOfHearts, aceOfSpades)), Player("Bob", List(kingOfHearts)))
    val game = new Game(initialHands, Suit)
    val winner = game.play()
    winner.name shouldBe "Bob"
    winner.remainingCards shouldBe List(kingOfHearts, aceOfHearts)
  }


  it should "play game with three cards and two players matching by value" in {
    val initialHands = List(Player("Alice", List(aceOfSpades, kingOfHearts)), Player("Bob", List(aceOfHearts)))
    val game = new Game(initialHands, Value)
    val winner = game.play()
    winner.name shouldBe "Bob"
    winner.remainingCards shouldBe List(aceOfHearts, aceOfSpades)
  }

  it should "return the winner when no cards are matched - three players" in {
    val initialHands = List(Player("Alice", List(aceOfHearts)), Player("Bob", List(kingOfHearts)), Player("Charlie", List(aceOfSpades)))
    val game = new Game(initialHands, Both)
    val winner = game.play()
    winner.name shouldBe "Charlie"
    winner.remainingCards shouldBe List(aceOfSpades)
  }

  it should "play game with four cards and three players matching by suit" in {
    val initialHands = List(Player("Alice", List(aceOfHearts, aceOfSpades)), Player("Bob", List(fourOfDiamonds)), Player("Charlie", List(kingOfHearts)))
    val game = new Game(initialHands, Suit)
    val winner = game.play()
    winner.name shouldBe "Charlie"
    winner.remainingCards shouldBe List(kingOfHearts, aceOfHearts)
  }

  it should "play game with four cards and three players matching by value" in {
    val initialHands = List(Player("Alice", List(aceOfHearts, fourOfDiamonds)), Player("Bob", List(kingOfHearts)), Player("Charlie", List(aceOfSpades)))
    val game = new Game(initialHands, Value)
    val winner = game.play()
    winner.name shouldBe "Charlie"
    winner.remainingCards shouldBe List(aceOfSpades, aceOfHearts)
  }

}
