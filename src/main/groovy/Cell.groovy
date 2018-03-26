class Cell {

    public static final String MINE = "*"

    private char value

    Cell(char value) {
        this.value = value
    }

    boolean isMine() {
        value == MINE
    }

}
