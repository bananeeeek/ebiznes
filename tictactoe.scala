class Game{

  val board = Array(Array("_", "_", "_"), Array("_", "_", "_"), Array("_", "_", "_"))

  var currentPlayer = "X"

  def play(mark: String, pos : List[Int]) : Boolean = {
    if(mark == currentPlayer && board(pos(0))(pos(1)) == "_"){
      board(pos(0))(pos(1)) = mark
      if(currentPlayer == "X") { currentPlayer = "O"} else { currentPlayer = "X"}

      return true
    }
    else {
      return false
    }
  }


  def status : String = {
    def row(index : Int) = {
      board(index)
    }

    def col(index : Int) = {
      board.map { row => row(index)}
    }

    def winner(line : Array[String]) = {
      if(!line(0).equals("_") && line(0).equals(line(1)) && line(1).equals(line(2))){
        true
      }
      else {
        false
      }
    }

    def boardIsFull() : Boolean = {
      board.foreach { row =>
        row.foreach { element => if(element == "_")
          return false
        }
      }
      return true
    }


    val rows = (0 to board.size-1).map { index => row(index)}
    val cols = (0 to board.size-1).map { index => col(index)}
    val diag1 = Array(board(0)(0), board(1)(1), board(2)(2))
    val diag2 = Array(board(0)(2), board(1)(1), board(2)(0))

    val lines = rows ++ cols ++ Array(diag1, diag2)

    lines.foreach { line =>
      if(winner(line)) {
        return "WINNER is " + line(0)
      }
    }

    if(boardIsFull()){
      return "Draw"
    }

    return "Unfinished"
  }

  def printBoard = {
    board.foreach { row => println("" + row(0) + " | "
      + row(1) + " | "
      + row(2) )}
  }
}

val game = new Game

while(game.status == "Unfinished"){

  var row =0;
  var col=0;
  game.printBoard
  println(game.currentPlayer + "'s turn.\n")
  var valid = false
  do {
    println("Provide row number(1-3): ")
    row = scala.io.StdIn.readInt()
    println("Provide column number(1-3): ")
    val col = scala.io.StdIn.readInt()
    valid = game.play(game.currentPlayer , List(row-1,col-1))
  } while(valid == false)
}

game.printBoard
println(game.status)