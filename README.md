Deck of Cards
===========

Solution contained in file Deck.java. I used Java because as a very Object Oriented language it is a lot more natural to implement this framework in Java. The Deck of Cards is represented by a class Deck which has a nested class Card to represent cards. All methods are in Deck; Cards are treated as inanimate data.Constants are used to make future additions and modifications more flexible, e.g. if we wanted to have 60 cards and 5 suits for example. 

ArrayLists were used over Arrays to take advantage of built in shuffling in java.util.Collections. An index based system rather than removal was used so that cards would not have to be regenerated on shuffle.

For GetNextCard(), there was an ambiguity of what kind of error to signal when the entire deck has been dealt. I decided to just print an error message for now and return null without throwing any exceptions. I think it would be a little bloated to create a special exception for this instance and I leave it to users of the framework to null check on that method.

In the main method of the Deck.java file there are a few basic tests I wrote to verify to myself that my framework was correct. More thorough testing could be done using JUnit and other testing frameworks, but I think this Deck of Cards framework is simple enough that the few basic tests should cover most use cases.


Vacationing Salesman
===========

Solution contained in the file vacationing-salesman.py. To run, use python 2.7 with requests module installed. Commands are given as such:

python vacationing-salesman.py [--flags] < cities.txt

where --flags consists of options such as --kilometers for switching to km units, --optimize for optimized path, etc. and cities.txt is a text file with each line the string "City, Country" for each destination.

I chose python as a language because I have previous experience using the requests library for python, and I feel that for web requests it is a lot easier and faster to write in than Java. 

Google's Geocoding API was used for the as the crow flies distance. The idea was to geocode the "City, Country" strings into latitude and longitude coordinates. From there I could mathematically calculate the as the crow flies distance between each pair of cities. HTTP requests were sent to query Google's Geocoding API to extract the relevant information. The requests library for python has a built in json decoder which I used to parse what Google's API returned. From what I could tell, Google did not have an API to get as to get as the crow flies distance, which necessitated the calculation using the Haversine formula. I did not investigate other map APIs out there, since I felt Google's was sufficient. If I had more time perhaps I would look into the other APIs available.

But, a good reason for using Google's API is they also have a Distance Matrix API which would have allowed me to specify travel modes such as driving, walking, and transit instead of using as the crow flies distance. Unfortunately, I ran out of time to fully implement this feature. Below is a small code snippet of what the payload to one of the requests to Google's Distance Matrix API would have looked like:

payload_orig = {'origins': origin[0] + ' ' + origin[1],
		           'destinations': dest[0] + ' ' + dest[1],
		           'key': key,
		           'mode': 'driving',
		           'units': 'imperial',
		           }
where origin and dest are tuples of (city, country) strings. Google's Distance Matrix API lets users request from multiple origins and destinations, but at the end of the day URLs are limited to 2000 characters. For large lists of cities, it would be ideal to block off cities into groups of 2000 characters to save requests.

To find the optimal path, my solution was to run Dijkstra's shortest path algorithm from all cities, and then select the shortest path from all of the cities. I converted the as the crow flies distances between cities into a graph representation. Unfortunately I was unable to finish my implementation of Dijkstra's algorithm. The Floyd Warshall algorithm would have worked as well, but I am more familiar with Dijkstra's so it would be easier for me to implement.

If I had more time I would have also added more error handling throughout the program, since querying web APIs can be unreliable at times. I would have also optimized by caching "returns" to a destination so that I would not have to re-request the coordinates again (e.g. if I visited Paris twice, on the second visit I don't want to request for the coordinates again, because I should have saved them before).