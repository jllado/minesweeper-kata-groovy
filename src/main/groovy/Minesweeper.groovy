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
        def currentCell = cellAt(line, column)
        if (currentCell.isMine()) {
            return Cell.MINE
        } else {
            return countAdjacentMines(line, column)
        }
    }

    private Cell cellAt(int line, int column) {
        new Cell(lines[line].charAt(column))
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
        if (line == numberLines - 1) {
            return false
        }
        def bottom = cellAt(line + 1, column)
        bottom.isMine()
    }

    private boolean hasMineOnLeftBottom(int line, int column) {
        if (line == numberLines - 1 || column == 0) {
            return false
        }
        def leftBottom = cellAt(line + 1, column - 1)
        leftBottom.isMine()
    }

    private boolean hasMineOnRightBottom(int line, int column) {
        if (line == numberLines - 1 || column == numberColumns - 1) {
            return false
        }
        def rightBottom = cellAt(line + 1, column + 1)
        rightBottom.isMine()
    }

    private boolean hasMineOnTop(int line, int column) {
        if (line == 0) {
            return false
        }
        def top = cellAt(line - 1, column)
        top.isMine()
    }

    private boolean hasMineOnRightTop(int line, int column) {
        if (line == 0 || column == 0) {
            return false
        }
        def rightTop = cellAt(line - 1, column - 1)
        rightTop.isMine()
    }

    private boolean hasMineOnLeftTop(int line, int column) {
        if (line == 0 || column == numberColumns - 1) {
            return false
        }
        def leftTop = cellAt(line - 1, column + 1)
        leftTop.isMine()
    }

    boolean hasMineOnRight(int column, int line) {
        if (column == numberColumns - 1) {
            return false
        }
        def right = cellAt(line, column + 1)
        right.isMine()
    }

    boolean hasMineOnLeft(int column, int line) {
        if (column == 0) {
            return false
        }
        cellAt(line, column - 1).isMine()
    }

    private String newLine(int line) {
        line > 0 ? "\n" : ""
    }

}
