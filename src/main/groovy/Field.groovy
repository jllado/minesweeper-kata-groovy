class Field {

    private int numberLines
    private int numberColumns
    private List<String> lines

    Field(int numberLines, int numberColumns, List<String> lines) {
        this.numberLines = numberLines
        this.numberColumns = numberColumns
        this.lines = lines
    }

    int getNumberLines() {
        return numberLines
    }

    int getNumberColumns() {
        return numberColumns
    }

    Cell cellAt(Position position) {
        if (!isValid(position)) {
            return null
        }
        new Cell(lines[position.line].charAt(position.column))
    }

    private boolean isValid(Position position) {
        isValidLine(position) && isValidColumn(position)
    }

    private boolean isValidColumn(Position position) {
        position.column >= 0 && position.column != numberColumns
    }

    private boolean isValidLine(Position position) {
        position.line >= 0 && position.line != numberLines
    }

    int countAdjacentMines(Position position) {
        adjacentPositions(position).findAll {cellAt(it)?.isMine()}.size()
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

}
