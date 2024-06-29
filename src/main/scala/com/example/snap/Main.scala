package com.example.snap

import scala.io.StdIn

object Main {

  def main(args: Array[String]): Unit = {
    println("Game of snap")
    println("Enter number of decks (1-10):")
    val userInput1: Int = StdIn.readInt()
    val decks  = userInput1 match {
      case d if d > 0 && d <= 10 => Some(d)
      case _ => None
    }
    println("Match by suit (S), value (V), both (B):")
    val userInput2: Char = StdIn.readChar()
    val matchCriteria: Option[MatchCriteria] = MatchCriteria(userInput2)
    (matchCriteria, decks) match {
      case (Some(m), Some(d)) =>
        println(s"You entered $d decks. Match Criteria $m")
        val dealer = new Dealer(List("Alice", "Bob"), d)
        val hands: List[Player] = dealer.deal().map { case (k, v) => Player(k, v) }.toList
        val winner: Player = new Game(hands, m).play()
        println(s"${winner.name} wins!")
      case _ => println(s"Invalid user input: $userInput1, $userInput2")
    }
  }
}
