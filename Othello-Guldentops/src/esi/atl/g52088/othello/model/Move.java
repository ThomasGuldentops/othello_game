package esi.atl.g52088.othello.model;

import java.util.Objects;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class Move {

    private final Position start;
    private final Position end;
    private final Direction direction;

    /**
     * Allow to create a move. A move is represented by a position where a piece
     * can be placed on the board.
     *
     * A move will be illegal if :
     * <ul>
     * <li> the end case will be a <b> already occuped </b> by an ennemy piece.
     * </li>
     * <li> the end case will be <b> out </b> of board. </li>
     * <li> your move will not capture one or many ennemy piece(s) </li>
     * </ul>
     *
     * @throws NullPointerException thrown if one of these arguments is null.
     * @param start the position of the putted piece
     * @param end the position on the already putted piece 
     * (piece previoused putted on the board)
     * @param direction the direction which be checked
     *
     *
     *
     */
    public Move(Position start, Position end, Direction direction) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        Objects.requireNonNull(direction);

        this.start = start;
        this.end = end;
        this.direction = direction;
    }

    /**
     * Getter of the start attribute
     *
     * @return start attribute
     */
    public Position getStart() {
        return start;
    }

    /**
     * Getter of the end attribute
     *
     * @return end attribute
     */
    public Position getEnd() {
        return end;
    }

    /**
     * Getter of the direction attribute
     *
     * @return diraction attribute
     */
    public Direction getDirection() {
        return direction;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.start);
        hash = 89 * hash + Objects.hashCode(this.end);
        hash = 89 * hash + Objects.hashCode(this.direction);
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
        final Move other = (Move) obj;
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        if (!Objects.equals(this.end, other.end)) {
            return false;
        }
        if (this.direction != other.direction) {
            return false;
        }
        return true;
    }

}
