package esi.atl.g52088.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guldentops Thomas - g52088
 */
public class PlayerColorTest {
    
    public PlayerColorTest() {
    }

    
    @Test
    public void testValues() {
        System.out.println("values");
        PlayerColor[] expResult = {PlayerColor.WHITE, PlayerColor.BLACK};
        PlayerColor[] result = PlayerColor.values();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "BLACK";
        PlayerColor expResult = PlayerColor.BLACK;
        PlayerColor result = PlayerColor.valueOf(name);
        assertEquals(expResult, result);
    }


    /**
     * Test of getWhite method, of class PlayerColor.
     */
    @Test
    public void testGetWhite() {
        System.out.println("getWhite");
        PlayerColor expResult = PlayerColor.WHITE;
        PlayerColor result = PlayerColor.getWhite();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBlack method, of class PlayerColor.
     */
    @Test
    public void testGetBlack() {
        System.out.println("getBlack");
        PlayerColor expResult = PlayerColor.BLACK;
        PlayerColor result = PlayerColor.getBlack();
        assertEquals(expResult, result);
    }
    
}
