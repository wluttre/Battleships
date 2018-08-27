package edu.clemson.cpsc2150.project2;

/**
 * Created by Owner on 9/20/2016.
 */
public class ShipImpl implements Ship {
    ShipType ship;
    Coordinate[] coordList;
    Status [] hitPoints;
    Direction direction;

    public ShipImpl(ShipType s)
    {
        ship = s;
        coordList = new Coordinate[ship.getLength()];
        hitPoints = new Status[ship.getLength()];
        for(int i = 0; i < hitPoints.length; ++i)
            hitPoints[i] = Status.SHIP;
    }


    // sets the placement of this ship starting at
    // coordinate "coord" and proceeding in direction "dir"
    public void setCoordinates(Coordinate coord, Direction dir)
    {
        direction = dir;
        for(int i = 0; i < coordList.length; ++i)
        {
            if(direction == Direction.DOWN)
            {
                coordList[i] = new Coordinate(coord.row + i, coord.column);
            }

            if(direction == Direction.RIGHT)
            {
                coordList[i] = new Coordinate(coord.row, coord.column + i);
            }
        }
    }

    // returns an array of the ship’s coordinates
    public Coordinate[] getCoordinates()
    {
        return coordList;
    }

    public ShipType getType()
    {
        return ship;
    }

    // takes a shot at this ship’s coordinates and
    // returns Status.MISS, Status.HIT, or STATUS.SUNK
    public Status shoot(Coordinate coord)
    {
        boolean isDead = true;
        boolean isHit = false;
        for(int i = 0; i < coordList.length; ++i)
        {
            if(coordList[i].row == coord.row && coordList[i].column == coord.column)
            {
                hitPoints[i] = Status.HIT;
                isHit = true;
            }

            if(hitPoints[i] == Status.SHIP)
                isDead = false;
        }
        if(!isHit)
            return Status.MISS;
        else if(isDead)
            return Status.SUNK;
        else
            return Status.HIT;
    }

    // returns true if all of the ship’s coordinates are hit
    // otherwise, false
    public boolean isSunk()
    {
        for(int i = 0; i < hitPoints.length; ++i)
        {
            if(hitPoints[i] == Status.SHIP)
                return false;
        }
        return true;
    }

    private boolean compareShip(Coordinate[] coords, ShipType s)
    {
        if(s != ship)
            return false;
        boolean sharedCoordinate;
        for(Coordinate otherCoord : coords)
        {
            sharedCoordinate = false;
            for(Coordinate myCoord : coordList)
            {
                if(otherCoord.equals(myCoord))
                    sharedCoordinate = true;
            }
            if(!sharedCoordinate)
                return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ShipImpl)
        {
            ShipImpl other = (ShipImpl) obj;
            return other.compareShip(coordList, ship);

        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return ship.toString();
    }
}
