package esi.atl.g52088.othello.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static esi.atl.g52088.othello.model.PlayerColor.*;
import esi.atl.g52088.othello.util.Observable;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class Game extends Observable implements Model {

    private Board board;
    private Player current;
    private Player opponent;

    private boolean gameOver;
    private Action currentAction;

    private int caught;

    private ArrayList<MoveInformation> moveHistory = new ArrayList<>();

    private List<Position> specialsSquares = new ArrayList<>();

    /**
     * Allows to create two players. Current player is BLACK and opponent player
     * is WHITE.
     */
    public Game() {
        this.current = new Player(BLACK, "");
        this.opponent = new Player(WHITE, "");

        currentAction = Action.BEGIN;
    }

    /**
     * Allow to get the game board
     *
     * @return the Board.
     */
    @Override
    public Board getBoard() {
        return board;
    }

    /**
     * Get the current player.
     *
     * @return current player.
     */
    @Override
    public Player getCurrent() {
        return current;
    }

    /**
     * Get the opponent.
     *
     * @return opponent player.
     */
    @Override
    public Player getOpponent() {
        return opponent;
    }

    /**
     * To know if the game is over or not, i added this for the "abandon"
     * button.
     *
     * @return true if the game is over, false if it's not
     */
    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * To change the current state of the game. It will be called when the
     * "abandon" button will be pressed by a player
     *
     * @param gameOver the state of the game
     */
    @Override
    public void setGameOver(boolean gameOver) {
        if (currentAction == Action.SURREND) {
            moveHistory.add(new MoveInformation(getCurrent().getName(),
                    getCurrentAction()));
            notifyObservers();
        }
        this.gameOver = gameOver;

    }

    /**
     * Initialize (create) the board and place the defaults pieces in.
     */
    @Override
    public void initialize() {
        board = new Board();

        //Current player pieces
        Piece b1 = new Piece(BLACK, 1);
        current.getPieces().add(b1);
        Position positionb1 = new Position(3, 4);
        board.put(b1, positionb1);

        Piece b2 = new Piece(BLACK, 1);
        current.getPieces().add(b2);
        Position positionb2 = new Position(4, 3);
        board.put(b2, positionb2);

        //Opponent player pieces
        Piece n1 = new Piece(WHITE, 1);
        opponent.getPieces().add(n1);
        Position positionN1 = new Position(3, 3);
        board.put(n1, positionN1);

        Piece n2 = new Piece(WHITE, 1);
        opponent.getPieces().add(n2);
        Position positionN2 = new Position(4, 4);
        board.put(n2, positionN2);

        this.createBonusSquares();
    }

    /**
     * Allows to verify if the game can start if not a exception is thrown.
     *
     * @throws NullPointerException to verify if the board was correctly created
     * or not.
     * @throws IllegalStateException throw an exception if the game is over
     * although it shouldn't be
     *
     */
    @Override
    public void start() {
        Objects.requireNonNull(board, "The board cannot be null,"
                + " you cannot play without a board.");

        if (this.isOver()) {
            throw new IllegalStateException("The game is over.");
        }

        if (currentAction == Action.BEGIN) {
            moveHistory.add(new MoveInformation(getCurrent().getName(),
                    getCurrentAction()));
        }
    }

    /**
     * This method allow to flip all the ennemies pieces between the
     * positionCurrentPiece (which means the piece the player just putted on the
     * board) and all the current piece around.
     *
     * @param positionCurrentPiece piece the player just putted on the board
     */
    void flipAllPieceBetweenTwoPositions(Position positionCurrentPiece) {
        Objects.requireNonNull(positionCurrentPiece,
                "The given position can't be null.");

        caught = 0;

        for (Move m : this.getMoves()) {
            Position end = m.getEnd();

            if (end.equals(positionCurrentPiece)) {
                Position ennemyPosition = m.getStart().next(m.getDirection());

                while (board.isInside(ennemyPosition)
                        && !board.isFree(ennemyPosition)
                        && board.isMyOwn(ennemyPosition, opponent.getColor())) {

                    board.getPiece(ennemyPosition).switchColor();
                    ennemyPosition = ennemyPosition.next(m.getDirection());

                    caught++;
                }
            }
        }
    }

    /**
     * To know if from the given Position there are ennemies pieces to capture
     *
     * @param position position which be evaluated
     * @return true if an ennemy piece can be capturated false in other any
     * cases
     */
    boolean canCapture(Position position) {
        Objects.requireNonNull(position, "The given position can't be null.");

        boolean capture = false;
        for (Move m : this.getMoves()) {
            Position end = m.getEnd();
            if (end.equals(position)) {
                capture = true;
            }
        }
        return capture;
    }

    /**
     * Allow to play a piece on a square on the board.
     *
     * @throws IllegalArgumentException to test if the position is inside
     * @throws IllegalArgumentException to know if the position is free or not
     * @throws IllegalStateException to know if the end position is occupied by
     * an enemy piece
     *
     * @param row selected square row
     * @param column selected square column
     */
    @Override
    public void play(int row, int column) {
        Position position = new Position(row, column);

        if (!board.isInside(position)) {
            throw new IllegalArgumentException("Position out of board.");
        }
        if (!board.isFree(position)) {
            throw new IllegalStateException("There is already a piece on "
                    + "this square.");
        }

        if (canCapture(position)) {

            currentAction = Action.PUT;

            Piece currentPiece = current.randomPiece();
            this.flipAllPieceBetweenTwoPositions(position);
            board.put(currentPiece, position);
            current.remove(currentPiece);

            this.updateScore();

            moveHistory.add(new MoveInformation(getCurrent().getName(),
                    currentAction.getText(), column + "-" + row, getCaught()));

            this.swapPlayers();

            notifyObservers();

            if (current.isBot()) {
                playBot();
            }

        }

    }

    private void playBot() {
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                Position position = new Position(i, j);
                if (canCapture(position)) {
                    positions.add(position);
                }
            }
        }
        Position toPlay = positions.get(
                (int) (Math.random() * (positions.size() - 1))
        );

        play(toPlay.getRow(), toPlay.getColumn());

    }

    /**
     * To get all the possibles moves for the current player.
     *
     *
     * @throws IllegalArgumentException throw an exception if no piece was
     * selected
     *
     * @return a list of moves.
     */
    List<Move> getMoves() {
        List<Move> moves = new ArrayList<>();

        for (Position position : board.getTakenSquare(current)) {
            for (Direction direction : Direction.values()) {

                Position end = position.next(direction);

                // Will become true if an opponent piece is next to the choosen
                // position
                boolean isThisPositionCanBeAdded = false;

                // Test if there is a opponent piece in the next direction.
                while (board.isInside(end) && !board.isFree(end)
                        && !board.isMyOwn(end, current.getColor())) {

                    isThisPositionCanBeAdded = true;
                    end = end.next(direction);
                }

                // To see if the last checked position is free.
                if (isThisPositionCanBeAdded
                        && board.isInside(end)
                        && board.isFree(end)) {

                    moves.add(new Move(position, end, direction));
                }
            }
        }

        return moves;
    }

    /**
     * Check if current player can still make a move
     *
     * @param player a player
     *
     * @return true if current player can still make a move, false in other any
     * cases
     */
    boolean hasMove(Player player) {
        Objects.requireNonNull(player, "The given player can't be null.");

        return !getPosition().isEmpty();
    }

    /**
     * Update the score of the current player
     */
    public void updateScore() {
        int score = 0;
        for (Position p : board.getTakenSquare(current)) {
            int value = board.getPiece(p).getValue();
            score += value;
        }
        current.setScore(score);
        score = 0;

        for (Position p : board.getTakenSquare(opponent)) {
            int value = board.getPiece(p).getValue();
            score += value;
        }
        opponent.setScore(score);

    }

    /**
     * Allow to swap the value between current and opponent player. The content
     * of the current attribute is placed in opponent and the content of the
     * opponent attribute is placed in current.
     */
    @Override
    public void swapPlayers() {
        if (!this.isOver()) {
            Player tmp = current;
            current = opponent;
            opponent = tmp;

            if (currentAction == Action.PASS || currentAction == Action.SURREND) {
                moveHistory.add(new MoveInformation(current.getName(),
                        getCurrentAction()));
                notifyObservers();
            }

        }

    }

    /**
     * Allows to know if the game is over.
     *
     * @return true if the game is over false in any other cases.
     */
    @Override
    public boolean isOver() {
        return (this.gameOver) || (!current.hasPieceInBag()
                && !opponent.hasPieceInBag())
                || (!hasMove(current) && !hasMove(opponent));
    }

    /**
     * Return how many empty square there are on the board.
     *
     * @return number of empty square on the board.
     */
    int scoreFreeSquare() {
        return board.findFree().size();
    }

    /**
     * to add to the winner score the number of empty square left on the board.
     */
    public int getFinalScore() {
        if (this.getWinner() == current) {
            for (Position position : getBoard().getTakenSquare(current)) {
                if (getSpecialsSquares().contains(position)) {
                    current.setScore(current.getScore() + 3);
                } else {
                    current.setScore(current.getScore() + this.scoreFreeSquare());

                }
            }
            return current.getScore();
        } else {
            for (Position position : getBoard().getTakenSquare(opponent)) {
                if (getSpecialsSquares().contains(position)) {
                    opponent.setScore(opponent.getScore() + 3);
                } else {
                    opponent.setScore(opponent.getScore() + this.scoreFreeSquare());

                }
            }
            return opponent.getScore();
        }
        

    }

    /**
     * This method return the winner
     *
     * @throws IllegalStateException If the game isn't finish
     *
     * @return the winner of the game.
     */
    @Override
    public Player getWinner() {
        if (!this.isOver()) {
            throw new IllegalStateException("Game is not finished yet !");
        }

        return current.getScore() > opponent.getScore() ? current : opponent;
    }

    /**
     * Transform the list of Move into a List of Position. Will be used on the
     * canPut() method int the view.
     *
     * @return the list of move's position
     */
    @Override
    public List<Position> getPosition() {
        List<Position> toReturn = new ArrayList<>();

        this.getMoves().forEach((m) -> {
            toReturn.add(m.getEnd());
        });

        return toReturn;
    }

    /**
     * To reset the game. It will be called when the "reset" button is pressed.
     */
    @Override
    public void resetGame() {
        setGameOver(false);

        if (current.getColor() == PlayerColor.WHITE) {
            swapPlayers();
        }
        current = new Player(BLACK, getCurrentName());
        opponent = new Player(WHITE, getOpponentName());
        specialsSquares.clear();
        
        
        initialize();
        start();

        notifyObservers();

    }

    /**
     * The score of the game. The score will be calculated by adding all the
     * pieces on the board and divided by the total number of boxes
     *
     * @return number total of pieces / number total of boxes
     */
    @Override
    public double progressBarScore() {
        int tot = current.getPieces().size() + opponent.getPieces().size();
        return current.getPieces().size() / tot;
    }

    /**
     * To get the current score of the given player this score is calculated by
     * adding all the value of the current piece's on the board
     *
     * @param color the player which be examinated
     * @return the score of the given player
     */
    @Override
    public int getScore(PlayerColor color) {
        Objects.requireNonNull(color, "The given color can't be null.");

        int score = 0;

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                Position p = new Position(i, j);

                if (board.getPiece(p) != null) {
                    if (board.isMyOwn(p, color)) {
                        score += board.getPiece(p).getValue();
                    }
                }

            }
        }
        
        if (gameOver) {
            score = getFinalScore();
        }
        return score;
    }

    /**
     * Know what is the current action (BEGIN, PASS, ...)
     *
     * @return the current action
     */
    @Override
    public String getCurrentAction() {

        return currentAction.getText();
    }

    /**
     * To change the current action. Use when a player click on a button
     *
     * @param currentAction the new value of currentAction
     */
    @Override
    public void setCurrentAction(Action currentAction) {
        Objects.requireNonNull(currentAction, "The given action can't be null.");

        this.currentAction = currentAction;
    }

    /**
     * To count how many pieces are caught on the current move
     *
     * @return number of flipped piece
     */
    @Override
    public int getCaught() {
        return caught;
    }

    /**
     * To have the last move on the game
     *
     * @return the last move
     */
    @Override
    public MoveInformation getCurrentMove() {
        return moveHistory.get(moveHistory.size() - 1);
    }

    /**
     * To know the current player's name
     *
     * @return current player name
     */
    @Override
    public String getCurrentName() {
        return getCurrent().getName();
    }

    /**
     * To know the opponent player's name
     *
     * @return opponent player name
     */
    @Override
    public String getOpponentName() {
        return getOpponent().getName();
    }

    /*
    Modification 
     */
    void createBonusSquares() {
        int randomRow = (int) (Math.random() * board.getRows());
        int randomColumn = (int) (Math.random() * board.getColumns());
        Position alreadyIn;

        int countSpecialBox = 0;
        while (countSpecialBox < 3) {

            do {

                alreadyIn = new Position(randomRow, randomColumn);
                randomRow = (int) (Math.random() * board.getRows());
                randomColumn = (int) (Math.random() * board.getColumns());

            } while ((specialsSquares.contains(alreadyIn)
                    || (randomRow == 3 && randomColumn == 4)
                    || (randomRow == 4 && randomColumn == 3)
                    || (randomRow == 3 && randomColumn == 3)
                    || (randomRow == 4 && randomColumn == 4)));

            Position randomBox = new Position(randomRow, randomColumn);
            specialsSquares.add(randomBox);
            countSpecialBox++;
        }

    }

    @Override
    public List<Position> getSpecialsSquares() {
        List<Position> defensiveCopyOfSpecialSquare = new ArrayList<>();

        specialsSquares.forEach((copy) -> {
            defensiveCopyOfSpecialSquare.add(copy);
        });
        return defensiveCopyOfSpecialSquare;
    }

}
