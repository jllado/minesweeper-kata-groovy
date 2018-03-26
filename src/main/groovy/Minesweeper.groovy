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
                field += cell(lines, line, column, numberColumns, numberLines)
            }
        }
        field
    }

    private char cell(List<String> lines, int line, int column, int numberColumns, int numberLines) {
        def currentCell = lines[line].charAt(column)
        if (!isEmpty(currentCell)) {
            return "*"
        } else {
            return countAdjacentMines(lines, line, column, numberColumns, numberLines)
        }
    }

    private String countAdjacentMines(List<String> lines, int line, int column, int numberColumns, int numberLines) {
        def currentLine = lines[line]
        def adjacentMines = 0
        if (hasMineOnLeft(column, currentLine)) {
            adjacentMines++
        }
        if (hasMineOnRight(column, currentLine, numberColumns)) {
            adjacentMines++
        }
        if (hasMineOnTop(line, column, lines)) {
            adjacentMines++
        }
        if (hasMineOnLeftTop(line, column, lines, numberColumns)) {
            adjacentMines++
        }
        if (hasMineOnRightTop(line, column, lines)) {
            adjacentMines++
        }
        if (hasMineOnBottom(line, column, lines, numberLines)) {
            adjacentMines++
        }
        if (hasMineOnLeftBottom(line, column, lines, numberLines)) {
            adjacentMines++
        }
        if (hasMineOnRightBottom(line, column, lines, numberColumns, numberLines)) {
            adjacentMines++
        }
        adjacentMines.toString()
    }

    private boolean hasMineOnBottom(int line, int column, List<String> lines, int numberLines) {
        if (line == numberLines - 1) {
            return false
        }
        lines[line + 1].charAt(column) == "*"
    }

    private boolean hasMineOnLeftBottom(int line, int column, List<String> lines, int numberLines) {
        if (line == numberLines - 1 || column == 0) {
            return false
        }
        lines[line + 1].charAt(column - 1) == "*"
    }

    private boolean hasMineOnRightBottom(int line, int column, List<String> lines, int numberColumns, int numberLines) {
        if (line == numberLines - 1 || column == numberColumns - 1) {
            return false
        }
        lines[line + 1].charAt(column + 1) == "*"
    }

    private boolean hasMineOnTop(int line, int column, List<String> lines) {
        if (line == 0) {
            return false
        }
        lines[line - 1].charAt(column) == "*"
    }

    private boolean hasMineOnRightTop(int line, int column, List<String> lines) {
        if (line == 0 || column == 0) {
            return false
        }
        lines[line - 1].charAt(column - 1) == "*"
    }

    private boolean hasMineOnLeftTop(int line, int column, List<String> lines, int numberColumns) {
        if (line == 0 || column == numberColumns - 1) {
            return false
        }
        lines[line - 1].charAt(column + 1) == "*"
    }

    boolean hasMineOnRight(int column, String line, int numberColumns) {
        if (column == numberColumns - 1) {
            return false
        }
        line.charAt(column + 1) == "*"
    }

    boolean hasMineOnLeft(int column, String line) {
        if (column == 0) {
            return false
        }
        line.charAt(column - 1) == "*"
    }

    private boolean isEmpty(char cell) {
        cell == "."
    }

    private String newLine(int line) {
        line > 0 ? "\n" : ""
    }

}