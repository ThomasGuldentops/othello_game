package esi.atl.g52088.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guldentops Thomas - g52088
 */
public class MoveTest {

    public MoveTest() {
    }

    /**
     * Test of getStart method, of class Move.
     */
    @Test
    public void testGetStart() {
        System.out.println("getStart");
        Position start = new Position(3, 2);
        Position end = new Position(4, 2);

        Move instance = new Move(start, end, Direction.NORTH);

        Position expResult = new Position(3, 2);
        Position result = instance.getStart();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnd method, of class Move.
     */
    @Test
    public void testGetEnd() {
        System.out.println("getEnd");
        Position start = new Position(3, 2);
        Position end = new Position(4, 2);

        Move instance = new Move(start, end, Direction.NORTH);

        Position expResult = new Position(4, 2);
        Position result = instance.getEnd();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDirection method, of class Move.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");

        Position start = new Position(3, 2);
        Position end = new Position(4, 2);
        Move instance = new Move(start, end, Direction.NORTH);

        Direction expResult = Direction.NORTH;
        Direction result = instance.getDirection();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Move.
     */
    @Test
    public void equalstrueMyself() {
        System.out.println("equalstrueMyself");

        Position start = new Position(3, 2);
        Position end = new Position(4, 2);
        Move instance = new Move(start, end, Direction.NORTH);

        assertTrue(instance.equals(instance));
        assertTrue(instance.hashCode() == instance.hashCode());
    }

    /**
     * Test of equals method, of class Move.
     */
    @Test
    public void equalsTrue() {
        System.out.println("equalsTrue");

        Position start = new Position(3, 2);
        Position end = new Position(4, 2);
        Move instance = new Move(start, end, Direction.NORTH);

        Position start2 = new Position(3, 2);
        Position end2 = new Position(4, 2);
        Move instance2 = new Move(start2, end2, Direction.NORTH);

        assertTrue(instance.equals(instance2));
        assertTrue(instance.hashCode() == instance.hashCode());
    }

    /**
     * Test of equals method, of class Move.
     */
    @Test
    public void equalsFalse() {
        System.out.println("equalsFalse");

        Position start = new Position(3, 2);
        Position end = new Position(4, 2);
        Move instance = new Move(start, end, Direction.EAST);

        Position start2 = new Position(3, 2);
        Position end2 = new Position(4, 2);
        Move instance2 = new Move(start2, end2, Direction.NORTH);

        assertFalse(instance.equals(instance2));
        assertFalse(instance.hashCode() == instance2.hashCode());
    }

    /**
     * Test of equals method, of class Move.
     */
    @Test
    public void equalsFalseOtherObject() {
        System.out.println("equalsFalseOtherObject");

        Position start = new Position(3, 2);
        Position end = new Position(4, 2);
        Move instance = new Move(start, end, Direction.NORTH);

        String notEqualsToMove = "hello world";

        assertFalse(instance.equals(notEqualsToMove));
        assertFalse(instance.hashCode() == notEqualsToMove.hashCode());
    }

    /**
     * Test of equals method, of class Move.
     */
    @Test
    public void equalsFalseIsNull() {
        System.out.println("equalsFalseIsNull");

        Position start = new Position(3, 2);
        Position end = new Position(4, 2);
        Move instance = new Move(start, end, Direction.NORTH);

        assertFalse(instance.equals(null));
    }

}
