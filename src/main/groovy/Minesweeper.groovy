class Minesweeper {

    private Field field

    Minesweeper(int numberLines, int numberColumns, List<String> lines) {
        this.field = new Field(numberLines, numberColumns, lines)
    }

    String generateField() {
        toString()
    }

    String toString() {
        def stringField = ""
        for (int line = 0; line < field.numberLines; line++) {
            stringField += newLine(line)
            for (int column = 0; column < field.numberColumns; column++) {
                stringField += cellValueFor(new Position(line, column))
            }
        }
        stringField
    }

    private char cellValueFor(Position position) {
        if (field.cellAt(position).isMine()) {
            return Cell.MINE
        } else {
            return field.countAdjacentMines(position).toString()
        }
    }

    private String newLine(int line) {
        line > 0 ? "\n" : ""
    }

}
