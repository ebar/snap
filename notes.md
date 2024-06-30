## Design decisions

- The game and deal mechanisms are implemented with recursive functions, to avoid mutable state within the game. This means that changes to player state result in a new player. Mutable map is used within the deal function but not outside.


## Additional features
- The game supports more than two players, but it is currently hardcoded to two in the Main. This can be modified by allowing the user to enter a list of player names.

## Future Improvements
- The dealer was implemented with a map of String -> List[Card], but the game was implemented with List[Player]. With more time, this could be refactored so that both use the same mechanism and the same playerIterator could be reused.
- Currently the snap function only returns the result of isMatch. This could be modified in future so that a user can call snap at some point during the game.
- User input and validation could be extracted out and tested separately
- Game details are currently printed out to the console, but in a real environment this can be replaced by a proper logging framework.


## Running the project
The project is built with Scala 2.13.8 and sbt 1.9.7. It is run using sbt run and tested usung sbt test