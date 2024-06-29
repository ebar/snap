package com.example.snap

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PlayerSpec extends AnyFlatSpec with Matchers {

  val twoOfDiamonds: Card = Card(Diamonds, Two)
  val fourOfHearts: Card = Card(Hearts, Four)

  it should "initialise player with a name and an initial hand" in {
    val player = Player("player1", List(twoOfDiamonds))
    player.name shouldBe "player1"
    player.remainingCards shouldBe List(twoOfDiamonds)
    player.faceUpCard shouldBe None
    player.faceUpCards shouldBe Nil
  }

  it should "not be out if player has remaining cards" in {
    val player = Player("player1", List(twoOfDiamonds))
    player.isOut shouldBe false
  }

  it should "be out if player has no remaining cards" in {
    val player = Player("player1", Nil)
    player.isOut shouldBe true
  }

  it should "play a card from hand to the stack" in {
    val player = Player("player1", List(twoOfDiamonds))
    val (card, playerAfterCard) = player.playCard
    card shouldBe(twoOfDiamonds)
    playerAfterCard.faceUpCards shouldBe List(twoOfDiamonds)
    playerAfterCard.remainingCards shouldBe Nil
  }

  it should "throw NoSuchElementException if trying to play with no cards left" in {
    assertThrows[NoSuchElementException] {
      val player = Player("player1", Nil)
      player.playCard
    }
  }

  it should "win cards by adding cards to the player's hand" in {
    val player = Player("player1", List(twoOfDiamonds))
    val winningCards = List(fourOfHearts)
    val newPlayer = player.winCards(winningCards)
    newPlayer.remainingCards shouldBe List(twoOfDiamonds, fourOfHearts)
  }

  it should "win cards by adding cards to the player's hand, and player should collect their stack" in {
    val player = Player("player1", List(twoOfDiamonds))
    val playerAfterCard = player.playCard._2
    val winningCards = List(fourOfHearts)
    playerAfterCard.winCards(winningCards).remainingCards shouldBe List(twoOfDiamonds, fourOfHearts)
  }

  it should "lose face up cards" in {
    val player = Player("player1", List(twoOfDiamonds))
    val playerAfterCard = player.playCard._2

    playerAfterCard.loseFaceUpCards.faceUpCards shouldBe Nil
    playerAfterCard.remainingCards shouldBe Nil
    playerAfterCard.isOut shouldBe true
  }

}
