public enum BlockSymbol {
    x('x'),
    o('o'),
    C('C'),
    B('B'),
    S('S'),
    P('P');
    private final char charRepresentation;

    BlockSymbol(char charRepresentation){
        this.charRepresentation = charRepresentation;
    }

    public char getCharRepresentation() {
        return charRepresentation;
    }
}
