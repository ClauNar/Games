package grid;

public enum Occupacy {
    EMPTY(' '),
    Player1('O'),
    Player2('X');
    private char symbol;

    Occupacy(char pSymbol){
        symbol = pSymbol;
    }
    public char getSymbol() {
        return symbol;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}