Deck of Cards
===========

Solution contained in file Deck.java. I used Java because as a very Object Oriented language it is a lot more natural to implement this framework in Java. The Deck of Cards is represented by a class Deck which has a nested class Card to represent cards. All methods are in Deck; Cards are treated as inanimate data.Constants are used to make future additions and modifications more flexible, e.g. if we wanted to have 60 cards and 5 suits for example. 

ArrayLists were used over Arrays to take advantage of built in shuffling in java.util.Collections. An index based system rather than removal was used so that cards would not have to be regenerated on shuffle.

For GetNextCard(), there was an ambiguity of what kind of error to signal when the entire deck has been dealt. I decided to just print an error message for now and return null without throwing any exceptions. I think it would be a little bloated to create a special exception for this instance and I leave it to users of the framework to null check on that method.

In the main method of the Deck.java file there are a few basic tests I wrote to verify to myself that my framework was correct. More thorough testing could be done using JUnit and other testing frameworks, but I think this Deck of Cards framework is simple enough that the few basic tests should cover most use cases.