package esi.atl.g52088.othello.model;

import java.util.Objects;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class Position {
    private final int row;
    private final int column;

    /**
     * Constructor of Position class.
     * Allow to create a position.
     * A position is composed of a row and a column
     * 
     * @param row integer a number of row
     * @param column integer a number of column
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * To know the row of a position
     * 
     * @return the row of a position
     */
    public int getRow() {
        return row;
    }


     /**
     * To know the column of a position
     * 
     * @return the column of a position
     */
    public int getColumn() {
        return column;
    }


    /**
     * To attribute a new position for a piece
     * 
     * @param direction new direction for a piece selected before
     * @return the new position
     */
    Position next(Direction direction){
        Objects.requireNonNull(direction, "The given direction can't be null.");
        return new Position(this.row + direction.getRow(), 
                this.column + direction.getColumn());
    }


    
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.row;
        hash = 11 * hash + this.column;
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
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        return this.column == other.column;
    }
}
