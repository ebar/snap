package com.example.snap

import scala.util.Random

case class Deck(cards: List[Card]) {
  def shuffle(random: Random = new Random()): Deck = new Deck(random.shuffle(cards))

}

case object Deck {
  def apply(): Deck = {
    new Deck(allSuits.flatMap(s => allValues.map(v => Card(s, v))))
  }
}
