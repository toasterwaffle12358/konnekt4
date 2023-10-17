

fun main(args: Array<String>) {

    //setting up colors
    // Everything after this is in red
    val colorRed = "\u001b[38;5;197m"
    // Everything after this is in yellow
    val colorYellow = "\u001b[38;5;226m"
    // Resets previous color codes
    val colorReset = "\u001b[0m"

    //will be true until game is won or quit, used to keep game while loop running
    var playing = true

    //shows the active player, true if the current player is player1 (yellow) false if player 2 (red) (what color piece will be placed on this turn)
    var isCurrentPlayerOne = true
    //used for editing board
    var playerNumber = 0
    //has the coordinates of where the last piece was placed (used for checking if there is a win, that way I don't have to check every possible location)
    var lastPiecePlacedCoord = mutableListOf(0,0)

    //counter that will show the current number of the same type of piece in a row
    var piecesInARowCounter = 0

    //setting up board
    var boardLineOne = mutableListOf(0,0,0,0,0,0,0)
    var boardLineTwo = mutableListOf(0,0,0,0,0,0,0)
    var boardLineThree = mutableListOf(0,0,0,0,0,0,0)
    var boardLineFour = mutableListOf(0,0,0,0,0,0,0)
    var boardLineFive = mutableListOf(0,0,0,0,0,0,0)
    var boardLineSix = mutableListOf(0,0,0,0,0,0,0)
    var board = mutableListOf(boardLineOne, boardLineTwo, boardLineThree, boardLineFour, boardLineFive, boardLineSix)

    //setting up map for board
    val boardMap = mapOf(1 to colorYellow + "●" + colorReset, 2 to colorRed + "●" + colorReset)


    println("Welcome to connect four!")

    while (playing) {

        //clearing terminal
        print("\u001b[H"+"\u001b[2J")

        //setting up the playerNumber variable, it will be used later to place the pieces so each player places a different int.
        when (isCurrentPlayerOne) {
            true ->  playerNumber = 1
            false -> playerNumber = 2
        }

        //printing the board
        for (row in board) {
            println(row)
        }

        //printing the current player
        when (isCurrentPlayerOne) {
            true -> println("player " + colorYellow +"one's " + colorReset + "turn")
            false -> println("player " + colorRed + "two's " + colorReset + "turn")
        }
        println("type the number of the row you want to place in (1-7)")

        //getting player input for the column they want to place in, then turning it into an int and checking if it is an allowed input
        val input = readln()
        val inputInt = input.toIntOrNull()
        if (inputInt == null || inputInt >= 8) {
            println("incorrect syntax, make sure input is a number from 1 to 7")
            continue
        }

        //placing piece, testing all rows from top to bottom to see if there's a piece there.
        when {
            boardLineSix[inputInt - 1] == 0 -> {
                boardLineSix[inputInt - 1] = playerNumber
                lastPiecePlacedCoord = mutableListOf(inputInt,6)
            }
            boardLineFive[inputInt - 1] == 0 -> {
                boardLineFive[inputInt - 1] = playerNumber
                lastPiecePlacedCoord = mutableListOf(inputInt,5)
            }
            boardLineFour[inputInt - 1] == 0 -> {
                boardLineFour[inputInt - 1] = playerNumber
                lastPiecePlacedCoord = mutableListOf(inputInt,4)
            }
            boardLineThree[inputInt - 1] == 0 -> {
                boardLineThree[inputInt - 1] = playerNumber
                lastPiecePlacedCoord = mutableListOf(inputInt,3)
            }
            boardLineTwo[inputInt - 1] == 0 -> {
                boardLineTwo[inputInt - 1] = playerNumber
                lastPiecePlacedCoord = mutableListOf(inputInt,2)
            }
            boardLineOne[inputInt - 1] == 0 -> {
                boardLineOne[inputInt - 1] = playerNumber
                lastPiecePlacedCoord = mutableListOf(inputInt,1)
            }
            else -> {
                println("sorry, you cant place in this spot anymore, try again")
                continue
            }
        }

        //testing purposes, remove later
        println("your last play was put on coordinate $lastPiecePlacedCoord")

        //testing to see if user won
        when (lastPiecePlacedCoord[1]) {
            6 -> for (item in boardLineSix){
                if (item == playerNumber) {
                    piecesInARowCounter++
                    println(piecesInARowCounter)
                    if (piecesInARowCounter >= 4) {
                        println("omg player $playerNumber, you won! no way omg")
                        break
                    }
                } else {
                    piecesInARowCounter = 0
                }
            }
        }


        //switching player
        isCurrentPlayerOne = !isCurrentPlayerOne
    }

}