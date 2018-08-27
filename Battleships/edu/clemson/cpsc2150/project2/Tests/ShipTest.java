package edu.clemson.cpsc2150.project2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Owner on 9/28/2016.
 */
public class ShipTest {
    private Ship carrierTest1, carrierTest2, battleshipTest, cruiserTest, destroyerTest, submarineTest;

    @Before
    public void setUp() throws Exception {
        carrierTest1 = new ShipImpl(ShipType.CARRIER);
        carrierTest2 = new ShipImpl(ShipType.CARRIER);

        battleshipTest = new ShipImpl(ShipType.BATTLESHIP);
        cruiserTest = new ShipImpl(ShipType.CRUISER);
        destroyerTest = new ShipImpl(ShipType.DESTROYER);
        submarineTest = new ShipImpl(ShipType.SUBMARINE);

    }

    @Test
    public void setCoordinates() throws Exception {
        carrierTest1.setCoordinates(new Coordinate(0, 0), Direction.DOWN);
        carrierTest2.setCoordinates(new Coordinate(0, 0), Direction.DOWN);
        assertEquals(carrierTest1,carrierTest2);

        carrierTest2.setCoordinates(new Coordinate(0, 0), Direction.RIGHT);
        assertFalse(carrierTest1 == carrierTest2);

        carrierTest2.setCoordinates(new Coordinate(0, 1), Direction.DOWN);
        assertFalse(carrierTest1 == carrierTest2);

    }

    @Test
    public void getCoordinates() throws Exception {

        carrierTest1.setCoordinates(new Coordinate(0, 0), Direction.DOWN);
        carrierTest2.setCoordinates(new Coordinate(0, 0), Direction.DOWN);
        assertEquals(carrierTest1.getCoordinates(), carrierTest2.getCoordinates());
        assertEquals(5 ,carrierTest1.getCoordinates().length);
        assertEquals(new Coordinate(4, 0) ,carrierTest1.getCoordinates()[4]);

        battleshipTest.setCoordinates(new Coordinate(0, 0), Direction.DOWN);
        assertEquals(4 ,battleshipTest.getCoordinates().length);
        assertEquals(new Coordinate(3, 0) ,battleshipTest.getCoordinates()[3]);

        cruiserTest.setCoordinates(new Coordinate(1, 1), Direction.RIGHT);
        assertEquals(3 ,cruiserTest.getCoordinates().length);
        assertEquals(new Coordinate(1, 3) ,cruiserTest.getCoordinates()[2]);

        submarineTest.setCoordinates(new Coordinate(2, 2), Direction.DOWN);
        assertEquals(3 , submarineTest.getCoordinates().length);
        assertEquals(new Coordinate(4, 2) ,submarineTest.getCoordinates()[2]);

        destroyerTest.setCoordinates(new Coordinate(3, 3), Direction.RIGHT);
        assertEquals(2 , destroyerTest.getCoordinates().length);
        assertEquals(new Coordinate(3, 4) ,destroyerTest.getCoordinates()[1]);

    }

    @Test
    public void getType() throws Exception {

        assertEquals(ShipType.CARRIER, carrierTest1.getType());
        assertEquals(ShipType.BATTLESHIP, battleshipTest.getType());
        assertEquals(ShipType.CRUISER, cruiserTest.getType());
        assertEquals(ShipType.SUBMARINE, submarineTest.getType());
        assertEquals(ShipType.DESTROYER, destroyerTest.getType());
    }

    @Test
    public void shoot() throws Exception {
        destroyerTest.setCoordinates(new Coordinate(2, 2), Direction.DOWN);
        assertEquals(Status.MISS, destroyerTest.shoot(new Coordinate(2, 3)));
        assertEquals(Status.HIT, destroyerTest.shoot(new Coordinate(2, 2)));
        assertEquals(Status.SUNK, destroyerTest.shoot(new Coordinate(3, 2)));
        assertEquals(Status.MISS, destroyerTest.shoot(new Coordinate(4, 2)));

    }

    @Test
    public void isSunk() throws Exception {
        destroyerTest.setCoordinates(new Coordinate(2, 2), Direction.DOWN);
        assertFalse(destroyerTest.isSunk());
        destroyerTest.shoot(new Coordinate(2, 2));
        assertFalse(destroyerTest.isSunk());
        destroyerTest.shoot(new Coordinate(3, 2));
        assertTrue(destroyerTest.isSunk());


    }

}