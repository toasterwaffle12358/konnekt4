//imports
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    //setting up colors
    // Everything after this is in red
    val colorRed = "\u001b[38;5;197m"
    // Everything after this is in yellow
    val colorYellow = "\u001b[38;5;226m"
    // Resets previous color codes
    val colorReset = "\u001b[0m"


    //functions
    //function to check rows to see if there's a 4 in a row in them, will be run on the row of the last placed piece
    fun winCheckRow(item: Int, playerNumber: Int, piecesInARowCounter: Int, board: MutableList<MutableList<Int>>): Int {
        var piecesInARowCounterMutable = piecesInARowCounter
        if (item == playerNumber) {
            piecesInARowCounterMutable++
            if (piecesInARowCounterMutable >= 4) {

                for (row in board) {
                    for (item in row) {
                        when (item) {
                            0 -> print("O ")
                            1 -> print(colorYellow + "● " + colorReset)
                            2 -> print(colorRed + "● " + colorReset)
                        }
                    }
                    println("")
                    //println(row)
                }

                when (playerNumber) {
                    1 -> println("omg player\u001B[38;5;226m one,\u001B[0m you won! no way omg!!!")
                    2 -> println("omg player\u001B[38;5;197m two,\u001B[0m you won! no way omg!!!")
                }
                exitProcess(0)
            }
        } else {
            piecesInARowCounterMutable = 0
        }
        return piecesInARowCounterMutable
    }
    fun winCheckColumn() {

    }

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
        if (isCurrentPlayerOne) {
            playerNumber = 1
        } else {
            playerNumber = 2
        }

        //printing the board
        for (row in board) {
            for (item in row) {
                when (item) {
                    0 -> print("O ")
                    1 -> print(colorYellow + "● " + colorReset)
                    2 -> print(colorRed + "● " + colorReset)
                }
            }
            println("")
            //println(row)
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
        //println("your last play was put on coordinate $lastPiecePlacedCoord")

        //adding space for new board
        println("\n \n \n")

        //testing to see if user won
        //testing rows
        when (lastPiecePlacedCoord[1]) {
            6 -> for (item in boardLineSix){
                piecesInARowCounter = winCheckRow(item, playerNumber, piecesInARowCounter, board)
            }
            5 -> for (item in boardLineFive){
                piecesInARowCounter = winCheckRow(item, playerNumber, piecesInARowCounter, board)
            }
            4 -> for (item in boardLineFour){
                piecesInARowCounter = winCheckRow(item, playerNumber, piecesInARowCounter, board)
            }
            3 -> for (item in boardLineThree){
                piecesInARowCounter = winCheckRow(item, playerNumber, piecesInARowCounter, board)
            }
            2 -> for (item in boardLineTwo){
                piecesInARowCounter = winCheckRow(item, playerNumber, piecesInARowCounter, board)
            }
            1 -> for (item in boardLineOne){
                piecesInARowCounter = winCheckRow(item, playerNumber, piecesInARowCounter, board)
            }

        }
        //put stuff here



        //switching player
        isCurrentPlayerOne = !isCurrentPlayerOne
    }

}