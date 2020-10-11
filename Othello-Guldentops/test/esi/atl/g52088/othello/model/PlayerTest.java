package esi.atl.g52088.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class PlayerTest {

    public PlayerTest() {
    }

    /**
     * Test of getColor method, of class Player.
     */
    @Test
    public void testGetColorWhite() {
        System.out.println("getColorWhite");

        Player instance = new Player(PlayerColor.WHITE, "");
        PlayerColor expResult = PlayerColor.WHITE;

        PlayerColor result = instance.getColor();

        assertEquals(expResult, result);

    }

    /**
     * Test of getColor method, of class Player.
     */
    @Test
    public void testGetColorBlack() {
        System.out.println("getColorBlack");

        Player instance = new Player(PlayerColor.BLACK, "");
        PlayerColor expResult = PlayerColor.BLACK;

        PlayerColor result = instance.getColor();

        assertEquals(expResult, result);

    }

    /**
     * Test of addPiece method, of class Player.
     */
    @Test
    public void testAddPiece() {
        System.out.println("addPiece");

        Piece piece = new Piece(PlayerColor.WHITE, 3);
        Player instance = new Player(PlayerColor.WHITE, "");

        instance.addPiece(piece);

        assertTrue(instance.getBagContent().contains(piece));
    }

    /**
     * Test of remove method, of class Player.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");

        Piece piece = new Piece(PlayerColor.WHITE, 3);
        Player instance = new Player(PlayerColor.WHITE, "");

        instance.remove(piece);

        assertFalse(instance.getBagContent().contains(piece));

    }

    @Test
    public void testRemoveWhenListEmpty() {
        System.out.println("testRemoveWhenListEmpty");
        Player player = new Player(PlayerColor.BLACK, "");
        Piece piece = new Piece(PlayerColor.BLACK, 0);
        player.addPiece(piece);
        player.remove(piece);
        assertEquals(player.getPieces().size(), 0);
    }

    @Test
    public void testRemoveWhenListNotEmpty() {
        System.out.println("testRemoveWhenListNotEmpty");
        Player instance = new Player(PlayerColor.BLACK, "");
        Piece p1 = new Piece(PlayerColor.BLACK, 0);
        Piece p2 = new Piece(PlayerColor.BLACK, 1);

        instance.addPiece(p1);
        instance.addPiece(p2);
        instance.remove(p1);

        assertFalse(instance.getPieces().contains(p1));
    }

    /**
     * Test of hasPieceInBag method, of class Player.
     */
    @Test
    public void testHasPieceInBagTrue() {
        System.out.println("hasPieceInBagTrue");
        Player instance = new Player(PlayerColor.WHITE, "");

        boolean expResult = true;
        boolean result = instance.hasPieceInBag();

        assertEquals(expResult, result);
    }

    /**
     * Test of hasPieceInBag method, of class Player.
     */
    @Test
    public void testHasPieceInBagFalse() {
        System.out.println("hasPieceInBagFalse");
        Player instance = new Player(PlayerColor.WHITE, "");

        instance.getBagContent().clear();

        boolean expResult = false;
        boolean result = instance.hasPieceInBag();

        assertEquals(expResult, result);
    }

    /**
     * Test of randomPiece method, of class Player.
     */
    @Test
    public void testRandomPiece() {
        System.out.println("randomPiece");
        Player instance = new Player(PlayerColor.WHITE, "");
        Piece result = instance.randomPiece();

        assertFalse(instance.getPieces().contains(result));

    }

    /**
     * Test of setScore method, of class Player.
     */
    @Test
    public void testSetScore() {
        System.out.println("setScore");
        int score = 5;
        Player instance = new Player(PlayerColor.WHITE, "");
        instance.setScore(score);

        assertEquals(score, instance.getScore());
    }

    /**
     * Test of setScore method, of class Player.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetScoreWhenGivenScoreIsNegative() {
        System.out.println("setScore");
        int score = -5;
        Player instance = new Player(PlayerColor.WHITE, "");
        instance.setScore(score);
    }

    /**
     * Test of getScore method, of class Player.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Player instance = new Player(PlayerColor.WHITE, "");
        int expResult = 5;
        instance.setScore(expResult);
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBagContent method, of class Player.
     */
    @Test
    public void testGetBagContentWhite() {
        System.out.println("getBagContent");
        Player instance = new Player(PlayerColor.WHITE, "");
        int expResult = 30;
        int result = instance.getBagContent().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBagContent method, of class Player.
     */
    @Test
    public void testGetBagContentBlack() {
        System.out.println("getBagContent");
        Player instance = new Player(PlayerColor.BLACK, "");
        int expResult = 30;
        int result = instance.getBagContent().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Player.
     */
    @Test
    public void equalsTrueMySelf() {
        Player player1 = new Player(PlayerColor.BLACK, "");
        assertTrue(player1.equals(player1));
        assertTrue(player1.hashCode() == player1.hashCode());
    }

    /**
     * Test of equals method, of class Player.
     */
    @Test
    public void equalsFalseDifferentColor() {
        Player player1 = new Player(PlayerColor.BLACK, "");
        Player player2 = new Player(PlayerColor.WHITE, "");
        assertFalse(player1.equals(player2));
    }

    /**
     * Test of equals method, of class Player.
     */
    @Test
    public void equalsFalseDifferentPiece() {
        Player player1 = new Player(PlayerColor.BLACK, "");
        player1.addPiece(new Piece(PlayerColor.BLACK, 1));

        Player player2 = new Player(PlayerColor.BLACK, "");
        player2.addPiece(new Piece(PlayerColor.BLACK, 2));

        assertFalse(player1.equals(player2));
    }

    /**
     * Test of equals method, of class Player.
     */
    @Test
    public void equalsFalseOtherObject() {
        Player player1 = new Player(PlayerColor.BLACK, "");
        String player2 = "abcd";
        assertFalse(player1.equals(player2));
    }

    /**
     * Test of equals method, of class Player.
     */
    @Test
    public void equalsFalseNull() {
        Player player1 = new Player(PlayerColor.BLACK, "");
        assertFalse(player1.equals(null));
    }

}
