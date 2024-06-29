package com.example.snap

import scala.annotation.tailrec
import scala.collection.mutable

class Dealer(val playerNames: List[String], val decks: Int = 1) {

  private val playerIterator: Iterator[String] = LazyList.continually(playerNames).flatten.iterator
  private def nextPlayer(): String = playerIterator.next()
  def deal(): Map[String, List[Card]] = {
    val deckList: List[Deck] = (for (i <- 1 to decks) yield Deck().shuffle()).toList
    dealCards(deckList.flatMap(_.cards))
  }
  def dealCards(cards: List[Card]): Map[String, List[Card]] = {
    @tailrec
    def dealRec(hands: mutable.Map[String, List[Card]], deck: List[Card], player: String): Map[String, List[Card]] = {
      deck.headOption match {
        case Some(c) =>
          hands.remove(player) match {
            case Some(hand) => hands.put(player, c :: hand)
            case None => hands.put(player, List(c))
          }
          dealRec(hands, deck.tail, nextPlayer())
        case None => hands.toMap
      }
    }
    dealRec(mutable.Map.empty, cards, playerIterator.next())
  }

}




