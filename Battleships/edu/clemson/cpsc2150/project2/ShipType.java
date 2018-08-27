package edu.clemson.cpsc2150.project2;

/**
 * Created by Owner on 9/20/2016.
 */
public enum ShipType {
    CARRIER("Carrier", 5),
    BATTLESHIP("Battleship", 4),
    CRUISER("Cruiser", 3),
    SUBMARINE("Submarine", 3),
    DESTROYER("Destroyer", 2);

    private String name;
    private int length;
    ShipType(String shipName, int l)
    {
        name = shipName;
        length = l;
    }
    public int getLength()
    {
        return length;
    }


    @Override
    public String toString() {
        return name;
    }
}
