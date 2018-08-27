package edu.clemson.cpsc2150.project2;

/**
 * Created by Owner on 9/20/2016.
 */
public class BoundedSetGrid implements Grid{

    private BoundedSet setGrid;
    //this is the fix to their problem
    private Ship[] shipList;
    private int lastSunkShip;

    public BoundedSetGrid()
    {
        lastSunkShip = -1;
        shipList = new ShipImpl[5];
    }

    public void setGridDimensions(int rows, int cols)
    {
        setGrid = new BoundedSet(rows*cols);
    }


    public void placeShip(Ship ship)
    {
        for(int i = 0; i < shipList.length; ++i) {
            if(shipList[i] == null) {
                shipList[i] = ship;
                i = shipList.length;
            }
        }
    }


    public boolean isConflictingShipPlacement(Ship ship)
    {
        Coordinate[] shipCoord = ship.getCoordinates();

        //OoB start
        if(shipCoord[0].row < 0 || shipCoord[0].column < 0 ||
                shipCoord[0].row > 10 ||
                shipCoord[0].column > 10)
        {
            return true;
        }
        //OoB end
        if(shipCoord[shipCoord.length-1].row >= 10 ||
                shipCoord[shipCoord.length-1].column >= 10)
        {
            return true;
        }

        for(int i = 0; i < shipList.length; ++i) {
            if(shipList[i] != null)
            {
                Coordinate[] placedShip = shipList[i].getCoordinates();
                for(int coord1 = 0; coord1 < shipCoord.length; ++coord1)
                {
                    for(int coord2 = 0; coord2 < placedShip.length; ++coord2)
                    {
                        if(shipCoord[coord1].equals(placedShip[coord2]))
                        {
                            return true;
                        }
                    }
                }
            }

        }
        return false;
    }


    // see Ship.shoot()
    public Status shoot(Coordinate coord)
    {
        setGrid.insert(coord);
        for(int i = 0; i < shipList.length && shipList[i] != null; ++i)
        {
            if(shipList[i].shoot(coord) == Status.HIT) {
                return Status.HIT;
            }

            if( shipList[i].shoot(coord) == Status.SUNK)
            {
                lastSunkShip = i;
                return Status.SUNK;
            }
        }
        return Status.MISS;
    }


    // returns Ship object representing the last ship
    // which was sunk (null if no ship has been sunk)
    public Ship getLastSunkShip()
    {
        if(lastSunkShip == -1)
            return null;
        return shipList[lastSunkShip];
    }


    public boolean hasBeenAttempted(Coordinate coord)
    {
        return setGrid.contains(coord);
    }


    public void displayGrid(boolean showShips)
    {
        System.out.println("   0  1  2  3  4  5  6  7  8  9");
        int row;

        for(row = 0; row < 10; ++row){
            System.out.print(row + " ");
            for(int column = 0; column < 10; ++column){
                boolean isShot = false;
                boolean isShip = false;
                Coordinate coord = new Coordinate(row, column);
                if (setGrid.contains(coord)) {
                    isShot = true;
                }
                for(int shipNum = 0; shipNum < shipList.length && shipList[shipNum] != null; ++shipNum)
                {
                    Coordinate[] shipLocation = shipList[shipNum].getCoordinates();
                    for(int coordNum = 0; coordNum < shipLocation.length; ++coordNum) {
                        if (coord.equals(shipLocation[coordNum])) {
                            isShip = true;
                            coordNum = shipLocation.length;
                            shipNum = shipList.length;
                        }
                    }

                }
                if(isShot && !isShip)
                {
                    System.out.print(" + ");
                }
                else if(isShot && isShip)
                {
                    System.out.print(" X ");
                }
                else if(!isShot && isShip && showShips)
                {
                    System.out.print(" @ ");
                }
                else
                {
                    System.out.print(" - ");
                }
            }
            System.out.println("");
        }
    }


    // returns true if all ships have been sunk
    // otherwise, false
    public boolean isGameOver()
    {
        for(int i = 0; i < shipList.length && shipList[i] != null; ++i)
        {
            if(!shipList[i].isSunk())
                return false;
        }
        return true;
    }



    private boolean compareGrid(BoundedSet otherGrid, Ship[] otherShips)
    {
        if(shipList[0] == null && otherShips[0] == null)
            return true;
        else if(shipList[0] == null || otherShips[0] == null)
            return false;

        if(!(otherGrid.equals(setGrid)))
            return false;

        boolean sharedShip;
        for(Ship other : otherShips)
        {
            sharedShip = false;
            for(Ship myShip : shipList)
            {
                if(myShip.equals(other))
                {
                    sharedShip = true;
                }
            }
            if(!sharedShip)
                return false;
        }
        return true;
    }


    //instead of having getters, could just make functions that take parameters, return equality boolean
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BoundedSetGrid)
        {
            BoundedSetGrid other = (BoundedSetGrid) obj;
            return other.compareGrid(setGrid, shipList);
        }
        return super.equals(obj);
    }
}
