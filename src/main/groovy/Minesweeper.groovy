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
        def adjacentMines = 0
        if (hasMineOnLeft(column, line)) {
            adjacentMines++
        }
        if (hasMineOnRight(column, line)) {
            adjacentMines++
        }
        if (hasMineOnTop(line, column)) {
            adjacentMines++
        }
        if (hasMineOnLeftTop(line, column)) {
            adjacentMines++
        }
        if (hasMineOnRightTop(line, column)) {
            adjacentMines++
        }
        if (hasMineOnBottom(line, column)) {
            adjacentMines++
        }
        if (hasMineOnLeftBottom(line, column)) {
            adjacentMines++
        }
        if (hasMineOnRightBottom(line, column)) {
            adjacentMines++
        }
        adjacentMines.toString()
    }

    private boolean hasMineOnBottom(int line, int column) {
        cellAt(new Position(line + 1, column))?.isMine()
    }

    private boolean hasMineOnLeftBottom(int line, int column) {
        cellAt(new Position(line + 1, column - 1))?.isMine()
    }

    private boolean hasMineOnRightBottom(int line, int column) {
        cellAt(new Position(line + 1, column + 1))?.isMine()
    }

    private boolean hasMineOnTop(int line, int column) {
        cellAt(new Position(line - 1, column))?.isMine()
    }

    private boolean hasMineOnRightTop(int line, int column) {
        cellAt(new Position(line - 1, column - 1))?.isMine()
    }

    private boolean hasMineOnLeftTop(int line, int column) {
        cellAt(new Position(line - 1, column + 1))?.isMine()
    }

    boolean hasMineOnRight(int column, int line) {
        cellAt(new Position(line, column + 1))?.isMine()
    }

    boolean hasMineOnLeft(int column, int line) {
        cellAt(new Position(line, column - 1))?.isMine()
    }

    private String newLine(int line) {
        line > 0 ? "\n" : ""
    }

}
