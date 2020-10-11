package esi.atl.g52088.othello.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class Board {

    private Piece[][] squares;
    private int rows;
    private int columns;
    final PlayerColor BLACK = PlayerColor.BLACK;
    final PlayerColor WHITE = PlayerColor.WHITE;

   /**
     * Constructor of Board class.
     *
     * @throws IllegalArgumentException if the given row or column or both
     * is/are negative
     *
     * @param rows integer number of row
     * @param columns integer number of column
     */
    Board(int rows, int columns) {
        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("Rows and columns can't"
                    + " be negative.");
        }

        this.rows = rows;
        this.columns = columns;
        this.squares = new Piece[rows][columns];

        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                this.squares[column][row] = null;
            }
        }
    }


    /**
     * Create 8 rows and 8 columns
     */
    public Board() {
        this(8, 8);
    }

    /**
     * Allows to return the squares
     *
     * @return the square
     */
    Piece[][] getSquares() { // supprimer
        return squares;
    }

    /**
     * To get the piece on the given position.
     *
     * @throws NullPointerException To verify if the given position is null
     *
     * @param position the position where the piece is
     * @return the piece on the given position
     */
    public Piece getPiece(Position position) {
        Objects.requireNonNull(position, "The given position can't be null.");

        if (!this.isInside(position)) {
            throw new IllegalArgumentException("Position given is not "
                    + "on the board");
        }

        return squares[position.getRow()][position.getColumn()];
    }

    /**
     * Let you know if the row or column exists in the Board
     *
     * @param position position to check
     *
     * @throws NullPointerException To verify if the given position is null
     *
     * @return True if the position given in parameter is contained in the game
     * board and false in other any case
     */
    boolean isInside(Position position) {
        Objects.requireNonNull(position, "The given position can't be null.");

        boolean testRow = position.getRow() >= 0
                && position.getRow() < this.rows;

        boolean testColumn = position.getColumn() >= 0
                && position.getColumn() < this.columns;

        return testRow && testColumn;
    }

    /**
     * Allows to place the given piece in parameter on the Board.
     *
     * @throws NullPointerException To verify if the given piece is null
     * @throws NullPointerException To verify if the given position is null
     *
     * @param piece piece you want to put on the board
     * @param position the position where the piece will be putted
     */
    void put(Piece piece, Position position) {
        Objects.requireNonNull(position, "The given position can't be null.");
        Objects.requireNonNull(piece, "The given piece can't be null.");
        this.squares[position.getRow()][position.getColumn()] = piece;
    }

    /**
     * To know if a position is free
     *
     * @throws IllegalArgumentException to test if the position is inside the
     * board or not
     * @throws NullPointerException To verify if the given position is null
     *
     * @param position position to check
     *
     * @return true if the position given is free. False in other any cases.
     */
    boolean isFree(Position position) {
        Objects.requireNonNull(position, "The given position can't be null.");

        if (!this.isInside(position)) {
            throw new IllegalArgumentException("Position given is not"
                    + " in the board.");
        }

        return this.getPiece(position) == null;
    }

    /**
     * Allow to know the position of all empty square on the board
     *
     * @return the list of the position where the square is empty
     */
    List<Position> findFree() {
        List<Position> allFreeCases = new ArrayList<>();
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                final Position currentPosition = new Position(row, column);

                if (this.isFree(currentPosition)) {
                    allFreeCases.add(currentPosition);
                }

            }
        }

        return allFreeCases;
    }

    /**
     * To know if a piece in the square is the same color than the given player.
     *
     * @param position position on the board
     * @param color color of player
     *
     * @throws NullPointerException To verify if the given position is null
     * @throws NullPointerException To verify if the given color is null
     *
     * @return true if the piece selected is the same color than the player
     * false on any other cases
     */
    boolean isMyOwn(Position position, PlayerColor color) {
        Objects.requireNonNull(position, "The given position can't be null.");
        Objects.requireNonNull(color, "The given color can't be null.");

        if (!isInside(position)) {
            throw new IllegalArgumentException("Position given is not"
                    + " in the board.");
        }

        return this.squares[position.getRow()][position.getColumn()].getColor()
                == color;

    }

    /**
     * To know which position is occupied by a player.
     *
     * @throws NullPointerException To verify if a player is null
     *
     * @param player player you want to know
     * @return list of position occupied by a player.
     */
    public List<Position> getTakenSquare(Player player) {
        Objects.requireNonNull(player, "Player can't be null.");

        List<Position> listPosition = new ArrayList<>();

        for (int row = 0; row < this.getSquares().length; row++) {
            for (int column = 0; column < this.getSquares()[row].length;
                    column++) {

                if (!this.isFree(new Position(row, column))) {
                    Position currentPosition = new Position(row, column);

                    if (isMyOwn(currentPosition, player.getColor())) {
                        listPosition.add(currentPosition);
                    }
                }
            }
        }

        return listPosition;

    }

    /**
     * To get the number total of rows contains on the board
     * @return total number of rows
     */
    public int getRows() {
        return rows;
    }

    
    /**
     * To get the number total of columns contains on the board
     * @return total number of columns
     */
    public int getColumns() {
        return columns;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Arrays.deepHashCode(this.squares);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Board other = (Board) obj;

        if (!Arrays.deepEquals(this.squares, other.squares)) {
            return false;
        }
        return true;
    }

}
