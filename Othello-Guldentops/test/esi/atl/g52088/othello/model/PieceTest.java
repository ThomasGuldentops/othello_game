package esi.atl.g52088.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class PieceTest {

    public PieceTest() {
    }

    /**
     * Test of switchColor method, of class Piece.
     */
    @Test
    public void testSwitchColorWhiteToBlack() {
        System.out.println("switchColorWhiteToBlack");
        Piece instance = new Piece(PlayerColor.WHITE, 0);
        instance.switchColor();
        assertFalse(instance.getColor() == PlayerColor.WHITE);
    }

    /**
     * Test of switchColor method, of class Piece.
     */
    @Test
    public void testSwitchColorBlackToWhite() {
        System.out.println("switchColorBlackToWhite");
        Piece instance = new Piece(PlayerColor.BLACK, 0);
        instance.switchColor();
        assertFalse(instance.getColor() == PlayerColor.BLACK);
    }
    
    
    /**
     * Test of hashCode method, of class Piece.
     */
      @Test
    public void equalsTrueMySelf() {
        Piece piece1 = new Piece(PlayerColor.BLACK, 1);
        assertTrue(piece1.equals(piece1));
        assertTrue(piece1.hashCode() == piece1.hashCode());
    }

    
   
    /**
     * Test of equals method, of class Piece.
     */
    @Test
    public void equalsFalseDifferentColor() {
        Piece piece1 = new Piece(PlayerColor.BLACK, 1);
        Piece piece2 = new Piece(PlayerColor.WHITE, 1);
        assertFalse(piece1.equals(piece2));
    }

    /**
     * Test of equals method, of class Piece.
     */
    @Test
    public void equalsFalseDifferentRank() {
        Piece piece1 = new Piece(PlayerColor.BLACK, 1);
        Piece piece2 = new Piece(PlayerColor.WHITE, 2);
        assertFalse(piece1.equals(piece2));
    }
    
    /**
     * Test of equals method, of class Piece.
     */
    @Test
    public void equalsFalseOtherObject() {
        Piece piece = new Piece(PlayerColor.BLACK, 0);
        String string = "abcd";
        assertFalse(piece.equals(string));
    }

    /**
     * Test of equals method, of class Piece.
     */
    @Test
    public void equalsFalseNull() {
        Piece piece = new Piece(PlayerColor.BLACK, 0);
        assertFalse(piece.equals(null));
    }


}
