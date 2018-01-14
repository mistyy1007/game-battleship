# game-battleship
A solo version of the game battleship, human attempts to sink computer's ships.

The computer places the ten ships on the ocean in such a way that no ships are immediately adjacent to each other, either horizontally, vertically, or diagonally. 

The human player does not know where the ships are. The initial display of the ocean shows a 10 by 10 array of locations, all the same.

The human player tries to hit the ships, by calling out a row and column number. The computer responds with one bit of information--hit" or "miss." When a ship is hit but not sunk, the program does not provide any information about what kind of a ship was hit. However, when a ship is hit and sinks, the program prints out a message "You just sank a ship-type." After each shot, the computer redisplays the ocean with the new information.

A ship is "sunk" when every square of the ship has been hit. Thus, it takes four hits (in four different places) to sink a battleship, three to sink a cruiser, two for a destroyer, and one for a submarine. The object is to sink the fleet with as few shots as possible; the best possible score would be 20. (Low scores are better.) When all ships have been sunk, the program prints out a message that the game is over, and tells how many shots were required.

