# scoreboard
A basic scoreboard

Notes :

1 The counter to track active games will if used heavily enough exceed that of an Integer variable and should
be re-worked in order to avoid this. ie at 10 000 could add code to re-set the counter.

2 In order to avoid concurrency issues synchronized is used on methods which edit or make use of the counter and maps
tracking and storing game information. ie startMatch() finishMatch() and updateScore()

3 An implementation of Comparator is used to make the comparison between BoardItem objects in order to sort as required.
The boardItem doesn't sort itself as this class should be independent of any sorting logic. 

4 Basic HashMaps are used to keep track of the games and theGameCounter which allows for straightforward 
sorting with the Comparator implementation.
