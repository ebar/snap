package com.example.snap

import scala.annotation.tailrec

class Game(initialHands: List[Player], matchCriteria: MatchCriteria) {

  private val playerIterator: Iterator[String] = LazyList.from(0).flatMap(_ => initialHands.map(_.name)).iterator
  private def nextPlayer(): String = playerIterator.next()
  def play(): Player = {

    @tailrec
    def playRec(player: String, players: List[Player]): Player = {
        players.find(_.name.equals(player)) match {
        case Some(currentPlayer) =>
          // Skip turn if current player is out
          if (currentPlayer.isOut) playRec(nextPlayer(), players)
          else {
            val opponents: List[Player] = players.filterNot(_.name.equals(currentPlayer.name))
            // Return player if all the other players are out
            if (opponents.forall(_.isOut)) currentPlayer
            else {
              val (cardInPlay, updatedPlayerHand) = currentPlayer.playCard
              val (winningCards, newOpponents) = calculateWinningCardsAndOpponents(currentPlayer, opponents, cardInPlay)
              val newPlayerHand: Player = if (winningCards.isEmpty) updatedPlayerHand
              else currentPlayer.winCards(winningCards)
              playRec(nextPlayer(), newPlayerHand :: newOpponents)
            }
          }
        case None => playRec(nextPlayer(), players)
      }
    }
    playRec(nextPlayer(), initialHands)
  }

  private def calculateWinningCardsAndOpponents(currentPlayer: Player, otherPlayers: List[Player], card: Card): (List[Card], List[Player]) = {
    val handsAndWinningCards: Seq[(List[Card], Player)] = otherPlayers.map{ player =>
      if (currentPlayer.snap(isMatch(card, player.faceUpCard))) {
        (player.faceUpCards, player.loseFaceUpCards)
      } else (Nil, player)
    }
    val opponentsHands = handsAndWinningCards.map(_._2)
    val winningCards = handsAndWinningCards.flatMap(_._1)
    (winningCards.toList, opponentsHands.toList)
  }

  private def isMatch(card: Card, faceUpCard: Option[Card]): Boolean = {
    matchCriteria match {
      case Suit => faceUpCard.map(_.suit).contains(card.suit)
      case Value => faceUpCard.map(_.value).contains(card.value)
      case Both => faceUpCard.contains(card)
    }
  }

}
