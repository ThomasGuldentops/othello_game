package esi.atl.g52088.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class PositionTest {

    public PositionTest() {
    }

    /**
     * Test of next method, of class Position.
     */
    @Test
    public void testNextRight() {
        System.out.println("nextRight");
        Direction direction = Direction.EAST;
        Position instance = new Position(3, 3);
        Position expResult = new Position(3, 4);
        Position result = instance.next(direction);
        assertEquals(expResult, result);

    }

    /**
     * Test of next method, of class Position.
     */
    @Test
    public void testNextUp() {
        System.out.println("nextUp");
        Direction direction = Direction.NORTH;
        Position instance = new Position(3, 3);
        Position expResult = new Position(2, 3);
        Position result = instance.next(direction);
        assertEquals(expResult, result);

    }

    /**
     * Test of next method, of class Position.
     */
    @Test
    public void testNextLeft() {
        System.out.println("nextLeft");
        Direction direction = Direction.WEST;
        Position instance = new Position(3, 3);
        Position expResult = new Position(3, 2);
        Position result = instance.next(direction);
        assertEquals(expResult, result);

    }

    /**
     * Test of next method, of class Position.
     */
    @Test
    public void testNextDown() {
        System.out.println("nextDown");
        Direction direction = Direction.SOUTH;
        Position instance = new Position(3, 3);
        Position expResult = new Position(4, 3);
        Position result = instance.next(direction);
        assertEquals(expResult, result);

    }

    /**
     * Test of next method, of class Position.
     */
    @Test
    public void testNextNorthEast() {
        System.out.println("nextNorthEast");
        Direction direction = Direction.NORTHEAST;
        Position instance = new Position(3, 3);
        Position expResult = new Position(2, 4);
        Position result = instance.next(direction);
        assertEquals(expResult, result);

    }

    /**
     * Test of next method, of class Position.
     */
    @Test
    public void testNextNorthWest() {
        System.out.println("nextNorthWest");
        Direction direction = Direction.NORTHWEST;
        Position instance = new Position(3, 3);
        Position expResult = new Position(2, 2);
        Position result = instance.next(direction);
        assertEquals(expResult, result);

    }

    /**
     * Test of next method, of class Position.
     */
    @Test
    public void testNextSouthEast() {
        System.out.println("nextSouthEast");
        Direction direction = Direction.SOUTHEAST;
        Position instance = new Position(3, 3);
        Position expResult = new Position(4, 4);
        Position result = instance.next(direction);
        assertEquals(expResult, result);

    }

    /**
     * Test of next method, of class Position.
     */
    @Test
    public void testNextSouthWest() {
        System.out.println("next");
        Direction direction = Direction.SOUTHWEST;
        Position instance = new Position(3, 3);
        Position expResult = new Position(4, 2);
        Position result = instance.next(direction);
        assertEquals(expResult, result);

    }

    /**
     * Test of hashCode methode, of class Position
     */
    @Test
    public void equalsTrueMySelf() {
        Position position1 = new Position(4, 7);
        assertTrue(position1.equals(position1));
        assertTrue(position1.hashCode() == position1.hashCode());
    }

    /**
     * Test of equals methode, of class Position
     */
    @Test
    public void equalsTrue() {
        Position position1 = new Position(4, 7);
        Position position2 = new Position(4, 7);
        assertTrue(position1.equals(position2));
        assertTrue(position1.hashCode() == position2.hashCode());
    }

    /**
     * Test of equals methode, of class Position
     */
    @Test 
    public void equalsFalseDifferentRow() {
        Position position1 = new Position(7, 7);
        Position position2 = new Position(8, 7);
        assertFalse(position1.equals(position2));
    }

    /**
     * Test of equals methode, of class Position
     */
    @Test
    public void equalsFalseDifferentColumn() {
        Position position1 = new Position(2, 7);
        Position position2 = new Position(2, 5);
        assertFalse(position1.equals(position2));
    }

    /**
     * Test of equals methode, of class Position
     */
    @Test
    public void equalsFalseOtherObject() {
        Position position1 = new Position(2, 96);
        String position2 = "abcd";
        assertFalse(position1.equals(position2));
    }

    /**
     * Test of equals methode, of class Position
     */
    @Test
    public void equalsFalseNull() {
        Position position1 = new Position(2, 96);
        assertFalse(position1.equals(null));
    }

}
