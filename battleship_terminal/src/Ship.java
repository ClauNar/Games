import java.util.ArrayList;

public class Ship {
    public final ShipType shipType;
    private int numberOfBlocksHidden;
    private ArrayList<String> coordinateList = new ArrayList<>();

    public Ship(ShipType shipType){
        this.shipType = shipType;
        this.numberOfBlocksHidden = shipType.getLength();
    }

    public int getNumberOfBlocksHidden() {return numberOfBlocksHidden;}

    public ArrayList<String> getCoordinateList() {return coordinateList;}

    public void addCoordinatesToCoordinateList(String coordinate){
        coordinateList.add(coordinate);
    }

    public void decreaseNumberOfBlocksHiddenby1(){
        numberOfBlocksHidden--;
    }
}
