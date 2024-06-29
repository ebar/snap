package com.example.snap

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DealerSpec extends AnyFlatSpec with Matchers {

  val aceOfSpades: Card = Card(Spades, Ace)
  val aceOfHearts: Card = Card(Hearts, Ace)
  val kingOfDiamonds: Card = Card(Diamonds, King)

  it should "initialise game with a list of player names" in {
    val dealer = new Dealer(List("Alice", "Bob"))
    dealer.playerNames shouldBe(List("Alice", "Bob"))
  }

  it should "deal one card each to two players, given two cards" in {
    val dealer = new Dealer(List("Alice", "Bob"))
    val players: Map[String, List[Card]] = dealer.dealCards(List(aceOfHearts, aceOfSpades))
    players.head._1 shouldBe "Bob"
    players.head._2 shouldBe List(aceOfSpades)
    players.last._1 shouldBe "Alice"
    players.last._2 shouldBe List(aceOfHearts)
  }

  it should "deal one card each to three players, given three cards" in {
    val dealer = new Dealer(List("Alice", "Bob", "Charlie"))
    val playersAndCards: Map[String, List[Card]] = dealer.dealCards(List(aceOfHearts, aceOfSpades, kingOfDiamonds))
    playersAndCards.keySet shouldBe Set("Alice", "Bob", "Charlie")
    playersAndCards("Alice") shouldBe List(aceOfHearts)
    playersAndCards("Bob") shouldBe List(aceOfSpades)
    playersAndCards("Charlie") shouldBe List(kingOfDiamonds)
  }

  it should "deal one deck to two players" in {
    val dealer = new Dealer(List("Alice", "Bob"))
    val playersAndCards: Map[String, List[Card]] = dealer.deal()
    playersAndCards.keySet shouldBe Set("Alice", "Bob")
    playersAndCards("Bob").size shouldBe 26
    playersAndCards("Alice").size shouldBe 26
  }

  it should "deal one deck to four players" in {
    val dealer = new Dealer(List("Alice", "Bob", "Charlie", "Dave"))
    val playersAndCards: Map[String, List[Card]] = dealer.deal()
    playersAndCards.keySet shouldBe Set("Alice", "Bob", "Charlie", "Dave")
    playersAndCards("Alice").size shouldBe 13
    playersAndCards("Bob").size shouldBe 13
    playersAndCards("Charlie").size shouldBe 13
    playersAndCards("Dave").size shouldBe 13
  }


  it should "deal two decks to two players" in {
    val dealer = new Dealer(List("Alice", "Bob"), 2)
    val playersAndCards: Map[String, List[Card]] = dealer.deal()
    playersAndCards.keySet shouldBe Set("Alice", "Bob")
    playersAndCards("Alice").size shouldBe 52
    playersAndCards("Bob").size shouldBe 52
  }


}
