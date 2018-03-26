import spock.lang.Specification

class MinesweeperTest extends Specification {

    def "given zero bombs should return an empty field"() {
        expect:
            generateField(0, 0, []) == ""
            generateField(1, 1, ["."]) == "0"
            generateField(1, 2, [".."]) == "00"
            generateField(1, 3, ["..."]) == "000"
            generateField(2, 1, [".", "."]) == "0\n0"
            generateField(3, 1, [".", ".", "."]) == "0\n0\n0"
    }

    def "given all bombs should return a field full of bombs"() {
        expect:
            generateField(1, 1, ["*"]) == "*"
            generateField(1, 2, ["**"]) == "**"
            generateField(2, 1, ["*", "*"]) == "*\n*"
    }

    String generateField(int numberLines, int numberColumns, List<String> lines) {
        def field = ""
        for (int line = 0; line < numberLines; line++) {
            field += newLine(line)
            for (int column = 0; column < numberColumns; column++) {
                field += cell(lines, line, column)
            }
        }
        field
    }

    private char cell(List<String> lines, int line, int column) {
        lines[line].charAt(column) == "*" ? "*" : '0'
    }

    private String newLine(int line) {
        line > 0 ? "\n" : ""
    }

}
