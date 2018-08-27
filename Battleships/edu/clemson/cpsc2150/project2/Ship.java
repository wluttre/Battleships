package edu.clemson.cpsc2150.project2;

/**
 * Created by Owner on 9/20/2016.
 */
public interface Ship {

    /**
     *
     * @param coord the starting coordinate of the ship
     * @param dir the direction the ship is aligned
     *
     * @requires dir != null
     *
     * @ensures [if dir = RIGHT, ship coordinates are set from coord.row, coord.column to coord.row + length,
     * coord.col] or
     * [if dir = DOWN, ship coordinates are set from coord.row, coord.column to coord.row, coord.col + length]
     */
    void setCoordinates(Coordinate coord, Direction dir);

    /**
     *
     * @requires [the ship coordinates have been set]
     *
     * @ensures [the ship coordinates are not changed]
     *
     * @return an array of the coordinates of the ship
     */
    Coordinate[] getCoordinates();

    /**
     *
     * @requires [the ship's type != null]
     *
     * @ensures [the ship's type is not changed]
     *
     * @return  the type of the ship
     */
    ShipType getType();

    /**
     *
     * @param coord the coordinate being shot at
     *
     * @requires [coord is within the grid dimensions]
     *
     * @ensures [returns a status based on whether the ship was hit]
     *
     * @return returns Status.MISS if coord does not match a ship coordinate,
     * returns Status.HIT if coord did match a ship coordinate but was not the last coordinate to be hit
     * returns Status.SUNK if coord was the last un-hit coordinate of the ship
     */
    Status shoot(Coordinate coord);

    /**
     *
     *
     * @requires [the coordinates have been set]
     *
     * @ensures [the ship is not changed]
     *
     * @return returns true if all coordinates of the ship have been shot, false otherwise
     */
    boolean isSunk();
}
