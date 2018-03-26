class Minesweeper {

    public static final String MINE = "*"

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
                field += cell(line, column, numberColumns, numberLines)
            }
        }
        field
    }

    private char cell(int line, int column, int numberColumns, int numberLines) {
        def currentCell = cellAt(line, column)
        if (isMine(currentCell)) {
            return MINE
        } else {
            return countAdjacentMines(line, column, numberColumns, numberLines)
        }
    }

    private char cellAt(int line, int column) {
        lines[line].charAt(column)
    }

    private String countAdjacentMines(int line, int column, int numberColumns, int numberLines) {
        def adjacentMines = 0
        if (hasMineOnLeft(column, line)) {
            adjacentMines++
        }
        if (hasMineOnRight(column, line, numberColumns)) {
            adjacentMines++
        }
        if (hasMineOnTop(line, column)) {
            adjacentMines++
        }
        if (hasMineOnLeftTop(line, column, numberColumns)) {
            adjacentMines++
        }
        if (hasMineOnRightTop(line, column)) {
            adjacentMines++
        }
        if (hasMineOnBottom(line, column, numberLines)) {
            adjacentMines++
        }
        if (hasMineOnLeftBottom(line, column, numberLines)) {
            adjacentMines++
        }
        if (hasMineOnRightBottom(line, column, numberColumns, numberLines)) {
            adjacentMines++
        }
        adjacentMines.toString()
    }

    private boolean hasMineOnBottom(int line, int column, int numberLines) {
        if (line == numberLines - 1) {
            return false
        }
        def bottom = cellAt(line + 1, column)
        isMine(bottom)
    }

    private boolean isMine(char cell) {
        cell == MINE
    }

    private boolean hasMineOnLeftBottom(int line, int column, int numberLines) {
        if (line == numberLines - 1 || column == 0) {
            return false
        }
        def leftBottom = cellAt(line + 1, column - 1)
        isMine(leftBottom)
    }

    private boolean hasMineOnRightBottom(int line, int column, int numberColumns, int numberLines) {
        if (line == numberLines - 1 || column == numberColumns - 1) {
            return false
        }
        def rightBottom = cellAt(line + 1, column + 1)
        isMine(rightBottom)
    }

    private boolean hasMineOnTop(int line, int column) {
        if (line == 0) {
            return false
        }
        def top = cellAt(line - 1, column)
        isMine(top)
    }

    private boolean hasMineOnRightTop(int line, int column) {
        if (line == 0 || column == 0) {
            return false
        }
        def rightTop = cellAt(line - 1, column - 1)
        isMine(rightTop)
    }

    private boolean hasMineOnLeftTop(int line, int column, int numberColumns) {
        if (line == 0 || column == numberColumns - 1) {
            return false
        }
        def leftTop = cellAt(line - 1, column + 1)
        isMine(leftTop)
    }

    boolean hasMineOnRight(int column, int line, int numberColumns) {
        if (column == numberColumns - 1) {
            return false
        }
        def right = cellAt(line, column + 1)
        isMine(right)
    }

    boolean hasMineOnLeft(int column, int line) {
        if (column == 0) {
            return false
        }
        def left = cellAt(line, column - 1)
        isMine(left)
    }

    private String newLine(int line) {
        line > 0 ? "\n" : ""
    }

}
