package edu.clemson.cpsc2150.project2;

/**
 * Created by Owner on 9/20/2016.
 */
public interface Grid {

    /**
     *
     * @param rows the number of rows in the grid
     * @param cols the number of columns in the grid
     *
     * @requires rows = 10, cols = 10
     *
     * @ensures [the grid is instantiated with the dimensions of rows and cols]
     *
     */
    void setGridDimensions(int rows, int cols);


    /**
     *
     * @param ship the ship to be placed in the gird
     *
     * @requires ship != null and [ship coordinates are within the grid dimensions]
     *
     * @ensures [the ship is placed on the grid at its specified coordinates]
     *
     */
    void placeShip(Ship ship);

    /**
     *
     * @param ship the ship being tested to be placed in the gird
     *
     * @requires ship != null
     *
     * @ensures [tells whether the ship is valid for placement]
     *
     * @return returns true if the ship can be placed on the grid, false otherwise
     *
     */
    boolean isConflictingShipPlacement(Ship ship);

    /**
     *
     * @param coord the coordinate of the shot on the gird
     *
     * @requires coord != null and [coord is within the grid]
     *
     * @ensures [the position at the grid is tested for shooting, and the shot is recorded in the grid]
     *
     * @return returns whether the shot missed, hit a ship, or sunk a ship
     *
     */
    Status shoot(Coordinate coord);

    /**
     *
     * @requires [grid is instantiated]
     *
     * @ensures [the most recently sunk ship is returned, or null is returned if no ship has been sunk yet]
     *
     * @return returns the most recently sunk ship, or null
     *
     */
    Ship getLastSunkShip();

    /**
     *
     * @param coord the coordinate of the shot being tested
     *
     * @requires coord != null and [coord is within the grid]
     *
     * @ensures [returns whether the position at coord on the grid has been shot at]
     *
     * @return returns true if the position of coord has been shot at, false otherwise
     *
     */
    boolean hasBeenAttempted(Coordinate coord);

    /**
     *
     * @param showShips a boolean that determines if ships are visible on the grid
     *
     * @requires [the grid has been instantiated, and the dimension have been set]
     *
     * @ensures [prints out the grid in a standardized format, displaying empty spaces, hits, misses, and
     * ships if showShips == true]
     *
     *
     */
    void displayGrid(boolean showShips);

    /**
     *
     *
     * @requires [the grid is instantiated]
     *
     * @ensures [returns the state of whether or not the game has ended]
     *
     * @return returns true if there are no ships on the grid that are not sunk, false otherwise
     *
     */
    boolean isGameOver();
}