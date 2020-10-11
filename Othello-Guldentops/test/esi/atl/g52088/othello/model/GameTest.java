package esi.atl.g52088.othello.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class GameTest {

    public final Board defaultBoard = new Board();

    @Before
    public void setUp() throws Exception {
        defaultBoard.put(new Piece(PlayerColor.WHITE, 1), new Position(3, 3));
        defaultBoard.put(new Piece(PlayerColor.BLACK, 1), new Position(3, 4));
        defaultBoard.put(new Piece(PlayerColor.WHITE, 1), new Position(4, 4));
        defaultBoard.put(new Piece(PlayerColor.BLACK, 1), new Position(4, 3));
    }

    public GameTest() {
    }

    @Test
    public void testGetCurrentBlack() {
        System.out.println("testGetCurrent");
        Game instance = new Game();
        instance.initialize();
        assertEquals(PlayerColor.BLACK, instance.getCurrent().getColor());
    }

    @Test
    public void testGetCurrentWhite() {
        System.out.println("testGetCurrentWhite");
        Game instance = new Game();
        instance.initialize();
        instance.swapPlayers();
        assertEquals(PlayerColor.WHITE, instance.getCurrent().getColor());
    }

    /**
     * Test of initialize method, of class Game.
     */
//    @Test
    public void testInitialize() {
        System.out.println("initialize");
        Game instance = new Game();
        instance.initialize();

        assertTrue(instance.getBoard().getPiece(new Position(3, 4)) != null);
        assertTrue(instance.getBoard().getPiece(new Position(4, 3)) != null);
        assertTrue(instance.getBoard().getPiece(new Position(3, 3)) != null);
        assertTrue(instance.getBoard().getPiece(new Position(4, 4)) != null);
    }

    /**
     * Test of start method, of class Game.
     */
    @Test(expected = NullPointerException.class)
    public void testStartWhenBoardIsNull() {
        System.out.println("startWhenBoardIsNull");
        Game instance = new Game();
        instance.start();
    }

    /**
     * Test of start method, of class Game.
     */
    @Test(expected = IllegalStateException.class)
    public void testStartWhenGameIsOver() {
        System.out.println("startWhenGameIsOver");
        Game instance = new Game();
        instance.initialize();
        instance.getCurrent().getBagContent().clear();
        instance.getOpponent().getBagContent().clear();
        instance.start();
    }

    /**
     * Test of isOver method, of class Game.
     */
    @Test
    public void testIsOverFalse() {
        System.out.println("isOver");
        Game instance = new Game();
        instance.initialize();
        boolean expResult = false;
        boolean result = instance.isOver();
        assertEquals(expResult, result);
    }

    /**
     * Test of isOver method, of class Game.
     */
    @Test
    public void testIsOverWhenBothPlayersDontHavePiece() {
        System.out.println("isOverWhenBothPlayersDontHavePiece");
        Game instance = new Game();
        instance.initialize();
        instance.getCurrent().getBagContent().clear();
        instance.getOpponent().getBagContent().clear();

        boolean expResult = true;
        boolean result = instance.isOver();

        assertEquals(expResult, result);
    }

    /**
     * Test of isOver method, of class Game.
     */
    @Test
    public void testIsOverWhenBothPlayersDontHaveMove() {
        System.out.println("isOverWhenBothPlayersDontHaveMove");
        Game instance = new Game();

        instance.initialize();

        instance.getCurrent().getPieces().clear();
        instance.getCurrent().getBagContent().clear();

        instance.swapPlayers();

        instance.getCurrent().getPieces().clear();
        instance.getCurrent().getBagContent().clear();

        boolean expResult = true;
        boolean result = instance.isOver();

        assertEquals(expResult, result);
    }

    @Test
    public void testGetOpponentBlack() {
        System.out.println("testGetOpponentBlack");
        Game instance = new Game();
        instance.initialize();
        instance.swapPlayers();
        assertEquals(PlayerColor.BLACK, instance.getOpponent().getColor());
    }

    @Test
    public void testGetOpponentWhite() {
        System.out.println("testGetOpponentWhite");
        Game instance = new Game();
        instance.initialize();
        assertEquals(PlayerColor.WHITE, instance.getOpponent().getColor());
    }

    /**
     * Test of play method, of class Game.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPlayWhenRowIsOutside() {
        System.out.println("testPlayWhenRowIsOutside");
        int row = -3;
        int column = 0;
        Game instance = new Game();
        instance.initialize();
        instance.play(row, column);

    }

    /**
     * Test of play method, of class Game.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPlayWhenColumnIsOutside() {
        System.out.println("testPlayWhenColumnIsOutside");
        int row = 0;
        int column = -3;
        Game instance = new Game();
        instance.initialize();
        instance.play(row, column);

    }

    /**
     * Test of play method, of class Game.
     */
    @Test(expected = IllegalStateException.class)
    public void testPlayWhenPositionIsAlreadyOccupiedByAPiece() {
        System.out.println("testPlayWhenPositionIsAlreadyOccupiedByAPiece");
        int row = 3;
        int column = 3;
        Game instance = new Game();
        instance.initialize();
        instance.play(row, column);
    }

    /**
     * Test of getMoves method, of class Game.
     */
    @Test
    public void testGetMoves() {
        System.out.println("testGetMoves");
        Game instance = new Game();
        instance.initialize();
        List<Move> expResult = new ArrayList<>();

        Position p1 = new Position(3, 4);// position of first black pion
        Move m1 = new Move(p1, new Position(5, 4), Direction.SOUTH);
        Move m2 = new Move(p1, new Position(3, 2), Direction.WEST);

        Position p2 = new Position(4, 3); // position of second black pion
        Move m3 = new Move(p2, new Position(4, 5), Direction.EAST);
        Move m4 = new Move(p2, new Position(2, 3), Direction.NORTH);

        expResult.add(m1);
        expResult.add(m2);
        expResult.add(m4);
        expResult.add(m3);

        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);

    }

    /**
     * Test of flipAllPieceBetweenTwoPositions method, of class Game.
     */
    @Test
    public void testFlipAllPieceBetweenTwoPositions() {
        System.out.println("testFlipAllPieceBetweenTwoPositions");
        Position start = new Position(3, 2);
        Game instance = new Game();
        instance.initialize();
        instance.play(3, 2);
        instance.flipAllPieceBetweenTwoPositions(start);

        assertEquals(PlayerColor.BLACK, instance.getBoard().getPiece(new Position(3, 3)).getColor());
    }

    /**
     * Test of updateScore method, of class Game.
     */
    @Test
    public void testUpdateScore() {
        System.out.println("testUpdateScore");
        Game instance = new Game();
        instance.initialize();
        instance.updateScore();

        assertEquals(2, instance.getCurrent().getScore());
        assertEquals(2, instance.getOpponent().getScore());
    }

    /**
     * Test of updateScore method, of class Game.
     */
    @Test
    public void testUpdateScoreWhenAPieceIsOnTheBoard() {
        System.out.println("testUpdateScoreWhenAPieceIsOnTheBoard");
        Game instance = new Game();

        instance.initialize();
        instance.flipAllPieceBetweenTwoPositions(new Position(3, 2));
        instance.getBoard().put(new Piece(PlayerColor.BLACK, 1),
                new Position(3, 2));

        instance.updateScore();

        assertEquals(4, instance.getCurrent().getScore());
        assertEquals(1, instance.getOpponent().getScore());

    }

    /**
     * Test of swapPlayers method, of class Game.
     */
    @Test
    public void testSwapPlayers() {
        System.out.println("testSwapPlayers");
        Game instance = new Game();

        instance.initialize();
        instance.swapPlayers();

        assertEquals(instance.getCurrent().getColor(), PlayerColor.WHITE);
    }

    /**
     * Test of hasMove method, of class Game.
     */
    @Test
    public void testHasMoveTrueForCurrentPlayer() {
        System.out.println("testHasMoveTrueForCurrentPlayer");
        Game instance = new Game();
        instance.initialize();
        Player player = instance.getCurrent();
        boolean expResult = true;
        boolean result = instance.hasMove(player);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasMove method, of class Game.
     */
    @Test
    public void testHasMoveFalseForCurrentPlayer() {
        System.out.println("testHasMoveFalseForCurrentPlayer");
        Game instance = new Game();
        Player player = instance.getCurrent();

        instance.initialize();
        instance.getBoard().getPiece(new Position(3, 4)).switchColor();
        instance.getBoard().getPiece(new Position(4, 3)).switchColor();

        boolean expResult = false;
        boolean result = instance.hasMove(player);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasMove method, of class Game.
     */
    @Test
    public void testHasMoveTrueForOpponentPlayer() {
        System.out.println("testHasMoveTrueForOpponentPlayer");
        Game instance = new Game();
        instance.initialize();
        Player player = instance.getOpponent();
        boolean expResult = true;
        boolean result = instance.hasMove(player);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasMove method, of class Game.
     */
    @Test
    public void testHasMoveFalseForOpponentPlayer() {
        System.out.println("testHasMoveFalseForOpponentPlayer");
        Game instance = new Game();
        Player player = instance.getOpponent();

        instance.initialize();
        instance.getBoard().getPiece(new Position(3, 3)).switchColor();
        instance.getBoard().getPiece(new Position(4, 4)).switchColor();

        boolean expResult = false;
        boolean result = instance.hasMove(player);
        assertEquals(expResult, result);
    }

    /**
     * Test of getWinner method, of class Game.
     */
    @Test
    public void testGetWinnersWhenCurrentPlayerWin() {
        System.out.println("testGetWinnersWhenCurrentPlayerWin");
        Game instance = new Game();
        instance.initialize();

        instance.getCurrent().getBagContent().clear();
        instance.getOpponent().getBagContent().clear();
        instance.getOpponent().setScore(0);
        instance.getCurrent().setScore(30);

        Player expResult = instance.getCurrent();
        Player result = instance.getWinner();

        assertEquals(expResult, result);
    }

    /**
     * Test of getWinner method, of class Game.
     */
    @Test
    public void testGetWinnersWhenOpponentPlayerWin() {
        System.out.println("testGetWinnersWhenOpponentPlayerWin");
        Game instance = new Game();
        instance.initialize();
        instance.getCurrent().setScore(0);

        instance.getCurrent().getBagContent().clear();
        instance.getOpponent().getBagContent().clear();

        Player expResult = instance.getOpponent();
        Player result = instance.getWinner();

        assertEquals(expResult, result);
    }

    /**
     * Test of getWinner method, of class Game.
     */
    @Test(expected = IllegalStateException.class)
    public void testGetWinnersWhenGameIsNotOver() {
        System.out.println("testGetWinnersWhenGameIsNotOver");
        Game instance = new Game();
        instance.initialize();
        instance.getCurrent().setScore(0);

        instance.getWinner();

    }

    /**
     * Test of start method, of class Game.
     */
    @Test
    public void testStart() {
        System.out.println("testStart");
        Game instance = new Game();
        instance.initialize();
        instance.start();
    }

    /**
     * Test of canCapture method, of class Game.
     */
    @Test
    public void testCanCaptureTrue() {
        System.out.println("testCanCaptureTrue");
        Position p = new Position(3, 2);
        Game instance = new Game();
        instance.initialize();
        boolean expResult = true;
        boolean result = instance.canCapture(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of canCapture method, of class Game.
     */
    @Test
    public void testCanCaptureFalse() {
        System.out.println("testCanCaptureFalse");
        Position p = new Position(1, 1);
        Game instance = new Game();
        instance.initialize();
        boolean expResult = false;
        boolean result = instance.canCapture(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of play method, of class Game.
     */
    @Test
    public void testPlay() {
        System.out.println("testPlay");
        int row = 0;
        int column = 0;
        Game instance = new Game();
        instance.initialize();
        instance.play(row, column);
    }

    /**
     * Test of scoreFreeSquare method, of class Game.
     */
    @Test
    public void testScoreFreeSquare() {
        System.out.println("testScoreFreeSquare");
        Game instance = new Game();
        instance.initialize();
        int expResult = 60;
        int result = instance.scoreFreeSquare();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFinalScore method, of class Game.
     */
    @Test
    public void testGetFinalScoreCurrentWin() {
        System.out.println("testGetFinalScoreCurrentWin");
        Game instance = new Game();
        instance.initialize();

        instance.getBoard().getPiece(new Position(3, 3)).switchColor();
        instance.getBoard().getPiece(new Position(4, 4)).switchColor();

        instance.getCurrent().getBagContent().clear();
        instance.getOpponent().getBagContent().clear();

        instance.updateScore();
        instance.getFinalScore();

        assertTrue(instance.isOver());
        assertEquals(instance.getCurrent(), instance.getWinner());
        assertEquals(64, instance.getCurrent().getScore());
    }

    /**
     * Test of getFinalScore method, of class Game.
     */
    @Test
    public void testGetFinalScoreOpponentWin() {
        System.out.println("testGetFinalScoreOpponentWin");
        Game instance = new Game();
        instance.initialize();

        instance.getBoard().getPiece(new Position(3, 4)).switchColor();
        instance.getBoard().getPiece(new Position(4, 3)).switchColor();

        instance.getCurrent().getBagContent().clear();
        instance.getOpponent().getBagContent().clear();

        instance.updateScore();
        instance.getFinalScore();

        assertTrue(instance.isOver());
        assertEquals(instance.getOpponent(), instance.getWinner());
        assertEquals(64, instance.getOpponent().getScore());
    }

    /**
     * Test of isGameOver method, of class Game.
     */
    @Test
    public void testIsGameOverFalse() {
        System.out.println("testIsGameOverFalse");
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.isGameOver();
        assertEquals(expResult, result);

    }

    /**
     * Test of isGameOver method, of class Game.
     */
    @Test
    public void testIsGameOverTrue() {
        System.out.println("testIsGameOverTrue");
        Game instance = new Game();
        instance.setGameOver(true);
        boolean expResult = true;
        boolean result = instance.isGameOver();
        assertEquals(expResult, result);

    }

    /**
     * Test of setGameOver method, of class Game.
     */
    @Test
    public void testSetGameOverFalse() {
        System.out.println("testSetGameOverFalse");
        boolean gameOver = false;
        Game instance = new Game();
        instance.initialize();
        instance.setGameOver(gameOver);
        assertFalse(instance.isOver());
    }

    /**
     * Test of setGameOver method, of class Game.
     */
    @Test
    public void testSetGameOverTrue() {
        System.out.println("testSetGameOverTrue");
        boolean gameOver = true;
        Game instance = new Game();
        instance.initialize();
        instance.setGameOver(gameOver);
        assertTrue(instance.isOver());
    }

    /**
     * Test of getPosition method, of class Game.
     */
    @Test(expected = NullPointerException.class)
    public void testGetPosition() {
        System.out.println("testGetPosition");
        Game instance = new Game();
        List<Position> expResult = null;
        List<Position> result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of resetGame method, of class Game.
     */
    @Test
    public void testResetGame() {
        System.out.println("testResetGame");
        Game instance = new Game();

        instance.getCurrent().getBagContent().clear();
        instance.getOpponent().getBagContent().clear();

        instance.resetGame();
        // --- test if the bags are filled correctly after reset
        assertEquals(30, instance.getCurrent().getBagContent().size());
        assertEquals(30, instance.getOpponent().getBagContent().size());

        assertEquals(2, instance.getScore(PlayerColor.BLACK));
        assertEquals(2, instance.getScore(PlayerColor.WHITE));
    }

    /**
     * Test of progressBarScore method, of class Game.
     */
    @Test
    public void testProgressBarScore() {
        System.out.println("testProgressBarScore");
        Game instance = new Game();
        instance.initialize();
        instance.play(5, 4);
        double expResult = 0.8;
        double result = instance.progressBarScore();
        assertEquals(expResult, result, 0.8);
    }

    /**
     * Test of getScore method, of class Game.
     */
    @Test
    public void testGetScoreCurrent() {
        System.out.println("testGetScoreCurrent");
        PlayerColor color = PlayerColor.BLACK;
        Game instance = new Game();
        instance.initialize();
        int expResult = 2;
        int result = instance.getScore(color);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetScoreOpponent() {
        System.out.println("testGetScoreOpponent");
        PlayerColor color = PlayerColor.WHITE;
        Game instance = new Game();
        instance.initialize();
        int expResult = 2;
        int result = instance.getScore(color);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentAction method, of class Game.
     */
    @Test
    public void testGetCurrentAction() {
        System.out.println("getCurrentAction");
        Game instance = new Game();
        String expResult = "commence";
        String result = instance.getCurrentAction();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCurrentAction method, of class Game.
     */
    @Test
    public void testSetCurrentAction() {
        System.out.println("setCurrentAction");
        Action currentAction = Action.PASS;
        Game instance = new Game();
        instance.setCurrentAction(currentAction);
        assertEquals(instance.getCurrentAction(), "passe ");
    }

    @Test(expected = NullPointerException.class)
    public void testSetCurrentActionWhenActionGivenIsNull() {
        System.out.println("testSetCurrentActionWhenActionGivenIsNull");
        Action currentAction = null;
        Game instance = new Game();
        instance.setCurrentAction(currentAction);
    }


    /**
     * Test of getCaught method, of class Game.
     */
    @Test
    public void testGetCaught() {
        System.out.println("getCaught");
        Game instance = new Game();
        instance.initialize();
        instance.play(5,4);
        int expResult = 1;
        int result = instance.getCaught();
        assertEquals(expResult, result);
    }


}
