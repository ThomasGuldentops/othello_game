package esi.atl.g52088.othello.model;

import esi.atl.g52088.othello.util.Observer;
import java.util.List;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public interface Model {

    /**
     * Initializes the Othello game with a default board.
     */
    void initialize();

    /**
     * Checks if a match can start.
     *
     * @throws IllegalStateException if no board is set.
     * @throws IllegalStateException if the board set is incomplete.
     */
    void start();

    /**
     * Declares if it's the end of the game.
     *
     * @return true if it's the end of the game.
     */
    boolean isOver();

    /**
     * Returns the board.
     *
     * @return the board.
     */
    Board getBoard(); // return Board

    /**
     * To play a position ont the board
     *
     * @param row play the row
     * @param column play the column
     */
    void play(int row, int column);

    /**
     * To know which player is the current player
     *
     * @return current player
     */
    Player getCurrent();

    /**
     * Get the opponent.
     *
     * @return opponent player.
     */
    Player getOpponent();

    /**
     * Return winner
     *
     * @return the winner
     */
    Player getWinner();

    /**
     * Transform the list of Move into a List of Position. Will be used on the
     * canPut() method int the view.
     *
     * @return the list of move's position
     */
    List<Position> getPosition();

    /**
     * To reset the game. It will be called when the "reset" button is pressed.
     */
    void resetGame();

    /**
     * The score of the game. The score will be calculated by adding all the
     * pieces on the board and divided by the total number of boxes
     *
     * @return number total of pieces / number total of boxes
     */
    double progressBarScore();

    /**
     * To get the current score of the given player this score is calculated by
     * adding all the value of the current piece's on the board
     *
     * @param color the player which be examinated
     * @return the score of the given player
     */
    int getScore(PlayerColor color);

    /**
     * Know what is the current action (BEGIN, PASS, ...)
     *
     * @return the current action
     */
    String getCurrentAction();

    /**
     * To change the current action. Use when a player click on a button
     *
     * @param currentAction the new value of currentAction
     */
    void setCurrentAction(Action currentAction);

    /**
     * To count how many pieces are caught on the current move
     *
     * @return number of flipped pieces
     */
    int getCaught();

    /**
     * To have the last move on the game
     *
     * @return the last move
     */
    MoveInformation getCurrentMove();

    /**
     * To know the current player's name
     *
     * @return current player name
     */
    String getCurrentName();

    /**
     * To know the opponent player's name
     *
     * @return opponent player name
     */
    String getOpponentName();

    /**
     * To know if the game is over or not, i added this for the "abandon"
     * button.
     *
     * @return true if the game is over, false if it's not
     */
    boolean isGameOver();

    /**
     * To change the current state of the game. It will be called when the
     * "abandon" button will be pressed by a player
     *
     * @param gameOver the state of the game
     */
    void setGameOver(boolean gameOver);

    /**
     * To add a new observer. This observer will be added on the list observers,
     * this list containt all the observers
     *
     * @param observer the new observer
     */
    void addObserver(Observer observer);

    /**
     * Allow to swap the value between current and opponent player. The content
     * of the current attribute is placed in opponent and the content of the
     * opponent attribute is placed in current.
     */
    void swapPlayers();
    
    /*
    modifications
    */
    
    public List<Position> getSpecialsSquares();
}
