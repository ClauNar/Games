public enum ShipType {

    Carrier("carrier",1, 6, BlockSymbol.C),
    Battleship("battleship",2,4, BlockSymbol.B),
    Submarine("submarine",3, 3, BlockSymbol.S),
    Patrolboat("patrol boat",4,2, BlockSymbol.P);

    private final String name;
    private final int count;
    private final int length;
    private final BlockSymbol blockSymbol;

    ShipType(String name, int count, int length, BlockSymbol blockSymbol){
        this.name = name;
        this.count = count;
        this.length = length;
        this.blockSymbol = blockSymbol;
    }

    public String getName(){
        return name;
    }
    public int getCount(){
        return count;
    }
    public int getLength(){return length;}
    public BlockSymbol getBlockSymbol(){return blockSymbol;}
}
