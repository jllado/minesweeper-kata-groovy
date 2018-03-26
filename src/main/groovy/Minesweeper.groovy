class Minesweeper {

    private int numberLines
    private int numberColumns
    private List<String> lines

    Minesweeper(int numberLines, int numberColumns, List<String> lines) {
        this.lines = lines
        this.numberColumns = numberColumns
        this.numberLines = numberLines
    }

    String generateField() {
        def field = ""
        for (int line = 0; line < numberLines; line++) {
            field += newLine(line)
            for (int column = 0; column < numberColumns; column++) {
                field += cell(line, column)
            }
        }
        field
    }

    private char cell(int line, int column) {
        def currentCell = cellAt(new Position(line, column))
        if (currentCell.isMine()) {
            return Cell.MINE
        } else {
            return countAdjacentMines(line, column)
        }
    }

    private Cell cellAt(Position position) {
        if (position.line < 0 || position.line == numberLines) {
            return null
        }
        if (position.column < 0 || position.column == numberColumns) {
            return null
        }
        new Cell(lines[position.line].charAt(position.column))
    }

    private String countAdjacentMines(int line, int column) {
        adjacentPositions(new Position(line, column)).collect {cellAt(it)}.findAll {it?.isMine()}.size().toString()
    }

    private List<Position> adjacentPositions(Position position) {
        [new Position(position.line - 1, position.column),
         new Position(position.line + 1, position.column),
         new Position(position.line, position.column + 1),
         new Position(position.line - 1, position.column + 1),
         new Position(position.line + 1, position.column + 1),
         new Position(position.line - 1, position.column - 1),
         new Position(position.line + 1, position.column - 1),
         new Position(position.line, position.column - 1)]
    }

    private String newLine(int line) {
        line > 0 ? "\n" : ""
    }

}
