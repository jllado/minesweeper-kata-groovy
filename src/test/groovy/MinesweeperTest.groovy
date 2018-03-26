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

    def "given one empty cell with a mine on left and right should return a field with 2 in that cell"() {
        expect:
            generateField(1, 3, ["*.*"]) == "*2*"
    }

    def "given one empty cell with a mine on top should return a field with 1 in that cell"() {
        expect:
            generateField(2, 1, ["*", "."]) == "*\n1"
    }

    def "given one empty cell with a mine on bottom should return a field with 1 in that cell"() {
        expect:
            generateField(2, 1, [".", "*"]) == "1\n*"
    }

    def "given one empty cell with a mine on left bottom should return a field with 1 in that cell"() {
        expect:
            generateField(2, 2, ["..", "*."]) == "11\n*1"
    }

    def "given one empty cell with a mine on right bottom should return a field with 1 in that cell"() {
        expect:
            generateField(2, 2, ["..", ".*"]) == "11\n1*"
    }

    def "given one empty cell with a mine on right top should return a field with 1 in that cell"() {
        expect:
            generateField(2, 2, ["*.", ".."]) == "*1\n11"
    }

    def "given one empty cell with a mine on left top should return a field with 1 in that cell"() {
        expect:
            generateField(2, 2, [".*", ".."]) == "1*\n11"
    }

    def "should return a field with adjacent mines for each cell"() {
        expect:
            generateField(4, 4, ["*...", "....", ".*..", "...."]) == "*100\n2210\n1*10\n1110"
    }

    String generateField(int numberLines, int numberColumns, List<String> lines) {
        new Minesweeper(numberLines, numberColumns, lines).generateField()
    }

}
