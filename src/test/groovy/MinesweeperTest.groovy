import spock.lang.Specification

class MinesweeperTest extends Specification {

    def "given zero mines should return an empty field"() {
        expect:
            generateField(0, 0, []) == ""
            generateField(1, 1, ["."]) == "0"
            generateField(1, 2, [".."]) == "00"
            generateField(1, 3, ["..."]) == "000"
            generateField(2, 1, [".", "."]) == "0\n0"
            generateField(3, 1, [".", ".", "."]) == "0\n0\n0"
    }

    def "given all mines should return a field full of mines"() {
        expect:
            generateField(1, 1, ["*"]) == "*"
            generateField(1, 2, ["**"]) == "**"
            generateField(2, 1, ["*", "*"]) == "*\n*"
    }

    def "given one empty cell with a mine on right should return a field with 1 in that cell"() {
        expect:
            generateField(1, 2, [".*"]) == "1*"
            generateField(1, 3, ["..*"]) == "01*"
            generateField(1, 4, ["..**"]) == "01**"
    }

    def "given one empty cell with a mine on left should return a field with 1 in that cell"() {
        expect:
            generateField(1, 2, ["*."]) == "*1"
            generateField(1, 3, ["*.."]) == "*10"
            generateField(1, 4, ["**.."]) == "**10"
    }

    def "given one empty cell with a bomb on left and right should return a field with 2 in that cell"() {
        expect:
            generateField(1, 3, ["*.*"]) == "*2*"
    }

    String generateField(int numberLines, int numberColumns, List<String> lines) {
        def field = ""
        for (int line = 0; line < numberLines; line++) {
            field += newLine(line)
            for (int column = 0; column < numberColumns; column++) {
                field += cell(lines, line, column, numberColumns)
            }
        }
        field
    }

    private char cell(List<String> lines, int line, int column, int numberColumns) {
        def currentLine = lines[line]
        def currentCell = currentLine.charAt(column)
        if (isEmpty(currentCell) && hasAdjacentMine(column, currentLine, numberColumns)) {
            return countAdjacentMines(column, currentLine, numberColumns)
        }
        currentCell == "*" ? "*" : '0'
    }

    private String countAdjacentMines(int column, String line, int numberColumns) {
        def adjacentMines = 0
        if (hasMineOnLeft(column, line)) {
            adjacentMines++
        }
        if (hasMineOnRight(column, line, numberColumns)) {
            adjacentMines++
        }
        adjacentMines.toString()
    }

    private boolean hasAdjacentMine(int column, String line, int numberColumns) {
        countAdjacentMines(column, line, numberColumns) > 0
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
