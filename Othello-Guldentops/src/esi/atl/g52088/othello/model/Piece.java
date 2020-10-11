package esi.atl.g52088.othello.model;

import java.util.Objects;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class Piece {

    private PlayerColor color;
    private final int value;
    private boolean isSpecial;

    /**
     * Constructor of Piece class. A piece is composed by :
     * <ul>
     * <li> Color, will determine which color the piece is </li>
     * <li> Value, will determine the value of the piece (a piece can have 4
     * values : 0, 1, 2 and 3).</li>
     * </ul>
     *
     * @param color color of the piece (BLACK or WHITE)
     * @param value value of the piece (0, 1, 2 or 3)
     */
    public Piece(PlayerColor color, int value) {
        Objects.requireNonNull(color, "Piece can't be null.");
        if (value < 0) {
            throw new IllegalArgumentException("A value of a piece cannot "
                    + "be negative.");
        }
        this.color = color;
        this.value = value;
    }

    public Piece(boolean isSpecial) {
        this.isSpecial = isSpecial;
        this.value = -1;
    }

    /**
     * Getter of the value attribute
     *
     * @return value attribute
     */
    public int getValue() {
        return value;
    }

    /**
     * To know which color is the current piece
     *
     * @return color of piece
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * To change de color of the current piece
     */
    void switchColor() {
        if (this.color == PlayerColor.WHITE) {
            this.color = PlayerColor.BLACK;
        } else {
            this.color = PlayerColor.WHITE;
        }
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

}
