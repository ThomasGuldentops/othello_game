package esi.atl.g52088.othello.model;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public enum Direction {
    //North direction.
    //E.g : If the current position is (2,1) the UP position is (1,1)
    NORTH(-1, 0),
    //South direction. 
    //E.g : If the current position is (2,1) the DOWN position is (3,1)
    SOUTH(1, 0),
    //West direction. 
    //E.g : If the current position is (2,1) the LEFT position is (2,0)
    WEST(0, -1),
    //East direction. 
    //E.g : If the current position is (2,1) the RIGHT position is (2,2)
    EAST(0, 1),
    
    //NorthWest direction. 
    //E.g : If the current position is (2,1) the NORTHWEST position is (1,0)
    NORTHWEST(-1, -1),
    //NorthEast direction. 
    //E.g : If the current position is (2,1) the NORTHEAST position is (1,2)
    NORTHEAST(-1, 1),
    //SouthWest direction. 
    //E.g : If the current position is (2,1) the SOUTHWEST position is (1,2)
    SOUTHWEST(1, -1),
    //SouthEast direction. 
    //E.g : If the current position is (2,1) the SOUTHEAST position is (3,2)
    SOUTHEAST(1, 1);
    
    
    
    private final int row;
    private final int column;

    /**
     * Allow to create a Direction
     * 
     * @param row 
     * @param column 
     */
    private Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * To know the row
     * 
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * To know the column
     * 
     * @return the column
     */
    public int getColumn() {
        return column;
    }   
}
