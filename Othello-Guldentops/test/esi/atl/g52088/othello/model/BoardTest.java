package esi.atl.g52088.othello.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class BoardTest {

    public BoardTest() {
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorWhenRowIsNegative() {
        System.out.println("constructorWhenRowIsNegative");
        Board instance = new Board(-3,5);
        
    }
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorWhenColumnIsNegative() {
        System.out.println("constructorWhenColumIsNegative");
        Board instance = new Board(3,-5);
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetPieceWhenItsNotInside(){
        System.out.println("testGetSquareWhenItsNotInside");
        Board instance = new Board();
        Position notInside = new Position(-4,0);
        instance.getPiece(notInside);
    }

    /**
     * Test of isInside method, of class Board.
     */
    @Test
    public void testIsInsideTrue() {
        System.out.println("isInsideTrue");
        Position position = new Position(1, 1);
        Board instance = new Board();
        boolean expResult = true;
        boolean result = instance.isInside(position);
        assertEquals(expResult, result);

    }

    /**
     * Test of isInside method, of class Board.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIsInsideFalseRow() {
        System.out.println("isInsideFalseRow");
        Position position = new Position(-5, 1);
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isInside(position);
        

    }

    /**
     * Test of isInside method, of class Board.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIsInsideFalseColumn() {
        System.out.println("isInsideFalseColumn");
        Position position = new Position(5, -1);
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isInside(position);

    }

    /**
     * Test of put method, of class Board.
     */
    @Test
    public void testPut() {
        System.out.println("putWhenItsWork");
        Piece piece = new Piece(PlayerColor.WHITE, 0);
        Position position = new Position(1, 1);
        Board instance = new Board();
        instance.put(piece, position);
        assertFalse(instance.getPiece(position) == null);
    }

    /**
     * Test of put method, of class Board.
     */
    @Test(expected = NullPointerException.class)
    public void testPutArgsNull() {
        System.out.println("putWhenGivenArgsAreNull");
        Piece piece = new Piece(null, 0);
        Position position = new Position(1, 1);
        Board instance = new Board();
        instance.put(piece, position);

    }

    /**
     * Test of isFree method, of class Board.
     */
    @Test
    public void testIsFreeTrue() {
        System.out.println("isFreeTrue");
        Position position = new Position(1, 1);
        Board instance = new Board();
        boolean expResult = true;
        boolean result = instance.isFree(position);
        assertEquals(expResult, result);

    }

    /**
     * Test of isFree method, of class Board.
     */
    @Test
    public void testIsFreeFalse() {
        System.out.println("isFreeFalse");
        Position position = new Position(1, 1);
        Board instance = new Board();
        Piece p = new Piece(PlayerColor.WHITE, 0);
        instance.put(p, position);
        boolean expResult = false;
        boolean result = instance.isFree(position);
        assertEquals(expResult, result);

    }

    /**
     * Test of isFree method, of class Board.
     */
    @Test(expected = NullPointerException.class)
    public void testIsFreeWhenArgsIsNull() {
        System.out.println("isFreeWhenArgsIsNull");
        Position position = null;
        Board instance = new Board();

        instance.isFree(position);

    }
    
    /**
     * Test of isFree method, of class Board.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIsFreeWhenArgsIsWrong() {
        System.out.println("isFreeWhenArgsIsWrong");
        Position position = new Position(-4, -4);
        Board instance = new Board();

        instance.isFree(position);

    }

    /**
     * Test of findFree method, of class Board.
     */
    @Test
    public void testFindFreeWhenThereAreFreeSquare() {
        System.out.println("findFreeWhenThereAreFreeSquare");
        Board instance = new Board(2, 2);

        Position p00 = new Position(0, 0);
        Position p01 = new Position(0, 1);
        Position p10 = new Position(1, 0);
        Position p11 = new Position(1, 1);

        List<Position> expResult = new ArrayList<>();
        expResult.add(p00);
        expResult.add(p01);
        expResult.add(p10);
        expResult.add(p11);
        List<Position> result = instance.findFree();

        assertEquals(expResult, result);

    }

    /**
     * Test of findFree method, of class Board.
     */
    @Test
    public void testFindFreeWhenThereAreNotFreeSquare() {
        System.out.println("findFreeWhenThereAreNotFreeSquare");
        Board instance = new Board(2, 2);

        Position p00 = new Position(0, 0);
        Position p01 = new Position(0, 1);
        Position p10 = new Position(1, 0);
        Position p11 = new Position(1, 1);

        Piece pi00 = new Piece(PlayerColor.WHITE, 0);
        Piece pi01 = new Piece(PlayerColor.WHITE, 0);
        Piece pi10 = new Piece(PlayerColor.WHITE, 0);
        Piece pi11 = new Piece(PlayerColor.WHITE, 0);

        instance.put(pi00, p00);
        instance.put(pi01, p01);
        instance.put(pi10, p10);
        instance.put(pi11, p11);

        List<Position> expResult = new ArrayList<>();
        List<Position> result = instance.findFree();

        assertEquals(expResult, result);

    }

    /**
     * Test of isMyOwn method, of class Board.
     */
    @Test
    public void testIsMyOwnTrue() {
        System.out.println("isMyOwnTrue");
        Position position = new Position(0, 0);
        PlayerColor color = PlayerColor.WHITE;
        Board instance = new Board();

        final Piece whitePiece = new Piece(color, 0);
        instance.put(whitePiece, position);

        boolean expResult = true;
        boolean result = instance.isMyOwn(position, color);

        assertEquals(expResult, result);
    }

    /**
     * Test of isMyOwn method, of class Board.
     */
    @Test
    public void testIsMyOwnFalse() {
        System.out.println("isMyOwnFalse");
        Position position = new Position(0, 0);
        PlayerColor color = PlayerColor.WHITE;
        Board instance = new Board();

        final Piece BlackPiece = new Piece(PlayerColor.BLACK, 0);
        instance.put(BlackPiece, position);

        boolean expResult = false;
        boolean result = instance.isMyOwn(position, color);

        assertEquals(expResult, result);
    }

    /**
     * Test of isMyOwn method, of class Board.
     */
    @Test(expected = NullPointerException.class)
    public void testIsMyOwnWhenArgsIsNull() {
        System.out.println("isMyOwnWhenArgsIsNull");
        Position position = null;
        PlayerColor color = null;
        Board instance = new Board();

        final Piece BlackPiece = new Piece(PlayerColor.BLACK, 0);
        instance.put(BlackPiece, position);

        boolean result = instance.isMyOwn(position, color);

    }
    
       /**
     * Test of isMyOwn method, of class Board.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIsMyOwnWhenArgsAreWrong() {
        System.out.println("isMyOwnWhenArgsAreWrong");
        Position position = new Position(-4,0);
        PlayerColor color = PlayerColor.BLACK;
        Board instance = new Board();


        instance.isMyOwn(position, color);

    }

    /**
     * Test of getTakenSquare method, of class Board.
     */
    @Test
    public void testGetTakenSquareWhenThereIsAPositionTaken() {
        System.out.println("getTakenSquareWhenThereIsAPositionTaken");
        Player player = new Player(PlayerColor.WHITE,"");
        Board instance = new Board(2, 2);

        final Piece whitePiece = new Piece(PlayerColor.WHITE, 0);
        final Position position = new Position(0, 0);

        instance.put(whitePiece, position);
        List<Position> expResult = new ArrayList<>();
        expResult.add(position);
        List<Position> result = instance.getTakenSquare(player);

        assertEquals(expResult, result);

    }

    /**
     * Test of getTakenSquare method, of class Board.
     */
    @Test
    public void testGetTakenSquareWhenPlayerHasNoPositionTaken() {
        System.out.println("getTakenSquareWhenThereIsAPositionTaken");
        Player player = new Player(PlayerColor.WHITE, "");
        Board instance = new Board(2, 2);

        List<Position> expResult = new ArrayList<>();

        List<Position> result = instance.getTakenSquare(player);

        assertEquals(expResult, result);

    }

    /**
     * Test of getTakenSquare method, of class Board.
     */
    @Test(expected = NullPointerException.class)
    public void testGetTakenSquareWhenArgsIsNull() {
        System.out.println("getTakenSquareWhenThereIsAPositionTaken");
        Player player = null;
        Board instance = new Board(2, 2);

        List<Position> expResult = new ArrayList<>();
        List<Position> result = instance.getTakenSquare(player);

        assertEquals(expResult, result);

    }
    
    
    
     
    
    @Test
    public void equalsTrueMySelf() {
        Board board = new Board();
        assertTrue(board.equals(board));
        assertTrue(board.hashCode() == board.hashCode());
    }
    
    @Test
    public void equalsTrue() {
        Board board1 = new Board();
        Board board2 = new Board();
        assertTrue(board1.equals(board2));
        assertTrue(board1.hashCode() == board2.hashCode());
    }
    
    @Test
    public void equalsFalse() {
        Board board1 = new Board();
        Board board2 = new Board();
        board2.put(new Piece(PlayerColor.BLACK, 0),new Position(3,3));
        assertFalse(board1.equals(board2));
    }
    
    @Test
    public void equalsFalseOtherObject() {
        Board board1 = new Board();
        String string = "abcd";
        assertFalse(board1.equals(string));
    }

    @Test
    public void equalsFalseNull() {
        Board board1 = new Board();
        assertFalse(board1.equals(null));
    }


}
