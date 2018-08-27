package edu.clemson.cpsc2150.project2;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Owner on 9/26/2016.
 */
public class GridTest {
    private Grid test1, test2;
    @Before
    public void setUp() throws Exception {
        test1 = new ArrayGrid();

        test2 = new BoundedSetGrid();
    }

    @Test
    public void  setGridDimensions() throws Exception
    {
        Grid test1b = new ArrayGrid();
        test1b.setGridDimensions(9, 9);
        Grid test2b = new BoundedSetGrid();
        test2b.setGridDimensions(9, 9);

        test1.setGridDimensions(10, 10);
        assertFalse(test1b == test1);
        test1b.setGridDimensions(10, 10);
        assertEquals(test1b, test1);

        test2.setGridDimensions(10, 10);
        assertFalse(test2b == test2);
        test2b.setGridDimensions(10, 10);
        assertEquals(test2b, test2);

    }

    @Test
    public void  placeShip() throws Exception
    {
        Ship ship1 = new ShipImpl(ShipType.CARRIER);
        ship1.setCoordinates(new Coordinate(5, 5), Direction.RIGHT);
        Ship ship2 = new ShipImpl(ShipType.DESTROYER);
        ship2.setCoordinates(new Coordinate(8, 0), Direction.DOWN);
        Ship ship3= new ShipImpl(ShipType.CRUISER);
        ship3.setCoordinates(new Coordinate(9, 7), Direction.RIGHT);
        Ship ship4 = new ShipImpl(ShipType.SUBMARINE);
        ship4.setCoordinates(new Coordinate(7, 2), Direction.DOWN);
        Ship ship5 = new ShipImpl(ShipType.BATTLESHIP);
        ship5.setCoordinates(new Coordinate(3, 6), Direction.RIGHT);

        test1.setGridDimensions(10, 10);
        assertFalse(test1.isConflictingShipPlacement(ship1));
        test1.placeShip(ship1);
        assertFalse(test1.isConflictingShipPlacement(ship2));
        test1.placeShip(ship2);
        assertFalse(test1.isConflictingShipPlacement(ship3));
        test1.placeShip(ship3);
        assertFalse(test1.isConflictingShipPlacement(ship4));
        test1.placeShip(ship4);
        assertFalse(test1.isConflictingShipPlacement(ship5));
        test1.placeShip(ship5);
        assertEquals(Status.HIT, test1.shoot(new Coordinate(5, 5)));


        test2.setGridDimensions(10, 10);
        assertFalse(test2.isConflictingShipPlacement(ship1));
        test2.placeShip(ship1);
        assertFalse(test2.isConflictingShipPlacement(ship2));
        test2.placeShip(ship2);
        assertFalse(test2.isConflictingShipPlacement(ship3));
        test2.placeShip(ship3);
        assertFalse(test2.isConflictingShipPlacement(ship4));
        test2.placeShip(ship4);
        assertFalse(test2.isConflictingShipPlacement(ship5));
        test2.placeShip(ship5);
        assertEquals(Status.HIT, test2.shoot(new Coordinate(5, 5)));

    }

    @Test
    public void  isConflictingShipPlacement() throws Exception
    {
        Ship ship1 = new ShipImpl(ShipType.CARRIER);
        Ship ship2 = new ShipImpl(ShipType.DESTROYER);

        test1.setGridDimensions(10, 10);
        ship1.setCoordinates(new Coordinate(6, 9), Direction.RIGHT);
        ship2.setCoordinates(new Coordinate(9, 7), Direction.DOWN);
        assertTrue(test1.isConflictingShipPlacement(ship1));
        assertTrue(test1.isConflictingShipPlacement(ship2));

        ship1.setCoordinates(new Coordinate(10, 10), Direction.RIGHT);
        ship2.setCoordinates(new Coordinate(-1, -1), Direction.DOWN);
        assertTrue(test1.isConflictingShipPlacement(ship1));
        assertTrue(test1.isConflictingShipPlacement(ship2));

        ship1.setCoordinates(new Coordinate(9, 5), Direction.RIGHT);
        ship2.setCoordinates(new Coordinate(9, 5), Direction.DOWN);
        assertEquals(false, test1.isConflictingShipPlacement(ship1));
        assertEquals(true, test1.isConflictingShipPlacement(ship2));
        ship2.setCoordinates(new Coordinate(8, 5), Direction.DOWN);
        assertEquals(false, test1.isConflictingShipPlacement(ship2));
        test1.placeShip(ship1);
        ship2.setCoordinates(new Coordinate(8, 5), Direction.DOWN);
        assertEquals(true, test1.isConflictingShipPlacement(ship2));


        test2.setGridDimensions(10, 10);

        ship1.setCoordinates(new Coordinate(6, 9), Direction.RIGHT);
        ship2.setCoordinates(new Coordinate(9, 7), Direction.DOWN);
        assertTrue(test2.isConflictingShipPlacement(ship1));
        assertTrue(test2.isConflictingShipPlacement(ship2));

        ship1.setCoordinates(new Coordinate(10, 10), Direction.RIGHT);
        ship2.setCoordinates(new Coordinate(-1, -1), Direction.DOWN);
        assertTrue(test2.isConflictingShipPlacement(ship1));
        assertTrue(test2.isConflictingShipPlacement(ship2));

        ship1.setCoordinates(new Coordinate(9, 5), Direction.RIGHT);
        ship2.setCoordinates(new Coordinate(9, 5), Direction.DOWN);
        assertEquals(false, test2.isConflictingShipPlacement(ship1));
        assertEquals(true, test2.isConflictingShipPlacement(ship2));
        ship2.setCoordinates(new Coordinate(8, 5), Direction.DOWN);
        assertEquals(false, test2.isConflictingShipPlacement(ship2));
        test2.placeShip(ship1);
        ship2.setCoordinates(new Coordinate(8, 5), Direction.DOWN);
        assertEquals(true, test2.isConflictingShipPlacement(ship2));

    }


    @Test
    public void  shoot() throws Exception
    {
        Ship ship1 = new ShipImpl(ShipType.DESTROYER);

        test1.setGridDimensions(10, 10);
        ship1.setCoordinates(new Coordinate(5, 5), Direction.RIGHT);
        test1.placeShip(ship1);
        assertEquals(Status.MISS, test1.shoot(new Coordinate(6, 5)));
        assertEquals(Status.HIT, test1.shoot(new Coordinate(5, 5)));
        assertEquals(Status.SUNK, test1.shoot(new Coordinate(5, 6)));
        assertEquals(Status.MISS, test1.shoot(new Coordinate(5, 7)));


        Ship ship2= new ShipImpl(ShipType.DESTROYER);
        test2.setGridDimensions(10, 10);
        ship2.setCoordinates(new Coordinate(5, 5), Direction.RIGHT);
        test2.placeShip(ship2);
        assertEquals(Status.MISS, test2.shoot(new Coordinate(6, 5)));
        assertEquals(Status.HIT, test2.shoot(new Coordinate(5, 5)));
        assertEquals(Status.SUNK, test2.shoot(new Coordinate(5, 6)));
        assertEquals(Status.MISS, test2.shoot(new Coordinate(5, 7)));


    }


    @Test
    public void  getLastSunkShip() throws Exception
    {
        Ship ship1 = new ShipImpl(ShipType.DESTROYER);
        Ship ship2 = new ShipImpl(ShipType.SUBMARINE);
        test1.setGridDimensions(10, 10);


        ship1.setCoordinates(new Coordinate(5, 5), Direction.RIGHT);
        test1.placeShip(ship1);

        ship2.setCoordinates(new Coordinate(0, 0), Direction.DOWN);
        test1.placeShip(ship2);

        assertEquals(null, test1.getLastSunkShip());
        test1.shoot(new Coordinate(5, 5));
        test1.shoot(new Coordinate(5, 6));
        assertEquals(ShipType.DESTROYER, test1.getLastSunkShip().getType());


        assertEquals(Status.HIT, test1.shoot(new Coordinate(0, 0)));
        assertEquals(Status.HIT, test1.shoot(new Coordinate(1, 0)));
        assertEquals(Status.SUNK, test1.shoot(new Coordinate(2, 0)));
        assertEquals(ShipType.SUBMARINE, test1.getLastSunkShip().getType());



        ship1 = new ShipImpl(ShipType.DESTROYER);
        ship2 = new ShipImpl(ShipType.SUBMARINE);
        test2.setGridDimensions(10, 10);

        ship1.setCoordinates(new Coordinate(5, 5), Direction.RIGHT);
        test2.placeShip(ship1);

        ship2.setCoordinates(new Coordinate(0, 0), Direction.DOWN);
        test2.placeShip(ship2);

        assertEquals(null, test2.getLastSunkShip());
        test2.shoot(new Coordinate(5, 5));
        test2.shoot(new Coordinate(5, 6));
        assertEquals(ShipType.DESTROYER, test2.getLastSunkShip().getType());


        assertEquals(Status.HIT, test2.shoot(new Coordinate(0, 0)));
        assertEquals(Status.HIT, test2.shoot(new Coordinate(1, 0)));
        assertEquals(Status.SUNK, test2.shoot(new Coordinate(2, 0)));
        assertEquals(ShipType.SUBMARINE, test2.getLastSunkShip().getType());



    }

    @Test
    public void  hasBeenAttempted() throws Exception
    {
        Ship ship1 = new ShipImpl(ShipType.DESTROYER);
        test1.setGridDimensions(10, 10);
        ship1.setCoordinates(new Coordinate(5, 5), Direction.RIGHT);
        test1.placeShip(ship1);
        assertEquals(false, test1.hasBeenAttempted(new Coordinate(0, 0)));
        test1.shoot(new Coordinate(0, 0));
        assertEquals(true, test1.hasBeenAttempted(new Coordinate(0, 0)));

        assertEquals(false, test1.hasBeenAttempted(new Coordinate(5, 5)));
        test1.shoot(new Coordinate(5, 5));
        assertEquals(true, test1.hasBeenAttempted(new Coordinate(5, 5)));

        ship1 = new ShipImpl(ShipType.DESTROYER);
        test2.setGridDimensions(10, 10);
        ship1.setCoordinates(new Coordinate(5, 5), Direction.RIGHT);
        test2.placeShip(ship1);
        assertEquals(false, test2.hasBeenAttempted(new Coordinate(0, 0)));
        test2.shoot(new Coordinate(0, 0));
        assertEquals(true, test2.hasBeenAttempted(new Coordinate(0, 0)));

        assertEquals(false, test2.hasBeenAttempted(new Coordinate(5, 5)));
        test2.shoot(new Coordinate(5, 5));
        assertEquals(true, test2.hasBeenAttempted(new Coordinate(5, 5)));

    }


    @Test
    public void isGameOver() throws Exception
    {
        Ship ship1 = new ShipImpl(ShipType.CARRIER);
        Ship ship2 = new ShipImpl(ShipType.DESTROYER);

        test1.setGridDimensions(10, 10);
        ship1.setCoordinates(new Coordinate(0, 0), Direction.RIGHT);
        ship2.setCoordinates(new Coordinate(1, 0), Direction.DOWN);
        assertEquals(true, test1.isGameOver());
        test1.placeShip(ship1);
        test1.placeShip(ship2);
        assertEquals(false, test1.isGameOver());
        test1.shoot(new Coordinate(0, 0));
        test1.shoot(new Coordinate(0, 1));
        test1.shoot(new Coordinate(0, 2));
        test1.shoot(new Coordinate(0, 3));
        assertEquals(Status.SUNK, test1.shoot(new Coordinate(0, 4)));
        assertEquals(false, test1.isGameOver());
        test1.shoot(new Coordinate(1, 0));
        assertEquals(Status.SUNK, test1.shoot(new Coordinate(2, 0)));
        assertEquals(true, test1.isGameOver());






        ship1 = new ShipImpl(ShipType.CARRIER);
        ship2 = new ShipImpl(ShipType.DESTROYER);

        test2.setGridDimensions(10, 10);
        ship1.setCoordinates(new Coordinate(0, 0), Direction.RIGHT);
        ship2.setCoordinates(new Coordinate(1, 0), Direction.DOWN);
        assertEquals(true, test2.isGameOver());
        test2.placeShip(ship1);
        test2.placeShip(ship2);
        assertEquals(false, test2.isGameOver());
        test2.shoot(new Coordinate(0, 0));
        test2.shoot(new Coordinate(0, 1));
        test2.shoot(new Coordinate(0, 2));
        test2.shoot(new Coordinate(0, 3));
        assertEquals(Status.SUNK, test2.shoot(new Coordinate(0, 4)));
        assertEquals(false, test2.isGameOver());
        test2.shoot(new Coordinate(1, 0));
        assertEquals(Status.SUNK, test2.shoot(new Coordinate(2, 0)));
        assertEquals(true, test2.isGameOver());
    }

}