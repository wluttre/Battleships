package edu.clemson.cpsc2150.project2;


/**
 * Created by Owner on 9/20/2016.
 */
public class ArrayGrid implements Grid {

    private Status[][] gridArray;
    //this is the fix to their problem
    private Ship[] shipList;
    private int lastSunkShip;

    public ArrayGrid()
    {
        shipList = new ShipImpl[5];
        lastSunkShip = -1;
    }

    public void setGridDimensions(int rows, int cols)
    {
        gridArray = new Status[rows][cols];
        for(int row = 0; row < gridArray.length; ++row) {
            for (int column = 0; column < gridArray[row].length; ++column) {
                gridArray[row][column] = Status.EMPTY;
            }
        }
    }


    public void placeShip(Ship ship)
    {
        Coordinate[] shipCoord = ship.getCoordinates();
        for(int i = 0; i < shipCoord.length; ++i)
        {
            gridArray[shipCoord[i].row][shipCoord[i].column] = Status.SHIP;
        }
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
           shipCoord[0].row >= gridArray.length ||
           shipCoord[0].column >= gridArray.length)
        {
            return true;
        }
        //OoB end
        if(shipCoord[shipCoord.length-1].row >= gridArray.length ||
           shipCoord[shipCoord.length-1].column >= gridArray.length)
        {
            return true;
        }

        //Hits another Ship
        for(int i = 0; i < shipCoord.length; ++i)
            {
                if(gridArray[shipCoord[i].row][shipCoord[i].column] != Status.EMPTY) {
                    return true;
                }
            }

        return false;
    }


    // see Ship.shoot()
    public Status shoot(Coordinate coord)
    {
        if(gridArray[coord.row][coord.column] == Status.SHIP)
        {
            gridArray[coord.row][coord.column] = Status.HIT;
            for(int i = 0; i < shipList.length; ++i)
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
        }
        gridArray[coord.row][coord.column] = Status.MISS;
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
        return gridArray[coord.row][coord.column] == Status.HIT ||
               gridArray[coord.row][coord.column] == Status.MISS;
    }


    public void displayGrid(boolean showShips)
    {
        System.out.println("   0  1  2  3  4  5  6  7  8  9");
        int i = 0;

        for(Status[] row : gridArray){
            System.out.print(i + " ");
            for(Status pos : row){
                if(pos == Status.MISS){
                    System.out.print(" + ");
                }
                else if(pos == Status.HIT) {
                    System.out.print(" X ");
                }
                else if(showShips && pos == Status.SHIP){
                    System.out.print(" @ ");
                }
                else{
                    System.out.print(" - ");
                }
            }
            System.out.println("");
            ++i;
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



    private boolean compareGrid(Status[][] otherGrid, Ship[] otherShips)
    {
        if(gridArray.length != otherGrid.length || gridArray[0].length != otherGrid[0].length)
            return false;
        if(shipList[0] == null && otherShips[0] == null)
            return true;
        else if(shipList[0] == null || otherShips[0] == null)
            return false;

        for(int row = 0; row < gridArray.length; ++row)
        {
            for(int column = 0; column < gridArray[row].length; ++column)
            {
                    if (!gridArray[row][column].equals(otherGrid[row][column]))
                        return false;
            }
        }
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




    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ArrayGrid)
        {
            ArrayGrid other = (ArrayGrid) obj;
            return other.compareGrid(gridArray, shipList);
        }
        return super.equals(obj);
    }
}
