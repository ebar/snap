package com.example.snap

case class Player(name: String, remainingCards: List[Card], faceUpCards: List[Card]) {

  val faceUpCard: Option[Card] = faceUpCards.headOption
  def isOut: Boolean = remainingCards.isEmpty

  def playCard: (Card, Player) = {
    val currentCard = remainingCards.head
    println(s"${name} plays ${currentCard.prettyPrint}")
    (currentCard, Player(name, remainingCards.tail, currentCard :: faceUpCards))
  }

  def winCards(winningCards: List[Card]): Player = {
    println(s"$name Wins ${winningCards.size + remainingCards.size} cards")
    Player(name, remainingCards ++ faceUpCards ++ winningCards, Nil)
  }

  def loseFaceUpCards: Player = Player(name, remainingCards, Nil)

  //TODO: this can be modified to respond to user input
  def snap(isMatch: Boolean): Boolean = {
    if (isMatch) println(s"$name calls SNAP!")
    isMatch
  }

}

object Player {
  def apply(name: String, initialHand: List[Card]): Player = Player(name, initialHand, Nil)
}
