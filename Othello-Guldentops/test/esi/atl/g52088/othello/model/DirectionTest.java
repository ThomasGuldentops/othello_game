/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atl.g52088.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guldentops Thomas - g52088
 */
public class DirectionTest {

    public DirectionTest() {
    }

    /**
     * Test of values, of Direction class
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Direction[] expResult = {
            Direction.NORTH, Direction.SOUTH,
            Direction.WEST, Direction.EAST,
            Direction.NORTHWEST, Direction.NORTHEAST,
            Direction.SOUTHWEST, Direction.SOUTHEAST};
        Direction[] result = Direction.values();

        for (Direction direction : result) {
            System.out.println(direction + "\n");
        }
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getRow, of Direction class
     */
    @Test
    public void testGetRow() {
        System.out.println("testGetRow");
        Direction instance = Direction.SOUTH;
        int expResult = 1;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumn, of Direction class
     */
    @Test
    public void testGetColumn() {
        System.out.println("testGetColumn");
        Direction instance = Direction.EAST;
        int expResult = 1;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of valueOf, of Direction class
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "NORTH";
        Direction expResult = Direction.NORTH;
        Direction result = Direction.valueOf(name);
        assertEquals(expResult, result);
    }


}
